package exam_student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


public class ScoreTableDAO {

	public static HashSet<ScoreTable> searchInfoStudentTBL(String searchStNo) throws ClassNotFoundException, SQLException {
		HashSet <ScoreTable> hashSet = new HashSet<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String Query = "select * from scoreTable where stNo = ?";
		
		try {
			preparedStatement = connection.prepareCall(Query);
			preparedStatement.setString(1, searchStNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int no = resultSet.getInt(1);
				String stNo = resultSet.getString(2);
				String name = resultSet.getString(3);
				int java = resultSet.getInt(4);
				int mysql = resultSet.getInt(5);
				int kotlin = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double avg = resultSet.getDouble(8);
				String grade = resultSet.getString(9);
				
				ScoreTable scoreTable = new ScoreTable(no, stNo, name, java, mysql, kotlin, total, avg, grade);
				hashSet.add(scoreTable);
			}
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ��ȸ �κп� ������ �߻�. ���˹ٶ�" + e.toString());
		}finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return hashSet;
	}


	public static ScoreTable searchSt_numInfoStudentTBL(String stNo1) throws ClassNotFoundException, SQLException {
		ScoreTable scoreTable = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String Query = "select * from scoreTable where stNo = ?";
		
		try {
			preparedStatement = connection.prepareStatement(Query);
			preparedStatement.setString(1, stNo1);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int no = resultSet.getInt(1);
				String stNo = resultSet.getString(2);
				String name = resultSet.getString(3);
				int java = resultSet.getInt(4);
				int mysql = resultSet.getInt(5);
				int kotlin = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double avg = resultSet.getDouble(8);
				String grade = resultSet.getString(9);
				
				scoreTable = new ScoreTable(no, stNo, name, java, mysql, kotlin, total, avg, grade);
			}
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ��ȸ �κп� ������ �߻�. ���˹ٶ�" + e.toString());
		}finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return scoreTable;
	}


	public static boolean insertMemberTBL(ScoreTable scoreTable) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String Query = "insert into scoreTable values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(Query);
			preparedStatement.setInt(1, scoreTable.getNo());
			preparedStatement.setString(2, scoreTable.getStNo());
			preparedStatement.setString(3, scoreTable.getName());
			preparedStatement.setInt(4, scoreTable.getJava());
			preparedStatement.setInt(5, scoreTable.getMysql());
			preparedStatement.setInt(6, scoreTable.getKotlin());
			preparedStatement.setInt(7, scoreTable.getTotal());
			preparedStatement.setDouble(8, scoreTable.getAvg());
			preparedStatement.setString(9, scoreTable.getGrade());
			
			int count = preparedStatement.executeUpdate();
			
			if(count == 1) {
				System.out.println(scoreTable.getName() + "�� �Է� ����");
				flag = true;
			}else {
				System.out.println(scoreTable.getName() + "�� �Է� ����");
			}
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� �Է� �κп� ������ �߻�. ���˹ٶ�" + e.toString());
		}finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return flag;
	}


	public static HashSet<ScoreTable> searchStudentTBLtotal() throws ClassNotFoundException, SQLException {
		HashSet <ScoreTable> hashSet = new HashSet<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String Query = "select * from scoreTable";
		
		try {
			preparedStatement = connection.prepareStatement(Query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int no = resultSet.getInt(1);
				String stNo = resultSet.getString(2);
				String name = resultSet.getString(3);
				int java = resultSet.getInt(4);
				int mysql = resultSet.getInt(5);
				int kotlin = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double avg = resultSet.getDouble(8);
				String grade = resultSet.getString(9);
				
				ScoreTable scoreTable = new ScoreTable(no, stNo, name, java, mysql, kotlin, total, avg, grade);
				hashSet.add(scoreTable);
			}
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ��ȸ(�հ�) �κп� ������ �߻�. ���˹ٶ�" + e.toString());
		}finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return hashSet;
	}


	public static boolean deleteStudentTBL(String stNo) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String Query = "delete from scoreTable where stNo = ?";
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(Query);
			preparedStatement.setString(1, stNo);
			
			int count = preparedStatement.executeUpdate();
			if(count == 1) {
				System.out.println("���� ����");
				flag = true;
			}else {
				System.out.println("��ȸ�Ͻ� �й��� ���� �й��Դϴ�.");
			}
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� �κп� ������ �߻�. ���˹ٶ�" + e.toString());
		}finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return flag;
	}


	public static boolean updateStudentScore(ScoreTable s) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String Query = "update scoreTable set java = ?, mysql = ?, kotlin = ?, total = ?, avg = ?, grade = ? where stNo = ?";
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(Query);
		
			preparedStatement.setInt(1, s.getJava());
			preparedStatement.setInt(2, s.getMysql());
			preparedStatement.setInt(3, s.getKotlin());
			preparedStatement.setInt(4, s.getTotal());
			preparedStatement.setDouble(5, s.getAvg());
			preparedStatement.setString(6, s.getGrade());
			preparedStatement.setString(7, s.getStNo());

			
			int count = preparedStatement.executeUpdate();
			System.out.println(count);
			if(count ==1) {
				System.out.println(s.getName() + "�� ���� ����");
				flag = true;
			}else {
				System.out.println(s.getName() + "�� ���� ����");
			}
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� �κп� ������ �߻�. ���˹ٶ�" + e.toString());
		}finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return flag;
	}
	

}
