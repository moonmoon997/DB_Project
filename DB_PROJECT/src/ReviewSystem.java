import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ReviewSystem {
	private Review[] Reviewlist;
	private Review post;
	private Users us;
	
	public ReviewSystem(Users us) {
		this.us=us;
	}
	
	public int writePost(Review review) throws SQLException {
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "RS";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver OK.");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver failed.");
		}
		
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("Connection OK.");
		}
		catch(SQLException e) {
			System.out.println("Connection failed.");
		}
		String sql="insert into POST values(viewnumSEQ.nextval, ?, ?, ?, ?>)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, post.get_content());
			pstmt.setString(2, post.get_star());
			pstmt.setString(3, post.get_writer());
			pstmt.setString(4, post.get_resname());
			
			int num=pstmt.executeUpdate();
			
			pstmt.close();
			return num;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			con.close();
		}
	}
	public void delPost() {
		
	}
	public void adjustPost() {
		
	}
	public Review[] searchPost(String str, String str2) throws SQLException {
		Review[] rv = null;
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "RS";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver OK.");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver failed.");
		}
		
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("Connection OK.");
		}
		catch(SQLException e) {
			System.out.println("Connection failed.");
		}
		String query = "select * from review where "+str+" like '"+str2+"' order by viewnum desc";
		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery(query);
			
			rs.last();
			int rnum=rs.getRow();
			rs.beforeFirst();
			
			rv=new Review[rnum];
			int i=0;
			
			while(rs.next()) {
				String viewnum=rs.getString(1);
				String content=rs.getString(2);
				String star=rs.getString(3);
				String writer=rs.getString(4);
				String resname=rs.getString(5);
				
			rv[i]=new Review(Integer.parseInt(viewnum), content, star, writer, resname);
				i++;
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		
		return rv;
	}
	public Reviewlist updatePostlist() throws SQLException {
		Reviewlist rl=new Reviewlist();
		//Review[] rll=rl.get_Reviewlist();
		
		return rl;
	}
}