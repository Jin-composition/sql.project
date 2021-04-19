package exam_student;

import java.util.Objects;


public class ScoreTable {
	private int no;
	private String stNo;
	private String name;
	private int java;
	private int mysql;
	private int kotlin;
	private int total;
	private double avg;
	private String grade;
	
	public ScoreTable() {
		
	}
	
	public ScoreTable(int no, String stNo, String name, int java, int mysql, int kotlin, int total, double avg, String grade) {
		super();
		this.no = no;
		this.stNo = stNo;
		this.name = name;
		this.java = java;
		this.mysql = mysql;
		this.kotlin = kotlin;
		this.total = total;
		this.avg = avg;
		this.grade = grade;
	}
	
	public static final String[]GRADE = new String[5];
	
	static {
		for(int i=0; i<GRADE.length; i++) {
			GRADE[i] = (char)('A'+i)+"ÇÐÁ¡";
		}
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStNo() {
		return stNo;
	}

	public void setSt_num(String stNo) {
		this.stNo = stNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getMysql() {
		return mysql;
	}

	public void setMysql(int mysql) {
		this.mysql = mysql;
	}

	public int getKotlin() {
		return kotlin;
	}

	public void setKotlin(int kotlin) {
		this.kotlin = kotlin;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void calculateTotal() {
		this.total = this.java + this.mysql + this.kotlin;
	}
	
	public void calculateAvg() {
		this.avg = Math.round((this.total / (double)3)*100)/100;
	}
	
	public void calculateGrade() {
		switch((int)avg/10) {
		case 10:
		case 9: this.grade=ScoreTable.GRADE[0]; break;
		case 8: this.grade=ScoreTable.GRADE[1]; break;
		case 7: this.grade=ScoreTable.GRADE[2]; break;
		case 6: this.grade=ScoreTable.GRADE[3]; break;
		default : this.grade=ScoreTable.GRADE[4]; break;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ScoreTable) {
			ScoreTable scoreTable = (ScoreTable)obj;
			return (this.stNo.equals(scoreTable.stNo));
		}
		return false;
	}

	@Override
	public String toString() {
		return no + "\t" + stNo + "\t" + name + "\t" + java + "\t" + mysql + "\t" + kotlin + "\t" + total + "\t" + avg + "\t" + grade;
	}

	
}
