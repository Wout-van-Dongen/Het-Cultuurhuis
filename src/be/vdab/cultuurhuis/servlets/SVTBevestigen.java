package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.cultuurhuis.data.DAOUsers;
import be.vdab.cultuurhuis.utils.DAOException;

@WebServlet("/bevestigen")
public class SVTBevestigen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> err_msgs = null;
	public SVTBevestigen() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String VIEW= "/WEB-INF/JSP/bevestigen.jsp";

		try{
			String klantnr = (String) request.getAttribute("klantnr");
			DAOUsers userDAO = new DAOUsers();
			request.setAttribute("userdata", userDAO.getUserData(klantnr));
		}catch(NullPointerException nullExc){
		}catch(DAOException daoExc){
			err_msgs.add(daoExc.getMessage());
		}
		request.setAttribute("errors", err_msgs); 
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}




}
