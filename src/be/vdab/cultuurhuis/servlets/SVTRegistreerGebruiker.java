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
import be.vdab.cultuurhuis.entities.Persoon;
import be.vdab.cultuurhuis.utils.DAOException;

@WebServlet("/registreer")
public class SVTRegistreerGebruiker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> err_msgs = null;

	public SVTRegistreerGebruiker() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Rubber Duckie");
		final String VIEW = "/WEB-INF/JSP/nieuweGebruiker.jsp", REDIRECT ="/registreer"; 
		DAOUsers userDAO = null;
		err_msgs = new ArrayList<String>();
		Persoon persoon = new Persoon(request.getParameter("voornaam"),
				request.getParameter("familienaam"),
				request.getParameter("straat"),
				request.getParameter("huisnr"),
				request.getParameter("postcode"),
				request.getParameter("gemeente"),
				request.getParameter("username"),
				request.getParameter("pass");
				String confirmpass = request.getParameter("confirmpass");

		if(persoon.getVoornaam().length() == 0 || familienaam ==null || straat.length() == 0 || huisnr.length() == 0 || postcode.length() == 0 || gemeente.length() == 0 || gebruikersnaam.length() == 0 || wachtwoord.length() == 0 || confirmpass.length() == 0){
			if(persoon.getVoornaam().length() == 0){err_msgs.add("Voornaam niet ingevuld!");}
			if(persoon.getFamilienaam().length() == 0){err_msgs.add("Familienaam niet ingevuld!");}
			if(persoon.getStraat().length() == 0){err_msgs.add("Straat niet ingevuld!");}
			if(persoon.getHuisnr().length() == 0){err_msgs.add("Huisnummer niet ingevuld!");}
			if(postcode.length() == 0){err_msgs.add("Postcode niet ingevuld!");}
			if(gemeente.length() == 0){err_msgs.add("Gemeente niet ingevuld!");}
			if(gebruikersnaam.length() == 0){err_msgs.add("Gebruikersnaam niet ingevuld!");}
			if(wachtwoord.length() == 0 || confirmpass.length() == 0){err_msgs.add("Wachtwoord niet ingevuld!");}

			request.setAttribute("voornaam", voornaam);
			request.setAttribute("familienaam", familienaam);
			request.setAttribute("straat", straat);
			request.setAttribute("huisnr", huisnr);
			request.setAttribute("postcode", postcode);
			request.setAttribute("gemeente", gemeente);
			request.setAttribute("user", gebruikersnaam);

			request.setAttribute("errors", err_msgs);
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
			dispatcher.forward(request, response);

		}else if(!wachtwoord.equals(confirmpass)){
			err_msgs.add("Passwords do not match!");


			request.setAttribute("voornaam", voornaam);
			request.setAttribute("familienaam", familienaam);
			request.setAttribute("straat", straat);
			request.setAttribute("huisnr", huisnr);
			request.setAttribute("postcode", postcode);
			request.setAttribute("gemeente", gemeente);
			request.setAttribute("user", gebruikersnaam);

			request.setAttribute("errors", err_msgs);
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
			dispatcher.forward(request, response);



		}else if(wachtwoord.length()<8 || gebruikersnaam.length() <4){
			if(gebruikersnaam.length() <4){err_msgs.add("Gebruikersnaam moet minstens 4 tekens lang zijn!");}
			if(wachtwoord.length() <8){err_msgs.add("Wachtwoord moet minstens 8 tekens lang zijn!");}

			request.setAttribute("voornaam", voornaam);
			request.setAttribute("familienaam", familienaam);
			request.setAttribute("straat", straat);
			request.setAttribute("huisnr", huisnr);
			request.setAttribute("postcode", postcode);
			request.setAttribute("gemeente", gemeente);
			request.setAttribute("user", gebruikersnaam);

			request.setAttribute("errors", err_msgs);
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
			dispatcher.forward(request, response);

		}else{
			try {
				userDAO = new DAOUsers();
				if(userDAO.addUser(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, wachtwoord)){
					HttpSession session = request.getSession();
					session.setAttribute("user", gebruikersnaam);
					
					response.sendRedirect(response.encodeRedirectURL(
							request.getContextPath() + REDIRECT));
					
				}else{
					err_msgs.add("Gebruiker bestaat al!");
					
					request.setAttribute("voornaam", voornaam);
					request.setAttribute("familienaam", familienaam);
					request.setAttribute("straat", straat);
					request.setAttribute("huisnr", huisnr);
					request.setAttribute("postcode", postcode);
					request.setAttribute("gemeente", gemeente);
					request.setAttribute("user", gebruikersnaam);
					
					request.setAttribute("errors", err_msgs);
					RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
					dispatcher.forward(request, response);
					
				}
			} catch (DAOException daoExc) {


				request.setAttribute("voornaam", voornaam);
				request.setAttribute("familienaam", familienaam);
				request.setAttribute("straat", straat);
				request.setAttribute("huisnr", huisnr);
				request.setAttribute("postcode", postcode);
				request.setAttribute("gemeente", gemeente);
				request.setAttribute("user", gebruikersnaam);
				
				err_msgs.add(daoExc.getMessage());
				request.setAttribute("errors", err_msgs);
				RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
				dispatcher.forward(request, response);
			}
		}


	}

}
