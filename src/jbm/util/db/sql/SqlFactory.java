package jbm.util.db.sql;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import hbm.vo.Book;

public class SqlFactory {
	
	private static enum COL_TYPE {
		ORACLE, INT, STRING, LOCAL_DATE, LOCAL_DATE_TIME
	}

	public static enum TABLE_NAME {
		BOOK
	}
	
	public static enum ORDER {
		ASC, DSC
	}

	private final static String INSERT_SQL = "INSERT INTO  VALUES(";	// INSERT INTO _TABLE_ VALUES(_COL_VALUES_)
	private final static String SELECT_ALL_SQL = "SELECT * FROM ";	// SELECT * FROM _TABLE_ ORDER BY _COL_ _ORDER_
	
	private static StringBuffer stringBuffer;
	
	
	/*
	 * Make Insert SQL
	 */
	public static String makeInsert(Object obj) {
		stringBuffer = new StringBuffer(INSERT_SQL);
		
		if(obj instanceof Book) {
			Book b = (Book) obj;
//			stringBuffer.insert(12, TABLE_BOOK);
			insertAppend(TABLE_NAME.BOOK);
			insertAppend(COL_TYPE.ORACLE, "BOOK_NO.NEXTVAL", false);
			insertAppend(COL_TYPE.INT, b.getSymbol(), false);
			insertAppend(COL_TYPE.STRING, b.getName(), false);
			insertAppend(COL_TYPE.STRING, b.getAuthor(), false);
			insertAppend(COL_TYPE.INT, b.getPrice(), false);
			insertAppend(COL_TYPE.STRING, b.getPub(), false);
			insertAppend(COL_TYPE.LOCAL_DATE, b.getPubDay(), false);
			insertAppend(COL_TYPE.STRING, b.getLoc(), false);
			insertAppend(COL_TYPE.STRING, b.getImgFile(), false);
			insertAppend(COL_TYPE.STRING, b.getBrwer(), false);
			insertAppend(COL_TYPE.LOCAL_DATE_TIME, b.getBrwDay(), false);
			insertAppend(COL_TYPE.LOCAL_DATE, b.getPeriod(), true);
		}
		
		System.out.println("stringBuffer.toString(): " + stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	/*
	 * Make Select All SQL
	 */
	public static String makeSelectAll(TABLE_NAME tableName, String orderCol, ORDER order) {
		stringBuffer = new StringBuffer(SELECT_ALL_SQL);
		stringBuffer.append(tableName.toString());
		stringBuffer.append(" ORDER BY ");
		stringBuffer.append(orderCol.toUpperCase());
		stringBuffer.append(" ");
		stringBuffer.append(order.toString());
		return stringBuffer.toString();
	}
	
	/*
	 * Append Values in Insert SQL by Column Type
	 */
	private static void insertAppend(COL_TYPE colType, Object colValue, boolean isFinalCol) {
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
		else
			stringBuffer.append(")");
	}
	
	/*
	 * Append Table Name in Insert SQL
	 */
	private static void insertAppend(TABLE_NAME tableName) {
		stringBuffer.insert(12, tableName.toString());
	}
}