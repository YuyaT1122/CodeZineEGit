package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;



@WebServlet("/showname2")
public class ShowNameServlet2 extends HttpServlet{
  
   
   
   public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
  
   //StudentDAOの新しいインスタンスを作成し、sdaoに格納
   StudentDAO sdao = new StudentDAO();
   
   //selectの１にある情報をnameに格納
   sdao.connect();
   String name = sdao.select(7);
   sdao.disconnect();
   
   
   req.setAttribute("name",name);
   
    RequestDispatcher rd = req.getRequestDispatcher("javaweb/showname2.jsp");
   
    rd.forward(req, res);
   }
   public void doGet(HttpServletRequest req,HttpServletResponse res)
   throws IOException, ServletException{
   doPost(req,res);
   }
 }