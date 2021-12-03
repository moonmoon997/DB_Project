import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RestaurantSystem {
	private Food[] Foodlist;
	private Food food;
	String[] mainc = null;
	String[] midc = null;
	public RestaurantSystem() {
		
	}
	public String[] recommend(String main) throws SQLException{
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
		}
		catch(SQLException e) {
			System.out.println("Connection failed.");
		}
		String sql=null;
		String sql1="SELECT resname FROM restaurant";
		String sql2="SELECT resname FROM restaurant where resfoodinfo=?)";
		
		if(main.equals("")) { //
			sql=sql1;
		}
		else {//
			sql=sql2;
		}
		
		String[] val=null;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if(sql.equals(sql1)) {
				
			}
			else{
				pstmt.setString(1, main);
			}
			
			ResultSet rs = pstmt.executeQuery();
			int i=1;
			while(rs.next()) {
				val[i]=rs.getString(i);
			}
			
			pstmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return val;
	}
}