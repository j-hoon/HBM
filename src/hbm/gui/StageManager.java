package hbm.gui;

import java.io.IOException;

import hbm.Main;
import hbm.controller.main.MainController;
import hbm.util.Properties;
import hbm.util.Properties.OS_NAME;
import hbm.visitor.Visitor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class StageManager {
	
	public enum STAGE {
		LOGIN("LoginView", "HBM - 로그인", false),
		JOIN("JoinView", "HBM - 회원가입", false),
		MAIN("main/MainView", "HBM", true);
		private String fxmlURL;
		private String title;
		private boolean resizable;
		private String cssURL;
		private STAGE(String fxmlURL, String title, boolean resizable, String cssURL) {
			this.fxmlURL = fxmlURL;
			this.title = title;
			this.resizable = resizable;
			this.cssURL = cssURL;
		}
		private STAGE(String fxmlURL, String title, boolean resizable) { this(fxmlURL, title, resizable, ""); }
		private String getFxmlURL() { return this.fxmlURL; }
		public String getTitle() { return this.title; }
		public String getCssURL() { return this.cssURL; }
		public boolean isResizable() { return this.resizable; }
	}

	private final static String FXML_PATH = "/hbm/view/";
	private final static String CSS_PATH = "/hbm/css/";
	
	
	/**
	 * Change stage to VIEW parameter
	 * @param stage enum value of STAGE
	 */
	public static void changeStage(STAGE stage) {
		Parent root = null;
		try {
			root = FXMLLoader.load(Main.class.getResource(FXML_PATH + stage.getFxmlURL() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		if(!stage.getCssURL().equals(""))
			scene.getStylesheets().add(Main.class.getResource(CSS_PATH + stage.getCssURL()).toExternalForm() + ".css");
		Main.getPrimaryStage().setResizable(stage.isResizable());
		Main.getPrimaryStage().setTitle(stage.getTitle());
		if(Properties.getInstance().getOsName().equals(OS_NAME.LINUX)) {
			// TODO: use other function to prevent memory leak
			Main.getPrimaryStage().close();
			Main.getPrimaryStage().show();
		}
		Main.getPrimaryStage().setScene(scene);
		Main.getPrimaryStage().centerOnScreen();
	}
	public static void changeStage(STAGE stage, Object controllerParam) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_PATH + stage.getFxmlURL() + ".fxml"));
		Parent root = null;
		
		if(stage.equals(STAGE.MAIN))
			fxmlLoader.setControllerFactory(c -> new MainController((Visitor) controllerParam));
//		else if()
//			
	
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		if(!stage.getCssURL().equals(""))
			scene.getStylesheets().add(Main.class.getResource(CSS_PATH + stage.getCssURL()).toExternalForm() + ".css");
		Main.getPrimaryStage().setResizable(stage.isResizable());
		Main.getPrimaryStage().setTitle(stage.getTitle());
		if(Properties.getInstance().getOsName().equals(OS_NAME.LINUX)) {
			// TODO: use other function to prevent memory leak
			Main.getPrimaryStage().close();
			Main.getPrimaryStage().show();
		}
		Main.getPrimaryStage().setScene(scene);
		Main.getPrimaryStage().centerOnScreen();
	}
	
}
