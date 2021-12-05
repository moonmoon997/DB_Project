
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FavoriteSystem {
   public FavoriteSystem() {
      
   }
   public void update() throws SQLException{
      Connection con=null;
      String url="jdbc:oracle:thin:@localhost:1521:XE";
      String id="RS";
      String password="1234";
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("Driver OK.");
      }
      catch(ClassNotFoundException e) {
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
      
      try {
         CallableStatement cstmt = con.prepareCall( "{call review_number } "); //프로시저 호출
                  
         ResultSet rs = cstmt.executeQuery();
        
         cstmt.close(); 
         rs.close(); 
         } 
      catch (SQLException e) { 
    	  e.printStackTrace(); 
    	  }
      }  
}