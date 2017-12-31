package hbm.visitor;

import com.sun.istack.internal.NotNull;

import hbm.util.Debug;

public class Visitor {

	// Member
	@NotNull private int no;		// 번호					PK, SEQ
	@NotNull private char grade;	// 계정 등급			default 3		(0)		(0: to default constructor, 1~2: bad, 3: default, 4~6: grade, 7~8: admin, 9: super)
	@NotNull private String id;		// ID					* 중복 불가능	(TODO: 암호화 고려)
	@NotNull private String pw;		// PW									(TODO: 암호화 고려)
	@NotNull private String name;	// 이름					
	@NotNull private char birth[];	// 생년월일				(birth date)	(000000 / char[6])
	@NotNull private String email;	// E-mail				
	@NotNull private char phone[];	// 휴대폰 번호							([01]0-0000-0000 / [01]0-000-0000 / char[9])
	private String imgFile;			// 사용자 사진 파일 명	
	private char hPhone[];			// 집 전화번호			(home phone)	([0]00-0000-0000 / [0]00-000-0000 / char[10] / -1은 알 수 없음)
	private String addr;			// 주소					(address)		
	private String comp;			// 소속					(company)					
	private String pos;				// 직책					(position)		
	//-- Member

	// Static Member
	
	
	// Constructor
	public Visitor(int no, char grade, String id, String pw, String name, char[] birth, String email, char[] phone,
			String imgFile, char[] hPhone, String addr, String comp, String pos) {
		this.no = no;
		this.grade = grade;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.imgFile = imgFile;
		this.hPhone = hPhone;
		this.addr = addr;
		this.comp = comp;
		this.pos = pos;
	}
	public Visitor(int no, char grade, String id, String pw, String name, char[] birth, String email, char[] phone) {
		this(no, grade, id, pw, name, birth, email, phone,
				"", "0000000000".toCharArray(), "", "", "");
	}
	public Visitor() {
		this(-1, '0', "", "", "", "000000".toCharArray(), "", "000000000".toCharArray());
	}

	@Override
	public String toString() {
		return "[no: " + this.no + ", grade: " + this.grade + ", id: " + this.id + ", pw: " + this.pw + ", name: " +this.name +
				", birth: " + new String(this.birth) + ", email: " + this.email + ", phone: " + new String(this.phone) +
				", imgFile: " + this.imgFile + ", hPhone: " + new String(this.hPhone) + ", addr: " + addr + ", comp: " + this.comp + ", pos: " + this.pos + "]";
	}
	

	// Default Getter and Setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public char getGrade() {
		return grade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char[] getBirth() {
		return birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char[] getPhone() {
		return phone;
	}
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public char[] getHPhone() {
		return hPhone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	// char Setter
	public void setGrade(String grade) {
		if(grade.length() > 1)
			Debug.error("'char Visitor.Grade' 데이터 형에 유효하지 않은 길이 입니다. (" + grade + ")");
		this.grade = grade.charAt(0);
	}
	public void setBirth(String birth) {
		if(birth.length() > 6) {
			Debug.error("'char[6] Visitor.Birth' 데이터 형에 유효하지 않은 길이 입니다. (" + birth + ")");
		} else
			this.birth = birth.toCharArray();
	}
	public void setPhone(String phone) {
		if(phone.length() > 9) {
			Debug.error("'char[9] Visitor.Phone' 데이터 형에 유효하지 않은 길이 입니다. (" + phone + ")");
		} else
			this.phone = phone.toCharArray();
	}
	public void setHPhone(String hPhone) {
		if(hPhone.length() > 10) {
			Debug.error("'char[10] Visitor.HPhone' 데이터 형에 유효하지 않은 길이 입니다. (" + hPhone + ")");
		} else
			this.hPhone = hPhone.toCharArray();
	}
	
}
