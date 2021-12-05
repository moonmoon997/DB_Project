import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RecommendSystem {
	private Food[] Foodlist;
	private Food food;
	String[] mainc = null;
	String[] midc = null;
	public RecommendSystem() {
		
	}
	public Food recommend(String main,String mid) throws SQLException{
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
		String sql1="SELECT * FROM (SELECT * FROM food ORDER BY dbms_random.value) WHERE rownum=1";
		String sql2="SELECT * FROM (SELECT * FROM food ORDER BY dbms_random.value) WHERE MAINCATAGORY=? and rownum=1";
		String sql3="SELECT * FROM (SELECT * FROM food ORDER BY dbms_random.value) WHERE MAINCATAGORY=? and MIDDLECLASS=? and rownum=1";
		
		if(main.equals("") && mid.equals("")) { //占쏙옙�겫袁⑥첒筌랃옙 占쎄퐨占쎄문占쎈뻥占쎌뱽占쎈르
			sql=sql1;
		}
		else if(mid.equals("") && !main.equals("")) {//餓λ쵎�뀋�몴�꼶彛� 占쎄퐨占쎄문占쎈뻥占쎌뱽占쎈르
			sql=sql2;
		}
		else sql=sql3;
		
		Food val=null;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if(sql.equals(sql1)) {
				
			}
			else if(sql.equals(sql2)) {
				pstmt.setString(1, main);
			}
			else {
				pstmt.setString(1, main);
				pstmt.setString(2, mid);
			}
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
				val=new Food(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
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