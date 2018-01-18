package hbm.visitor;

import java.util.List;
import java.util.Map;

import hbm.db.DBConnectionPoolManager;
import hbm.db.sql.Condition;
import hbm.db.sql.Order;
import hbm.db.sql.Order.ORDER;
import hbm.db.sql.SqlFactory.TABLE_NAME;
import javafx.collections.ObservableList;

public class VisitorDAO {

	private DBConnectionPoolManager dbManager = null;
	
	public VisitorDAO() {
		this.dbManager = DBConnectionPoolManager.getInstance();
	}
	
	
	/** Check Id Validation **/
	public boolean isVaildId(String id) {
		int rowCnt = dbManager.execCountQuery(VisitorSQL.getCountSQL(id));
		if(rowCnt == 0)
			return true;
		else
			return false;
	}

	/** Join **/
	public int join(Visitor visitor) {
		int ret = dbManager.execInsert(VisitorSQL.getInsertSQL(), visitor);
		return ret;
	}
	
	/** Login **/
	public Visitor login(String id, String pw) {
		Visitor ret = (Visitor) dbManager.execSelectOnlyOne(TABLE_NAME.VISITOR, VisitorSQL.getLoginSQL(id, pw));
		return ret;
	}
	
	/**  **/
	public ObservableList<Visitor> getAllVisitor() {
		ObservableList<Visitor> ret = dbManager.execSelect(TABLE_NAME.VISITOR, VisitorSQL.getSelectSQL());
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
