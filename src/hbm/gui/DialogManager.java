package hbm.gui;

import java.io.IOException;

import hbm.Main;
import hbm.controller.main.MainController;
import hbm.controller.main.VisitorDetailController;
import hbm.gui.StageManager.STAGE;
import hbm.visitor.Visitor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogManager {
	
	public enum DIALOG {
		VISITOR_DETAIL("VisitorDetailDialog", "사용자 상세 정보");
		private String fxmlName;
		private String title;
		private String cssName;
		private DIALOG(String fxmlName, String title, String cssName) {
			this.fxmlName = fxmlName;
			this.title = title;
			this.cssName = cssName;
		}
		private DIALOG(String fxmlName, String title) { this(fxmlName, title, ""); }
		private String getFxmlName() { return this.fxmlName; }
		public String getTitle() { return this.title; }
		public String getCssName() { return this.cssName; }
	}

	private final static String FXML_PATH = "/hbm/view/";
	private final static String CSS_PATH = "/hbm/css/";
	
	
	public static void show(Event event, DIALOG dialog, Modality modality, Object controllerParam) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_PATH + dialog.getFxmlName() + ".fxml"));
		Parent root = null;
		Stage stage = new Stage();
		
//		if(dialog.equals(DIALOG.VISITOR_DETAIL))
//			fxmlLoader.setControllerFactory(c -> new VisitorDetailController((Visitor) controllerParam));
//		else if()
//			
		
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		if() {	// css
//			
//		}
		stage.setScene(new Scene(root));
		stage.setTitle(dialog.getTitle());
		stage.initModality(modality);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.show();
	}
}
