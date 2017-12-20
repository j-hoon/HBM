package hbm.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hbm.util.DataConverter;
import hbm.util.db.DbManager;
import hbm.vo.TestTable;

public class TestTableDAO {
	
	private Statement statement = null;
	
	public TestTableDAO() {
		this.statement = DbManager.getInstance().getStatement();
	}
	
	public void add(TestTable testTable) {
		
	}
	
	public List<Map<String, Object>> selectAll() throws SQLException {
//		ResultSet resultSet = this.statement.executeQuery(sql);
//		while(resultSet.next()) {
//			int retNo = resultSet.getInt(1);
//			String retData = resultSet.getString(2);
//			System.out.println("retSelect[" + resultSet.getRow() + "] no: " + retNo + ", data: " + retData);
//		}
		
		String sql = "SELECT * FROM TEST_TABLE ORDER BY NO ASC";
		
		List<Map<String, Object>> list = DataConverter.convResultSetToMapList(this.statement.executeQuery(sql));
		
//		Iterator<Map<String, Object>> iterator = list.iterator();
//		for(Map<String, Object> map : list) {
//			for(Map.Entry<String, Object> entry : map.entrySet()) {
//				System.out.println("selectAll-TestTable: " + entry.getKey() + ", " + entry.getValue());
//			}
//		}
		return list;
	}
	
	
	
}
