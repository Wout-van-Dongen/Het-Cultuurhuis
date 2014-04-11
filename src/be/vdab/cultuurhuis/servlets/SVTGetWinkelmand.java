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

import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.utils.DAOException;

@WebServlet("/winkelmand")
public class SVTGetWinkelmand extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SVTGetWinkelmand() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String VIEW ="/JSP/winkelmand.jsp";
		DAOVoorstellingen voorstellingsDAO = new DAOVoorstellingen();
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Long> reservaties = (Map<Long, Long>)session.getAttribute("winkelmand");
		if(reservaties == null){
			reservaties = new LinkedHashMap<Long, Long>();
			System.out.print("Geen reservaties binnen gekomen.\n");
		}
		ArrayList<Reservatie> basket = new ArrayList<Reservatie>();
		try{
			for(Map.Entry<Long, Long> entry:reservaties.entrySet()){
				basket.add(new Reservatie(voorstellingsDAO.getVoorstelling(entry.getKey().intValue()), entry.getValue().intValue()));
			}
			request.setAttribute("basket", basket);
		}catch(DAOException daoExc){

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}
}
