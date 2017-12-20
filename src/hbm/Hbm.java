package hbm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import hbm.book.Book;
import hbm.book.BookDAO;
import hbm.dao.TestTableDAO;
import hbm.gui.GuiManager;
import hbm.util.db.DbManager;
import hbm.util.db.sql.DataConverter;
import hbm.util.db.sql.SqlFactory;
import hbm.vo.TestTable;

public class Hbm {
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		System.out.println("=====  HBM Started!  =====");
		
		// HBM GuiManager
		@SuppressWarnings("unused")
		GuiManager gm = new GuiManager();
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
		/** Insert a Book **/
		BookDAO bookDAO = new BookDAO();
		List<Map<String, Object>> bookList;
//		System.out.println("bookDAO.insert(b1): " + bookDAO.insert(b1));
//		System.out.println("bookDAO.insert(b2): " + bookDAO.insert(b2));
		/** Select all Books **/
		bookList = bookDAO.selectAll();
//		System.out.println(bookList.toString());
//		Book book1 = DataConverter.mapToObject(bookList.get(0), new Book());	// TODO 컬렉션 기반으로 변경
//		System.out.println("book(0): " + book1.toString());
//		Book book2 = (Book) DataConverter.mapToObject(bookList.get(1), new Book());	// TODO 컬렉션 기반으로 변경
//		System.out.println("book(1): " + book2.toString());
//		Book book3 = (Book) DataConverter.mapToObject(bookList.get(2), new Book());	// TODO 컬렉션 기반으로 변경
//		System.out.println("book(2): " + book3.toString());
		/** Select Books by Condition **/
		bookList = bookDAO.selectAllByName("윤성우", true, SqlFactory.COL_TYPE.STRING, "name", SqlFactory.ORDER.ASC);
		for(int i = 0; i < bookList.size(); i++) System.out.println(bookList.get(i).toString());
		bookList = bookDAO.selectAllByName("윤성우", true, SqlFactory.COL_TYPE.INT, "no", SqlFactory.ORDER.ASC);
		for(int i = 0; i < bookList.size(); i++) System.out.println(bookList.get(i).toString());
		bookList = bookDAO.selectAllByName("윤성우", true, SqlFactory.COL_TYPE.LOCAL_DATE, "pubDay", SqlFactory.ORDER.ASC);
		for(int i = 0; i < bookList.size(); i++) System.out.println(bookList.get(i).toString());
		bookList = bookDAO.selectAllByName("윤성우", true, SqlFactory.COL_TYPE.LOCAL_DATE_TIME, "brwDay", SqlFactory.ORDER.ASC);
		for(int i = 0; i < bookList.size(); i++) System.out.println(bookList.get(i).toString());
		/** Update a Book by Condition **/
//		b1.setAuthor("수정된 저자");
//		bookDAO.updateAllByNo("no", 178, b1);
		
		

//		// select with DAO
//		TestTableDAO testTableDAO = new TestTableDAO();
//		List<Map<String, Object>> testTableList = testTableDAO.selectAll();
//		System.out.println(testTableList.toString());
//		TestTable testTable = (TestTable) DataConverter.mapToObject(testTableList.get(0), new TestTable());	// TODO 컬렉션 기반으로 변경
//		System.out.println("testTable.getNo(): " + testTable.getNo());
//		System.out.println("testTable.getData(): " + testTable.getData());
		
		
		/** Run SQL **/
//		System.out.println("statement: " + statement);
//		int no;
//		String data;
//		String sql;
		// insert
//		no = 12;
//		data = "data";
//		sql = "INSERT INTO TEST_TABLE VALUES(" + no + ", '" + data + "')";
//		int retInsert = statement.executeUpdate(sql);
//		conn.commit();
//		System.out.println("retInsert: " + retInsert);	// 1 is commited.
//		// select
//		sql = "SELECT * FROM TEST_TABLE ORDER BY NO ASC";
//		ResultSet retSelect = statement.executeQuery(sql);
//		ResultSetMetaData resultSetMetaData = retSelect.getMetaData();
//		System.out.println("resultSetMetaData.getColumnCount(): " + resultSetMetaData.getColumnCount());
//		while(retSelect.next()) {
//			int retNo = retSelect.getInt(1);
//			String retData = retSelect.getString(2);
//			System.out.println("retSelect[" + retSelect.getRow() + "] no: " + retNo + ", data: " + retData);
//		}
//		// select with DAO
//		TestTableDAO testTableDAO = new TestTableDAO();
//		List<Map<String, Object>> testTableList = testTableDAO.selectAll();
//		System.out.println(testTableList.toString());
//		TestTable testTable = (TestTable) DataConverter.mapToObject(testTableList.get(0), new TestTable());	// TODO 컬렉션 기반으로 변경
//		System.out.println("testTable.getNo(): " + testTable.getNo());
//		System.out.println("testTable.getData(): " + testTable.getData());
		
		
		/** Exit Program **/
		DbManager.getInstance().getStatement().close();
		DbManager.getInstance().getConn().close();
	}
}