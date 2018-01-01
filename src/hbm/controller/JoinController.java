package hbm.controller;

import hbm.gui.StageManager;
import hbm.gui.StageManager.STAGE;
import hbm.visitor.VisitorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class JoinController {

	// FXML member
	@FXML private TextField tfJoinId;
	@FXML private Button btnCheckJoinId;
	@FXML private Button btnReCheckJoinId;
	@FXML private PasswordField pfJoinPw;
	@FXML private TextField tfJoinName;
	@FXML private TextField tfJoinBirth;
	@FXML private TextField tfJoinEmail;
	@FXML private TextField tfJoinPhone;
	@FXML private Text txtJoinStatus;
	
	public void checkJoinId(ActionEvent e) {
		VisitorDAO visitorDAO = new VisitorDAO();
		boolean isVaild = visitorDAO.isVaildId(tfJoinId.getText());
		if(isVaild) {
			tfJoinId.setDisable(true);
			btnCheckJoinId.setVisible(false);
			btnReCheckJoinId.setVisible(true);
			txtJoinStatus.setText("사용가능한 아이디 입니다.");
		} else {
			txtJoinStatus.setText("이미 사용중인 아이디 입니다.");
		}
	}
	
	public void reCheckJoinId(ActionEvent e) {
		tfJoinId.setDisable(false);
		btnCheckJoinId.setVisible(true);
		btnReCheckJoinId.setVisible(false);
	}

	public void goBackToLogin(ActionEvent e) {
		StageManager.changeStage(STAGE.LOGIN);
	}
	
	public void join(ActionEvent e) {
		
	}
	
}
