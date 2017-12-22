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
	public <T> List<Map<String, Object>> executeSelectByCond(TABLE_NAME tableName, String condColName, boolean isLike, T condValue, String orderCol, ORDER order) {
		Connection conn = getConnection(DBConnectionPool.name);
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		String sql = SqlFactory.makeSelectAllByCond(tableName, condColName, isLike, orderCol, order);
		System.out.println("condValue: " + condValue + ", condValue.getClass(): " + condValue.getClass());
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			if(isLike) {
				if(condValue instanceof Integer || condValue instanceof LocalDate || condValue instanceof LocalDateTime) {
					System.out.println(condValue.getClass().getSimpleName() + "형 열 조건에 'LIKE'를 사용할 수 없습니다.");	// TODO: 년, 월, 일, 시, 분, 초 단위로 검색 기능
					return null;
				} else if(condValue instanceof String)
					pstmt.setString(1, "%"+condValue+"%");
				else
					pstmt.setObject(1, condValue);
			} else {
				if(condValue instanceof LocalDate)
					pstmt.setDate(1, Date.valueOf((LocalDate) condValue));
				else if(condValue instanceof LocalDateTime)
					pstmt.setTimestamp(1, Timestamp.valueOf((LocalDateTime) condValue));
				else
					pstmt.setObject(1, condValue);
			}
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
