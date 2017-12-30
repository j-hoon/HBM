package hbm.util.db.sql;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hbm.util.Debug;

// Marker Interface
interface COND {}
interface COND_SINGLE extends COND {}
interface COND_MULTI extends COND {}

public class Condition<T> {
	
	// Condition enum
	public static enum COND_INT_SINGLE implements COND_SINGLE {
		EQ, BIG_EX, BIG_IN, SMALL_EX, SMALL_IN
	}
	public static enum COND_STRING_SINGLE implements COND_SINGLE {
		EQ, LIKE
	}
	public static enum COND_LOCALDATE_SINGLE implements COND_SINGLE {
		EQ, BEFORE, AFTER, YEAR_IN, MONTH_IN
	}
	public static enum COND_LOCALDATETIME_SINGLE implements COND_SINGLE {
		/*EQ, */BEFORE, AFTER, YEAR_IN, MONTH_IN, DAY_IN, HOUR_IN, MIN_IN, SEC_IN
	}
	public static enum COND_CHAR_SINGLE implements COND_SINGLE {
		// TODO
	}
	public static enum COND_INT_MULTI implements COND_MULTI {
		BETWEEN_EX_EX, BETWEEN_IN_EX, BETWEEN_EX_IN, BETWEEN_IN_IN
	}
	public static enum COND_LOCALDATE_MULTI implements COND_MULTI {
		BETWEEN
	}
	public static enum COND_LOCALDATETIME_MULTI implements COND_MULTI {
		BETWEEN
	}
	
	// Members
	private COND cond;
	private T val1;
	private T val2;
	private String condColName;
	private String condSql;
	
	// private Constructors
	private Condition(COND_SINGLE cond, T value, String condColName, String condSql) {
		this.cond = cond;
		this.val1 = value;
		this.condColName = condColName;
		this.condSql = condSql;
	}
	private Condition(COND_MULTI cond, T value1, T value2, String condColName, String condSql) {
		this.cond = cond;
		this.val1 = value1;
		this.val2 = value2;
		this.condColName = condColName;
		this.condSql = condSql;
	}

	// Constructors
	/**
	 * Generate Single Integer Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_INT_SINGLE choose method of condition (EQ, BIG_EX, BIG_IN, SMALL_EX, SMALL_IN)
	 * @param value the int value of condition
	 * @return Single Condition containing condition SQL string and int value
	 */
	public static Condition<Integer> of(String condColName, COND_INT_SINGLE cond, int value) {
		condColName = condColName.toUpperCase();
		String condSql = makeCondSql(condColName, cond, value);
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value: " + value + " | condSql: " + condSql);
		return new Condition<Integer>(cond, value, condColName, condSql);
	}
	/**
	 * Generate Single String Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_STRING_SINGLE choose method of condition (EQ, LIKE)
	 * @param value the String value of condition
	 * @return Single Condition containing condition SQL string and String value
	 */
	public static Condition<String> of(String condColName, COND_STRING_SINGLE cond, String value) {
		condColName = condColName.toUpperCase();
		if(cond.equals(COND_STRING_SINGLE.LIKE)) value = "%" + value + "%";
		String condSql = makeCondSql(condColName, cond, value);
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value: " + value + " | condSql: " + condSql);
		return new Condition<String>(cond, value, condColName, condSql);
	}
	/**
	 * Generate Single LocalDate Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_LOCALDATE_SINGLE choose method of condition (EQ, BEFORE, AFTER, YEAR_IN, MONTH_IN)
	 * @param value the LocalDate value of condition
	 * @return Single Condition containing condition SQL string and LocalDate value
	 */
	public static Condition<LocalDate> of(String condColName, COND_LOCALDATE_SINGLE cond, LocalDate value) {
		condColName = condColName.toUpperCase();
		String condSql = makeCondSql(condColName, cond, value);
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value1: " + value + " | condSql: " + condSql);
		return new Condition<LocalDate>(cond, value, condColName, condSql);
	}
	/**
	 * Generate Single LocalDateTime Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_LOCALDATETIME_SINGLE choose method of condition(BEFORE, AFTER, YEAR_IN, MONTH_IN, DAY_IN, HOUR_IN, MIN_IN, SEC_IN)
	 * @param value the LocalDateTime value of condition
	 * @return Single Condition containing condition SQL string and LocalDateTime value
	 */
	public static Condition<LocalDateTime> of(String condColName, COND_LOCALDATETIME_SINGLE cond, LocalDateTime value) {
		condColName = condColName.toUpperCase();
		String condSql = makeCondSql(condColName, cond, value);
		// debug
		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value1: " + value + " | condSql: " + condSql);
		return new Condition<LocalDateTime>(cond, value, condColName, condSql);
	}
	/**
	 * Generate Multiple Integer Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_INT_MULTI choose method of condition (BETWEEN_EX_EX, BETWEEN_IN_EX, BETWEEN_EX_IN, BETWEEN_IN_IN)
	 * @param value1 the first int value of condition
	 * @param value2 the second int value of condition
	 * @return Multiple Condition containing condition SQL string and int values
	 */
	public static Condition<Integer> of(String condColName, COND_INT_MULTI cond, int value1, int value2) {
		condColName = condColName.toUpperCase();
		String condSql = makeCondSql(condColName, cond, value1, value2);
		// debug
		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() +
				" | value1: " + value1 + " | value2: " + value2 + " | condSql: " + condSql);
		return new Condition<Integer>(cond, value1, value2, condColName, condSql);
	}
	/**
	 * Generate Multiple LocalDate Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_LOCALDATE_MULTI choose method of condition (BETWEEN)
	 * @param value1 the first LocalDate value of condition
	 * @param value2 the second LocalDate value of condition
	 * @return Multiple Condition containing condition SQL string and LocalDate values
	 */
	public static Condition<LocalDate> of(String condColName, COND_LOCALDATE_MULTI cond, LocalDate value1, LocalDate value2) {
		condColName = condColName.toUpperCase();
		String condSql = makeCondSql(condColName, cond, value1, value2);
		// debug
		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() +
				" | value1: " + value1 + " | value2: " + value2 + " | condSql: " + condSql);
		return new Condition<LocalDate>(cond, value1, value2, condColName, condSql);
	}
	/**
	 * Generate Multiple LocalDateTime Condition be used to make SQL
	 * @param condColName the String value choose name of condition column
	 * @param cond the enum value in COND_LOCALDATETIME_MULTI choose method of condition (BETWEEN)
	 * @param value1 the first LocalDateTime value of condition
	 * @param value2 the second LocalDateTime value of condition
	 * @return Multiple Condition containing condition SQL string and LocalDateTime values
	 */
	public static Condition<LocalDateTime> of(String condColName, COND_LOCALDATETIME_MULTI cond, LocalDateTime value1, LocalDateTime value2) {
		condColName = condColName.toUpperCase();
		String condSql = makeCondSql(condColName, cond, value1, value2);
		// debug
		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() +
				" | value1: " + value1 + " | value2: " + value2 + " | condSql: " + condSql);
		return new Condition<LocalDateTime>(cond, value1, value2, condColName, condSql);
	}

	
	// Make Single Condition String be used to make SQL
	private static <T> String makeCondSql(String condColName, COND_SINGLE cond, T value) {
		// debug
		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value: " + value + " | value.getClass(): " + value.getClass());
		/** Single Condition **/
		// int
		if(cond instanceof COND_INT_SINGLE) {
			if(cond.equals(COND_INT_SINGLE.EQ)) return condColName + " = ?";
			else if(cond.equals(COND_INT_SINGLE.BIG_EX)) return condColName + " > ?";
			else if(cond.equals(COND_INT_SINGLE.BIG_IN)) return condColName + " >= ?";
			else if(cond.equals(COND_INT_SINGLE.SMALL_EX)) return condColName + " < ?";
			else if(cond.equals(COND_INT_SINGLE.SMALL_IN)) return condColName + " <= ?";
			else { Debug.error("COND_INT_SINGLE 타입에 대한 비정상적인 접근입니다."); }
		}
		// String
		else if(cond instanceof COND_STRING_SINGLE) {
			if(cond.equals(COND_STRING_SINGLE.EQ)) return condColName + " = ?";
			else if(cond.equals(COND_STRING_SINGLE.LIKE)) return condColName + " LIKE ?";
			else { Debug.error("COND_STRING_SINGLE 타입에 대한 비정상적인 접근입니다."); }
		}
		// LocalDate
		else if(cond instanceof COND_LOCALDATE_SINGLE) {
			if(cond.equals(COND_LOCALDATE_SINGLE.EQ)) return condColName + " = ?";
			else if(cond.equals(COND_LOCALDATE_SINGLE.BEFORE)) return condColName + " <= ?";
			else if(cond.equals(COND_LOCALDATE_SINGLE.AFTER)) return condColName + " >= ?";
			else if(cond.equals(COND_LOCALDATE_SINGLE.MONTH_IN)) { return condColName + " BETWEEN ? AND LAST_DAY(?)"; }
			else if(cond.equals(COND_LOCALDATE_SINGLE.YEAR_IN)) { return condColName + " BETWEEN ? AND TO_CHAR(TO_DATE(?), 'YYYY') || '-12-31'"; }
			else { Debug.error("COND_LOCALDATE_SINGLE 타입에 대한 비정상적인 접근입니다."); }
		}
		// LocalDateTime
		else if(cond instanceof COND_LOCALDATETIME_SINGLE) {
			if(cond.equals(COND_LOCALDATETIME_SINGLE.BEFORE)) return condColName + " <= ?";
			else if(cond.equals(COND_LOCALDATETIME_SINGLE.AFTER)) return condColName + " >= ?";
			else if(cond.equals(COND_LOCALDATETIME_SINGLE.SEC_IN) ||
					cond.equals(COND_LOCALDATETIME_SINGLE.MIN_IN) ||
					cond.equals(COND_LOCALDATETIME_SINGLE.HOUR_IN)) return condColName + " BETWEEN ? AND ?";
			else if(cond.equals(COND_LOCALDATETIME_SINGLE.DAY_IN)) return condColName + " BETWEEN ? AND TO_CHAR(TO_DATE(?)) || ' 23:59:59.999999999'";
			else if(cond.equals(COND_LOCALDATETIME_SINGLE.MONTH_IN)) return condColName + " BETWEEN ? AND TO_CHAR(LAST_DAY(?)) || ' 23:59:59.999999999'";
			else if(cond.equals(COND_LOCALDATETIME_SINGLE.YEAR_IN)) return condColName + " BETWEEN ? AND TO_CHAR(TO_DATE(?), 'YYYY') || '-12-31 23:59:59.999999999'";
			else { Debug.error("COND_LOCALDATETIME_SINGLE 타입에 대한 비정상적인 접근입니다."); }
		}
		// char
//		else if() {
//			
//		}
		return "";
	}
	
	// Make Multiple Condition String be used to make SQL
	private static <T> String makeCondSql(String condColName, COND_MULTI cond, T value1, T value2) {
		// debug
		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() +
				" | value1: " + value1 + " | value2: " + value2 + " | value1.getClass(): " + value1.getClass() + " | value2.getClass(): " + value2.getClass());
		/** Multi Condition **/
		// int
		if(cond instanceof COND_INT_MULTI) {
			if(cond.equals(COND_INT_MULTI.BETWEEN_EX_EX)) return "? < " + condColName + " AND " + condColName + " < ?";
			else if(cond.equals(COND_INT_MULTI.BETWEEN_IN_EX)) return "? <= " + condColName + " AND " + condColName + " < ?";
			else if(cond.equals(COND_INT_MULTI.BETWEEN_EX_IN)) return "? < " + condColName + " AND " + condColName + " <= ?";
			else if(cond.equals(COND_INT_MULTI.BETWEEN_IN_IN)) return "? <= " + condColName + " AND " + condColName + " <= ?";
			else { Debug.error("COND_INT_MULTI 타입에 대한 비정상적인 접근입니다."); }
		}
		// LocalDate, LocalDateTime
		else if(cond instanceof COND_LOCALDATE_MULTI ||
				cond instanceof COND_LOCALDATETIME_MULTI) {
			return condColName + " BETWEEN ? AND ?";
		}
		return "";
	}
	
	
	// Getter
	public COND getCond() {
		return cond;
	}
	public T getVal1() {
		return val1;
	}
	public T getVal2() {
		return val2;
	}
	public String getCondColName() {
		return condColName;
	}
	public String getCondSql() {
		return condSql;
	}
	
}
