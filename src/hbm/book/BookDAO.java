package hbm.book;

import java.util.List;
import java.util.Map;

import hbm.util.db.pool.DBConnectionPoolManager;
import hbm.util.db.sql.Condition;
import hbm.util.db.sql.Order.ORDER;
import hbm.util.db.sql.Order;
import hbm.util.db.sql.SqlFactory.*;

public class BookDAO {

	private DBConnectionPoolManager dbManager = null;
	
	public BookDAO() {
		this.dbManager = DBConnectionPoolManager.getInstance();
	}
	
	// Insert a Book
	public int insert(Book book) {
		int ret = dbManager.executeInsert(TABLE_NAME.BOOK, book);
		return ret;
	}
	
	// Select Only One Book - useless?
	
	// Select All Books with order
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> list = dbManager.executeSelect(TABLE_NAME.BOOK, Order.of("no", ORDER.ASC));
		return list;
	}
	
	// Select Books by Condition with order
	public <T> List<Map<String, Object>> selectAllByCond(Condition<T> cond, Order order) {
		List<Map<String, Object>> list = dbManager.executeSelectByCond(TABLE_NAME.BOOK, cond, order);
		return list;
	}
	
	// Update All of a Book by Condition
	public <T> int updateAllByCond(Book book, Condition<T> cond) {
		int ret = dbManager.executeUpdateByCond(TABLE_NAME.BOOK, book, cond);
		return ret;
	}
	
	// Delete a Book by Condition
	public <T> int deleteByCond(Condition<T> cond) {
		int ret = dbManager.executeDeleteByCond(TABLE_NAME.BOOK, cond);
		return ret;
	}
	
}
