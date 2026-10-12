package servlets;

import java.io.IOException;

import dao.ProductsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import objects.Products;

@WebServlet("/Confirm")
public class ConfirmServlet extends HttpServlet {

	//選択した値を習得するdoGetメソッド
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	            
	            
	            
   //選択した値を取り出す
    String productNumberStr = request.getParameter("ProductNumber");
    
    if(productNumberStr == null || productNumberStr.isEmpty()){
        response.sendRedirect(request.getContextPath() + "/List");
        return;
    }

    int productNumber;
    try{
        productNumber = Integer.parseInt(productNumberStr);
    }catch(NumberFormatException e){
        response.sendRedirect(request.getContextPath() + "/List");
        return;
    }
	//DAOにある商品検索のインスタンスを呼び出し値を当てはめて検索する	 
    ProductsDAO dao = new ProductsDAO();
    Products product = dao.findByProductNumber(productNumber);
    
    if(product == null){
        // 商品が見つからない場合はエラーメッセージを出すか、一覧に戻す
        request.setAttribute("errorMessage", "指定された商品が見つかりませんでした。");
        request.getRequestDispatcher("/homepages/products.jsp").forward(request, response);
        return;
    }
    
    //検索した内容をsessionスコープに保存する
    HttpSession session = request.getSession();
    session.setAttribute("selectedProduct", product);
    
    //フォーワード
    request.getRequestDispatcher("/homepages/reservation-confirm.jsp").forward(request, response);
    }
}