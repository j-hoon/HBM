package hbm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hbm.book.Book;
import hbm.book.BookDAO;
import hbm.dao.TestTableDAO;
import hbm.gui.GuiManager;
import hbm.util.DataConverter;
import hbm.util.db.DbManager;
import hbm.util.db.sql.SqlFactory.*;
import hbm.vo.TestTable;

//import hbm.util.db.sql.SqlFactory.COL_TYPE;

public class Hbm {
	
//	final boolean isDebug = true;
	
	public static void main(String[] args) throws SQLException {
		System.out.println("=====  HBM Started!  =====");
		
		// HBM GuiManager
//		@SuppressWarnings("unused")
//		GuiManager gm = new GuiManager();
		// Java LookAndFeel (JTatto?)
//		UIManager.LookAndFeelInfo[] lookAndFeelInfo = UIManager.getInstalledLookAndFeels();
//		for(LookAndFeelInfo i : lookAndFeelInfo)
//			System.out.println("lookAndFeelInfo: " + i.getName() + ", " + i.getClassName());
//		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		// make book instance and test
		Book b1 = new Book(1, 513144, "윤성우의 열혈 C 프로그래밍", "윤성우", 30000,
				"오렌지미디어", LocalDate.now(), "문적원1", "2_yoon_sung_woo_c.jpg",
				"전양훈", LocalDateTime.now(), LocalDate.now());
		Book b2 = new Book(2, 513143, "윤성우의 열혈 Java 프로그래밍", "윤성우", -1);

//		System.out.println(b1.toString() + "\n" + b2.toString()+ "\n");
		
		b1.editInfo(513145, "윤성우의 열혈 C++ 프로그래밍", "윤성우", 35000, "오렌지미디어", LocalDate.now(), "문적원2", "2_yoon_sung_woo_c++.jpg");
		b2.lend("전양훈");

//		System.out.println(b1.toString() + "\n" + b2.toString()+ "\n");
		
		b2.turnIn();
		
		System.out.println(b1.toString() + "\n" + b2.toString()+ "\n");
		//-- make book instance and test
		
		
		/***** DAO test *****/
		BookDAO bookDAO = new BookDAO();
		List<Map<String, Object>> bookMapList;
		List<Book> bookVOList;
		Book editedBook = new Book(-1, 523144, "윤성우의 열혈 C# 프로그래밍", "미상", 50000);
		/** Insert a Book **/
		bookDAO.insert(b1);
//		bookDAO.insert(b2);
		/** Select all Books **/
		bookMapList = bookDAO.selectAll();
//		for(int i = 0; i < bookMapList.size(); i++) System.out.println(bookMapList.get(i).toString());
		// Map to VO
		Book book1 = DataConverter.mapToVO(Book.class, bookMapList.get(0));
//		System.out.println(book1.toString());
		// List<Map> to List<VO>
//		bookVOList = DataConverter.mapListToVOList(Book.class, bookMapList);
//		for(int i = 0; i < bookVOList.size(); i++) System.out.println(bookVOList.get(i).toString());
		/** Select Books by Condition **/
		// String, int
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.STRING, "name", "윤성우", true, COL_TYPE.INT, "no", ORDER.ASC);
//		// String, String
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.STRING, "name", "윤성우", true, COL_TYPE.STRING, "name", ORDER.ASC);
//		// String, LocalDate
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.STRING, "name", "윤성우", true, COL_TYPE.LOCAL_DATE, "pubDay", ORDER.ASC);
//		// String, LocalDateTime
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.STRING, "name", "윤성우", true, COL_TYPE.LOCAL_DATE_TIME, "brwDay", ORDER.ASC);
//		// int, String
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.INT, "no", 179, false, COL_TYPE.STRING, "name", ORDER.ASC);
//		// LocalDate, String
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.LOCAL_DATE, "pubDay", "2017-12-18", false, COL_TYPE.STRING, "name", ORDER.ASC);
//		// LocalDateTime, String
		bookMapList = bookDAO.selectAllByCondWithOrder(COL_TYPE.LOCAL_DATE_TIME, "brwDay", "2017-12-18 00:17:42.875", false, COL_TYPE.STRING, "name", ORDER.ASC);
		/** Update a Book by PK **/
		bookDAO.updateAllByNo(178, editedBook);
		/** Delete a Book by PK **/
		bookDAO.deleteByNo(178);
		
		
		
		/** Exit Program **/
//		DbManager.getInstance().getStatement().close();
//		DbManager.getInstance().getConn().close();
		DbManager.getInstance().close();
	}
}