package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
	ArrayList<String> err_msgs = null;
	public SVTReserveer() {
		super();

	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		String view ="/WEB-INF/JSP/reserveren.jsp";
			err_msgs = new ArrayList<String>();
		try {
			int vID = Integer.parseInt(request.getParameter("vID"));
			Voorstelling vs = voorstellingDAO.getVoorstelling(vID);
			if(vs != null){
				request.setAttribute("voorstelling", vs);
				request.setAttribute("subtitle", String.format("%s Reserveren", vs.getTitle()));
			}else{
				err_msgs.add("De gevraagde voorstelling kan niet worden gevonden.");
			}
		} catch (DAOException daoExc) {
			err_msgs.add(daoExc.getMessage());
		}catch(NumberFormatException numExc){
			err_msgs.add("De gevraagde voorstelling kan niet worden gevonden.");
		}
		request.setAttribute("errors", err_msgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectURL ="/winkelmand", view = "/WEB-INF/JSP/reserveren.jsp";
		err_msgs = new ArrayList<String>();
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		Long vID = null, seats = null, vrijePlaatsen;
		Voorstelling vs = null;
		if (request.getParameter("seats") != null && request.getParameter("vID") != null) {
			

			try{
				vID = Long.parseLong(request.getParameter("vID"));
				vs = voorstellingDAO.getVoorstelling((vID.intValue()));
				seats = Long.parseLong(request.getParameter("seats"));
				vrijePlaatsen = vs.getVrijePlaatsen();
				if(vrijePlaatsen >= seats && seats > 0){
					HttpSession session = request.getSession();
					@SuppressWarnings("unchecked")
					Map<Long, Long> geboekteVoorstellingen = (Map<Long, Long>)session.getAttribute("winkelmand");
					if(geboekteVoorstellingen == null){
						geboekteVoorstellingen = new LinkedHashMap<Long, Long>();
					}
					if(geboekteVoorstellingen.containsKey(vID)){
						Long reservation = geboekteVoorstellingen.get(vID);
						reservation += seats;
						geboekteVoorstellingen.put(vID, reservation);
					}else{
						geboekteVoorstellingen.put(vID, seats);
					}
					session.setAttribute("winkelmand", geboekteVoorstellingen);

					response.sendRedirect(response.encodeRedirectURL(
							request.getContextPath() + redirectURL));
				}else if(vrijePlaatsen == 0){
					err_msgs.add("Er zijn geen vrije plaatsen meer voor deze voorstelling.");
					request.setAttribute("voorstelling", vs);
					this.gotoView(request, response, view);  
				}else{
					err_msgs.add("Tik een getal tussen 1 en " + vrijePlaatsen + ".");
					System.out.println();
					request.setAttribute("voorstelling", vs);
					this.gotoView(request, response, view); 
				}
			}catch(NumberFormatException numExc){
				err_msgs.add("Tik een getal.");
				request.setAttribute("voorstelling", vs);
				this.gotoView(request, response, view); 
			} catch (DAOException daoExc) {
				err_msgs.add(daoExc.getMessage());
				this.gotoView(request, response, view); 
			}catch(NullPointerException nullExc){
			}
		}
	}

	private void gotoView(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException{
		request.setAttribute("errors", err_msgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
