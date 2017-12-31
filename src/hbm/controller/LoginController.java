package hbm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hbm.db.sql.Condition;
import hbm.db.sql.Order;
import hbm.db.sql.Condition.COND_STRING_SINGLE;
import hbm.db.sql.Order.ORDER;
import hbm.gui.StageManager;
import hbm.gui.StageManager.STAGE;
import hbm.visitor.VisitorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController /*implements Initializable */{
	
	// FXML member
	@FXML private TextField tfLoginId;
	@FXML private PasswordField pfLoginPw;
//	@FXML private Button btnJoin;
//	@FXML private Button btnLogin;
	@FXML private Text txtLoginStatus;

	
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		System.out.println("location: " + location);
//		System.out.println("resources: " + resources);
//	}
	
	/**
	 * Join
	 */
	public void join(ActionEvent e) {
		StageManager.changeStage(STAGE.JOIN);
	}
	
	/**
	 * Login
	 */
	public void login(ActionEvent e) {
		txtLoginStatus.setText("Login...");
		
		if(!tfLoginId.getText().equals("") && !pfLoginPw.getText().equals("")) {
            System.out.println("tfLoginId: " + tfLoginId.getText() + ", pfLoginPw: " + pfLoginPw.getText());
            
            // Execute Select Query
            VisitorDAO visitorDAO = new VisitorDAO();
            visitorDAO.selectAllByCond(Condition.of("id", COND_STRING_SINGLE.EQ, tfLoginId.getText()), Order.of("no", ORDER.ASC));
            
		} else {
			System.err.println("ID와 PW를 입력해 주세요.");
			txtLoginStatus.setText("ID와 PW를 입력해 주세요.");
		}
	}
	
}
