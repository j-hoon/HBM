package hbm;
	
import hbm.gui.StageManager;
import hbm.gui.StageManager.VIEW;
import hbm.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage pStage;
	
	@Override
	public void start(Stage primaryStage) {
		// Set PrimaryStage
		setPrimaryStage(primaryStage);
		System.out.println(getPrimaryStage());
		
		// Set Current OS
		Properties.getInstance().setOsName();
		System.out.println(Properties.getInstance().getOsName());
		
		// Show LoginScene
		StageManager.changeStage(VIEW.LOGIN);
		getPrimaryStage().show();
	}
	
	/**
	 * Get Primary Stage of Application
	 * @return the unique Stage of Application
	 */
	public static Stage getPrimaryStage() {
		return Main.pStage;
	}
	
	private void setPrimaryStage(Stage pStage) {
		Main.pStage = pStage;
	}
	
	
	public static void main(String[] args) {
		launch();
		
		
		
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
//		// Convert MapList to VOList
//		List<Book> bookList = null;
//		try {
//			bookList = DataConverter.mapListToVOList(Book.class, bookMapList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(bookList.size());
//		for(Book b : bookList) {
//			System.out.println(b);
//		}
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
//		// Convert MapList to VOList
//		List<Visitor> visitorList = null;
//		try {
//			visitorList = DataConverter.mapListToVOList(Visitor.class, visitorMapList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(visitorList.size());
//		for(Visitor v : visitorList) {
//			System.out.println(v);
//		}
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
		
	}
}
