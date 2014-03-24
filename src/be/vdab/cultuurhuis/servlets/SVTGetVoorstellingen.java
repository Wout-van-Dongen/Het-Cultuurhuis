package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.cultuurhuis.data.DAOException;
import be.vdab.cultuurhuis.data.DAOGenres;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Voorstelling;



public class SVTGetVoorstellingen extends HttpServlet {
	private static final String VIEW="/WEB-INF/JSP/voorstellingen.jsp";
	private static final long serialVersionUID = 1L;

	public SVTGetVoorstellingen() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOVoorstellingen voorstellingDAO = new DAOVoorstellingen();
		int id;
		try{
			id = Integer.parseInt(request.getParameter("vID"));
			ArrayList<Voorstelling> voorstellingen = voorstellingDAO.getVoorstellingList(id);
			if(voorstellingen.size() > 0){
				request.setAttribute("voorstellingList", voorstellingen);
				request.setAttribute("subtitle", voorstellingen.get(0).getGenre());
			}else{
				DAOGenres genreDAO = new DAOGenres();
				request.setAttribute("voorstellingList", null);
				request.setAttribute("subtitle", genreDAO.getGenre(id));
			}
		} catch (DAOException daoExc) {
			System.out.println(daoExc.getMessage());
			request.setAttribute("fout", daoExc.getMessage());
		}catch(NumberFormatException numExc){
			//Given invalid characters in ID
			System.out.println("Invalid vID");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}
}
