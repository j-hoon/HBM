package hbm.util.db.sql;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hbm.util.Debug;

public class Condition<T> {
	
	public static enum COND_METHOD {
		EQ, 
		BIG_EX, BIG_IN, 
		SMALL_EX, SMALL_IN, 
		BETWEEN_EX_EX, BETWEEN_IN_EX, BETWEEN_EX_IN, BETWEEN_IN_IN, 
		
		LIKE
	}
	
	// Members
	private COND_METHOD method;
	private T val1;
	private T val2;
	
	// private Constructor
	private Condition(COND_METHOD method, T value) {
		this.method = method;
		this.val1 = value;
	}
	private Condition(COND_METHOD method, T value1, T value2) {
		this.method = method;
		this.val1 = value1;
		this.val2 = value2;
	}

	/**
	 * @param method Condition (EQ, BIG_EX, BIG_IN, SMALL_EX, SMALL_IN, ...)
	 * @param value Value
	 */
	public static <T> Condition<T> of(COND_METHOD method, T value) {
		Debug.show("value: " + value + ", value.getClass(): " + value.getClass());
		if(value instanceof Integer && method.equals(COND_METHOD.LIKE)) {
			Debug.show(value.getClass().getSimpleName() + "형 변수는 '" + COND_METHOD.LIKE + "'조건을 사용할 수 없습니다.");
			return null;
		} else if(value instanceof Character) {
			// TODO
		} else if(value instanceof String) {
			// TODO
		} else if(value instanceof LocalDate) {
			// TODO
		} else if(value instanceof LocalDateTime) {
			// TODO
		} else {
			// TODO
		}
		return new Condition<T>(method, value);
	}
	
	public static <T> Condition<T> of(COND_METHOD method, T value1, T value2) {
		Debug.show("value1: " + value1 + ", value1.getClass(): " + value1.getClass() + ", value2: " + value2 + ", value2.getClass(): " + value2.getClass());
		if(method.equals(COND_METHOD.EQ) || method.equals(COND_METHOD.BIG_EX) || method.equals(COND_METHOD.BIG_IN) ||
				method.equals(COND_METHOD.SMALL_EX) || method.equals(COND_METHOD.SMALL_IN) || method.equals(COND_METHOD.LIKE)) {
			Debug.show("'" + method + "'조건은 비교값이 두 개 필요하지 않습니다.");
			return null;
		}
		if(value1 instanceof Integer && method.equals(COND_METHOD.LIKE)) {
			Debug.show(value1.getClass().getSimpleName() + "형 변수는 '" + COND_METHOD.LIKE + "'조건을 사용할 수 없습니다.");
			return null;
		} else if(value1 instanceof Character) {
			// TODO
		} else if(value1 instanceof String) {
			// TODO
		} else if(value1 instanceof LocalDate) {
			// TODO
		} else if(value1 instanceof LocalDateTime) {
			// TODO
		} else {
			// TODO
		}
		return new Condition<T>(method, value1, value2);
	}

	
	
	// Getter and Setter
	public COND_METHOD getMethod() {
		return method;
	}

	public void setMethod(COND_METHOD method) {
		this.method = method;
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
	
}
