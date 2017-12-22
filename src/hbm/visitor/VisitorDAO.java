package hbm.visitor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import hbm.util.db.DbManager;
import hbm.util.db.pool.DBConnectionPoolManager;
import hbm.util.db.sql.SqlFactory;

public class VisitorDAO {

//	private Statement stmt = null;
//	private Connection conn = null;
	
	public VisitorDAO() {
//		this.stmt = DbManager.getInstance().getStatement();
//		this.conn = DbManager.getInstance().getConn();
	}
	
	// Insert a Visitor
	public int insert(Visitor visitor) {
//		String sql = SqlFactory.makeInsert(visitor);
//		int ret = -1;
//		try (Statement st = DbManager.getInstance().getStatement()) {
//			ret = stmt.executeUpdate(sql);
//			conn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return -1;
	}
	
	// Select All Visitors
	public List<Map<String, Object>> selectAll() {
		
		
		return null;
	}
	
}
