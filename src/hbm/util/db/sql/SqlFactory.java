package hbm.util.db.sql;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import hbm.book.Book;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.util.db.sql.Condition.COND_INT_SINGLE;
import hbm.util.db.sql.Condition.*;

public class SqlFactory {

	public static enum TABLE_NAME {
		BOOK
	}
	
	public static enum COL_TYPE { ORACLE, INT, STRING, LOCAL_DATE, LOCAL_DATE_TIME }
	private static enum CU_METHOD { INSERT, UPDATE }
	
	private final static String INSERT_SQL = "INSERT INTO  VALUES(";	// INSERT INTO _TABLE_ VALUES(_COL_VALUES_);
	private final static String SELECT_SQL = "SELECT * FROM ";			// SELECT * FROM _TABLE_ WHERE = _COND_ ORDER BY _COL_ _ORDER_;
	private final static String UPDATE_SQL = "UPDATE  SET ";			// UPDATE _TABLE_ SET COL1 = _COL1_VALUE_, ... WHERE _COND_ = _COND_VALUE_;
	private final static String DELETE_SQL = "DELETE FROM ";			// DELETE FROM BOOK WHERE no = 158;
	
	private static StringBuffer stringBuffer = null;
	
	/***** INSERT *****/
	/*
	 * Make Insert SQL
	 */
	public static <T> String makeInsert(TABLE_NAME tableName, T obj) {
		// "INSERT INTO "
		stringBuffer = new StringBuffer(INSERT_SQL);
		
		// TODO: static INSER SQL 어디에 생성할지 (___SQL.java) 
//		if(obj instanceof Book) {
//			Book book = (Book) obj;
//			stringBuffer.insert(12, TABLE_NAME.BOOK.toString());
//			cuAppend(COL_TYPE.ORACLE, "BOOK_NO.NEXTVAL", false);
//			appendAllColsOfBook(CU_METHOD.INSERT, book);
//		}
//		stringBuffer.append(")");
//		if(Properties.getInstance().isDebugMode()) {
//			Debug.show("============================================================ makeInsert() ============================================================");
//			Debug.show("[VO] " + obj.getClass().getSimpleName() + "-" + obj.toString());
//			Debug.show("[SQL] " + stringBuffer.toString());
//		}
		
		
		return stringBuffer.toString();
	}
	
	/***** SELECT *****/
	/*
	 * Make Select All SQL
	 */
	public static String makeSelectAll(TABLE_NAME tableName, Order order) {
		// "SELECT * FROM _TABLE_ ORDER BY _ORDER_COL_ _ORDER_"
		stringBuffer = new StringBuffer(SELECT_SQL);
		stringBuffer.append(tableName.toString()).append(order.getOrderSql());
		
		if(Properties.getInstance().isDebugMode()) {
			Debug.show("========================================================== makeSelectAll() ===========================================================");
			Debug.show("[Params] " + tableName + " | " + order.getOrderColName() + " | " + order.getOrder());
			Debug.show("[SQL] " + stringBuffer.toString());
		}
		return stringBuffer.toString();
	}
	/*
	 * Make Select All by Condition SQL (TODO: 대소비교 기능 추가)
	 */
	public static <T> String makeSelectAllByCond(TABLE_NAME tableName, Condition<T> cond, Order order) {
		// common (=)
		// "SELECT * FROM _TABLE_ WHERE (_COND_COL_ = _COND_VALUE_) ORDER BY _ORDER_COL_ _ORDER_"
		// int (<, >, <=, >=)
		// "SELECT * FROM _TABLE_ WHERE (_COND_COL_ _COND_ _COND_VALUE_) ORDER BY _ORDER_COL_ _ORDER_"
		// "SELECT * FROM _TABLE_ WHERE (_COND_VALUE1_ _COND1_ _COND_COL_ AND _COND_VALUE2_ _COND2_ _COND_COL_) ORDER BY _ORDER_COL_ _ORDER_"
		// String (LIKE)
		// "SELECT * FROM _TABLE_ WHERE (_COND_COL_ LIKE _COND_VALUE_) ORDER BY _ORDER_COL_ _ORDER_"
		// LocalDate, LocalDateTime
		// "SELECT * FROM _TABLE_ WHERE (_COND_COL_ BETWEEN _COND_VALUE1_ AND _COND_VALUE2_) ORDER BY _ORDER_COL_ _ORDER_"
		// char
		// ""
		// append SELECT and TABLE
		stringBuffer = new StringBuffer(SELECT_SQL);
		stringBuffer.append(tableName.toString()).append(" WHERE ");
		// append Condition Method
		stringBuffer.append(cond.getCondSql());
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
	 * Make Update All of a row by Int(PK) SQL
	 */
//	public static String makeUpdateAllByInt(TABLE_NAME tableName, String colName, int colNo, Object obj) {
//		stringBuffer = new StringBuffer(UPDATE_SQL);
//		
//		if(obj instanceof Book) {
//			Book book = (Book) obj;
//			stringBuffer.insert(7, TABLE_NAME.BOOK.toString());
//			appendAllColsOfBook(CU_METHOD.UPDATE, book);
//		}
//		
//		stringBuffer.append(" WHERE ");
//		stringBuffer.append(colName.toUpperCase());
//		stringBuffer.append(" = ");
//		stringBuffer.append(colNo);
//
//		if(Properties.getInstance().isDebugMode()) {
//			Debug.show("======================================================== makeUpdateAllByInt() ========================================================");
//			Debug.show("[Params] " + tableName + ", " + colName + ", " + colNo + ", " + obj);
//			Debug.show("[SQL] " + stringBuffer.toString());
//		}
//		return stringBuffer.toString();
//	}
	// TODO: how to make various update SQL

	/***** DELETE *****/
	/*
	 * Make Delete a row by int(PK) SQL
	 */
//	public static String makeDeleteByInt(TABLE_NAME tableName, String colName, int colNo) {
//		stringBuffer = new StringBuffer(DELETE_SQL);
//		stringBuffer.append(tableName.toString());
//		stringBuffer.append(" WHERE ");
//		stringBuffer.append(colName);
//		stringBuffer.append(" = ");
//		stringBuffer.append(colNo);
//
//		if(Properties.getInstance().isDebugMode()) {
//			Debug.show("========================================================= makeDeleteByInt() ==========================================================");
//			Debug.show("[Params] " + tableName + ", " + colName + ", " + colNo);
//			Debug.show("[SQL] " + stringBuffer.toString());
//		}
//		return stringBuffer.toString();
//	}
	
	
	
	/***** Utility *****/
	/*
	 * Append Values in Insert or Update SQL by Column Type
	 */
//	private static void cuAppend(COL_TYPE colType, Object colValue, boolean isFinalCol) {
//		if(colType.equals(COL_TYPE.ORACLE) || colType.equals(COL_TYPE.INT))
//			stringBuffer.append(colValue);
//		else if(colType.equals(COL_TYPE.STRING)) {
//			stringBuffer.append("'");
//			stringBuffer.append(colValue);
//			stringBuffer.append("'");
//		} else if(colType.equals(COL_TYPE.LOCAL_DATE)) {
//			stringBuffer.append("'");
//			if(colValue != null)
//				stringBuffer.append(colValue);
//			stringBuffer.append("'");
//		} else if(colType.equals(COL_TYPE.LOCAL_DATE_TIME)) {
//			stringBuffer.append("'");
//			if(colValue != null)
//				stringBuffer.append(java.sql.Timestamp.valueOf((LocalDateTime) colValue));
//			stringBuffer.append("'");
//		}
//		
//		if(!isFinalCol)
//			stringBuffer.append(", ");
//	}
	
	
	/***** Append Columns & Values of Various Objects *****/
	/*
	 * [Book] - Append All Columns (TODO: create new bookSQL.java class?)
	 */
//	private static void appendAllColsOfBook(CU_METHOD m, Book b) {
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("SYMBOL = ");
//		cuAppend(COL_TYPE.INT, b.getSymbol(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("NAME = ");
//		cuAppend(COL_TYPE.STRING, b.getName(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("AUTHOR = ");
//		cuAppend(COL_TYPE.STRING, b.getAuthor(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("PRICE = ");
//		cuAppend(COL_TYPE.INT, b.getPrice(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("PUB = ");
//		cuAppend(COL_TYPE.STRING, b.getPub(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("PUBDAY = ");
//		cuAppend(COL_TYPE.LOCAL_DATE, b.getPubDay(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("LOC = ");
//		cuAppend(COL_TYPE.STRING, b.getLoc(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("IMGFILE = ");
//		cuAppend(COL_TYPE.STRING, b.getImgFile(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("BRWER = ");
//		cuAppend(COL_TYPE.STRING, b.getBrwer(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("BRWDAY = ");
//		cuAppend(COL_TYPE.LOCAL_DATE_TIME, b.getBrwDay(), false);
//
//		if(m == CU_METHOD.UPDATE) stringBuffer.append("PERIOD = ");
//		cuAppend(COL_TYPE.LOCAL_DATE, b.getPeriod(), true);
//	}
	
	/*
	 * [Book] - Append Selected Columns (TODO: integrate in appendAllColsOfBook())
	 */
//	private static void appendColsOfBook(CU_METHOD m, Book b, String ... cols) {
////		// Uses
////		appendColsOfBook(CU_METHOD.INSERT, new Book());
////		appendColsOfBook(CU_METHOD.INSERT, new Book(), "str1");
////		appendColsOfBook(CU_METHOD.INSERT, new Book(), "str1", "str2");		
//	}
	
}