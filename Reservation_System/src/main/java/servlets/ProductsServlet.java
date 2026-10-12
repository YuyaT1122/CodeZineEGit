package servlets;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProductsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.Products;
import objects.ProductsDTO;

@WebServlet("/pdservlet")
public class ProductsServlet extends HttpServlet{
 
   public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
  
  //ProductsDAOの新しいインスタンスを作成
  
  ProductsDAO pddao = new ProductsDAO();
  
  //全件検索した結果をDTOオブジェクトとして習得
  ProductsDTO pddto = pddao.select();
  
  ArrayList<Products> list = pddto.getList();
  
 
  
    //検索結果をリクエストスコープに格納
  
  
  
  req.setAttribute("list",list);
  
  //JSPへフォワード
   RequestDispatcher rd = req.getRequestDispatcher("/homepages/products.jsp");
  
   rd.forward(req, res);
  }
  public void doGet(HttpServletRequest req,HttpServletResponse res)
  throws IOException, ServletException{
  doPost(req,res);
  }
}