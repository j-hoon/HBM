package hbm.util.db.sql;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import hbm.book.Book;

public class SqlFactory {

	public static enum TABLE_NAME {
		BOOK
	}
	
	public static enum COL_TYPE { ORACLE, INT, STRING, LOCAL_DATE, LOCAL_DATE_TIME }
	private static enum CU_METHOD { INSERT, UPDATE }
	public static enum ORDER { ASC, DSC }
	
	private final static String INSERT_SQL = "INSERT INTO  VALUES(";	// INSERT INTO _TABLE_ VALUES(_COL_VALUES_);
	private final static String SELECT_SQL = "SELECT * FROM ";	// SELECT * FROM _TABLE_ WHERE = _COND_ ORDER BY _COL_ _ORDER_;
	private final static String UPDATE_SQL = "UPDATE  SET ";	// UPDATE _TABLE_ SET COL1 = _COL1_VALUE_, ... WHERE _COND_ = _COND_VALUE_;
	private final static String DELETE_SQL = "DELETE FROM ";	// DELETE FROM BOOK WHERE no = 158;
	
	private static StringBuffer stringBuffer;
	
	/***** INSERT *****/
	/*
	 * Make Insert SQL
	 */
	public static String makeInsert(Object obj) {
		stringBuffer = new StringBuffer(INSERT_SQL);
		
		if(obj instanceof Book) {
			Book book = (Book) obj;
			stringBuffer.insert(12, TABLE_NAME.BOOK.toString());
//			System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
			cuAppend(COL_TYPE.ORACLE, "BOOK_NO.NEXTVAL", false);
			appendAllColsOfBook(CU_METHOD.INSERT, book);
//			System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
		}
		stringBuffer.append(")");
		
		System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	/***** SELECT *****/
	/*
	 * Make Select All SQL
	 */
	public static String makeSelectAll(TABLE_NAME tableName, String orderCol, ORDER order) {
		stringBuffer = new StringBuffer(SELECT_SQL);
		stringBuffer.append(tableName.toString());
		stringBuffer.append(" ORDER BY ");
		stringBuffer.append(orderCol.toUpperCase());
		stringBuffer.append(" ");
		stringBuffer.append(order.toString());
		return stringBuffer.toString();
	}
	
	/*
	 * Make Select All by Condition with Condition SQL (TODO: 대소비교 기능 추가)
	 */
	public static <T> String makeSelectAllByCondWithOrder(TABLE_NAME tableName, COL_TYPE colType, String colName, T cond,
			boolean isLike, COL_TYPE orderColType, String orderColName, ORDER order) {
		stringBuffer = new StringBuffer(SELECT_SQL);
		stringBuffer.append(tableName.toString());
		stringBuffer.append(" WHERE ");
		stringBuffer.append(colName);
		if(isLike) {
			stringBuffer.append(" LIKE ");
			if(colType.toString() == COL_TYPE.STRING.toString() || colType.toString() == COL_TYPE.LOCAL_DATE.toString()
					 || colType.toString() == COL_TYPE.LOCAL_DATE_TIME.toString())
				stringBuffer.append("'");
			stringBuffer.append("%");
			stringBuffer.append(cond);
			stringBuffer.append("%");
			if(colType.toString() == COL_TYPE.STRING.toString() || colType.toString() == COL_TYPE.LOCAL_DATE.toString()
					 || colType.toString() == COL_TYPE.LOCAL_DATE_TIME.toString())
				stringBuffer.append("'");
		} else {
			stringBuffer.append(" = ");
			if(colType.toString() == COL_TYPE.STRING.toString() || colType.toString() == COL_TYPE.LOCAL_DATE.toString()
					 || colType.toString() == COL_TYPE.LOCAL_DATE_TIME.toString())
				stringBuffer.append("'");
			stringBuffer.append(cond);
			if(colType.toString() == COL_TYPE.STRING.toString() || colType.toString() == COL_TYPE.LOCAL_DATE.toString()
					 || colType.toString() == COL_TYPE.LOCAL_DATE_TIME.toString())
				stringBuffer.append("'");
		}
		stringBuffer.append(" ORDER BY ");
		stringBuffer.append(orderColName.toUpperCase());
		stringBuffer.append(" ");
		stringBuffer.append(order.toString());
		return stringBuffer.toString();
	}
	
	/***** UPDATE *****/
	/*
	 * Make Update All of a row by Int(PK) SQL
	 */
	public static String makeUpdateAllByInt(TABLE_NAME tableName, String colName, int colNo, Object obj) {
		stringBuffer = new StringBuffer(UPDATE_SQL);
		
		if(obj instanceof Book) {
			Book book = (Book) obj;
			stringBuffer.insert(7, TABLE_NAME.BOOK.toString());
//			System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
			appendAllColsOfBook(CU_METHOD.UPDATE, book);
//			System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
		}
		
		stringBuffer.append(" WHERE ");
		stringBuffer.append(colName.toUpperCase());
		stringBuffer.append(" = ");
		stringBuffer.append(colNo);
		
//		System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
		return stringBuffer.toString();
	}
	// TODO: how to make various update SQL

	/***** DELETE *****/
	/*
	 * Make Delete a row by int(PK) SQL
	 */
	public static String makeDeleteByInt(TABLE_NAME tableName, String colName, int colNo) {
		stringBuffer = new StringBuffer(DELETE_SQL);
		stringBuffer.append(tableName.toString());
		stringBuffer.append(" WHERE ");
		stringBuffer.append(colName);
		stringBuffer.append(" = ");
		stringBuffer.append(colNo);
		return stringBuffer.toString();
	}
	
	
	
	/***** Utility *****/
	/*
	 * Append Values in Insert or Update SQL by Column Type
	 */
	private static void cuAppend(COL_TYPE colType, Object colValue, boolean isFinalCol) {
		if(colType.equals(COL_TYPE.ORACLE) || colType.equals(COL_TYPE.INT))
			stringBuffer.append(colValue);
		else if(colType.equals(COL_TYPE.STRING)) {
			stringBuffer.append("'");
			stringBuffer.append(colValue);
			stringBuffer.append("'");
		} else if(colType.equals(COL_TYPE.LOCAL_DATE)) {
			stringBuffer.append("'");
			if(colValue != null)
				stringBuffer.append(colValue);
			stringBuffer.append("'");
		} else if(colType.equals(COL_TYPE.LOCAL_DATE_TIME)) {
			stringBuffer.append("'");
			if(colValue != null)
				stringBuffer.append(java.sql.Timestamp.valueOf((LocalDateTime) colValue));
			stringBuffer.append("'");
		}
		
		if(!isFinalCol)
			stringBuffer.append(", ");
	}
	
	
	/***** Append Columns & Values of Various Objects *****/
	/*
	 * [Book] - Append All Columns (TODO: create new bookSQL.java class?)
	 */
	private static void appendAllColsOfBook(CU_METHOD m, Book b) {
		if(m == CU_METHOD.UPDATE) stringBuffer.append("SYMBOL = ");
		cuAppend(COL_TYPE.INT, b.getSymbol(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("NAME = ");
		cuAppend(COL_TYPE.STRING, b.getName(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("AUTHOR = ");
		cuAppend(COL_TYPE.STRING, b.getAuthor(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("PRICE = ");
		cuAppend(COL_TYPE.INT, b.getPrice(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("PUB = ");
		cuAppend(COL_TYPE.STRING, b.getPub(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("PUBDAY = ");
		cuAppend(COL_TYPE.LOCAL_DATE, b.getPubDay(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("LOC = ");
		cuAppend(COL_TYPE.STRING, b.getLoc(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("IMGFILE = ");
		cuAppend(COL_TYPE.STRING, b.getImgFile(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("BRWER = ");
		cuAppend(COL_TYPE.STRING, b.getBrwer(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("BRWDAY = ");
		cuAppend(COL_TYPE.LOCAL_DATE_TIME, b.getBrwDay(), false);

		if(m == CU_METHOD.UPDATE) stringBuffer.append("PERIOD = ");
		cuAppend(COL_TYPE.LOCAL_DATE, b.getPeriod(), true);
	}
	
	/*
	 * [Book] - Append Selected Columns (TODO: integrate in appendAllColsOfBook())
	 */
	private static void appendColsOfBook(CU_METHOD m, Book b, String ... cols) {
//		// Uses
//		appendColsOfBook(CU_METHOD.INSERT, new Book());
//		appendColsOfBook(CU_METHOD.INSERT, new Book(), "str1");
//		appendColsOfBook(CU_METHOD.INSERT, new Book(), "str1", "str2");		
	}
	
}