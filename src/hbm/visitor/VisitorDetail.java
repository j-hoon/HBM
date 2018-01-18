package hbm.visitor;

import java.io.InputStream;
import java.time.LocalDate;

public class VisitorDetail extends Visitor {
	
	// Member
//	private int vNo;					// FK (VISITOR_PK)		
	private String imgFileName;			// 사용자 사진 파일 명	
	private InputStream imgFileCont;	// 사용자 사진 파일						
	private String hPhone;				// 집 전화번호			(home phone)	([0]00-0000-0000 / [0]00-000-0000 / VARCHAR(10) / -1은 알 수 없음)
	private String addr;				// 주소					(address)		
	private String comp;				// 소속					(company)		
	private String pos;					// 직책					(position)		
	//-- Member
	

	// Constructor
	public VisitorDetail(int no, char grade, String id, String pw, String lName, String fName, LocalDate birth, String email, String phone, 
			String imgFileName, InputStream imgFileCont, String hPhone, String addr, String comp, String pos) {
		super(no, grade, id, pw, lName, fName, birth, email, phone);
		this.imgFileName = imgFileName;
		this.imgFileCont = imgFileCont;
		this.hPhone = hPhone;
		this.addr = addr;
		this.comp = comp;
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "[no: " + this.no + ", grade: " + this.grade + ", id: " + this.id + ", pw: " + this.pw + 
				", lName: " + this.lName + ", fName: " + this.fName + ", birth: " + this.birth + ", email: " + this.email + ", phone: " + this.phone + 
				", imgFileName: " + this.imgFileName + ", imgFileCont: " + this.imgFileCont + ", hPhone: " + this.hPhone + ", addr: " + this.addr + ", comp: " + this.comp + ", pos: " + this.pos + "]";
	}
	

	// Default Getter and Setter
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public InputStream getImgFileCont() {
		return imgFileCont;
	}
	public void setImgFileCont(InputStream imgFileCont) {
		this.imgFileCont = imgFileCont;
	}
	public String gethPhone() {
		return hPhone;
	}
	public void sethPhone(String hPhone) {
		this.hPhone = hPhone;
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
	
}
