package hbm.util.db.sql;

public class Order {
	
	// Order enum
	public static enum ORDER { ASC, DESC }
	
	// Members
	private String orderColName;
	private ORDER order;
	private String orderSql;
	
	// private Constructor
	private Order(String orderColName, ORDER order, String orderSql) {
		this.orderColName = orderColName;
		this.order = order;
		this.orderSql = orderSql;
	}

	// Constructor
	/**
	 * Generate Order be used to make SQL
	 * @param orderColName the String value choose name of order column
	 * @param order the enum value in ORDER choose method of order (ASC, DSC)
	 * @return Order containing order SQL string
	 */
	public static Order of(String orderColName, ORDER order) {
		orderColName = orderColName.toUpperCase();
		String orderSql = " ORDER BY " + orderColName + " " + order.toString();
		return new Order(orderColName, order, orderSql);
	}

	
	// Getter
	public String getOrderColName() {
		return orderColName;
	}
	public ORDER getOrder() {
		return order;
	}
	public String getOrderSql() {
		return orderSql;
	}
	
}
