package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * lprod 테이블에 새로운 데이터추가하기
 * lprod_gu와 lprod_nm은 직접 입력 받아 처리하고, 
 * lprod_id는 현재의 lprod_id들 중 제일 큰 값보다 1증가된 값으로 한다.
 * (기타사항 : lprod_gu도 중복되는지 검사한다.)
 */

public class T04_jdbcTest {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "DDDD";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			String sql = "SELECT MAX(lprod_id) lprod_id FROM lprod";
			
			pstmt = conn.prepareStatement(sql);
//			rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			int cnt = rs.getInt("lprod_id");
			cnt++;
			
			System.out.println("lprod_gu와 lprod_nm을 입력>");
			System.out.println("lprod_gu을 입력>");
			String input1 = sc.nextLine();
			System.out.println("lprod_nm을 입력>");
			String input2 = sc.nextLine();
			
			sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm) values (?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cnt);
			pstmt.setString(2, input1);
			pstmt.setString(3, input2);
			
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
