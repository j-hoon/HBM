package hbm.util.db.sql;

import hbm.book.Book;
import hbm.book.BookSQL;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorSQL;

public class SqlFactory {

	public static enum TABLE_NAME {
		BOOK, VISITOR
	}
	
	private final static String INSERT_SQL = "INSERT INTO ";		// INSERT INTO _TABLE_ VALUES(_COL_VALUES_1_, _COL_VALUES_2_, _COL_VALUES_3_, ...)
	private final static String SELECT_SQL = "SELECT * FROM ";		// SELECT * FROM _TABLE_ WHERE (_COND_ ...) ORDER BY _ORDER_COL_ _ORDER_
	private final static String UPDATE_SQL = "UPDATE ";				// UPDATE _TABLE_ SET COL1 = _COL1_VALUE_, COL2 = _COL2_VALUE_, ... WHERE (_COND_)
	private final static String DELETE_SQL = "DELETE FROM ";		// DELETE FROM _TABLE_ (_COND_ ...)
	private static StringBuffer stringBuffer = null;

	
	/***** INSERT *****/
	/*
	 * Make Insert SQL
	 */
	public static <T> String makeInsert(TABLE_NAME tableName, T obj) {
		// "INSERT INTO _TABLE_ VALUES(_COL_VALUES_1_, _COL_VALUES_2_, _COL_VALUES_3_, ...);"
		// append INSERT and TABLE
		stringBuffer = new StringBuffer(INSERT_SQL);
		stringBuffer.append(tableName);
		// append VALUES of VO to ?
		stringBuffer.append(" VALUES(");
		if(obj instanceof Book)
			stringBuffer.append(BookSQL.getInsertAllSQL());
		else if(obj instanceof Visitor)
			stringBuffer.append(VisitorSQL.getInsertAllSQL());
		stringBuffer.append(")");
		
		if(Properties.getInstance().isDebugMode()) {
			Debug.show("============================================================ makeInsert() ============================================================");
			Debug.show("[Params] " + tableName + " | " + obj.getClass().getSimpleName() + "::" + obj.toString());
			Debug.show("[SQL] " + stringBuffer.toString());
		}
		return stringBuffer.toString();
	}
	
	/***** SELECT *****/
	/*
	 * Make Select All SQL
	 */
	public static String makeSelectAll(TABLE_NAME tableName, Order order) {
		// "SELECT * FROM _TABLE_ ORDER BY _ORDER_COL_ _ORDER_;"
		// append SELECT and TABLE
		stringBuffer = new StringBuffer(SELECT_SQL);
		stringBuffer.append(tableName.toString());
		// append Order
		stringBuffer.append(order.getOrderSql());
		
		if(Properties.getInstance().isDebugMode()) {
			Debug.show("========================================================== makeSelectAll() ===========================================================");
			Debug.show("[Params] " + tableName + " | " + order.getOrderColName() + " | " + order.getOrder());
			Debug.show("[SQL] " + stringBuffer.toString());
		}
		return stringBuffer.toString();
	}
	/*
	 * Make Select All SQL by Condition
	 */
	public static <T> String makeSelectAllByCond(TABLE_NAME tableName, Condition<T> cond, Order order) {
		// "SELECT * FROM _TABLE_ WHERE (_COND_ ...) ORDER BY _ORDER_COL_ _ORDER_;"
		// append SELECT and TABLE
		stringBuffer = new StringBuffer(SELECT_SQL);
		stringBuffer.append(tableName.toString());
		// append Condition Method
		stringBuffer.append(" WHERE ").append(cond.getCondSql());
		// append Order
		stringBuffer.append(order.getOrderSql());

		if(Properties.getInstance().isDebugMode()) {
			Debug.show("======================================================= makeSelectAllByCond() ========================================================");
			Debug.show("[Params] " + tableName + " | " + cond.getCondColName() + " | " + cond.getCond() + " | " + cond.getVal1() + " | " + cond.getVal2() +
					" | " + order.getOrderColName() + " | " + order.getOrder());
			Debug.show("[SQL] " + stringBuffer.toString());
		}
		return stringBuffer.toString();
	}

	/***** UPDATE *****/
	/*
	 * Make Update SQL by Condition
	 */
	public static <T> String makeUpdateByCond(TABLE_NAME tableName, Object obj, Condition<T> cond) {
		// "UPDATE _TABLE_ SET COL1 = _COL1_VALUE_, COL2 = _COL2_VALUE_, ... WHERE (_COND_);"
		// append UPDATE and TABLE
		stringBuffer = new StringBuffer(UPDATE_SQL);
		stringBuffer.append(tableName).append(" SET ");
		// append COLS and VALUES of VO to ?
		if(obj instanceof Book)
			stringBuffer.append(BookSQL.getUpdateAllSQL());
		else if(obj instanceof Visitor)
			stringBuffer.append(VisitorSQL.getUpdateAllSQL());
		// append Condition Method
		stringBuffer.append(" WHERE ").append(cond.getCondSql());

		if(Properties.getInstance().isDebugMode()) {
			Debug.show("========================================================= makeUpdateByCond() =========================================================");
			Debug.show("[Params] " + tableName + " | " + cond.getCondColName() + " | " + cond.getCond() + " | " + cond.getVal1() + " | " + cond.getVal2() +
					"\n         " + obj.getClass().getSimpleName() + "::" + obj.toString());
			Debug.show("[SQL] " + stringBuffer.toString());
		}
		return stringBuffer.toString();
	}
	
	/***** DELETE *****/
	/*
	 * Make Delete SQL by Condition
	 */
	public static <T> String makeDeleteByCond(TABLE_NAME tableName, Condition<T> cond) {
		// "DELETE FROM _TABLE_ (_COND_ ...);"
		// append DELETE and TABLE
		stringBuffer = new StringBuffer(DELETE_SQL);
		stringBuffer.append(tableName);
		// append Condition Method
		stringBuffer.append(" WHERE ").append(cond.getCondSql());
		
		if(Properties.getInstance().isDebugMode()) {
			Debug.show("========================================================= makeUpdateByCond() =========================================================");
			Debug.show("[Params] " + tableName + " | " + cond.getCondColName() + " | " + cond.getCond() + " | " + cond.getVal1() + " | " + cond.getVal2());
			Debug.show("[SQL] " + stringBuffer.toString());
		}
		return stringBuffer.toString();
	}
	
	
	/***** Utility *****/

	
}