package hbm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hbm.visitor.Visitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class VisitorDetailController implements Initializable {

	// FXML Member
//	@FXML private 
	@FXML private Button btnCloseVisitorDetail;
	@FXML private Text txtVisitorDetailNo;
//	@FXML private TextField tfJoinId;
//	@FXML private Button btnCheckJoinId;
//	@FXML private Button btnReCheckJoinId;
//	@FXML private Text txtJoinIdDesc;
//	@FXML private PasswordField pfJoinPw;
//	@FXML private Text txtJoinPwDesc;
//	@FXML private PasswordField pfJoinPwConf;
//	@FXML private Text txtJoinPwConfDesc;
//	@FXML private TextField tfJoinLName;
//	@FXML private TextField tfJoinFName;
//	@FXML private Text txtJoinNameDesc;
//	@FXML private DatePicker dpJoinBirth;
//	@FXML private Text txtJoinBirthDesc;
//	@FXML private TextField tfJoinEmail;
//	@FXML private Text txtJoinEmailDesc;
//	@FXML private TextField tfJoinPhone;
//	@FXML private Text txtJoinPhoneDesc;
//
//	@FXML private ImageView ivJoinImgFile;
//	@FXML private Button btnJoinImgFile;
//	@FXML private Text txtJoinImgFileDesc;
//	@FXML private TextField tfJoinHPhone;
//	@FXML private Text txtJoinHPhoneDesc;
//	@FXML private TextField tfJoinAddr;
//	@FXML private Text txtJoinAddrDesc;
//	@FXML private TextField tfJoinComp;
//	@FXML private Text txtJoinCompDesc;
//	@FXML private TextField tfJoinPos;
//	@FXML private Text txtJoinPosDesc;
	
	// 
	private MainController parentController;
	private Visitor visitor;
//	private VisitorDetailService visitorDetailService;
	
	public VisitorDetailController(MainController parentController, Visitor visitor) {
		this.parentController = parentController;
		this.visitor = visitor;
		System.out.println("===== VisitorDetailController() =====\nvisitor: " + visitor);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		this.visitorDetailService = new VisitorDetailService(this);
		
		// set Visitor Detail
		this.txtVisitorDetailNo.setText(String.valueOf(this.visitor.getNo()));
		
	}
	
	// 
	public void closeVisitorDetail(ActionEvent e) {
		this.parentController.getvBoxVisitorDetail().setMaxWidth(0.0);
		this.parentController.getvBoxVisitorDetail().getChildren().clear();
	}
	
	
	// 아이디 사용 가능 확인
	public void checkJoinId(ActionEvent e) {
		
	}
	// 사진 파일 선택
	public void chooseJoinImgFile(ActionEvent e) {
		
	}
	// 회원 가입
	public void join(ActionEvent e) {
		
	}
	
}
