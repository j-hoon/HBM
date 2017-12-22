package hbm.visitor;

import com.sun.istack.internal.NotNull;

public class Visitor {
	
	// Members
	@NotNull private int no;		// 번호					PK, SEQ
	@NotNull private char grade;	// 계정 등급			default 3		(0)		(0: to default constructor, 1~2: bad, 3: default, 4~6: grade, 7~8: admin, 9: super)
	@NotNull private String id;		// ID					* 중복 불가능	(TODO: 암호화 고려)
	@NotNull private String pw;		// PW									(TODO: 암호화 고려)
	@NotNull private String name;	// 이름					
	@NotNull private int birth;		// 생년월일				(birth date)	(000000)						(TODO: char[] 형으로? or NUMBER(6)?)
	@NotNull private String email;	// E-mail				
	@NotNull private int phone;		// 휴대폰 번호							(000-0000-0000 / 000-000-0000)	(TODO: int형 범위초과, char[] 형으로?)
	private String imgFile;			// 사용자 사진 파일 명	
	private int hPhone;				// 집 전화번호			(home phone)	(-1은 알 수 없음)				(TODO: int형 범위초과, char[] 형으로?)
	private String addr;			// 주소					(address)		
	private String group;			// 소속					
	private String pos;				// 직책					(position)		
	
	// Constructor
	public Visitor(int no, char grade, String id, String pw, String name, int birth, String email, int phone,
			String imgFile, int hPhone, String addr, String group, String pos) {
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
		this.group = group;
		this.pos = pos;
	}
	public Visitor(int no, char grade, String id, String pw, String name, int birth, String email, int phone) {
		this(no, grade, id, pw, name, birth, email, phone, "", -1, "", "", "");
	}
	public Visitor() {
		this(-1, '0', "", "", "", -1, "", -1);
	}

	@Override
	public String toString() {
		return "[no: " + this.no + ", grade: " + this.grade + ", id: " + this.id + ", pw: " + this.pw +
				", name: " +this.name + ", birth: " + this.birth + ", email: " + this.email + ", phone: " + this.phone +
				", imgFile: " + this.imgFile + ", hPhone: " + this.hPhone + ", group: " + this.group + ", pos: " + this.pos + "]";
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
	public void setGrade(char grade) {
		this.grade = grade;
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
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public int gethPhone() {
		return hPhone;
	}
	public void sethPhone(int hPhone) {
		this.hPhone = hPhone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	
}
