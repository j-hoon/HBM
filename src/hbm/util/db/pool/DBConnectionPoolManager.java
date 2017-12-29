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
import hbm.book.BookSQL;
import hbm.util.DataConverter;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.util.db.sql.Condition;
import hbm.util.db.sql.Condition.*;
import hbm.util.db.sql.Order;
import hbm.util.db.sql.SqlFactory;
import hbm.util.db.sql.SqlFactory.*;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorSQL;

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
	

	// TODO: 아래 정적 SQL과 동적 SQL 시간 성능 비교 (test to select)
//	public List<Map<String, Object>> executeQuery(String sql) {
//		Connection conn = getConnection(DBConnectionPool.name);
//		List<Map<String, Object>> list = null;
//		try (Statement stmt = conn.createStatement() ; ResultSet resultSet = stmt.executeQuery(sql)) {
//			list = DataConverter.convResultSetToMapList(resultSet);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			freeConnection(DBConnectionPool.name, conn);
//		}
//		return list;
//	}
	
	
	/***** Execute CRUD *****/
	/**
	 * Execute Only INSERT SQL
	 * @param tableName the enum value in TABLE_NAME choose table to execute insert query
	 * @param obj the Value Object to insert into table
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public int executeInsert(TABLE_NAME tableName, Object obj) {
		Connection conn = getConnection(DBConnectionPool.name);
		int ret = -1;
		String sql = SqlFactory.makeInsert(tableName, obj);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set ? Value Parameters of INSERT SQL
			if(obj instanceof Book)
				BookSQL.setAllOfParams(pstmt, obj);
			else if(obj instanceof Visitor)
				VisitorSQL.setAllOfParams(pstmt, obj);
			
			ret = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			freeConnection(DBConnectionPool.name, conn);
		}
		if(Properties.getInstance().isDebugMode())
			Debug.show("[Ret int] " + ret);
		return ret;
	}

	/**
	 * Execute Only SELECT SQL
	 * @param tableName the enum value in TABLE_NAME choose table to execute select query
	 * @param order the Order determining the sorting of query result
	 * @return a List<Map<String, Object>> that contains the data produced by the query
	 */
	public List<Map<String, Object>> executeSelect(TABLE_NAME tableName, Order order) {
		Connection conn = getConnection(DBConnectionPool.name);
		List<Map<String, Object>> ret = null;
		String sql = SqlFactory.makeSelectAll(tableName, order);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql) ; ResultSet resultSet = pstmt.executeQuery()) {
			ret = DataConverter.convResultSetToMapList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			freeConnection(DBConnectionPool.name, conn);
		}
		return ret;
	}
	
	/**
	 * Execute Only SELECT SQL by Condition
	 * @param tableName the enum value in TABLE_NAME choose table to execute select query
	 * @param cond the Condition determining condition of select query
	 * @param order the Order determining sorting of query result
	 * @return a List<Map<String, Object>> that contains the data produced by the query
	 */
	public <T> List<Map<String, Object>> executeSelectByCond(TABLE_NAME tableName, Condition<T> cond, Order order) {
		Connection conn = getConnection(DBConnectionPool.name);
		ResultSet resultSet = null;
		List<Map<String, Object>> ret = null;
		String sql = SqlFactory.makeSelectAllByCond(tableName, cond, order);
		Debug.show("[Values] " + cond.getVal1() + " | " + cond.getVal2());
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set ? Condition Parameters of SELECT SQL
			setCond(pstmt, 1, cond);

			resultSet = pstmt.executeQuery();
			ret = DataConverter.convResultSetToMapList(resultSet);
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
		return ret;
	}

	/**
	 * Execute Only UPDATE SQL by Condition
	 * @param tableName the enum value in TABLE_NAME choose table to execute update query
	 * @param obj the Value Object to be updated in the table
	 * @param cond the Condition to determine which rows to update
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public <T> int executeUpdateByCond(TABLE_NAME tableName, Object obj, Condition<T> cond) {
		Connection conn = getConnection(DBConnectionPool.name);
		int ret = -1;
		String sql = SqlFactory.makeUpdateByCond(tableName, obj, cond);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set UPDATE SQL
			if(obj instanceof Book) {
				BookSQL.setAllOfParams(pstmt, obj);		// set ? Value Parameters
				setCond(pstmt, 12, cond);				// set ? Condition Parameters
			}
			else if(obj instanceof Visitor) {
				VisitorSQL.setAllOfParams(pstmt, obj);	// set ? Value Parameters
				setCond(pstmt, 13, cond);				// set ? Condition Parameters
			}
			
			ret = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			freeConnection(DBConnectionPool.name, conn);
		}
		if(Properties.getInstance().isDebugMode())
			Debug.show("[Ret int] " + ret);
		return ret;
	}
	
	/**
	 * Execute Only DELETE SQL by Condition
	 * @param tableName the enum value in TABLE_NAME choose table to execute delete query
	 * @param cond the Condition to determine which rows to delete
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public <T> int executeDeleteByCond(TABLE_NAME tableName, Condition<T> cond) {
		Connection conn = getConnection(DBConnectionPool.name);
		int ret = -1;
		String sql = SqlFactory.makeDeleteByCond(tableName, cond);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set ? Condition Parameters of DELETE SQL
			setCond(pstmt, 1, cond);			
			
			ret = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			freeConnection(DBConnectionPool.name, conn);
		}
		if(Properties.getInstance().isDebugMode())
			Debug.show("[Ret int] " + ret);
		return ret;
	}

	
	
	/**
	 * Set Condition parameters(?, ...) in PreparedStatement
	 * @param pstmt 
	 * @param idx 
	 * @param cond 
	 */
	private <T> void setCond(PreparedStatement pstmt, int idx, Condition<T> cond) {
		/***** PreparedStatement 인자 삽입 *****/
		try {
			/** Single Condition **/
			// int
			if(cond.getCond() instanceof COND_INT_SINGLE)
				pstmt.setInt(idx, (int) cond.getVal1());
			// String
			else if(cond.getCond() instanceof COND_STRING_SINGLE)
				pstmt.setString(idx, cond.getVal1().toString());
			// LocalDate
			else if(cond.getCond() instanceof COND_LOCALDATE_SINGLE) {
				if(((COND_LOCALDATE_SINGLE) cond.getCond()).equals(COND_LOCALDATE_SINGLE.MONTH_IN) ||
						((COND_LOCALDATE_SINGLE) cond.getCond()).equals(COND_LOCALDATE_SINGLE.YEAR_IN)) {
					pstmt.setDate(idx, Date.valueOf((LocalDate) cond.getVal1()));
					pstmt.setDate(idx + 1, Date.valueOf((LocalDate) cond.getVal1()));
				} else
					pstmt.setDate(idx, Date.valueOf((LocalDate) cond.getVal1()));	
			}
			// LocalDateTime
			else if(cond.getCond() instanceof COND_LOCALDATETIME_SINGLE) {
				if(((COND_LOCALDATETIME_SINGLE) cond.getCond()).equals(COND_LOCALDATETIME_SINGLE.SEC_IN)) {
					pstmt.setTimestamp(idx, Timestamp.valueOf(((LocalDateTime) cond.getVal1())
							.withNano(0)));
					pstmt.setTimestamp(idx + 1, Timestamp.valueOf(((LocalDateTime) cond.getVal1())
							.withNano(0)
							.plusNanos(999999999)));
				} else if(((COND_LOCALDATETIME_SINGLE) cond.getCond()).equals(COND_LOCALDATETIME_SINGLE.MIN_IN)) {
					pstmt.setTimestamp(idx, Timestamp.valueOf(((LocalDateTime) cond.getVal1())
							.withSecond(0).withNano(0)));
					pstmt.setTimestamp(idx + 1, Timestamp.valueOf(((LocalDateTime) cond.getVal1())
							.withSecond(0).withNano(0)
							.plusSeconds(59).plusNanos(999999999)));
				} else if(((COND_LOCALDATETIME_SINGLE) cond.getCond()).equals(COND_LOCALDATETIME_SINGLE.HOUR_IN)) {
					pstmt.setTimestamp(idx, Timestamp.valueOf(((LocalDateTime) cond.getVal1())
							.withMinute(0).withSecond(0).withNano(0)));
					pstmt.setTimestamp(idx + 1, Timestamp.valueOf(((LocalDateTime) cond.getVal1())
							.withMinute(0).withSecond(0).withNano(0)
							.plusMinutes(59).plusSeconds(59).plusNanos(999999999)));
				} else if(((COND_LOCALDATETIME_SINGLE) cond.getCond()).equals(COND_LOCALDATETIME_SINGLE.DAY_IN)) {
					pstmt.setDate(idx, Date.valueOf(((LocalDateTime) cond.getVal1()).toLocalDate()));
					pstmt.setDate(idx + 1, Date.valueOf(((LocalDateTime) cond.getVal1()).toLocalDate()));
				} else if(((COND_LOCALDATETIME_SINGLE) cond.getCond()).equals(COND_LOCALDATETIME_SINGLE.MONTH_IN)) {
					pstmt.setDate(idx, Date.valueOf(((LocalDateTime) cond.getVal1()).toLocalDate()
							.withDayOfMonth(1)));
					pstmt.setDate(idx + 1, Date.valueOf(((LocalDateTime) cond.getVal1()).toLocalDate()));
				} else if(((COND_LOCALDATETIME_SINGLE) cond.getCond()).equals(COND_LOCALDATETIME_SINGLE.YEAR_IN)) {
					pstmt.setDate(idx, Date.valueOf(((LocalDateTime) cond.getVal1()).toLocalDate()
							.withMonth(1).withDayOfMonth(1)));
					pstmt.setDate(idx + 1, Date.valueOf(((LocalDateTime) cond.getVal1()).toLocalDate()));
				} else {
					pstmt.setTimestamp(1, Timestamp.valueOf((LocalDateTime) cond.getVal1()));
				}
			}
			/** Multi Condition **/
			// int
			else if(cond.getCond() instanceof COND_INT_MULTI) {
				pstmt.setInt(idx, (int) cond.getVal1());
				pstmt.setInt(idx + 1, (int) cond.getVal2());
			}
			// LocalDate
			else if(cond.getCond() instanceof COND_LOCALDATE_MULTI) {
				pstmt.setDate(idx, Date.valueOf((LocalDate) cond.getVal1()));
				pstmt.setDate(idx + 1, Date.valueOf((LocalDate) cond.getVal2()));
			}
			// LocalDateTime
			else if(cond.getCond() instanceof COND_LOCALDATETIME_MULTI) {
				pstmt.setTimestamp(idx, Timestamp.valueOf((LocalDateTime) cond.getVal1()));
				pstmt.setTimestamp(idx + 1, Timestamp.valueOf((LocalDateTime) cond.getVal2()));
			}
			// 
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
