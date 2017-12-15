package hbm.vo;

public class TestTable {
	
	private int no;
	private String data;
	
	public TestTable() {}
	
	public TestTable(int no, String data) {
		this.no = no;
		this.data = data;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
