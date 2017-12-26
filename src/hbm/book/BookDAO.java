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
import hbm.util.db.sql.Order.ORDER;
import hbm.util.db.sql.Order;
import hbm.util.db.sql.SqlFactory;
import hbm.util.db.sql.SqlFactory.*;

public class BookDAO {

	private DBConnectionPoolManager dbManager = null;
	
	public BookDAO() {
		this.dbManager = DBConnectionPoolManager.getInstance();
	}
	
	// Insert a Book
	public int insert(Book book) throws SQLException {
//		String sql = SqlFactory.makeInsert(book);
//		int ret = stmt.executeUpdate(sql);
//		conn.commit();
//		if(Properties.getInstance().isDebugMode())
//			Debug.show("[Ret int] " + ret + "\n");
//		return ret;
		int ret = dbManager.executeInsert(TABLE_NAME.BOOK, book);
		return ret;
	}
	
	// Select All Books with order
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> list = dbManager.executeSelect(TABLE_NAME.BOOK, Order.of("no", ORDER.ASC));
		return list;
	}
	
	// Select Only One Book - useless?
	
	// Select Books by Condition with order
	public <T> List<Map<String, Object>> selectAllByCond(Condition<T> cond, Order order) {
		List<Map<String, Object>> list = dbManager.executeSelectByCond(TABLE_NAME.BOOK, cond, order);
		return list;
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
