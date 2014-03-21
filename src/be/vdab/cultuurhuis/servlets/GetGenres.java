package be.vdab.cultuurhuis.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;
import be.vdab.cultuurhuis.data.DAOException;
import be.vdab.cultuurhuis.data.DAOGenres;

@WebServlet("/GetGenres")
public class GetGenres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/menu.jsp";

	public GetGenres() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOGenres genreDAO = new DAOGenres();
		try {
			request.setAttribute("menuList", genreDAO.getGenreList());
			request.setAttribute("menuName", "Genres");
		} catch (DAOException e) {
			request.setAttribute("fout", "De lijst met genres kan niet worden opgehaald!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

}