import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Foodlist {
	private Food[] Foodlist;
	
	public Foodlist() throws SQLException{
		Connection con=null;
		
		String url="jdbc:oracle:thin:@localhost:1521:XE";
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
		
		String sql = "SELECT * FROM food";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			
			rs.last();
			int rnum = rs.getRow();
			rs.beforeFirst();
			
			Foodlist=new Food[rnum];
			
			int i=0;
			while(rs.next()) {
				Foodlist[i]=new Food(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4));
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
		public Food[] get_Foodlist() {
			return this.Foodlist;
		}
}

