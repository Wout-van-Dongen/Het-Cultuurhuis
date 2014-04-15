package be.vdab.cultuurhuis.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class SVTLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SVTLogin() {
        super();
       
    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view;
		String check = request.getParameter("action");
		if(check.equals("lookup")){
			view="/WEB-INF/JSP/bevestigen.jsp";
			System.out.println("Button value = \"lookup\"!\nSource: SVTLogin.java");
		}else if(check.equals("new")){
			view="/WEB-INF/JSP/nieuweGebruiker.jsp";
			System.out.println("Button value = \"new\"!\nSource: SVTLogin.java");
		}else{
			view="/WEB-INF/JSP/bevestigen.jsp";
			System.out.println("No valid button value!\nSource: SVTLogin.java");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
