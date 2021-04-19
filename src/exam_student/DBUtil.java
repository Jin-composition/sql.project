package exam_student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/studentdb";
	
	public static Connection getConnection () throws ClassNotFoundException, SQLException {
		Connection connection = null;
		
		try {
			Class.forName(MYSQL_DRIVER);
			System.out.println("���� ����");
			connection = DriverManager.getConnection(URL+"?useSSL=false", "root", "math63041012!");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("�����ͺ��̽� ������ �����߽��ϴ�. ");
		}
		return connection;
	}
	
}
