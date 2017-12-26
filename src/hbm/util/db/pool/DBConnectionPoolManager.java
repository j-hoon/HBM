package hbm.util.db.pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import hbm.book.Book;
import hbm.util.DataConverter;
import hbm.util.Debug;
import hbm.util.db.sql.Condition;
import hbm.util.db.sql.Condition.COND_INT_SINGLE;
import hbm.util.db.sql.Condition.*;
import hbm.util.db.sql.SqlFactory;
import hbm.util.db.sql.SqlFactory.ORDER;
import hbm.util.db.sql.SqlFactory.TABLE_NAME;

public class DBConnectionPoolManager {
	
	private Vector<String> drivers = new Vector<String>();
	private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();

	// Demand Holder Idiom - Singleton
	private static class Singleton {
		private static final DBConnectionPoolManager instance = new DBConnectionPoolManager();
	}
	public static DBConnectionPoolManager getInstance() {
		return Singleton.instance;
	}
	
	// Default Constructor
	private DBConnectionPoolManager() {
		init("oracle.jdbc.driver.OracleDriver", 20, 5, 5);
	}
	
	// 현재 Connection을 Free Connection List로 보냄
	// @param name : Pool Name
	// @param con : Connection
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		}
		
//		Log.out("One Connection of " + name + " was freed");
	}
	
	// Open Connection을 얻음. 현재 열린 커넥션이 없고 최대 커넥션 개수가
	// 사용 중이 아닐 때는 새로운 커넥션을 생성. 현재 열린 커넥션이 없고
	// 최대 커넥션 개수가 사용 중일 때 기본 대기 시간을 기다림
	// @param name : Pool Name
	// @return Connection : The connection or null
	public Connection getConnection(String name) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection(10);
		}
		return null;
	}

	// Connection Pool을 생성
	// @param poolName : 생성할 Pool Name
	// @param url : DB URL
	// @param user : DB UserID
	// @param password : DB Password
	private void createPools(int maxConn, int initConn, int maxWait) {
//	private void createPools(String poolName, /*String url, String user, String password, */int maxConn, int initConn, int maxWait) {
		DBConnectionPool pool = new DBConnectionPool(maxConn, initConn, maxWait);
//		DBConnectionPool pool = new DBConnectionPool(poolName, /*url, user, password, */maxConn, initConn, maxWait);
		pools.put(DBConnectionPool.name, pool);
//		Log.out("Initialized pool " + poolName);
	}
	
	// 초기화 작업
	public void init(String driver, int maxConn, int initConn, int maxWait) {
//	public void init(/*String poolName, */String driver, /*String url, String user, String passwd, */int maxConn, int initConn, int maxWait) {
		loadDrivers(driver);
		createPools(maxConn, initConn, maxWait);
//		createPools(poolName, /*url, user, passwd, */maxConn, initConn, maxWait);
		
	}
	
	// JDBC Driver Loading
	// @param driverClassName : 사용하고자 하는 DB의 JDBC 드라이버
	private void loadDrivers(String driverClassName) {
		try {
			Class.forName(driverClassName);
			drivers.addElement(driverClassName);
//			Log.out("Registered JDBC driver " + driverClassName);
		} catch (Exception e) {
//			Log.err(e, "Can't register JDBC driver: " + driverClassName);
		}
	}
	
	public Hashtable<String, DBConnectionPool> getPools() {
		return pools;
	}
	
	public int getDriverNumber() {
		return drivers.size();
	}
	
	
	/*
	 * @param String sql : SQL to execute (only SELECT)  
	 * @return List<Map<String, Object>> : result of query (ResultSet to List)
	 */
	public List<Map<String, Object>> executeQuery(String sql) {
		Connection conn = getConnection(DBConnectionPool.name);
		List<Map<String, Object>> list = null;
		try (Statement stmt = conn.createStatement() ; ResultSet resultSet = stmt.executeQuery(sql)) {
			list = DataConverter.convResultSetToMapList(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			freeConnection(DBConnectionPool.name, conn);
		}
		
		return list;
	}

	/**
	 * @param
	 */
	public List<Map<String, Object>> executeSelect(TABLE_NAME tableName, String orderCol, ORDER order) {
		Connection conn = getConnection(DBConnectionPool.name);
		List<Map<String, Object>> list = null;
		String sql = SqlFactory.makeSelectAll(tableName, orderCol, order);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql) ; ResultSet resultSet = pstmt.executeQuery()) {
			list = DataConverter.convResultSetToMapList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			freeConnection(DBConnectionPool.name, conn);
		}
		
		return list;
	}
	
	/**
	 * @param
	 */
	public <T> List<Map<String, Object>> executeSelectByCond(TABLE_NAME tableName, Condition<T> cond, String orderCol, ORDER order) {
		Connection conn = getConnection(DBConnectionPool.name);
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		String sql = SqlFactory.makeSelectAllByCond(tableName, cond, orderCol, order);
		Debug.show("[Values] " + cond.getVal1() + " | " + cond.getVal2());
//		System.out.println("cond.getCondColName(): " + cond.getCondColName() + " | cond.getMethod: " + cond.getCond() + cond.get", cond.getVal1(): " + cond.getVal1() + ", cond.getVal2(): " + cond.getVal2());
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			/** PreparedStatement ? 인자 삽입 **/
			// Single Condition
			if(cond.getCond() instanceof COND_INT_SINGLE)
				pstmt.setInt(1, (int) cond.getVal1());
			else if(cond.getCond() instanceof COND_STRING_SINGLE)
				pstmt.setString(1, cond.getVal1().toString());
			else if(cond.getCond() instanceof COND_LOCALDATE_SINGLE) {
				if(((COND_LOCALDATE_SINGLE) cond.getCond()).equals(COND_LOCALDATE_SINGLE.MONTH_IN) ||
						((COND_LOCALDATE_SINGLE) cond.getCond()).equals(COND_LOCALDATE_SINGLE.YEAR_IN)) {
					pstmt.setDate(1, Date.valueOf((LocalDate) cond.getVal1()));
					pstmt.setDate(2, Date.valueOf((LocalDate) cond.getVal1()));
				} else
					pstmt.setDate(1, Date.valueOf((LocalDate) cond.getVal1()));	
			} else if(cond.getCond() instanceof COND_LOCALDATETIME_SINGLE) {
				
				pstmt.setTimestamp(1,  Timestamp.valueOf((LocalDateTime) cond.getVal1());
			}
			else {
				
			}
			// Multi Condition
//			if() {
//				
//			}
			
			
//			this.setColumn(1);

			resultSet = pstmt.executeQuery();
			list = DataConverter.convResultSetToMapList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			freeConnection(DBConnectionPool.name, conn);
		}
		
		return list;
	}

}
