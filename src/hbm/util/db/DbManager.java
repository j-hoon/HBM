package hbm.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {
	
	private final String connUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String connUser = "HBM";
	private final String connPw = "79167916";
	private Connection connection = null;
	private Statement statement = null;
	
	private DbManager() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			this.connection = DriverManager.getConnection(connUrl, connUser, connPw);
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// LazyHolder-Singleton
	public static DbManager getInstance() {
		return LazyHolder.INSTANCE;
	}
	private static class LazyHolder {
		private static final DbManager INSTANCE = new DbManager();
	}
	
	// Getter
	public Connection getConn() {
		return this.connection;
	}
	public Statement getStatement() {
		return this.statement;
	}

	// Public Methods
	
}
