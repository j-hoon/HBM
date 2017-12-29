package hbm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hbm.book.Book;
import hbm.book.BookDAO;
import hbm.gui.GuiManager;
import hbm.util.DataConverter;
import hbm.util.db.DbManager;
import hbm.util.db.sql.Condition;
import hbm.util.db.sql.Condition.*;
import hbm.util.db.sql.Order;
import hbm.util.db.sql.Order.ORDER;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDAO;


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
//		Visitor v1 = new Visitor();
//		Visitor v2 = new Visitor(1, '3', "test1", "1234", "김철수", 910301, "test@naver.com", 1012345678);
//		Visitor v3 = new Visitor(2, '3', "test2", "4567", "박영희", 930920, "test@gmail.com", 1045678901, "pyh.jpg", 515017619, "부산시 남구 대연동", "부경대학교", "컴퓨터공학과");
//		System.out.println(v1 + "\n" + v2 + "\n" + v3 + "\n");
		//---- VisitorDAO test -----/
		

		/***** All Condition<T> test *****/
//		BookDAO bookDAO = new BookDAO();
//		List<Map<String, Object>> bookMapList;
//		bookMapList = bookDAO.selectAll();
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
//		// 해당 시각 검색 (무의미)
//		// 해당 초 검색 (무의미?)
//		Condition<LocalDateTime> cldt1 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.SEC_IN, LocalDateTime.of(2017, 12, 20, 19, 47, 18));
//		// 해당 분 검색 (무의미?)
//		Condition<LocalDateTime> cldt2 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.MIN_IN, LocalDateTime.of(2017, 12, 20, 19, 48));
//		// 해당 시 검색
//		Condition<LocalDateTime> cldt3 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.HOUR_IN, LocalDateTime.of(2017, 12, 20, 19, 0));
//		// 해당 일 검색
//		Condition<LocalDateTime> cldt4 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.DAY_IN, LocalDateTime.of(2017, 12, 20, 0, 0));
//		// 해당 월 검색
//		Condition<LocalDateTime> cldt5 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.MONTH_IN, LocalDateTime.of(2017, 12, 10, 0, 0));
//		// 해당 년 검색
//		Condition<LocalDateTime> cldt6 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.YEAR_IN, LocalDateTime.of(2017, 1, 1, 0, 0));
//		// 해당 일 까지 검색
//		Condition<LocalDateTime> cldt7 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.BEFORE, LocalDateTime.of(2017, 12, 20, 19, 47, 18));
//		// 해당 일 부터 검색
//		Condition<LocalDateTime> cldt8 = Condition.of("brwDay", COND_LOCALDATETIME_SINGLE.AFTER, LocalDateTime.of(2017, 12, 20, 19, 47, 18));
//		bookMapList = bookDAO.selectAllByCond(cldt1, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt2, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt3, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt4, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt5, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt6, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt7, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cldt8, "no", ORDER.ASC);
		// Multi Condition
		// int
//		Condition<Integer> cMi1 = Condition.of("price", COND_INT_MULTI.BETWEEN_EX_EX, 110, 35000);
//		Condition<Integer> cMi2 = Condition.of("price", COND_INT_MULTI.BETWEEN_IN_EX, 111, 35000);
//		Condition<Integer> cMi3 = Condition.of("price", COND_INT_MULTI.BETWEEN_EX_IN, 110, 35000);
//		Condition<Integer> cMi4 = Condition.of("price", COND_INT_MULTI.BETWEEN_IN_IN, -1, 34999);
//		bookMapList = bookDAO.selectAllByCond(cMi1, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cMi2, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cMi3, "no", ORDER.ASC);
//		bookMapList = bookDAO.selectAllByCond(cMi4, "no", ORDER.ASC);
		// LocalDate
//		// 해당 날짜 사이의 모든 데이터 검색
//		Condition<LocalDate> cldM1 = Condition.of("period", COND_LOCALDATE_MULTI.BETWEEN, LocalDate.of(2017, 12, 17), LocalDate.of(2017, 12, 18));
//		bookMapList = bookDAO.selectAllByCond(cldM1, "no", ORDER.ASC);
		// LocalDateTime
//		// 해당 시각 사이의 모든 데이터 검색
//		Condition<LocalDateTime> cldtM1 = Condition.of("brwDay", COND_LOCALDATETIME_MULTI.BETWEEN, LocalDateTime.of(2017, 12, 18, 00, 17, 42, 845),
//				LocalDateTime.of(2017, 12, 20, 19, 48, 14));
//		bookMapList = bookDAO.selectAllByCond(cldtM1, "no", ORDER.ASC);
		//---- All Condition<T> test -----/
		
		
		
		/***** BookDAO Usage *****/
//		/** local variables **/
//		BookDAO bookDAO = new BookDAO();
//		List<Map<String, Object>> bookMapList;
//		/** Insert a Book **/
//		Book b1 = new Book(-1, 613144, "윤성우의 열혈 C 프로그래밍", "윤성우", 40000,
//				"오렌지미디어", LocalDate.now(), "문적원1", "2_yoon_sung_woo_c.jpg",
//				"전양훈", LocalDateTime.now(), LocalDate.now());
//		bookDAO.insert(b1);
//		/** Select All **/
//		bookMapList = bookDAO.selectAll();
//		/** Select by Condition with Order **/
//		Condition<Integer> condSI1 = Condition.of("price", COND_INT_SINGLE.EQ, 35000);
//		Order ordSI1 = Order.of("no", ORDER.ASC);
//		Condition<LocalDate> condMLD1 = Condition.of("period", COND_LOCALDATE_MULTI.BETWEEN, LocalDate.of(2017, 12, 17), LocalDate.of(2017, 12, 18));
//		Order ordMLD1 = Order.of("no", ORDER.DESC);
//		bookMapList = bookDAO.selectAllByCond(condSI1, ordSI1);
//		bookMapList = bookDAO.selectAllByCond(condMLD1, ordMLD1);
//		/** Update a Book **/
//		Book b2 = new Book(-1, 713144, "윤성우의 열혈 Python 프로그래밍", "윤성우", 37000,
//				"오렌지미디어", LocalDate.now(), "문적원3", "yoon_sung_woo_python.jpg",
//				"김영희", LocalDateTime.now(), LocalDate.now());
//		bookDAO.updateAllByCond(b2, Condition.of("no", COND_INT_SINGLE.EQ, 229));
//		/** Delete a Book **/
//		bookDAO.deleteByCond(Condition.of("no", COND_INT_SINGLE.EQ, 230));
		//---- BookDAO Usage -----/
		

		/***** VisitorDAO Usage *****/
//		/** local variables **/
//		VisitorDAO visitorDAO = new VisitorDAO();
//		List<Map<String, Object>> visitorMapList;
//		/** Insert a Visitor **/
//		Visitor v1 = new Visitor(1, '3', "test1", "1234", "김철수", "910301".toCharArray(), "test@naver.com", "012345678".toCharArray());
//		Visitor v2 = new Visitor(2, '3', "test2", "4567", "박영희", "930920".toCharArray(), "test@gmail.com", "04567890".toCharArray(),
//				"pyh.jpg", "515017619".toCharArray(), "부산시 남구 대연동", "부경대학교", "컴퓨터공학과");
//		System.out.println(v1 + "\n" + v2);
//		visitorDAO.insert(v1);
//		visitorDAO.insert(v2);
//		/** Select All **/
//		visitorMapList = visitorDAO.selectAll();
//		/** Select by Condition with Order **/
//		Condition<Integer> condVistorSI1 = Condition.of("no", COND_INT_SINGLE.EQ, 13);
//		Order ordVisitorSI1 = Order.of("no", ORDER.ASC);
//		visitorMapList = visitorDAO.selectAllByCond(condVistorSI1, ordVisitorSI1);
//		visitorMapList = visitorDAO.selectAllByCond(Condition.of("name", COND_STRING_SINGLE.LIKE, "철"), Order.of("no", ORDER.DESC));
//		/** Update a Visitor **/
//		Visitor v3 = new Visitor(-1, '5', "test1", "1234", "김철수", "910301".toCharArray(), "test@naver.com", "012345678".toCharArray(),
//				"kcs.jpg", "515027916".toCharArray(), "부산시 사상구 모라동", "집", "집");
//		visitorDAO.updateAllByCond(v3, Condition.of("no", COND_INT_SINGLE.EQ, 16));
//		/** Delete a Visitor **/
//		visitorDAO.deleteByCond(Condition.of("no", COND_INT_SINGLE.EQ, 14));
		//---- VisitorDAO Usage -----/
		
		
		
		
		
		
		/** Exit Program **/
//		DbManager.getInstance().getStatement().close();
//		DbManager.getInstance().getConn().close();
//		DbManager.getInstance().close();
	}
}