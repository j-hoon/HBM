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
import hbm.util.db.pool.DBConnectionPoolManager;
import hbm.util.db.sql.Condition;
import hbm.util.db.sql.Condition.*;
import hbm.util.db.sql.SqlFactory.*;
import hbm.visitor.Visitor;
import hbm.vo.TestTable;

//import hbm.util.db.sql.SqlFactory.COL_TYPE;

public class Hbm {
	
//	final boolean isDebug = true;
	
	public static void main(String[] args) throws SQLException {
		System.out.println("==================================================  HBM Started!  ==================================================");
		
		// HBM GuiManager
//		@SuppressWarnings("unused")
//		GuiManager gm = new GuiManager();
		// Java LookAndFeel (JTatto?)
//		UIManager.LookAndFeelInfo[] lookAndFeelInfo = UIManager.getInstalledLookAndFeels();
//		for(LookAndFeelInfo i : lookAndFeelInfo)
//			System.out.println("lookAndFeelInfo: " + i.getName() + ", " + i.getClassName());
//		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		
		/***** VisitorDAO test *****/
		Visitor v1 = new Visitor();
		Visitor v2 = new Visitor(1, '3', "test1", "1234", "김철수", 910301, "test@naver.com", 1012345678);
		Visitor v3 = new Visitor(2, '3', "test2", "4567", "박영희", 930920, "test@gmail.com", 1045678901, "pyh.jpg", 515017619, "부산시 남구 대연동", "부경대학교", "컴퓨터공학과");
		System.out.println(v1 + "\n" + v2 + "\n" + v3 + "\n");
		//---- VisitorDAO test *****/
		
		
		BookDAO bookDAO = new BookDAO();
		List<Map<String, Object>> bookMapList;
//		bookMapList = bookDAO.selectAll();

		// TODO: test Condition<T>
		// Single Condition
		// int
//		Condition<Integer> ci1 = Condition.of("price", COND_INT_SINGLE.EQ, 35000);
//		Condition<Integer> ci2 = Condition.of("price", COND_INT_SINGLE.BIG_EX, 110);
//		Condition<Integer> ci3 = Condition.of("price", COND_INT_SINGLE.BIG_IN, 111);
//		Condition<Integer> ci4 = Condition.of("price", COND_INT_SINGLE.SMALL_EX, 112);
//		Condition<Integer> ci5 = Condition.of("price", COND_INT_SINGLE.SMALL_IN, 111);
//		bookMapList = bookDAO.selectAllByCond(ci1, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(ci2, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(ci3, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(ci4, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(ci5, "no", ORDER.ASC);
		// String
//		Condition<String> cs1 = Condition.of("name", COND_STRING_SINGLE.EQ, "윤성우의 열혈 Java 프로그래밍");
//		Condition<String> cs2 = Condition.of("name", COND_STRING_SINGLE.LIKE, "C++");
//		bookMapList = bookDAO.selectAllByCond(cs1, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cs2, "no", ORDER.ASC);
		// LocalDate
//		// 해당 일 검색
//		int y1 = 2017; int m1 = 12; int d1 = 16;
//		Condition<LocalDate> cld1 = Condition.of("period", COND_LOCALDATE_SINGLE.EQ, LocalDate.of(y1, m1, d1));
//		// 해당 월 검색
//		int y2 = 2017; int m2 = 12;
//		Condition<LocalDate> cld2 = Condition.of("period", COND_LOCALDATE_SINGLE.MONTH_IN, LocalDate.of(y2, m2, 1));
//		// 해당 년 검색
//		int y3 = 2017;
//		Condition<LocalDate> cld3 = Condition.of("period", COND_LOCALDATE_SINGLE.YEAR_IN, LocalDate.of(y3, 1, 1));
//		// 해당 일 까지 검색
//		Condition<LocalDate> cld4 = Condition.of("period", COND_LOCALDATE_SINGLE.BEFORE, LocalDate.of(2017, 12, 18));
//		// 해당 일 부터 검색
//		Condition<LocalDate> cld5 = Condition.of("period", COND_LOCALDATE_SINGLE.AFTER, LocalDate.of(2017, 12, 16));
//		bookMapList = bookDAO.selectAllByCond(cld1, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cld2, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cld3, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cld4, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cld5, "no", ORDER.ASC);
		// LocalDateTime
		// 해당 시각 검색 (무의미)
		// 해당 초 검색 (무의미?)
//		Condition<LocalDate> cldt1 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.EQ, LocalDate.of());
		// 해당 분 검색 (무의미?)
//		Condition<LocalDate> cldt2 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.EQ, LocalDate.of());
		// 해당 시 검색
		Condition<LocalDate> cldt3 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.EQ, LocalDate.of());
		// 해당 일 검색
		Condition<LocalDate> cldt4 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.EQ, LocalDate.of());
		// 해당 월 검색
		// 해당 년 검색
		// 해당 시각 까지 검색 (기준 어떻게? - 초)
		// 해당 시각 부터 검색 (기준 어떻게? - 초)
		
		
		
		
		
//		bookMapList = bookDAO.selectAllByCond("name", false, "윤성우의 열혈 C++ 프로그래밍", "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond("name", true, "Java", "no", ORDER.ASC);
		
//		bookMapList = bookDAO.selectAllByCond("price", false, 111, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond("price", true, 350, "no", ORDER.ASC);
		
//		// TODO: LocalDate 대소비교
//		bookMapList = bookDAO.selectAllByCond("period", false, LocalDate.of(2017, 12, 16), "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond("period", true, LocalDate.of(2017, 12, 18), "no", ORDER.ASC);
		
//		// TODO: LocalDateTime 대소비교
//		bookMapList = bookDAO.selectAllByCond("brwDay", false, LocalDateTime.of(2017, 12, 20, 22, 49, 33, 188000000) , "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond("brwDay", true, LocalDateTime.of(2017, 12, 20, 22, 49), "no", ORDER.ASC);
		
		
		/*
		*//***** BookDAO test *****//*
		// make book instance and test
		Book b1 = new Book(1, 513144, "윤성우의 열혈 C 프로그래밍", "윤성우", 30000,
				"오렌지미디어", LocalDate.now(), "문적원1", "2_yoon_sung_woo_c.jpg",
				"전양훈", LocalDateTime.now(), LocalDate.now());
		Book b2 = new Book(2, 513143, "윤성우의 열혈 Java 프로그래밍", "윤성우", -1);
		b1.editInfo(513145, "윤성우의 열혈 C++ 프로그래밍", "윤성우", 35000, "오렌지미디어", LocalDate.now(), "문적원2", "2_yoon_sung_woo_c++.jpg");
		b2.lend("전양훈");
		b2.turnIn();
		System.out.println(b1.toString() + "\n" + b2.toString()+ "\n");
		//-- make book instance and test
		BookDAO bookDAO = new BookDAO();
		List<Map<String, Object>> bookMapList;
		List<Book> bookVOList;
		Book editedBook = new Book(-1, 523144, "윤성우의 열혈 C# 프로그래밍", "미상", 50000);
		*//** Insert a Book **//*
		bookDAO.insert(b1);
//		bookDAO.insert(b2);
		*//** Select all Books **//*
		bookMapList = bookDAO.selectAll();
//		for(int i = 0; i < bookMapList.size(); i++) System.out.println(bookMapList.get(i).toString());
		// Map to VO
		Book book1 = DataConverter.mapToVO(Book.class, bookMapList.get(0));
//		System.out.println(book1.toString());
		// List<Map> to List<VO>
//		bookVOList = DataConverter.mapListToVOList(Book.class, bookMapList);
//		for(int i = 0; i < bookVOList.size(); i++) System.out.println(bookVOList.get(i).toString());
		*//** Select Books by Condition **//*
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
		*//** Update a Book by PK **//*
		bookDAO.updateAllByNo(178, editedBook);
		*//** Delete a Book by PK **//*
		bookDAO.deleteByNo(178);
		*/
		
		
		/** Exit Program **/
//		DbManager.getInstance().getStatement().close();
//		DbManager.getInstance().getConn().close();
		DbManager.getInstance().close();
	}
}