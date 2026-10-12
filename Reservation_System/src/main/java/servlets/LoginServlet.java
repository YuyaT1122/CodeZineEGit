package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet ("/login")
public class LoginServlet extends HttpServlet{
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  
  
  String a = request.getParameter("id");
  Integer userId = Integer.valueOf(a);
  String password = request.getParameter("password");
  
 
  
  final String URL = "jdbc:mysql://localhost:3306/reservation_system";
  final String USER = "root";
  final String PASS = "root";
  
  String sql = "SELECT USER_ID, PASSWORD FROM user WHERE USER_ID =? AND PASSWORD = ? ";
  
  //Connection conn = null;
  
  
  try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}

  try(Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement pstmt = con.prepareStatement(sql)){
	  
	  
        

	  
	 //conn = DriverManager.getConnection(URL,USER, PASS);
	  
        pstmt.setInt(1,userId);
        pstmt.setString(2,password);
        
        
        ResultSet res = pstmt.executeQuery();
        String path = "";
        
        if(res.next()){    
        request.setAttribute("userId", res.getInt("USER_ID"));
        request.setAttribute("password", res.getString("PASSWORD"));
        
        RequestDispatcher rd = request.getRequestDispatcher("/pdservlet");
        
        rd.forward(request, response);
        }else{
        
        request.setAttribute("loginFailure","ログインに失敗しました");
        
         RequestDispatcher rd = request.getRequestDispatcher("/homepages/login.jsp");
        
        rd.forward(request, response);
    }
  }catch (SQLException e){
       e.printStackTrace();
    }//finally {
    	 //{
    		
    		//conn.close();
    	//}
    	//catch(SQLException e) {
    		
    	//}
    	 //}
   }
   
   public void doGet(HttpServletRequest req,HttpServletResponse res)
		   throws IOException, ServletException{
		   doPost(req,res);
		   }
}
