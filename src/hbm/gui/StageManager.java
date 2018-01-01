package hbm.gui;

import java.io.IOException;

import hbm.Main;
import hbm.util.Properties;
import hbm.util.Properties.OS_NAME;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class StageManager {
	
	public enum STAGE {
		LOGIN("LoginView", 450, 600, "HBM - 로그인"),
		JOIN("JoinView", 450, 600, "HBM - 회원가입");
		private String fxmlURL;
		private double width;
		private double height;
		private String title;
		private String cssURL;
		private STAGE(String f, int w, int h, String t, String c) {
			this.fxmlURL = f;
			this.width = w;
			this.height = h;
			this.title = t;
			this.cssURL = c;
		}
		private STAGE(String f, int w, int h, String t) { this(f, w, h, t, ""); }
		private String getFxmlURL() { return this.fxmlURL; }
		public double getWidth() { return this.width; }
		public double getHeight() { return this.height; }
		public String getTitle() { return this.title; }
		public String getCssURL() { return this.cssURL; }
	}

	private final static String FXML_PATH = "/hbm/view/";
	private final static String CSS_PATH = "/hbm/css/";
	
	
	/**
	 * Change stage to STAGE parameter
	 * @param _STAGE enum value of STAGE
	 */
	public static void changeStage(STAGE _STAGE) {
		Parent root = null;
		try {
			root = FXMLLoader.load(Main.class.getResource(FXML_PATH + _STAGE.getFxmlURL() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, _STAGE.getWidth(), _STAGE.getHeight());
		if(!_STAGE.getCssURL().equals(""))
			scene.getStylesheets().add(Main.class.getResource(CSS_PATH + _STAGE.getCssURL()).toExternalForm() + ".css");
		Main.getPrimaryStage().setTitle(_STAGE.getTitle());
		if(Properties.getInstance().getOsName().equals(OS_NAME.LINUX)) {
//			Main.getPrimaryStage().hide();
			Main.getPrimaryStage().close();		// TODO: use other function to prevent memory leak
		}
//		Main.getPrimaryStage().setScene(null);	// TODO: use other function to prevent memory leak
		Main.getPrimaryStage().setScene(scene);
//		Main.getPrimaryStage().show();
	}
	
}
