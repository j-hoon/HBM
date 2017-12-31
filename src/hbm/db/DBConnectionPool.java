package hbm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class DBConnectionPool {

    private Vector<Connection> freeConnections = new Vector<Connection>();	// Free Connection List
    private int checkedOut;													// Current using Connection count
    private int maxConn;													// Connection Max count
    private int initConn;													// Connection Default count
    private int maxWait;													// Waiting time (Max time for waiting Connection in pool)
    public final static String name = "HBM";								// Connection Pool Name
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";		// DB URL
    private final String user = "HBM";										// DB UserID
    private final String password = "79167916";								// DB Password
    
    // Constructor
    public DBConnectionPool(int maxConn, int initConn, int waitTime) {
//    public DBConnectionPool(String name, /*String url, String user, String password, */int maxConn, int initConn, int waitTime) {
//    	this.name = name;
//    	this.url = url;
//    	this.user = user;
//    	this.password = password;
    	this.maxConn = maxConn;
    	this.initConn = initConn;
    	this.maxWait = waitTime;
    	
    	for (int i = 0; i < initConn; i++) {
            freeConnections.addElement(newConnection());
        }
    }
    
	// Free Connection
	// @param con : 반납할 Connection
	public synchronized void freeConnection(Connection con) {
		freeConnections.addElement(con);
		checkedOut--;
		// Connection을 얻기 위해 대기하고 있는 thread에 알림
		notifyAll();
	}
	
	// Connection 을 얻음
	public synchronized Connection getConnection() {
		Connection con = null;
		// Connection이 Free List에 있으면 List의 첫 번째를 얻음
		if (freeConnections.size() > 0) {
			con = (Connection) freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			
			try {
				// DBMS에 의해 Connection이 close 되었으면 다시 요구
				if (con.isClosed()) {
//					Log.err("Removed bad connection from " + name);
					con = getConnection();
				}
			} // 요상한 Connection 발생하면 다시 요구
			catch (SQLException e) {
//				Log.err(e, "Removed bad connection from " + name);
				con = getConnection();
			}
		} // Connection이 Free List에 없으면 새로 생성
		else if (maxConn == 0 || checkedOut < maxConn) {
			con = newConnection();
		}
		
		if (con != null) {
			checkedOut++;
		}
		
		return con;
	}
	
	// Connection을 얻음
	// @param timeout : Connection을 얻기 위한 최대 기다림 시간
	public synchronized Connection getConnection(long timeout) {
		long startTime = new Date().getTime();
		Connection con;
		while ((con = getConnection()) == null) {
			try {
				wait(timeout * maxWait);
			} catch (InterruptedException e) {}
				if ((new Date().getTime() - startTime) >= timeout) {
				// 기다림 시간 초과
				return null;
			}
		}
		
		return con;
	}

	// Create a Connection
	private Connection newConnection() {
		Connection con = null;
		try {
			if (user == null) {
				con = DriverManager.getConnection(this.url);
			} else {
				con = DriverManager.getConnection(this.url, this.user, this.password);
			}
//			Log.out("Created a new connection in pool " + name);
		} catch (SQLException e) {
			StringBuffer sb = new StringBuffer();
			sb.append("Can't create a new connection for ");
			sb.append(this.url);
			sb.append(" user: ");
			sb.append(this.user);
			sb.append(" passwd: ");
			sb.append(this.password);
//			Log.err(e, sb.toString());
			return null;
		}
		return con;
	}
	
}
