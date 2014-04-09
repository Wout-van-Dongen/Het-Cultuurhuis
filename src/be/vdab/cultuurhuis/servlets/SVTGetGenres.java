package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.data.DAOGenres;

public class SVTGetGenres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/JSP/genreMenu.jsp";

	public SVTGetGenres() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOGenres genreDAO = new DAOGenres();
		try {
			Map<Long,String> menuList = genreDAO.getGenreList();
			if(menuList == null){
				request.setAttribute("menuFout", "Er zijn geen Genres gevonden in de databank.");
			}else{
				request.setAttribute("menuList", menuList);
			}
		} catch (DAOException daoExc) {
			request.setAttribute("menuFout", daoExc.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

}
