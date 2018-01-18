package hbm.visitor;

import java.time.LocalDate;

import com.sun.istack.internal.NotNull;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Visitor {

	// Member
//	@NotNull protected int no;			// 번호					PK, SEQ			
//	@NotNull protected char grade;		// 계정 등급			default 3		(0)		(0: to default constructor, 1~2: bad, 3: default, 4~6: grade, 7~8: admin, 9: super)
//	@NotNull protected String id;		// ID					* 중복 불가능	
//	@NotNull protected String pw;		// PW									(TODO: 암호화 고려)
//	@NotNull protected String lName;	// 성					
//	@NotNull protected String fName;	// 이름					
//	@NotNull protected LocalDate birth;	// 생년월일				(birth date)
//	@NotNull protected String email;	// E-mail				
//	@NotNull protected String phone;	// 휴대폰 번호							([01]0-0000-0000 / [01]0-000-0000 / VARCHAR(9))
	
	@NotNull protected final IntegerProperty no;				// 번호					PK, SEQ			
	@NotNull protected final StringProperty grade;			// 계정 등급			default 3		(0)		(0: to default constructor, 1~2: bad, 3: default, 4~6: grade, 7~8: admin, 9: super)
	@NotNull protected final StringProperty id;				// ID					* 중복 불가능	
	@NotNull protected final StringProperty pw;				// PW									(TODO: 암호화 고려)
	@NotNull protected final StringProperty lName;			// 성					
	@NotNull protected final StringProperty fName;			// 이름					
	@NotNull protected final ObjectProperty<LocalDate> birth;	// 생년월일				(birth date)
	@NotNull protected final StringProperty email;			// E-mail				
	@NotNull protected final StringProperty phone;			// 휴대폰 번호							([01]0-0000-0000 / [01]0-000-0000 / VARCHAR(9))
	//-- Member

	// Static Member
	
	
	// Constructor
	public Visitor(int no, char grade, String id, String pw, String lName, String fName, LocalDate birth, String email, String phone) {
		this.no = new SimpleIntegerProperty(no);
		this.grade = new SimpleStringProperty(String.valueOf(grade));
		this.id = new SimpleStringProperty(id);
		this.pw = new SimpleStringProperty(pw);
		this.lName = new SimpleStringProperty(lName);
		this.fName = new SimpleStringProperty(fName);
		this.birth = new SimpleObjectProperty<LocalDate>(birth);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
	}
	public Visitor() {
		this(-1, '0', "", "", "", "", null, "", "");
	}

	@Override
	public String toString() {
		return "[no: " + this.no.get() + ", grade: " + this.grade.get() + ", id: " + this.id.get() + ", pw: " + this.pw.get() + 
				", lName: " + this.lName.get() + ", fName: " + this.fName.get() + ", birth: " + this.birth.get() + ", email: " + this.email.get() + ", phone: " + this.phone.get() + "]";
	}

	
	// Default Getter and Setter
	public int getNo() {
		return no.get();
	}
	public void setNo(int no) {
		this.no.set(no);
	}
	public IntegerProperty noProperty() {
		return this.no;
	}
	
	public char getGrade() {
		return grade.get().charAt(0);
	}
	public void setGrade(char grade) {
		this.grade.set(String.valueOf(grade));
	}
	public StringProperty gradeProperty() {
		return this.grade;
	}
	
	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public StringProperty idProperty() {
		return this.id;
	}
	
	public String getPw() {
		return pw.get();
	}
	public void setPw(String pw) {
		this.pw.set(pw);
	}
	public StringProperty pwProperty() {
		return this.pw;
	}
	
	public String getlName() {
		return lName.get();
	}
	public void setlName(String lName) {
		this.lName.set(lName);
	}
	public StringProperty lNameProperty() {
		return this.lName;
	}
	
	public String getfName() {
		return fName.get();
	}
	public void setfName(String fName) {
		this.fName.set(fName);
	}
	public StringProperty fNameProperty() {
		return this.fName;
	}
	
	public LocalDate getBirth() {
		return birth.get();
	}
	public void setBirth(LocalDate birth) {
		this.birth.set(birth);
	}
	public ObjectProperty<LocalDate> birthProperty() {
		return this.birth;
	}
	
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return this.email;
	}
	
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	public StringProperty phoneProperty() {
		return this.phone;
	}
	
	
	
	
	
//	// char Setter
//	public void setGrade(String grade) {
//		if(grade.length() > 1)
//			Debug.error("'char Visitor.Grade' 데이터 형에 유효하지 않은 길이 입니다. (" + grade + ")");
//		this.grade = grade.charAt(0);
//	}
	
	
	
	
	
//	// Constructor
//	public Visitor(int no, char grade, String id, String pw, String lName, String fName, LocalDate birth, String email, String phone) {
//		this.no = no;
//		this.grade = grade;
//		this.id = id;
//		this.pw = pw;
//		this.lName = lName;
//		this.fName = fName;
//		this.birth = birth;
//		this.email = email;
//		this.phone = phone;
//	}
//	public Visitor() {
//		this(-1, '0', "", "", "", "", null, "", "");
//	}

//	@Override
//	public String toString() {
//		return "[no: " + this.no + ", grade: " + this.grade + ", id: " + this.id + ", pw: " + this.pw + 
//				", lName: " + this.lName + ", fName: " + this.fName + ", birth: " + this.birth + ", email: " + this.email + ", phone: " + this.phone + "]";
//	}
	

//	// Default Getter and Setter
//	public int getNo() {
//		return no;
//	}
//	public void setNo(int no) {
//		this.no = no;
//	}
//	public char getGrade() {
//		return grade;
//	}
//	public void setGrade(char grade) {
//		this.grade = grade;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getPw() {
//		return pw;
//	}
//	public void setPw(String pw) {
//		this.pw = pw;
//	}
//	public String getlName() {
//		return lName;
//	}
//	public void setlName(String lName) {
//		this.lName = lName;
//	}
//	public String getfName() {
//		return fName;
//	}
//	public void setfName(String fName) {
//		this.fName = fName;
//	}
//	public LocalDate getBirth() {
//		return birth;
//	}
//	public void setBirth(LocalDate birth) {
//		this.birth = birth;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	
//	
//	// char Setter
//	public void setGrade(String grade) {
//		if(grade.length() > 1)
//			Debug.error("'char Visitor.Grade' 데이터 형에 유효하지 않은 길이 입니다. (" + grade + ")");
//		this.grade = grade.charAt(0);
//	}
	
}
