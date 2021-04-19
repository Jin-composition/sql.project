package exam_student;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class ScoreTableExample {
	public static Scanner sc = new Scanner(System.in);
	public static final int SEARCH = 1;
	public static final int INSERT = 2;
	public static final int DELETE = 3;
	public static final int UPDATE = 4;
	public static final int EXIT = 5;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		
		printData();
		
		while(!flag) {
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("1.데이터조회	2.데이터 입력	3.데이터 삭제	4.데이터 수정	5.프로그램 종료 ");
			System.out.println("---------------------------------------------------------------------------------");
			
			int answer = sc.nextInt();
			
			switch(answer) {
				case SEARCH: searchData(); break;
				case INSERT: insertData(); break;
				case DELETE: deleteData(); break;
				case UPDATE: updateData(); break;
				case EXIT: flag = true; break;
				default : break;
			}
		}
		System.out.println("프로그램이 종료되었습니다.");
	}

	private static void updateData() throws ClassNotFoundException, SQLException {
		
		
			System.out.println("수정할 학번을 입력하세요>>");
			String stNo = sc.next();
			
			ScoreTable s = ScoreTableDAO.searchSt_numInfoStudentTBL(stNo);
			
			if(s != null) {
				System.out.println(s);
				System.out.print("수정 전 java 점수: " + s.getJava() +"\t"+ "수정 할 java점수:");
				int java = sc.nextInt();
				System.out.print("수정 전 mysql점수: " + s.getMysql() + "\t"+ "수정 할 mysql점수:");
				int mysql = sc.nextInt();
				System.out.print("수정 전 kotlin점수: " + s.getKotlin() + "\t"+ "수정 할 kotlin점수:");
				int kotlin = sc.nextInt();
				
				
				s.setJava(java);
				s.setMysql(mysql);
				s.setKotlin(kotlin);
				s.calculateTotal();
				s.calculateAvg();
				s.calculateGrade();
				
				boolean returnValue = ScoreTableDAO.updateStudentScore(s);
				if(returnValue == false) {
					System.out.println("수정하는 데 실패했습니다. ");
				}else {
					printData();
				}
			}else {
				System.out.println("찾는 번호가 없습니다. ");
			}
		}

		


	private static void deleteData() throws ClassNotFoundException, SQLException {
		boolean flag = false;
		
		try {
			while(!flag) {
				System.out.print("삭제하실 학번을 입력하세요: ");
				String stNo = sc.next();
				
				boolean returnValue = ScoreTableDAO.deleteStudentTBL(stNo);
				if(returnValue == true) {
					printData();
					flag = true;
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printData(HashSet<ScoreTable> hashSet) {
		System.out.println("번호	학번		이름	나이	핸드폰번호		주소");
		for(ScoreTable s : hashSet) {
			System.out.println(s.toString());
		}
	}

	private static void printData() throws ClassNotFoundException, SQLException {
		HashSet <ScoreTable> hashSet = ScoreTableDAO.searchStudentTBLtotal();
		System.out.println("번호	학번		이름	java	mysql	kotlin	총점	평균	등급");
		
		if(hashSet.size() != 0) {
			Iterator <ScoreTable> iterator = hashSet.iterator();
			while(iterator.hasNext()) {
				ScoreTable scoreTable = iterator.next();
				System.out.println(scoreTable);
			}
		}
	}

	
	private static void insertData() throws ClassNotFoundException, SQLException {
		boolean flag = false;
		
		String stNo = null;
		ScoreTable scoreTable = null;
		ScoreTable inputScoreTable = null;
		
			while(!flag) {
				try {
				System.out.print("학번을 입력하세요: ");
				stNo = sc.next();
				
				scoreTable = ScoreTableDAO.searchSt_numInfoStudentTBL(stNo);
				
				if(scoreTable != null && scoreTable.getStNo().equals(stNo)) {
					System.out.println("이미 저장되어있는 학번입니다. 다시 입력해주세요");
					continue;
				}
				
				System.out.print("번호를 입력하세요: ");
				int no = sc.nextInt();
				System.out.print("이름을 입력하세요: ");
				String name = sc.next();
				System.out.print("java 점수를 입력하세요: ");
				int java = sc.nextInt();
				System.out.print("mysql 점수를 입력하세요: ");
				int mysql = sc.nextInt();
				System.out.print("kotlin 점수를 입력하세요: ");
				int kotlin = sc.nextInt();
				inputScoreTable = new ScoreTable(no, stNo, name, java,  mysql, kotlin, 0, 0.0, null);
				inputScoreTable.calculateTotal();
				inputScoreTable.calculateAvg();
				inputScoreTable.calculateGrade();
				flag = true;
			}catch(Exception e) {
				System.out.println("정확한 학번을 입력해주세요");
			}
		}
		
		boolean returnValue = ScoreTableDAO.insertMemberTBL(inputScoreTable);
		
		if(returnValue == true) {
			printData();
			System.out.println(inputScoreTable.getName() + "님 저장되었습니다.");
		}else {
			System.out.println("삽입에 실패했습니다.");
		}
	}
	
	

	private static void searchData() throws ClassNotFoundException, SQLException {
		boolean flag = false;
		
		while(!flag) {
			System.out.print("학번을 입력하세요: ");
			String stNo = sc.next();
			
			HashSet <ScoreTable> hashSet = ScoreTableDAO.searchInfoStudentTBL(stNo);
			
			if(hashSet.size() >= 1) {
				Iterator <ScoreTable> iterator = hashSet.iterator();
				System.out.println("번호	학번		이름	java	mysql	kotlin	총점	평균	등급");
				while(iterator.hasNext()) {
					ScoreTable scoreTable = iterator.next();
					System.out.println(scoreTable);
					flag = true;
					break;
				}
			}else {
				System.out.println("없는 학번입니다. 다시 입력해주세요");
				continue;
			}
		}
	}

	

}
