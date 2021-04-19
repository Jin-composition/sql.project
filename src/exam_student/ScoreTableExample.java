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
			System.out.println("1.��������ȸ	2.������ �Է�	3.������ ����	4.������ ����	5.���α׷� ���� ");
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
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}

	private static void updateData() throws ClassNotFoundException, SQLException {
		
		
			System.out.println("������ �й��� �Է��ϼ���>>");
			String stNo = sc.next();
			
			ScoreTable s = ScoreTableDAO.searchSt_numInfoStudentTBL(stNo);
			
			if(s != null) {
				System.out.println(s);
				System.out.print("���� �� java ����: " + s.getJava() +"\t"+ "���� �� java����:");
				int java = sc.nextInt();
				System.out.print("���� �� mysql����: " + s.getMysql() + "\t"+ "���� �� mysql����:");
				int mysql = sc.nextInt();
				System.out.print("���� �� kotlin����: " + s.getKotlin() + "\t"+ "���� �� kotlin����:");
				int kotlin = sc.nextInt();
				
				
				s.setJava(java);
				s.setMysql(mysql);
				s.setKotlin(kotlin);
				s.calculateTotal();
				s.calculateAvg();
				s.calculateGrade();
				
				boolean returnValue = ScoreTableDAO.updateStudentScore(s);
				if(returnValue == false) {
					System.out.println("�����ϴ� �� �����߽��ϴ�. ");
				}else {
					printData();
				}
			}else {
				System.out.println("ã�� ��ȣ�� �����ϴ�. ");
			}
		}

		


	private static void deleteData() throws ClassNotFoundException, SQLException {
		boolean flag = false;
		
		try {
			while(!flag) {
				System.out.print("�����Ͻ� �й��� �Է��ϼ���: ");
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
		System.out.println("��ȣ	�й�		�̸�	����	�ڵ�����ȣ		�ּ�");
		for(ScoreTable s : hashSet) {
			System.out.println(s.toString());
		}
	}

	private static void printData() throws ClassNotFoundException, SQLException {
		HashSet <ScoreTable> hashSet = ScoreTableDAO.searchStudentTBLtotal();
		System.out.println("��ȣ	�й�		�̸�	java	mysql	kotlin	����	���	���");
		
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
				System.out.print("�й��� �Է��ϼ���: ");
				stNo = sc.next();
				
				scoreTable = ScoreTableDAO.searchSt_numInfoStudentTBL(stNo);
				
				if(scoreTable != null && scoreTable.getStNo().equals(stNo)) {
					System.out.println("�̹� ����Ǿ��ִ� �й��Դϴ�. �ٽ� �Է����ּ���");
					continue;
				}
				
				System.out.print("��ȣ�� �Է��ϼ���: ");
				int no = sc.nextInt();
				System.out.print("�̸��� �Է��ϼ���: ");
				String name = sc.next();
				System.out.print("java ������ �Է��ϼ���: ");
				int java = sc.nextInt();
				System.out.print("mysql ������ �Է��ϼ���: ");
				int mysql = sc.nextInt();
				System.out.print("kotlin ������ �Է��ϼ���: ");
				int kotlin = sc.nextInt();
				inputScoreTable = new ScoreTable(no, stNo, name, java,  mysql, kotlin, 0, 0.0, null);
				inputScoreTable.calculateTotal();
				inputScoreTable.calculateAvg();
				inputScoreTable.calculateGrade();
				flag = true;
			}catch(Exception e) {
				System.out.println("��Ȯ�� �й��� �Է����ּ���");
			}
		}
		
		boolean returnValue = ScoreTableDAO.insertMemberTBL(inputScoreTable);
		
		if(returnValue == true) {
			printData();
			System.out.println(inputScoreTable.getName() + "�� ����Ǿ����ϴ�.");
		}else {
			System.out.println("���Կ� �����߽��ϴ�.");
		}
	}
	
	

	private static void searchData() throws ClassNotFoundException, SQLException {
		boolean flag = false;
		
		while(!flag) {
			System.out.print("�й��� �Է��ϼ���: ");
			String stNo = sc.next();
			
			HashSet <ScoreTable> hashSet = ScoreTableDAO.searchInfoStudentTBL(stNo);
			
			if(hashSet.size() >= 1) {
				Iterator <ScoreTable> iterator = hashSet.iterator();
				System.out.println("��ȣ	�й�		�̸�	java	mysql	kotlin	����	���	���");
				while(iterator.hasNext()) {
					ScoreTable scoreTable = iterator.next();
					System.out.println(scoreTable);
					flag = true;
					break;
				}
			}else {
				System.out.println("���� �й��Դϴ�. �ٽ� �Է����ּ���");
				continue;
			}
		}
	}

	

}
