package hbm.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sun.istack.internal.NotNull;

public class Book {
	
	// Members
	@NotNull private int no;		// 등록 번호				PK					(00000000)
	@NotNull private int symbol;	// 청구 기호									(000.000-000)
	@NotNull private String name;	// 도서 명
	@NotNull private String author;	// 저자
	@NotNull private int price;		// 가격										(-1은 알 수 없음)
	
	private String pub;				// 출판사				(publisher)
	private LocalDate pubDay;		// 출판일				(publication day)
	private String loc;				// 도서 위치				(location)
	private String imgFile;			// 도서 이미지 파일 명	
	
	private String brwer;			// 대여자				(borrower)			(TODO int형 user_no 고려)
	private LocalDateTime brwDay;	// 대여일				(borrow day)		(Mapping to TIMESTAMP WITH LOCAL TIME ZONE)	(TODO null 외의 값 고려)
	private LocalDate period;		// 대여기간									(TODO null 외의 값 고려)
	// 연체일: 반납기간-오늘	TODO 대여기간 알고리즘 고려
	// 예약자: TODO 별도 테이블 고려
	//-- Members
	
	// Static Members
	
	
	// Constructor
	public Book(int no, int symbol, String name, String author, int price,
			String pub, LocalDate pubDay, String loc, String imgFile,
			String brwer, LocalDateTime brwDay, LocalDate period) {
		this.no = no;
		this.symbol = symbol;
		this.name = name;
		this.author = author;
		this.price = price;
		this.pub = pub;
		this.pubDay = pubDay;
		this.loc = loc;
		this.imgFile = imgFile;
		this.brwer = brwer;
		this.brwDay = brwDay;
		this.period = period;
	}
	public Book(int no, int symbol, String name, String author, int price) {
		this.no = no;
		this.symbol = symbol;
		this.name = name;
		this.author = author;
		this.price = price;
		this.pub = "";
		this.pubDay = null;
		this.loc = "";
		this.imgFile = "";
		this.brwer = "";
		this.brwDay = null;
		this.period = null;
	}
	public Book() {
		this.no = -1;
		this.symbol = -1;
		this.name = "";
		this.author = "";
		this.price = -1;
	}
	
	
	/*
	 *  Edit a book detail
	 */
	public void editInfo(int symbol, String name, String author, int price,
			String pub, LocalDate pubDay, String loc, String imgFile) {
		this.symbol = symbol;
		this.name = name;
		this.author = author;
		this.price = price;
		this.pub = pub;
		this.pubDay = pubDay;
		this.loc = loc;
		this.imgFile = imgFile;
	}
	
	/*
	 *  Lend a book
	 */
	public void lend(String brwer) {
		this.brwer = brwer;
		this.brwDay = LocalDateTime.now();
		this.period = LocalDate.now();		// TODO 대여자 등급에 따라 차등 대여기간이 되도록 수정
	}
	
	/*
	 * Turn in a book
	 */
	public void turnIn() {
		this.brwer = "";
		this.brwDay = null;
		this.period = null;
	}

	@Override
	public String toString() {
		return "[num: " + this.no + ", symbol: " + symbol + ", name: " + this.name + ", author: " + this.author + ", price: " +this.price +
				", pub: " + this.pub + ", pubDay: " + this.pubDay + ", loc: " + this.loc + ", imgFile: " + this.imgFile +
				", brwer: " + this.brwer + ", brwDay: " + this.brwDay + ", period: " + this.period + "]";
	}
	
	
	// Getter and Setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getSymbol() {
		return symbol;
	}
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public LocalDate getPubDay() {
		return pubDay;
	}
	public void setPubDay(LocalDate pubDay) {
		this.pubDay = pubDay;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public String getBrwer() {
		return brwer;
	}
	public void setBrwer(String brwer) {
		this.brwer = brwer;
	}
	public LocalDateTime getBrwDay() {
		return brwDay;
	}
	public void setBrwDay(LocalDateTime brwDay) {
		this.brwDay = brwDay;
	}
	public LocalDate getPeriod() {
		return period;
	}
	public void setPeriod(LocalDate period) {
		this.period = period;
	}
	
	
	
}
