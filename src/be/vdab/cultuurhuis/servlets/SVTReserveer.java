package be.vdab.cultuurhuis.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Voorstelling;


public class SVTReserveer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SVTReserveer() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		String view ="/JSP/reserveren.jsp";
		try {
			int id = Integer.parseInt(request.getParameter("vID"));
			Voorstelling vs = voorstellingDAO.getVoorstelling(id);
			if(vs !=null){
				request.setAttribute("voorstelling", vs);
				request.setAttribute("subtitle", String.format("%s Reserveren", vs.getTitle()));
			}else{
				request.setAttribute("fouten", "De gevraagde voorstelling kan niet worden gevonden.");
			}
		} catch (DAOException daoExc) {
			request.setAttribute("fouten", daoExc.getMessage());
		}catch(NumberFormatException numExc){
			request.setAttribute("fouten", "De gevraagde voorstelling kan niet worden gevonden.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
