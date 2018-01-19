package hbm.gui;

import java.io.IOException;

import hbm.Main;
import hbm.controller.main.MainController;
import hbm.controller.main.VisitorDetailController;
import hbm.util.wrapper.Controller;
import hbm.visitor.Visitor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ViewManager {
	
	public enum VIEW {
		VISITOR_DETAIL("main/MainVisitorDetail", 800.0);
		private String fxmlURL;
		private double maxWidth;
		private VIEW(String fxmlURL, double maxWidth) {
			this.fxmlURL = fxmlURL;
			this.maxWidth = maxWidth;
		}
		private String getFxmlURL() { return this.fxmlURL; }
		public double getMaxWidth() { return this.maxWidth; }
	}

	private final static String FXML_PATH = "/hbm/view/";
	private final static String CSS_PATH = "/hbm/css/";
	
	
	// 
	public static void showView(VIEW view, Pane desPane, Controller srcController, Object controllerParam) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_PATH + view.getFxmlURL() + ".fxml"));
		Parent root = null;
		
		if(view.equals(VIEW.VISITOR_DETAIL)) {
			fxmlLoader.setControllerFactory(c -> new VisitorDetailController((MainController) srcController, (Visitor) controllerParam));

			// css
//			if(!view.getCssURL().equals(""))
//				root.getStylesheets().add(Main.class.getResource(CSS_PATH + view.getCssURL()).toExternalForm() + ".css");
			
			try {
				root = (Parent) fxmlLoader.load();
				((VBox) desPane).getChildren().clear();
				((VBox) desPane).setMaxWidth(view.getMaxWidth());
				((VBox) desPane).getChildren().add(root);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
