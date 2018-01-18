package hbm.gui;

import java.io.IOException;
import java.util.function.Predicate;

import hbm.Main;
import hbm.util.Validation;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class PopupAfterChange<T> implements ChangeListener<T> {

	private Control control;
	private Popup popup;
	private Predicate<T> predicate;
	private String popupText;
	
	public PopupAfterChange(Control control, Predicate<T> predicate, String popupText) {
		this.control = control;
		this.popup = null;
		this.predicate = predicate;
		this.popupText = popupText;
//		System.out.println("control: " + control + ", popup: " + popup + ", predicate: " + predicate);
	}

	@Override
	public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
		// Validation 유효
		if(predicate.test(newValue)) {
			if(popup != null)
				popup.hide();
		}
		// Validation 무효
		else {
			if(popup == null) {
				popup = new Popup();
				popup.setAutoFix(false);
				popup.setAutoHide(true);
				try {
					popup.getContent().add(FXMLLoader.load(Main.class.getResource("/hbm/view/Popup.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Bounds localBounds = control.localToScreen(control.getBoundsInLocal());
					double x = localBounds.getMinX();
					double y = localBounds.getMinY() + control.getHeight();
					
					((Text) popup.getScene().getRoot().lookup("#popupText")).setText(popupText);
					
					
					
					popup.setX(x);
					popup.setY(y);
					popup.show(Main.getPrimaryStage().getScene().getWindow());
				}
			});
		}
	}

}
