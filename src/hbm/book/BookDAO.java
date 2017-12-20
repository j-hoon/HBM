package hbm.book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import hbm.util.db.DbManager;
import hbm.util.db.sql.DataConverter;
import hbm.util.db.sql.SqlFactory;

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
	
	// Select Books by Book.name with order (TODO | author | pub | loc | brwer) - <String>
	public List<Map<String, Object>> selectAllByName(String name, boolean isLike, SqlFactory.COL_TYPE orderColType, String orderColName, SqlFactory.ORDER order) throws SQLException {
		String sql = SqlFactory.makeSelectByCond(SqlFactory.TABLE_NAME.BOOK, SqlFactory.COL_TYPE.STRING,
				"NAME", name, isLike, orderColType, orderColName, order);
		System.out.println("SqlFactory.makeSelectByCond: " + sql);
		List<Map<String, Object>> list = DataConverter.convResultSetToMapList(this.stmt.executeQuery(sql));
		return list;
	}
	// TODO Select Books by Book.pubDay (| period) - <LocalDate>
	// TODO Select Books by Book.brwDay - <LocalDateTime>
	
	// Update All a Book by Book.No (TODO | symbol | price) - <int>
	public int updateAllByNo(String colName, int colNo, Book book) throws SQLException {
		String sql = SqlFactory.makeUpdateAllByInt(SqlFactory.TABLE_NAME.BOOK, colName, colNo, book);
//		System.out.println(sql);
		int ret = stmt.executeUpdate(sql);
		conn.commit();
		return ret;
	}
	
}
