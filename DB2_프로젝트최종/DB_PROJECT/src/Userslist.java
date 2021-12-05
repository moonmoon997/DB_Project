
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Types;

public class Userslist {
	private Users[] Userslist;
	
	public Userslist() throws SQLException{
		Connection con = null;
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String id = "RS";
		String password="1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver OK.");
		}catch(ClassNotFoundException e){
			System.out.println("Driver Failed");
		}
		
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("Connection Ok.");
		}catch(SQLException e) {
			System.out.println("Connection Ok.");
		}
		
		String query = "select * from userinfo";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			
			rs.last();
			int rnum=rs.getRow();
			rs.beforeFirst();
			
			Userslist=new Users[rnum];
			int i=0;
			while (rs.next()) {
				String ID=rs.getNString(1);
				String PW=rs.getNString(2);
				String name=rs.getNString(3);
				String phone=rs.getNString(4);
				String grade=rs.getNString(5);
				Userslist[i] =new Users(ID, PW, name, phone,grade);
				i++;
			}
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
	}
		
		public Users[] get_Userslist() {
			return this.Userslist;
		}
		public static void main(String[] args) throws SQLException{
			Userslist ul=new Userslist();
		}
}