package hbm.visitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitorSQL {

	public static String getInsertAllSQL() {
		return "VISITOR_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
	}
	
	public static String getUpdateAllSQL() {
		return "GRADE = ?, ID = ?, PW = ?, LNAME = ?, FNAME = ?, BIRTH = ?, EMAIL = ?, PHONE = ?, IMGFILENAME = ?, HPHONE = ?, ADDR = ?, COMP = ?, POS = ?";
	}
	
	public static void setAllOfParams(PreparedStatement pstmt, Object obj) {
		/*Visitor v = (Visitor) obj;
		try {
			pstmt.setString(1, String.valueOf(v.getGrade()));
			pstmt.setString(2, v.getId());
			pstmt.setString(3, v.getPw());
			pstmt.setString(4, v.getlName());
			pstmt.setString(5, v.getfName());
			pstmt.setString(6, String.valueOf(v.getBirth()));
			pstmt.setString(7, v.getEmail());
			pstmt.setString(8, String.valueOf(v.getPhone()));
			pstmt.setString(9, v.getImgFileName());
//			pstmt.setString(10, v.getImgFile());
			pstmt.setString(11, String.valueOf(v.gethPhone()));
			pstmt.setString(12, v.getAddr());
			pstmt.setString(13, v.getComp());
			pstmt.setString(14, v.getPos());
		} catch(SQLException e) {
			e.printStackTrace();
		}*/
	}
	
	
	/****/
	public static String getCountSQL(String id) {
		return "SELECT COUNT(*) FROM VISITOR WHERE ID = '" + id + "'";
	}
	
	public static String getInsertSQL() {
		return "" + 
				"MERGE " +
					"INTO VISITOR USING DUAL " +
				"ON (ID = ?) " +
				"WHEN NOT MATCHED THEN " +
					"INSERT VALUES (VISITOR_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public static String getInsertDetailSQL() {
		return "INSERT INTO VISITOR_DETAIL VALUES(VISITOR_NO.CURRVAL, ?, ?, ?, ?, ?, ?)";
	}
	
	public static String getLoginSQL(String id, String pw) {
		return "" +
				"SELECT " +
					"NO, GRADE, ID, PW, LNAME, FNAME, BIRTH, EMAIL, PHONE " +
				"FROM VISITOR " +
				"WHERE " +
					"ID = '" + id + "' AND PW = '" + pw + "'";
	}
	
	public static String getSelectSQL() {
		return "" +
				"SELECT " +
					"NO, GRADE, ID, PW, LNAME, FNAME, BIRTH, EMAIL, PHONE " +
				"FROM VISITOR";
	}

	public static void setAllParamsOfInsertSQL(PreparedStatement pstmt, Visitor v) {
		try {
			pstmt.setString(1, v.getId());
			pstmt.setString(2, String.valueOf(v.getGrade()));
			pstmt.setString(3, v.getId());
			pstmt.setString(4, v.getPw());
			pstmt.setString(5, v.getlName());
			pstmt.setString(6, v.getfName());
			pstmt.setDate(7, Date.valueOf(v.getBirth()));
			pstmt.setString(8, v.getEmail());
			pstmt.setString(9, String.valueOf(v.getPhone().substring(2)));
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setAllParamsOfInsertDetailSQL(PreparedStatement pstmt, VisitorDetail v) {
		try {
			if(!v.getImgFileName().equals("")) {
				Path imgFile = Paths.get(v.getImgFileName());
				pstmt.setString(1, Paths.get(v.getImgFileName()).getFileName().toString());
				pstmt.setBinaryStream(2, Files.newInputStream(imgFile, StandardOpenOption.READ), Files.size(imgFile));
			} else {
				pstmt.setString(1, null);
				pstmt.setObject(2, null);
			}
			pstmt.setString(3, v.gethPhone().equals("") ? "" : String.valueOf(v.gethPhone().substring(1)));
			pstmt.setString(4, v.getAddr());
			pstmt.setString(5, v.getComp());
			pstmt.setString(6, v.getPos());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
