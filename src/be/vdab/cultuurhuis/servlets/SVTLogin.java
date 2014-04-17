package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.cultuurhuis.data.DAOUsers;
import be.vdab.cultuurhuis.utils.DAOException;

@WebServlet("/login")
public class SVTLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> err_msgs = null;

	public SVTLogin() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		err_msgs = new ArrayList<String>();
		String view = null, user = null, pass = null, check = null;
		check = request.getParameter("action");
		pass = request.getParameter("pass");
		user = request.getParameter("username");

		//Kijk na of de gebruikersnaam en het paswoord overeen komen

		if(check.equals("lookup")){
			System.out.println("Entered \"lookup\"\n\tsrc:SVTLogin.java");
			if(pass.length() == 0 || user.length() == 0){
				view = "/WEB-INF/JSP/bevestigen.jsp";
				if(user.length() == 0){
					System.out.println("Checks user length.\n\tsrc:SVTLogin.java");
					err_msgs.add("Gelieve een gebruikersnaam in te vullen.");
				}
				if(pass.length() == 0){
					System.out.println("Checks pass length.\n\tsrc:SVTLogin.java");
					err_msgs.add("Gelieve een wachtwoord in te vullen.");
				}
			}else if(pass.length()<8 || user.length()<4){
				view = "/WEB-INF/JSP/bevestigen.jsp";
				if(pass.length()<8){
					err_msgs.add("Uw gebruikersnaam voldoet niet aan de minimum vereisten.");
				}
				if(pass.length()<8){
					err_msgs.add("Uw wachtwoord voldoet niet aan de minimum vereisten.");
				}
			}else{
				view="/WEB-INF/JSP/bevestigen.jsp";
				HttpSession session = request.getSession();
				DAOUsers userDAO = new DAOUsers();
				try{
					if(userDAO.login(user, pass)){
						session.setAttribute("klantnr", user);
					}else{
						err_msgs.add("Onjuist wachtwoord en/of gebruikersnaam.");
					}
				}catch(DAOException daoExc){
					err_msgs.add(daoExc.getMessage());
				}catch (NullPointerException nullExc) {
					System.out.println("Nullpointer caught\n\tsrc:SVTLogin.java");
				}
			}


			//Ga naar registratie nieuwe gebruiker

		}else if(check.equals("new")){
			view="/WEB-INF/JSP/nieuweGebruiker.jsp";

			if(user != null){
				request.setAttribute("user", user);
			}

		}else{
			view="/WEB-INF/JSP/bevestigen.jsp";
			System.out.println("No valid button value!\n\tsrc: SVTLogin.java");
		}

		request.setAttribute("errors", err_msgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		System.out.println("Dispatcher :" +dispatcher + "View: " + view);
		dispatcher.forward(request, response);
	}
}


