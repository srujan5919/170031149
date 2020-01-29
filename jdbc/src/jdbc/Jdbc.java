package jdbc;
import java.sql.*;
import java.util.Scanner;
public class Jdbc {
public static void main(String[]args)throws Exception {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
	Statement stmt=conn.createStatement();
	int total_sum=0;
	Scanner sc=new Scanner(System.in);
    System.out.println("Enter num of rows");	
	int n=sc.nextInt();
	String b = "insert into sales values(?,?,?)";
	for (int i=1;i<=n;i++)
	{
		System.out.println("Enter itemid:");
		int itid=sc.nextInt();
		System.out.println("Enter item name:");
		String itnam=sc.next();
		System.out.println("Enter cost of item:");
		int coi=sc.nextInt();
		PreparedStatement pstmt=conn.prepareStatement(b);
		pstmt.setInt(1, itid);
		pstmt.setString(2, itnam);
		pstmt.setInt(3, coi);
		pstmt.executeUpdate();
		String c="Select*from sales";
		ResultSet rs= stmt.executeQuery(c);
		while(rs.next()) {
			total_sum=total_sum+rs.getInt(3);
		}
	}
	System.out.println("The total cost of items:"+total_sum);
	conn.close();
	
}
}
