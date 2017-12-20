package hbm.book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import hbm.util.DataConverter;
import hbm.util.db.DbManager;
import hbm.util.db.sql.SqlFactory;
import hbm.util.db.sql.SqlFactory.*;

public class BookDAO {

	private Statement stmt = null;
	private Connection conn = null;
	
	public BookDAO() {
		this.stmt = DbManager.getInstance().getStatement();
		this.conn = DbManager.getInstance().getConn();
	}
	
	// Insert a Book
	public int insert(Book book) throws SQLException {
		String sql = SqlFactory.makeInsert(book);
//		System.out.println(sql);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		return ret;
	}
	
	// Select All Books
	public List<Map<String, Object>> selectAll() throws SQLException {
		String sql = SqlFactory.makeSelectAll(SqlFactory.TABLE_NAME.BOOK, "NO", SqlFactory.ORDER.ASC);
		List<Map<String, Object>> list = DataConverter.convResultSetToMapList(this.stmt.executeQuery(sql));
		return list;
	}
	
	// Select Only One Book - useless?
	
	// Select Books by Book.anyAttr Condition with Order
	public <T> List<Map<String, Object>> selectAllByCondWithOrder(COL_TYPE condColType, String condColName, T condValue, boolean isLike,
			COL_TYPE orderColType, String orderColName, ORDER order) throws SQLException {
		String sql = SqlFactory.makeSelectAllByCondWithOrder(TABLE_NAME.BOOK, condColType, condColName, condValue, isLike, orderColType, orderColName, order);
		System.out.println("SqlFactory.makeSelectAllByCondWithOrder: " + sql);
		List<Map<String, Object>> list = DataConverter.convResultSetToMapList(this.stmt.executeQuery(sql));
		return list;
	}
	
	// Update All of a Book by Book.No(PK)
	public int updateAllByNo(int colNo, Book book) throws SQLException {
		String sql = SqlFactory.makeUpdateAllByInt(TABLE_NAME.BOOK, "NO", colNo, book);
		System.out.println("SqlFactory.makeUpdateAllByInt: " + sql);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		return ret;
	}
	
	// Delete a Book by Book.No(PK)
	public int deleteByNo(int colNo) throws SQLException {
		String sql = SqlFactory.makeDeleteByInt(TABLE_NAME.BOOK, "NO", colNo);
		System.out.println("SqlFactory.makeDeleteByInt: " + sql);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		return ret;
	}
	
}
