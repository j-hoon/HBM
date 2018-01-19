package hbm.visitor;

public class VisitorDetailSQL {

	public static String getSelectSQL(int no) {
		return "" +
				"SELECT " +
					"NO, GRADE, ID, PW, LNAME, FNAME, BIRTH, EMAIL, PHONE, IMGFILENAME, IMGFILECONT, HPHONE, ADDR, COMP, POS " +
				"FROM " + 
					"VISITOR V JOIN VISITOR_DETAIL VD " +
				"ON " +
					"V.NO = " + no + " AND V.NO = VD.VNO(+) " +
				"ORDER BY V.NO ASC";
	}
	
}
