package hbm.gui;

import java.io.IOException;

import hbm.Main;
import hbm.controller.MainController;
import hbm.util.Properties;
import hbm.util.Properties.OS_NAME;
import hbm.visitor.Visitor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class StageManager {
	
	public enum VIEW {
		LOGIN("LoginView", "HBM - 로그인", false),
		JOIN("JoinView", "HBM - 회원가입", false),
		MAIN("MainView", "HBM", true);
		private String fxmlURL;
		private String title;
		private boolean resizable;
		private String cssURL;
		private VIEW(String fxmlURL, String title, boolean resizable, String cssURL) {
			this.fxmlURL = fxmlURL;
			this.title = title;
			this.resizable = resizable;
			this.cssURL = cssURL;
		}
		private VIEW(String fxmlURL, String title, boolean resizable) { this(fxmlURL, title, resizable, ""); }
		private String getFxmlURL() { return this.fxmlURL; }
		public String getTitle() { return this.title; }
		public String getCssURL() { return this.cssURL; }
		public boolean isResizable() { return this.resizable; }
	}

	private final static String FXML_PATH = "/hbm/view/";
	private final static String CSS_PATH = "/hbm/css/";
	
	
	/**
	 * Change stage to VIEW parameter
	 * @param view enum value of VIEW
	 */
	public static void changeStage(VIEW view) {
		Parent root = null;
		try {
			root = FXMLLoader.load(Main.class.getResource(FXML_PATH + view.getFxmlURL() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		if(!view.getCssURL().equals(""))
			scene.getStylesheets().add(Main.class.getResource(CSS_PATH + view.getCssURL()).toExternalForm() + ".css");
		Main.getPrimaryStage().setResizable(view.isResizable());
		Main.getPrimaryStage().setTitle(view.getTitle());
		if(Properties.getInstance().getOsName().equals(OS_NAME.LINUX)) {
			// TODO: use other function to prevent memory leak
			Main.getPrimaryStage().close();
			Main.getPrimaryStage().show();
		}
		Main.getPrimaryStage().setScene(scene);
		Main.getPrimaryStage().centerOnScreen();
	}
	public static void changeStage(VIEW view, Object controllerParam) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_PATH + view.getFxmlURL() + ".fxml"));
		Parent root = null;
		
		if(view.equals(VIEW.MAIN))
			fxmlLoader.setControllerFactory(c -> new MainController((Visitor) controllerParam));
//		else if()
//			
	
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		if(!view.getCssURL().equals(""))
			scene.getStylesheets().add(Main.class.getResource(CSS_PATH + view.getCssURL()).toExternalForm() + ".css");
		Main.getPrimaryStage().setResizable(view.isResizable());
		Main.getPrimaryStage().setTitle(view.getTitle());
		if(Properties.getInstance().getOsName().equals(OS_NAME.LINUX)) {
			// TODO: use other function to prevent memory leak
			Main.getPrimaryStage().close();
			Main.getPrimaryStage().show();
		}
		Main.getPrimaryStage().setScene(scene);
		Main.getPrimaryStage().centerOnScreen();
	}
	
}
