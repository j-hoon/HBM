package hbm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hbm.Main;
import hbm.gui.AlertManager;
import hbm.gui.StageManager;
import hbm.gui.StageManager.VIEW;
import hbm.service.JoinService;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.util.Validation;
import hbm.util.Validation.Check;
import hbm.util.Validation.CheckPw;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDetail;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;

public class JoinController implements Initializable {

	// FXML Member
	@FXML private TextField tfJoinId;
	@FXML private Button btnCheckJoinId;
	@FXML private Button btnReCheckJoinId;
	@FXML private Text txtJoinIdDesc;
	@FXML private PasswordField pfJoinPw;
	@FXML private Text txtJoinPwDesc;
	@FXML private PasswordField pfJoinPwConf;
	@FXML private Text txtJoinPwConfDesc;
	@FXML private TextField tfJoinLName;
	@FXML private TextField tfJoinFName;
	@FXML private Text txtJoinNameDesc;
	@FXML private DatePicker dpJoinBirth;
	@FXML private Text txtJoinBirthDesc;
	@FXML private TextField tfJoinEmail;
	@FXML private Text txtJoinEmailDesc;
	@FXML private TextField tfJoinPhone;
	@FXML private Text txtJoinPhoneDesc;

	@FXML private ImageView ivJoinImgFile;
	@FXML private Button btnJoinImgFile;
	@FXML private Text txtJoinImgFileDesc;
	@FXML private TextField tfJoinHPhone;
	@FXML private Text txtJoinHPhoneDesc;
	@FXML private TextField tfJoinAddr;
	@FXML private Text txtJoinAddrDesc;
	@FXML private TextField tfJoinComp;
	@FXML private Text txtJoinCompDesc;
	@FXML private TextField tfJoinPos;
	@FXML private Text txtJoinPosDesc;
	
	// 
	private JoinService joinService;
	private StringBinding joinNameBinding;
	private String joinImgFileName = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		joinService = new JoinService(this);
		
		
		// Add ChangeListener of each Field to set Description Text after Validation Check
		// 각 필드의 유효성 검사 후 확인 텍스트 변경
		tfJoinId.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinIdDesc, newValue, new Validation.CheckId()));
		pfJoinPw.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinPwDesc, newValue, new Validation.CheckPw()));
		pfJoinPwConf.textProperty().addListener((observable, oldValue, newValue) -> {
			setTxtDesc(txtJoinPwConfDesc, (newValue.equals(pfJoinPw.getText()) && new Validation.CheckPw().test(newValue)),
					CheckPw.VALID_DESC, "유효하지 않은 비밀번호 또는 위 비밀번호와 동일하지 않습니다.");
		});
		joinNameBinding = new StringBinding() {
			{ super.bind(tfJoinLName.textProperty(), tfJoinFName.textProperty()); }
			@Override
			protected String computeValue() { return tfJoinLName.getText() + " " + tfJoinFName.getText(); }
		};
		joinNameBinding.addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinNameDesc, 
				new String[]{tfJoinLName.getText(), tfJoinFName.getText()}, new Validation.CheckName()));
		// Set DatePicker Converter
		dpJoinBirth.setConverter(new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate localDate) {
				if(localDate != null) {
					setTxtValidDesc(txtJoinBirthDesc, true, "");	// 확인 텍스트 변경
					return localDate.toString();
				} else return "";
			}
			@Override
			public LocalDate fromString(String string) {
				return LocalDate.parse(string);
			}
		});
		tfJoinEmail.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinEmailDesc, newValue, new Validation.CheckEmail()));
		tfJoinPhone.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinPhoneDesc, newValue, new Validation.CheckPhone()));
		tfJoinHPhone.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinHPhoneDesc, newValue, new Validation.CheckHPhone()) );
		tfJoinAddr.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinAddrDesc, newValue, new Validation.CheckAddr()) );
		tfJoinComp.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinCompDesc, newValue, new Validation.CheckComp()) );
		tfJoinPos.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtJoinPosDesc, newValue, new Validation.CheckPos()) );

		// TODO 파일명 길이 유효성검사 추가
		// joinImgFileName
	}
	

	// 아이디 사용 가능 확인
	public void checkJoinId(ActionEvent e) {
		// 유효한 아이디
		if(new Validation.CheckId().test(tfJoinId.getText())) {
			tfJoinId.setDisable(true);			// 아이디 필드 잠금
			btnCheckJoinId.setDisable(true);	// 확인 버튼 잠금
			
			// CheckJoinId 서비스 실행
			joinService.execCheckJoinId(tfJoinId.getText());
		}
		// 유효하지 않은 아이디
		else
			txtJoinIdDesc.setText("유효하지 않은 아이디 입니다. " + Validation.CheckId.INVALID_DESC);
	}
	
	// 사진 파일 선택
	public void chooseJoinImgFile(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files (*.jpg, *.jpeg, *.bmp, *.png, *.gif)", 
				"*.jpg", "*.jpeg", "*.bmp", "*.png", "*.gif"));
		
		File selectedFile = fileChooser.showOpenDialog(null);
		if(selectedFile != null) {
			try (FileInputStream fis = new FileInputStream(selectedFile)) {
				ivJoinImgFile.setImage(new Image(fis));
				ivJoinImgFile.setFitWidth(200.0);
//				ivJoinImgFile.setFitHeight(150.0);
				joinImgFileName = selectedFile.getAbsolutePath();
//				System.out.println("joinImgFileName: " + joinImgFileName);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
//		else
//			System.out.println("chooseJoinImgFile() - File is not valid...");
	}
	
	// 
	private boolean isNotEmptyAllDetailField() {
		return !joinImgFileName.equals("") || !tfJoinHPhone.getText().equals("") || !tfJoinAddr.getText().equals("") || !tfJoinComp.getText().equals("") || !tfJoinPos.getText().equals("");
	}
	
	// 회원 가입
	public void join(ActionEvent e) {
		Debug.show("============================== join() ==============================");

		// 모든 필드 유효성 검사 확인
		Main.getPrimaryStage().getScene().getRoot().setDisable(true);	// 루트 Scene 잠금
		if(isValidAllField()) {
			// Join 서비스 실행
			
			if(isNotEmptyAllDetailField()) {
				System.out.println("isNotEmptyAllDetailField: true");
				joinService.execJoin(new VisitorDetail(
						-1, 
						'3', 
						tfJoinId.getText(), 
						pfJoinPw.getText(), 
						tfJoinLName.getText(), 
						tfJoinFName.getText(), 
						dpJoinBirth.getValue(), 
						tfJoinEmail.getText(), 
						tfJoinPhone.getText(),
						joinImgFileName, 
						null, 
						tfJoinHPhone.getText().equals("") ? "" : tfJoinHPhone.getText().substring(1), 
						tfJoinAddr.getText(), 
						tfJoinComp.getText(), 
						tfJoinPos.getText()
						));
			} else {
				System.out.println("isNotEmptyAllDetailField: false");
				joinService.execJoin(new Visitor(
						-1, 
						'3', 
						tfJoinId.getText(), 
						pfJoinPw.getText(), 
						tfJoinLName.getText(), 
						tfJoinFName.getText(), 
						dpJoinBirth.getValue(), 
						tfJoinEmail.getText(), 
						tfJoinPhone.getText()
						));
			}
		} else {
			AlertManager.show(AlertType.INFORMATION, "회원 가입", null,
					"비어있거나 유효하지 않은 회원 정보가 있습니다. 확인 후 다시 입력해 주세요.");
			Main.getPrimaryStage().getScene().getRoot().setDisable(false);	// 루트 Scene 잠금 해제
		}
	}
	
	// 모든 필드의 유효성 검사
	private boolean isValidAllField() {
		boolean isValidId = new Validation.CheckId().test(tfJoinId.getText()) && tfJoinId.isDisable();
		boolean isValidPw = new Validation.CheckPw().test(pfJoinPw.getText());
		boolean isValidPwConf = new Validation.CheckPw().test(pfJoinPwConf.getText()) && pfJoinPwConf.getText().equals(pfJoinPw.getText());
		boolean isValidLName = new Validation.CheckName().test(tfJoinLName.getText());
		boolean isValidFName = new Validation.CheckName().test(tfJoinFName.getText());
		boolean isValidBirth = dpJoinBirth.getValue() != null;
		boolean isValidEmail = new Validation.CheckEmail().test(tfJoinEmail.getText());
		boolean isValidPhone = new Validation.CheckPhone().test(tfJoinPhone.getText());
		boolean isValidHPhone = new Validation.CheckHPhone().test(tfJoinHPhone.getText());
		boolean isValidAddr = new Validation.CheckAddr().test(tfJoinAddr.getText());
		boolean isValidComp = new Validation.CheckComp().test(tfJoinComp.getText());
		boolean isValidPos = new Validation.CheckPos().test(tfJoinPos.getText());
		
		if(Properties.getInstance().isDebugMode()) {
			Debug.show("id: " + tfJoinId.getText() + " (" + isValidId + ")");
			Debug.show("pw: " + pfJoinPw.getText() + " (" + isValidPw + ")");
			Debug.show("pwConf: " + pfJoinPwConf.getText() + " (" + isValidPwConf + ")");
			Debug.show("lName: " + tfJoinLName.getText() + " (" + isValidLName + ")");
			Debug.show("fName: " + tfJoinFName.getText() + " (" + isValidFName + ")");
			Debug.show("birth: " + dpJoinBirth.getValue() + " (" + isValidBirth + ")");
			Debug.show("email: " + tfJoinEmail.getText() + " (" + isValidEmail + ")");
			Debug.show("phone: " + tfJoinPhone.getText() + " (" + isValidPhone + ")");
			Debug.show("imgFileName: " + joinImgFileName);
			Debug.show("hPhone: " + tfJoinHPhone.getText() + " (" + isValidHPhone + ")");
			Debug.show("addr: " + tfJoinAddr.getText() + " (" + isValidAddr + ")");
			Debug.show("comp: " + tfJoinComp.getText() + " (" + isValidComp + ")");
			Debug.show("pos: " + tfJoinPos.getText() + " (" + isValidPos + ")");
		}
		
		setTxtInValidDesc(txtJoinIdDesc, !isValidId, Validation.CheckId.INVALID_DESC);
		setTxtInValidDesc(txtJoinPwDesc, !isValidPw, Validation.CheckPw.INVALID_DESC);
		setTxtInValidDesc(txtJoinPwConfDesc, !isValidPwConf, "유효하지 않은 비밀번호 이거나 위 비밀번호와 동일하지 않습니다.");
		setTxtInValidDesc(txtJoinNameDesc, !isValidLName, Validation.CheckName.INVALID_DESC);
		setTxtInValidDesc(txtJoinNameDesc, !isValidFName, Validation.CheckName.INVALID_DESC);
		setTxtInValidDesc(txtJoinBirthDesc, !isValidBirth, Validation.CheckLocalDateNotNull.INVALID_DESC);
		setTxtInValidDesc(txtJoinEmailDesc, !isValidEmail, Validation.CheckEmail.INVALID_DESC);
		setTxtInValidDesc(txtJoinPhoneDesc, !isValidPhone, Validation.CheckPhone.INVALID_DESC);
		setTxtInValidDesc(txtJoinHPhoneDesc, !isValidHPhone, Validation.CheckHPhone.INVALID_DESC);
		setTxtInValidDesc(txtJoinAddrDesc, !isValidAddr, Validation.CheckAddr.INVALID_DESC);
		setTxtInValidDesc(txtJoinCompDesc, !isValidComp, Validation.CheckComp.INVALID_DESC);
		setTxtInValidDesc(txtJoinPosDesc, !isValidPos, Validation.CheckPos.INVALID_DESC);
		
		return isValidId && isValidPw && isValidPwConf && isValidLName && isValidFName && isValidBirth && isValidEmail && isValidPhone && 
				isValidHPhone && isValidAddr && isValidComp && isValidPos;
	}

	// 로그인 화면으로 돌아가기
	public void goBackToLogin(ActionEvent e) {
		StageManager.changeStage(VIEW.LOGIN);
	}
	
	
	/***** Utility *****/
	
	private <T> void setTxtDesc(Text textControl, T checkValue, Check<T> validationMethod) {
		if(validationMethod.test(checkValue)) {
			textControl.setFill(Color.BLUE);
			textControl.setText(validationMethod.getValidDesc());
		} else {
			textControl.setFill(Color.RED);
			textControl.setText(validationMethod.getInvalidDesc());
		}
	}
	
	private <T> void setTxtDesc(Text textControl, T checkValue[], Check<T> validationMethod) {
		boolean isValid = true;
		for(T t : checkValue) {
			if(validationMethod.test(t))
				isValid = isValid && true;
			else
				isValid = isValid && false;
		}
		if(isValid) {
			textControl.setFill(Color.BLUE);
			textControl.setText(validationMethod.getValidDesc());
		} else {
			textControl.setFill(Color.RED);
			textControl.setText(validationMethod.getInvalidDesc());
		}
	}
	
	private void setTxtDesc(Text textControl, boolean isValid, String validDesc, String inValidDesc) {
		textControl.setFill(isValid ? Color.BLUE : Color.RED);
		textControl.setText(isValid ? validDesc : inValidDesc);
	}
	
	private void setTxtValidDesc(Text textControl, boolean isValid, String validDesc) {
		if(isValid) {
			textControl.setFill(Color.BLUE);
			textControl.setText(validDesc);
		}
	}
	
	private void setTxtInValidDesc(Text textControl, boolean isInValid, String inValidDesc) {
		if(isInValid) {
			textControl.setFill(Color.RED);
			textControl.setText(inValidDesc);
		}
	}

	
	// Getter and Setter
	public TextField getTfJoinId() {
		return tfJoinId;
	}
	public Button getBtnCheckJoinId() {
		return btnCheckJoinId;
	}
	public Text getTxtJoinIdDesc() {
		return txtJoinIdDesc;
	}
	
}
