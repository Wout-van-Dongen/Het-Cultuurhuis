package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
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
		String view ="/WEB-INF/JSP/reserveren.jsp";
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
		String redirectURL ="/winkelmand";
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		Long vID = null, seats = null;
		if (request.getParameter("seats") != null || request.getParameter("vID") != null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Long> geboekteVoorstellingen = (Map<Long, Long>)session.getAttribute("winkelmand");
			if(geboekteVoorstellingen == null){
				geboekteVoorstellingen = new LinkedHashMap<Long, Long>();
			}

			/*==Checking if someone tampered with the voorstellingsID==*/
			try{
				System.out.println(vID = Long.parseLong(request.getParameter("vID")));
			}catch(NumberFormatException numExc){
				System.out.println("Invalid vID");
				redirectURL="/voorstellingen";
				request.setAttribute("fouten", "Tik een getal.");
				RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
				dispatcher.forward(request, response);
			}

			try{
				seats = Long.parseLong(request.getParameter("seats"));
				Voorstelling vs = voorstellingDAO.getVoorstelling((vID.intValue()));
				if(vs.getVrijePlaatsen() >= seats){				
					if(geboekteVoorstellingen.containsKey(vID)){
						Long reservation = geboekteVoorstellingen.get(vID);
						reservation += seats;
						geboekteVoorstellingen.put(vID, reservation);
					}else{
						geboekteVoorstellingen.put(vID, seats);
					}
					session.setAttribute("winkelmand", geboekteVoorstellingen);
				}else if(vs.getVrijePlaatsen() == 0){
					System.out.println("Invalid vID");
					redirectURL="/WEB-INF/JSP/reserveren.jsp";
					request.setAttribute("fouten", "Er zijn geen vrije plaatsen meer voor deze voorstelling.");
					request.setAttribute("voorstelling", vs);
					RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
					dispatcher.forward(request, response);
				}else{
					redirectURL="/WEB-INF/JSP/reserveren.jsp";
					request.setAttribute("fouten", "Tik een getal tussen 1 en " + vs.getVrijePlaatsen() + ".");
					request.setAttribute("voorstelling", vs);
					RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
					dispatcher.forward(request, response);
				}
			}catch(NumberFormatException numExc){
				redirectURL="/WEB-INF/JSP/Reserveren";
				request.setAttribute("fouten", "Tik een getal.");
				RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
				dispatcher.forward(request, response);
			} catch (DAOException daoExc) {
				redirectURL="/voorstelling";
				request.setAttribute("fouten", daoExc.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
				dispatcher.forward(request, response);
			}catch(NullPointerException nullExc){
			}finally{
				response.sendRedirect(response.encodeRedirectURL(
						request.getContextPath() + redirectURL));
			}
		}
	}
}
