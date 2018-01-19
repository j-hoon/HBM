package hbm.controller.main;

import java.io.FileInputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hbm.Main;
import hbm.service.main.VisitorDetailService;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class VisitorDetailController implements Initializable {

	// FXML Member
	@FXML private ScrollPane spVisitorDetail;
	@FXML private Button btnCloseVisitorDetail;
	
	@FXML private Text txtVisitorDetailNo;
	@FXML private TextField tfVisitorDetailGrade;
	@FXML private TextField tfVisitorDetailId;
	@FXML private PasswordField pfVisitorDetailPw;
	@FXML private PasswordField pfVisitorDetailPwConf;
	@FXML private TextField tfVisitorDetailLName;
	@FXML private TextField tfVisitorDetailFName;
	@FXML private DatePicker dpVisitorDetailBirth;
	@FXML private TextField tfVisitorDetailEmail;
	@FXML private TextField tfVisitorDetailPhone;
	@FXML private ImageView ivVisitorDetailImgFile;
	@FXML private TextField tfVisitorDetailHPhone;
	@FXML private TextField tfVisitorDetailAddr;
	@FXML private TextField tfVisitorDetailComp;
	@FXML private TextField tfVisitorDetailPos;

	@FXML private Text txtVisitorDetailGradeDesc;
	@FXML private Text txtVisitorDetailIdDesc;
	@FXML private Text txtVisitorDetailPwDesc;
	@FXML private Text txtVisitorDetailPwConfDesc;
	@FXML private Text txtVisitorDetailNameDesc;
	@FXML private Text txtVisitorDetailBirthDesc;
	@FXML private Text txtVisitorDetailEmailDesc;
	@FXML private Text txtVisitorDetailPhone;
	@FXML private Text txtVisitorDetailHPhoneDesc;
	@FXML private Text txtVisitorDetailAddrDesc;
	@FXML private Text txtVisitorDetailCompDesc;
	@FXML private Text txtVisitorDetailPosDesc;
	
//	@FXML private Button btnCheckJoinId;
//	@FXML private Button btnReCheckJoinId;
//	@FXML private Button btnJoinImgFile;
	
	// 
	private MainController parentController;
	private VisitorDetailService visitorDetailService;
	private Visitor visitor;
	private VisitorDetail visitorDetail;
	private StringBinding visitorDetailNameBinding;
	private String visitorDetailImgFileName = "";
	
	
	public VisitorDetailController(MainController parentController, Visitor visitor) {
		this.parentController = parentController;
		this.visitor = visitor;
		System.out.println("===== VisitorDetailController() =====\nvisitor: " + visitor);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.visitorDetailService = new VisitorDetailService(this);
		
		// Add ChangeListener of each Field to set Description Text after Validation Check
		// 각 필드의 유효성 검사 후 확인 텍스트 변경
		// tfVisitorDetailGrade
		// 
		tfVisitorDetailId.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailIdDesc, newValue, new Validation.CheckId()));
		pfVisitorDetailPw.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals("") && pfVisitorDetailPwConf.getText().equals("")) {
				txtVisitorDetailPwDesc.setText("");
				txtVisitorDetailPwConfDesc.setText("");
			} else
				setTxtDesc(txtVisitorDetailPwDesc, newValue, new Validation.CheckPw());
		});
		pfVisitorDetailPwConf.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals("") && pfVisitorDetailPw.getText().equals("")) {
				txtVisitorDetailPwDesc.setText("");
				txtVisitorDetailPwConfDesc.setText("");
			} else {
				setTxtDesc(txtVisitorDetailPwConfDesc, (newValue.equals(pfVisitorDetailPw.getText()) && new Validation.CheckPw().test(newValue)),
					CheckPw.VALID_DESC, "유효하지 않은 비밀번호 또는 위 비밀번호와 동일하지 않습니다.");
			}
		});
		visitorDetailNameBinding = new StringBinding() {
			{ super.bind(tfVisitorDetailLName.textProperty(), tfVisitorDetailFName.textProperty()); }
			@Override
			protected String computeValue() { return tfVisitorDetailLName.getText() + " " + tfVisitorDetailFName.getText(); }
		};
		visitorDetailNameBinding.addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailNameDesc, 
				new String[]{tfVisitorDetailLName.getText(), tfVisitorDetailFName.getText()}, new Validation.CheckName()));
		// Set DatePicker Converter
		dpVisitorDetailBirth.setConverter(new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate localDate) {
				if(localDate != null) {
					setTxtValidDesc(txtVisitorDetailBirthDesc, true, "");	// 확인 텍스트 변경
					return localDate.toString();
				} else return "";
			}
			@Override
			public LocalDate fromString(String string) {
				return LocalDate.parse(string);
			}
		});
		tfVisitorDetailEmail.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailEmailDesc, newValue, new Validation.CheckEmail()));
		tfVisitorDetailPhone.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailPhone, newValue, new Validation.CheckPhone()));
		tfVisitorDetailHPhone.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailHPhoneDesc, newValue, new Validation.CheckHPhone()) );
		tfVisitorDetailAddr.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailAddrDesc, newValue, new Validation.CheckAddr()) );
		tfVisitorDetailComp.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailCompDesc, newValue, new Validation.CheckComp()) );
		tfVisitorDetailPos.textProperty().addListener((observable, oldValue, newValue) -> setTxtDesc(txtVisitorDetailPosDesc, newValue, new Validation.CheckPos()) );
		
		// get Visitor Detail
		visitorDetailService.execGetVisitorDetail(this.visitor);
		
		// 
		Main.getPrimaryStage().heightProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("oldValue(" + oldValue + "), newValue(" + newValue + ")");
//			vBoxVisitorDetail.setMinHeight(vBoxVisitorDetail.getPrefHeight());
			spVisitorDetail.setPrefHeight(Main.getPrimaryStage().getHeight());
		});
	}
	
	// 
	public void showVisitorDetail(VisitorDetail vd) {
		this.visitorDetail = vd;
		this.txtVisitorDetailNo.setText(String.valueOf(vd.getNo()));
		this.tfVisitorDetailGrade.setText(String.valueOf(vd.getGrade()));
		this.tfVisitorDetailId.setText(vd.getId());
//		this.pfVisitorDetailPw.setText("");
//		this.pfVisitorDetailPwConf.setText("");
		this.tfVisitorDetailLName.setText(vd.getlName());
		this.tfVisitorDetailFName.setText(vd.getfName());
		dpVisitorDetailBirth.setValue(vd.getBirth());
		tfVisitorDetailEmail.setText(vd.getEmail());
		tfVisitorDetailPhone.setText("01" + vd.getPhone());

		if(vd.getImgFileName() != null && vd.getImgFileCont() != null) {
//			visitorDetailImgFileName = vd.getImgFileName();
			ivVisitorDetailImgFile.setImage(new Image(vd.getImgFileCont()));
//			ivVisitorDetailImgFile.setFitWidth(200.0);
		}
		if(vd.gethPhone() != null)
			tfVisitorDetailHPhone.setText(vd.gethPhone());
		if(vd.getAddr() != null)
			tfVisitorDetailAddr.setText(vd.getAddr());
		if(vd.getComp() != null)
			tfVisitorDetailComp.setText(vd.getComp());
		if(vd.getPos() != null)
			tfVisitorDetailPos.setText(vd.getPos());
		
		if(Properties.getInstance().isDebugMode()) {
			System.out.println("showVisitorDetail().vd: " + vd);
			System.out.println("this.visitorDetail:     " + this.visitorDetail);

			System.out.println("(Field.grade == VO.grade): " + (tfVisitorDetailGrade.getText().charAt(0) == this.visitorDetail.getGrade()));
			System.out.println("(Field.id == VO.id): " + (tfVisitorDetailId.getText().equals(this.visitorDetail.getId())));
//			System.out.println("(Field.pw == VO.pw): " + (pfVisitorDetailPw.getText() == this.visitorDetail.getPw()));
//			System.out.println("(Field.pwConf == VO.pwConf): " + (pfVisitorDetailPwConf.getText() == this.visitorDetail.getPw()));
			System.out.println("(Field.lName == VO.lName): " + (tfVisitorDetailLName.getText().equals(this.visitorDetail.getlName())));
			System.out.println("(Field.fName == VO.fName): " + (tfVisitorDetailFName.getText().equals(this.visitorDetail.getfName())));
			System.out.println("(Field.birth == VO.birth): " + (dpVisitorDetailBirth.getValue().equals(this.visitorDetail.getBirth())));
			System.out.println("(Field.email == VO.email): " + (tfVisitorDetailEmail.getText().equals(this.visitorDetail.getEmail())));
			System.out.println("(Field.phone == VO.phone): " + (tfVisitorDetailPhone.getText().substring(2).equals(this.visitorDetail.getPhone())));
			
//			System.out.println("(Field. == VO.): " + (ivVisitorDetailImgFile.getText().equals(this.visitorDetail.get)));
			
//			System.out.println("(Field.hPhone == VO.hPhone): " + (tfVisitorDetailHPhone.getText().equals(this.visitorDetail.gethPhone())));
//			System.out.println("(Field.addr == VO.addr): " + (tfVisitorDetailAddr.getText().equals(this.visitorDetail.getAddr())));
//			System.out.println("(Field.comp == VO.comp): " + (tfVisitorDetailComp.getText().equals(this.visitorDetail.getComp())));
//			System.out.println("(Field.pos == VO.pos): " + (tfVisitorDetailPos.getText().equals(this.visitorDetail.getPos())));
		}
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

	
	/***** Utility *****/
	
	private <T> void setTxtDesc(Text textControl, T checkValue, Check<T> validationMethod) {
		/*if(checkValue.equals("")) {
			textControl.setText("");
		} else */if(validationMethod.test(checkValue)) {
			textControl.setFill(Color.BLUE);
			textControl.setText(validationMethod.getValidDesc());
		} else {
			textControl.setFill(Color.RED);
			textControl.setText(validationMethod.getInvalidDesc());
		}
		spVisitorDetail.setPrefHeight(Main.getPrimaryStage().getHeight());
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
//		spVisitorDetail.setPrefHeight(100000.0);
		spVisitorDetail.setPrefHeight(Main.getPrimaryStage().getHeight());
	}
	
	private void setTxtDesc(Text textControl, boolean isValid, String validDesc, String inValidDesc) {
		textControl.setFill(isValid ? Color.BLUE : Color.RED);
		textControl.setText(isValid ? validDesc : inValidDesc);
//		spVisitorDetail.setPrefHeight(100000.0);
		spVisitorDetail.setPrefHeight(Main.getPrimaryStage().getHeight());
	}
	
	private void setTxtValidDesc(Text textControl, boolean isValid, String validDesc) {
		if(isValid) {
			textControl.setFill(Color.BLUE);
			textControl.setText(validDesc);
//			spVisitorDetail.setPrefHeight(100000.0);
			spVisitorDetail.setPrefHeight(Main.getPrimaryStage().getHeight());
		}
	}
	
	private void setTxtInValidDesc(Text textControl, boolean isInValid, String inValidDesc) {
		if(isInValid) {
			textControl.setFill(Color.RED);
			textControl.setText(inValidDesc);
//			spVisitorDetail.setPrefHeight(100000.0);
			spVisitorDetail.setPrefHeight(Main.getPrimaryStage().getHeight());
		}
	}
	
}
