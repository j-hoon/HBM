package hbm.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import hbm.vo.Book;
import jbm.util.db.DbManager;
import jbm.util.db.sql.DataConverter;
import jbm.util.db.sql.SqlFactory;

public class BookDAO {

	private Statement stmt = null;
	
	public BookDAO() {
		this.stmt = DbManager.getInstance().getStatement();
	}
	
	// Insert a Book
	public int insert(Book book) throws SQLException {
//		String sql = "INSERT INTO BOOK VALUES(BOOK_NO.NEXTVAL, " + book.getSymbol() + ", '" + book.getName() + "', '" + book.getAuthor() + "', " +
//		book.getPrice() + ", '" + book.getPub() + "', '" + book.getPubDay() + "', '" + book.getLoc() + "', '" + book.getImgFile() + "', '" +
//		book.getBrwer() + "', '" + book.getBrwDay() + "', '" + book.getPeriod() + "')";
		String sql = SqlFactory.makeInsert(book);
		System.out.println(sql);
		return stmt.executeUpdate(sql);
	}
	
	// Select All Books
	public List<Map<String, Object>> selectAll() throws SQLException {
		String sql = SqlFactory.makeSelectAll(SqlFactory.TABLE_NAME.BOOK, "NO", SqlFactory.ORDER.ASC);
		List<Map<String, Object>> list = DataConverter.convResultSetToMapList(this.stmt.executeQuery(sql));
		return list;
	}
	
	// 
	
	
	// 
	
}
