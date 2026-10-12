package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Complete")
public class CompleteServlet extends HttpServlet{
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); 
        if(session != null){
        
        session.removeAttribute("selectedProduct");
         session.removeAttribute("ProductNumber");
         
         }
         
        request.getRequestDispatcher("/pdservlet").forward(request, response);
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    doPost(request, response);
	}
}