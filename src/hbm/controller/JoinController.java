package hbm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hbm.Main;
import hbm.gui.StageManager;
import hbm.gui.StageManager.STAGE;
import hbm.service.JoinService;
import hbm.util.Validation;
import hbm.visitor.VisitorDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class JoinController implements Initializable {

	// FXML Member
	@FXML private TextField tfJoinId;
	@FXML private Button btnCheckJoinId;
	@FXML private Button btnReCheckJoinId;
	@FXML private PasswordField pfJoinPw;
	@FXML private TextField tfJoinName;
//	@FXML private TextField tfJoinBirth;
	@FXML private DatePicker dpJoinBirth;
	@FXML private TextField tfJoinEmail;
	@FXML private TextField tfJoinPhone;
	@FXML private Text txtJoinStatus;
	
	// 
	private JoinService joinService;
	private Popup popup;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		joinService = new JoinService(this);
		
		// TODO: Popup 멤버 유지하는 클래스로 추출?
		tfJoinId.textProperty().addListener((observable, oldValue, newValue) -> {
			if(Validation.checkId(newValue)) {
				if(popup != null)
					popup.hide();
			} else {
				if(popup == null) {
					popup = new Popup();
					try {
						popup.getContent().add(FXMLLoader.load(Main.class.getResource("/hbm/view/Popup.fxml")));
					} catch (IOException e) {
						e.printStackTrace();
					}
					Bounds localBounds = tfJoinId.localToScreen(tfJoinId.getBoundsInLocal());
					double x = localBounds.getMinX();
					double y = localBounds.getMinY() + tfJoinId.getHeight();
					popup.setAutoHide(true);
					popup.show(Main.getPrimaryStage(), x, y);
				}
				else if(!popup.isShowing()) {
					Bounds localBounds = tfJoinId.localToScreen(tfJoinId.getBoundsInLocal());
					double x = localBounds.getMinX();
					double y = localBounds.getMinY() + tfJoinId.getHeight();
					popup.show(Main.getPrimaryStage(), x, y);
				}
			}
		});
		
	}
	
	// 
	public void checkJoinId(ActionEvent e) {
		tfJoinId.setDisable(true);			// 아이디 필드 잠금
		btnCheckJoinId.setDisable(true);	// 확인 버튼 잠금
		// CheckJoinIdService 초기화 (id 전달)
		joinService.setCheckJoinId(tfJoinId.getText());
		// CheckJoinIdService 실행
		if(!joinService.getCheckJoinId().isRunning()) {
			joinService.getCheckJoinId().restart();
		}
	}
	
	// 
	public void reCheckJoinId(ActionEvent e) {
		tfJoinId.setDisable(false);			// 아이디 필드 잠금 해제
		btnCheckJoinId.setDisable(false);	// 확인 버튼 잠금 해제
		btnReCheckJoinId.setVisible(false);	// 재확인 버튼 숨기기
		btnCheckJoinId.setVisible(true);	// 확인 버튼 보이기
		tfJoinId.requestFocus();			// 아이디 필드로 포커스 변경
		txtJoinStatus.setText("아이디를 다시 입력해주세요.");
	}

	// 
	public void goBackToLogin(ActionEvent e) {
		StageManager.changeStage(STAGE.LOGIN);
	}
	
	// 
	public void join(ActionEvent e) {
		
	}
	
	// 
	public void onActionJoinId(ActionEvent e) {
		System.out.println("onActionJoinId");
	}


	// Getter
	public TextField getTfJoinId() {
		return tfJoinId;
	}
	public Button getBtnCheckJoinId() {
		return btnCheckJoinId;
	}
	public Button getBtnReCheckJoinId() {
		return btnReCheckJoinId;
	}
	public PasswordField getPfJoinPw() {
		return pfJoinPw;
	}
	public TextField getTfJoinName() {
		return tfJoinName;
	}
	public DatePicker getDpJoinBirth() {
		return dpJoinBirth;
	}
	public TextField getTfJoinEmail() {
		return tfJoinEmail;
	}
	public TextField getTfJoinPhone() {
		return tfJoinPhone;
	}
	public Text getTxtJoinStatus() {
		return txtJoinStatus;
	}
	public JoinService getJoinService() {
		return joinService;
	}
	
}
