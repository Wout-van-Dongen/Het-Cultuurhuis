package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.data.DAOGenres;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Voorstelling;



public class SVTGetVoorstellingen extends HttpServlet {
	private static final String VIEW="/JSP/voorstellingen.jsp";
	private static final long serialVersionUID = 1L;

	public SVTGetVoorstellingen() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		request.setCharacterEncoding("UTF-8");
		try{
			int id = Integer.parseInt(request.getParameter("gID"));
			ArrayList<Voorstelling> voorstellingen = voorstellingDAO.getVoorstellingList(id);
			if(voorstellingen.size() > 0){
				request.setAttribute("voorstellingList", voorstellingen);
				request.setAttribute("subtitle", voorstellingen.get(0).getGenre());
			}else{
				DAOGenres genreDAO = new DAOGenres();
				request.setAttribute("voorstellingList", null);
				String genre= genreDAO.getGenre(id);
				if(genre == null){
					request.setAttribute("foutTitel", "Genre niet gevonden");
					request.setAttribute("fouten", "het door u meegegeven genre word niet teruggevonden in onze databanken. </br>"
							+ "Gebruik a.u.b. het menu om tussen pagina's te navigeren.");
				}else{
					request.setAttribute("subtitle", genre);
					request.setAttribute("foutTitel", "Geen voorstellingen");
					request.setAttribute("fouten", "Er zijn geen voorstellingen gevonden binnen het genre " + genre+".");
				}
			}
		} catch (DAOException daoExc) {
			request.setAttribute("fouten", daoExc.getMessage());
		}catch(NumberFormatException numExc){
			request.setAttribute("foutTitel", "Onjuiste voorstellingsID");
			request.setAttribute("fouten", "Het meegegeven voorstellingsID kan niet worden verwerkt.</br>"
					+ "Gebruik a.u.b. het menu om tussen pagina's te navigeren.");
		}
		request.setAttribute("logo", "voorstellingen.png");
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}
}
