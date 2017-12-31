package hbm.controller;

import hbm.gui.StageManager;
import hbm.gui.StageManager.STAGE;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class JoinController {

	// FXML member
	@FXML private TextField tfJoinId;
	@FXML private PasswordField pfJoinPw;
	@FXML private TextField tfJoinName;
	@FXML private TextField tfJoinBirth;
	@FXML private TextField tfJoinEmail;
	@FXML private TextField tfJoinPhone;
	
	public void checkJoinId(ActionEvent e) {
		
	}

	public void goBack(ActionEvent e) {
		StageManager.changeStage(STAGE.LOGIN);
	}
	
	public void join(ActionEvent e) {
		
	}
	
}
