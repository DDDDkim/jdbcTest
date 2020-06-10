package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * Select 예제
 */

public class T02_jdbcTest {

	/*
	 * 문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하세요.
	 * 
	 * 문제2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오.
	 */
	public static void main(String[] args) {
		
		System.out.println("lprod_id의 값을 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "DDDD";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM lprod WHERE lprod_id > " + input;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------------");
			}
			
			System.out.println("lprod_id값을 2개 입력");
			System.out.println("첫번째 lprod_id값을 입력.");
			String input1 = sc.nextLine();
			System.out.println("두번째 lprod_id값을 입력.");
			String input2 = sc.nextLine();
			
			if(Integer.parseInt(input1) > Integer.parseInt(input2)) {
				String temp = input1;
				input1 = input2;
				input2 = temp;
			}
			
			sql = 		"SELECT * "
					+ 	"FROM lprod "
					+ 	"WHERE lprod_id >= " + input1 
					+ 	"AND lprod_id <= " + input2;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------------");
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		catch(ClassNotFoundException e) {e.printStackTrace();}
		finally {
			//6. 종료(사용했던 자원을 모두 반납한다.)
			if(rs != null) try { rs.close();}catch(SQLException e) {}
			if(stmt != null) try { stmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
	}
}
