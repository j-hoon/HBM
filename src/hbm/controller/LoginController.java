package hbm.controller;

import hbm.db.sql.Condition;
import hbm.db.sql.Order;
import hbm.db.sql.Condition.COND_STRING_SINGLE;
import hbm.db.sql.Order.ORDER;
import hbm.gui.StageManager;
import hbm.gui.StageManager.VIEW;
import hbm.util.Debug;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController /*implements Initializable */{
	
	// FXML member
	@FXML private TextField tfLoginId;
	@FXML private PasswordField pfLoginPw;
	@FXML private Text txtLoginStatus;

	
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		System.out.println("location: " + location);
//		System.out.println("resources: " + resources);
//	}
	
	/**
	 * Join
	 */
	public void goToJoin(ActionEvent e) {
		StageManager.changeStage(VIEW.JOIN);
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
            Visitor visitor = visitorDAO.login(tfLoginId.getText(), pfLoginPw.getText());
            Debug.show("Visitor: " + visitor);
            
            if(visitor != null) {
            	Debug.show("로그인 성공!");
        		StageManager.changeStage(VIEW.MAIN, visitor);
            } else {
            	Debug.show("로그인 실패.");
            }
            
		} else {
			System.err.println("ID와 PW를 입력해 주세요.");
			txtLoginStatus.setText("ID와 PW를 입력해 주세요.");
		}
	}
	
}
