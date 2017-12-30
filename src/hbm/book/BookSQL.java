package hbm.book;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public class BookSQL {
	
	public static String getInsertAllSQL() {
		return "BOOK_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
	}

	public static String getUpdateAllSQL() {
		return "SYMBOL = ?, NAME = ?, AUTHOR = ?, PRICE = ?, PUB = ?, PUBDAY = ?, LOC = ?, IMGFILE = ?, BRWER = ?, BRWDAY = ?, PERIOD = ?";
	}
	
	public static void setAllOfParams(PreparedStatement pstmt, Object obj) {
		Book b = (Book) obj;
		try {
			pstmt.setInt(1, b.getSymbol());
			pstmt.setString(2, b.getName());
			pstmt.setString(3, b.getAuthor());
			pstmt.setInt(4, b.getPrice());
			pstmt.setString(5, b.getPub());
			pstmt.setDate(6, Date.valueOf(b.getPubDay()));
			pstmt.setString(7, b.getLoc());
			pstmt.setString(8, b.getImgFile());
			pstmt.setString(9, b.getBrwer());
			pstmt.setTimestamp(10, Timestamp.valueOf(b.getBrwDay()));
			pstmt.setDate(11, Date.valueOf(b.getPeriod()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
