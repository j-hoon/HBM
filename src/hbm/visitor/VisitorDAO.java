package hbm.visitor;

import java.util.List;
import java.util.Map;

import hbm.db.DBConnectionPoolManager;
import hbm.db.sql.Condition;
import hbm.db.sql.Order;
import hbm.db.sql.Order.ORDER;
import hbm.db.sql.SqlFactory.TABLE_NAME;

public class VisitorDAO {

	private DBConnectionPoolManager dbManager = null;
	
	public VisitorDAO() {
		this.dbManager = DBConnectionPoolManager.getInstance();
	}
	
//	// Insert a Visitor
//	public int insert(Visitor visitor) {
//		int ret = dbManager.executeInsert(TABLE_NAME.VISITOR, visitor);
//		return ret;
//	}
	/** Join **/
	public int signIn(Visitor visitor) {
		int ret = 0;
		return ret;
	}
	
	// Select All Visitors with Order
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> list = dbManager.executeSelect(TABLE_NAME.VISITOR, Order.of("no", ORDER.ASC));
		return list;
	}
	
	// Select Visitors by Condition with Order
	public <T> List<Map<String, Object>> selectAllByCond(Condition<T> cond, Order order) {
		List<Map<String, Object>> list = dbManager.executeSelectByCond(TABLE_NAME.VISITOR, cond, order);
		return list;
	}
	
	// Update All of a Visitor by Condition
	public <T> int updateAllByCond(Visitor visitor, Condition<T> cond) {
		int ret = dbManager.executeUpdateByCond(TABLE_NAME.VISITOR, visitor, cond);
		return ret;
	}
	
	// Delete a Book by Condition
	public <T> int deleteByCond(Condition<T> cond) {
		int ret = dbManager.executeDeleteByCond(TABLE_NAME.VISITOR, cond);
		return ret;
	}
	
}
