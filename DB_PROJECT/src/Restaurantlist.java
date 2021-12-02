import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Types;

public class Restaurantlist {
	private Restaurant[] Restaurantlist;
	
	public Restaurantlist(String main) throws SQLException{
		Connection con=null;
		
		String url="jdbc:oralce:thin:@localhost:1521:XE";
		String id="RS";
		String password="1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver OK.");
		}catch(ClassNotFoundException e) {
			System.out.println("Driver Failed.");
		}
		
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("Connection OK.");
		}catch(SQLException e) {
			System.out.println("Connection Failed.");
		}
		
		String sql = "SELECT * FROM restaurant where maincatagory=?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			
			rs.last();
			int rnum = rs.getRow();
			rs.beforeFirst();
			
			Restaurantlist=new Restaurant[rnum];
			
			int i=0;
			while(rs.next()) {
				Restaurantlist[i]=new Restaurant(rs.getString(1), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));
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
		public Restaurant[] get_Restaurnatlist() {
			return this.Restaurantlist;
		}
}
