package hbm.visitor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitorSQL {
	
	public static String getInsertAllSQL() {
		return "VISITOR_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
	}
	
	public static String getUpdateAllSQL() {
		return "GRADE = ?, ID = ?, PW = ?, NAME = ?, BIRTH = ?, EMAIL = ?, PHONE = ?, IMGFILE = ?, HPHONE = ?, ADDR = ?, COMP = ?, POS = ?";
	}
	
	public static void setAllOfParams(PreparedStatement pstmt, Object obj) {
		Visitor v = (Visitor) obj;
		try {
			pstmt.setString(1, String.valueOf(v.getGrade()));
			pstmt.setString(2, v.getId());
			pstmt.setString(3, v.getPw());
			pstmt.setString(4, v.getName());
			pstmt.setString(5, String.valueOf(v.getBirth()));
			pstmt.setString(6, v.getEmail());
			pstmt.setString(7, String.valueOf(v.getPhone()));
			pstmt.setString(8, v.getImgFile());
			pstmt.setString(9, String.valueOf(v.getHPhone()));
			pstmt.setString(10, v.getAddr());
			pstmt.setString(11, v.getComp());
			pstmt.setString(12, v.getPos());
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
