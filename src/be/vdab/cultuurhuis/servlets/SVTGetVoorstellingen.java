package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.data.DAOGenres;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Voorstelling;


@WebServlet("/voorstellingen")
public class SVTGetVoorstellingen extends HttpServlet {
	private static final String VIEW="/WEB-INF/JSP/voorstellingen.jsp";
	ArrayList<String> err_msgs = null;
	private static final long serialVersionUID = 1L;

	public SVTGetVoorstellingen() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		err_msgs = new ArrayList<String>();
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("gID") != null){
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
						err_msgs.add("het door u meegegeven genre word niet teruggevonden in onze databanken. </br>"
								+ "Gebruik a.u.b. het menu om tussen pagina's te navigeren.");
					}else{
						request.setAttribute("subtitle", genre);
						err_msgs.add("Er zijn geen voorstellingen gevonden binnen het genre " + genre+".");
					}
				}
			} catch (DAOException daoExc) {
				err_msgs.add(daoExc.getMessage());
			}catch(NumberFormatException numExc){
				System.out.println("numformatexc");
				err_msgs.add("Het meegegeven voorstellingsID kan niet worden verwerkt.</br>"
						+ "Gebruik a.u.b. het menu om tussen pagina's te navigeren.");
			}
		}
		request.setAttribute("errors", err_msgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}
}
