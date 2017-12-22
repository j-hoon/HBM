package hbm.book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import hbm.util.DataConverter;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.util.db.DbManager;
import hbm.util.db.pool.DBConnectionPoolManager;
import hbm.util.db.sql.Condition;
import hbm.util.db.sql.Condition.*;
import hbm.util.db.sql.SqlFactory;
import hbm.util.db.sql.SqlFactory.*;

public class BookDAO {

//	private Statement stmt = null;
//	private Connection conn = null;

	private DBConnectionPoolManager dbManager = null;
	
	public BookDAO() {
//		this.stmt = DbManager.getInstance().getStatement();
//		this.conn = DbManager.getInstance().getConn();
		this.dbManager = DBConnectionPoolManager.getInstance();
	}
	/*
	// Insert a Book
	public int insert(Book book) throws SQLException {
		String sql = SqlFactory.makeInsert(book);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		if(Properties.getInstance().isDebugMode())
			Debug.show("[Ret int] " + ret + "\n");
		return ret;
	}
	*/
	// Select All Books
	public List<Map<String, Object>> selectAll() {
//		long start = System.currentTimeMillis();
//		String sql = SqlFactory.makeSelectAll(TABLE_NAME.BOOK, "NO", ORDER.ASC);
		List<Map<String, Object>> list = dbManager.executeSelect(TABLE_NAME.BOOK, "NO", ORDER.ASC);
//		long end = System.currentTimeMillis();
//		System.out.println("selectAll: " + (end - start));
		return list;
	}
	// Select Only One Book - useless?
	
	// Select Books by Book.anyAttr Condition with Order
	public <T> List<Map<String, Object>> selectAllByCond(String condColName, boolean isLike, T condValue, String orderColName, ORDER order) {
//	public <T> List<Map<String, Object>> selectAllByCond(COL_TYPE condColType, String condColName, T condValue, boolean isLike,
//			COL_TYPE orderColType, String orderColName, ORDER order) throws SQLException {
//		String sql = SqlFactory.makeSelectAllByCondWithOrder(TABLE_NAME.BOOK, condColType, condColName, condValue, isLike, orderColType, orderColName, order);
		List<Map<String, Object>> list = dbManager.executeSelectByCond(TABLE_NAME.BOOK, condColName, isLike, condValue, orderColName, ORDER.ASC);
		
		return null;
	}

	/*
	// Update All of a Book by Book.No(PK)
	public int updateAllByNo(int colNo, Book book) throws SQLException {
		String sql = SqlFactory.makeUpdateAllByInt(TABLE_NAME.BOOK, "NO", colNo, book);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		if(Properties.getInstance().isDebugMode())
			Debug.show("[Ret int] " + ret + "\n");
		return ret;
	}
	
	// Delete a Book by Book.No(PK)
	public int deleteByNo(int colNo) throws SQLException {
		String sql = SqlFactory.makeDeleteByInt(TABLE_NAME.BOOK, "NO", colNo);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		if(Properties.getInstance().isDebugMode())
			Debug.show("[Ret int] " + ret + "\n");
		return ret;
	}
	*/
}
