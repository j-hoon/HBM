package hbm.visitor;

import hbm.db.DBConnectionPoolManager;
import hbm.db.sql.SqlFactory.TABLE_NAME;

public class VisitorDetailDAO {

	private DBConnectionPoolManager dbManager = null;
	
	public VisitorDetailDAO() {
		this.dbManager = DBConnectionPoolManager.getInstance();
	}
	
	
	/**  **/
	public VisitorDetail getVisitorDetail(Visitor visitor) {
		VisitorDetail ret = (VisitorDetail) dbManager.execSelectOnlyOne(TABLE_NAME.VISITOR_DETAIL, VisitorDetailSQL.getSelectSQL(visitor.getNo()));
		return ret;
	}
}
