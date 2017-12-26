package hbm.util.db.sql;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hbm.util.Debug;


public class Condition<T> {

	private interface COND {}
	private interface COND_SINGLE extends COND {}
	private interface COND_MULTI extends COND {}
	private interface COND_INT extends COND {}
	private interface COND_STRING extends COND {}
	private interface COND_LOCALDATE extends COND {}
	private interface COND_LOCALDATETIME extends COND {}
	private interface COND_CHAR extends COND {}
	
	public static enum COND_INT_SINGLE implements COND, COND_SINGLE, COND_INT {
		EQ, BIG_EX, BIG_IN, SMALL_EX, SMALL_IN
	}
	public static enum COND_STRING_SINGLE implements COND, COND_SINGLE, COND_STRING {
		EQ, LIKE
	}
	public static enum COND_LOCALDATE_SINGLE implements COND, COND_SINGLE, COND_LOCALDATE {
		EQ, BEFORE, AFTER, YEAR_IN, MONTH_IN
	}
	public static enum COND_LOCALDATETIME_SINGLE implements COND, COND_SINGLE, COND_LOCALDATETIME {
		EQ, BEFORE, AFTER, YEAR_IN, MONTH_IN, DAY_IN, HOUR_IN, MIN_IN, SEC_IN
	}
	public static enum COND_CHAR_SINGLE implements COND, COND_SINGLE, COND_CHAR {
		
	}
//	public static enum COND_INT_MULTI implements COND, COND_MULTI, COND_INT {
//		EQ, BETWEEN_EX_EX, BETWEEN_IN_EX, BETWEEN_EX_IN, BETWEEN_IN_IN
//	}
	
	// Members
	private COND cond;
	private T val1;
	private T val2;
	private String condColName;
	private String condStr;
	
	// private Constructor
	private Condition(COND_SINGLE cond, T value, String condColName, String condStr) {
		this.cond = cond;
		this.val1 = value;
		this.condColName = condColName;
		this.condStr = condStr;
	}
	private Condition(COND_MULTI cond, T value1, T value2, String condColName, String condStr) {
		this.cond = cond;
		this.val1 = value1;
		this.val2 = value2;
		this.condColName = condColName;
		this.condStr = condStr;
	}

	
	/**
	 * 
	 * @param condColName
	 * @param cond
	 * @param value
	 * @return Single Condition with int
	 */
	public static Condition<Integer> of(String condColName, COND_INT_SINGLE cond, int value) {
		condColName = condColName.toUpperCase();
		String condStr = makeCondStr(condColName, cond, value);
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value: " + value + " | condStr: " + condStr);
		return new Condition<Integer>(cond, value, condColName, condStr);
	}
	/**
	 * 
	 * @param condColName
	 * @param cond
	 * @param value
	 * @return Single Condition with String
	 */
	public static Condition<String> of(String condColName, COND_STRING_SINGLE cond, String value) {
		condColName = condColName.toUpperCase();
		if(cond.equals(COND_STRING_SINGLE.LIKE))
			value = "%" + value + "%";
		
		String condStr = makeCondStr(condColName, cond, value);
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value: " + value + " | condStr: " + condStr);
		return new Condition<String>(cond, value, condColName, condStr);
	}
	/**
	 * 
	 * @param condColName
	 * @param cond
	 * @param value
	 * @return Single Condition with LocalDate
	 */
	public static Condition<LocalDate> of(String condColName, COND_LOCALDATE_SINGLE cond, LocalDate value) {
		condColName = condColName.toUpperCase();
		
		String condStr = makeCondStr(condColName, cond, value);
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value1: " + value + " | condStr: " + condStr);
		return new Condition<LocalDate>(cond, value, condColName, condStr);
	}
	/**
	 * 
	 * @param condColName
	 * @param cond
	 * @param value
	 * @return Single Condition with LocalDateTime
	 */
	public static Condition<LocalDateTime> of(String condColName, COND_LOCALDATETIME_SINGLE cond, LocalDateTime value) {
		
		
		return null;
	}
	
	
	/**
	 * 
	 * @param condColName
	 * @param cond
	 * @param value
	 * @return 
	 */
	private static <T> String makeCondStr(String condColName, COND_SINGLE cond, T value) {
		// debug
//		System.out.println("condColName: " + condColName + " | cond: " + cond + " | cond.getClass().getSimpleName(): " + cond.getClass().getSimpleName() + " | value: " + value + " | value.getClass(): " + value.getClass());
		
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
//		else if() {
//			
//		}
		
		
		return "";
	}
	
	/**
	 * 
	 * @param condColName
	 * @param cond
	 * @param value
	 * @return 
	 */
	private static <T> String makeCondStr(String condColName, COND_MULTI cond, T value) {
		
		return null;
	}
	
	
	// Getter and Setter
	public COND getCond() {
		return cond;
	}
	public void setCond(COND cond) {
		this.cond = cond;
	}
	public T getVal1() {
		return val1;
	}
	public void setVal1(T val1) {
		this.val1 = val1;
	}
	public T getVal2() {
		return val2;
	}
	public void setVal2(T val2) {
		this.val2 = val2;
	}
	public String getCondColName() {
		return condColName;
	}
	public void setCondColName(String condColName) {
		this.condColName = condColName;
	}
	public String getCondStr() {
		return condStr;
	}
	public void setCondStr(String condStr) {
		this.condStr = condStr;
	}
	
}
