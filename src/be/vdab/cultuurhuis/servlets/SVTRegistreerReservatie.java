package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.sql.SQLException;
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

import be.vdab.cultuurhuis.data.DAOSchrijfReservatieWeg;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.utils.DAOException;

@WebServlet("/reservatie/registratie")
public class SVTRegistreerReservatie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> err_msgs = null;
	ArrayList<Reservatie>succes;
	LinkedHashMap<Reservatie, Long> fail;

	public SVTRegistreerReservatie() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> err_msgs = null;
		final String VIEW= "/WEB-INF/JSP/overzicht.jsp";
		err_msgs = new ArrayList<String>();
		succes = (ArrayList<Reservatie>)request.getAttribute("regSuccesful");
		fail = (LinkedHashMap<Reservatie, Long>)request.getAttribute("regFailed");

		request.setAttribute("regSuccesful", request.getAttribute("regSuccesful"));
		request.setAttribute("regFailed", request.getAttribute("regFailed"));
		HttpSession session = request.getSession();

		succes = new ArrayList<Reservatie>();
		fail = new LinkedHashMap<Reservatie, Long>();

		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response); 

	}

	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String VIEW = "/WEB-INF/JSP/overzicht.jsp";
		err_msgs = new ArrayList<String>();
		succes = new ArrayList<Reservatie>();
		fail = new LinkedHashMap<Reservatie, Long>();

		DAOSchrijfReservatieWeg reservatieDAO = new DAOSchrijfReservatieWeg();
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();

		HttpSession session = request.getSession();

		long vrijePlaatsen = 0;

		long klantNr = (long)session.getAttribute("klantnr");
		LinkedHashMap<Long, Long> winkelmand = (LinkedHashMap<Long, Long>)session.getAttribute("winkelmand");
		for(Map.Entry<Long, Long> entry: winkelmand.entrySet()){
			int 
			voorstellingsID = entry.getKey().intValue(),
			aantalPlaatsen = entry.getValue().intValue();
			try {
				vrijePlaatsen = voorstellingDAO.getVrijePlaatsen(voorstellingsID);
			} catch (DAOException daoExc) {
				err_msgs.add(daoExc.getMessage());
			}
			try {
				reservatieDAO.schrijfWeg(voorstellingsID, aantalPlaatsen, klantNr);
				Voorstelling vs = voorstellingDAO.getVoorstelling(voorstellingsID);
				succes.add(new Reservatie(vs, aantalPlaatsen));
			} catch (DAOException e) {
				Voorstelling vs;
				try {
					System.out.println("VID: "+voorstellingsID);
					vs = voorstellingDAO.getVoorstelling(voorstellingsID);
					fail.put(new Reservatie(vs, aantalPlaatsen), vrijePlaatsen);
				} catch (DAOException daoExc) {
					err_msgs.add(daoExc.getMessage());
				}			
				System.out.println(e.getMessage()+ "\n\tsrc:SVTRegistreerReservatie.java");
			}
		}
		request.setAttribute("errors", err_msgs);
		request.setAttribute("regSuccesful", succes);
		request.setAttribute("regFailed", fail);
		session.removeAttribute("klantnr");
		session.removeAttribute("winkelmand");	
		//forward
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

}
