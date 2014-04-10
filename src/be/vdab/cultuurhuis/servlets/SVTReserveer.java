package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Boeking;
import be.vdab.cultuurhuis.entities.Voorstelling;

@WebServlet("/reserveer")
public class SVTReserveer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();

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
		final String VIEW="/JSP/winkelmand.jsp";
		String redirectURL ="/winkelmand";
		if (request.getParameter("seats") != null || request.getParameter("vID") != null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Boeking> geboekteVoorstellingen = (Set<Boeking>)session.getAttribute("winkelmand");
			if(geboekteVoorstellingen == null){
				geboekteVoorstellingen = new LinkedHashSet<Boeking>();
			}
			try{			int id = Integer.parseInt(request.getParameter("vID"));
			Voorstelling vs = voorstellingDAO.getVoorstelling(id);
			if(vs !=null){
				geboekteVoorstellingen.add(new Boeking(vs, Integer.parseInt(request.getParameter("seats"))));
				session.setAttribute("winkelmand", geboekteVoorstellingen);
			}else{
				redirectURL="/reserveer";
				request.setAttribute("fouten", "De gevraagde voorstelling kan niet worden gevonden.");
			}
			} catch (DAOException daoExc) {
				redirectURL="/reserveer";
				request.setAttribute("fouten", daoExc.getMessage());
			}catch(NumberFormatException numExc){
				redirectURL="/reserveer";
				
				request.setAttribute("fouten", "");
			}finally{
				response.sendRedirect(response.encodeRedirectURL(
						request.getContextPath() + redirectURL));
			}
		}
	}
}
