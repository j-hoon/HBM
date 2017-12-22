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
import hbm.util.db.sql.Condition.COND_METHOD;
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

		
		
		BookDAO bookDAO = new BookDAO();
		List<Map<String, Object>> bookMapList;
//		bookMapList = bookDAO.selectAll();
		
//		bookMapList = bookDAO.selectAllByCond("name", false, "윤성우의 열혈 C++ 프로그래밍", "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond("name", true, "Java", "no", ORDER.ASC);
		
		// TODO: int 대소비교
		// test Condition<T>
//		Condition<T> c1 = new Condition<>(Condition.METHOD.BIG_EX, condValue);
//		Condition<T> c2 = new Condition<>(Condition.METHOD.LIKE, condValue);
		Condition<Integer> c1 = Condition.of(COND_METHOD.BIG_EX, 100);
//		Condition<Integer> c2 = Condition.of(COND_METHOD.LIKE, 110);
		Condition<Integer> c3 = Condition.of(COND_METHOD.BETWEEN_EX_IN, 100, 200);
		Condition<Integer> c4 = Condition.of(COND_METHOD.BIG_IN, 100, 200);
		System.out.println("c1: " + c1.getMethod() + ", " + c1.getVal1() + ", " + c1.getVal2());
//		System.out.println("c2: " + c2.getMethod() + ", " + c2.getVal1() + ", " + c2.getVal2());
		System.out.println("c3: " + c3.getMethod() + ", " + c3.getVal1() + ", " + c3.getVal2());
//		System.out.println("c4: " + c4.getMethod() + ", " + c4.getVal1() + ", " + c4.getVal2());
		
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