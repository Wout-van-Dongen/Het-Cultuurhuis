package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/winkelmand/remove")
public class SVTRemoveFromWinkelmand extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SVTRemoveFromWinkelmand() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectURL ="/winkelmand";
		if (request.getParameterValues("vID") != null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Long> geboekteVoorstellingen = (Map<Long, Long>)session.getAttribute("winkelmand");
			if(geboekteVoorstellingen == null){
				System.out.print("geen winkelmand gevonden");
				geboekteVoorstellingen = new LinkedHashMap<Long, Long>();
			}else{
			}
			try{
				for(String vID: request.getParameterValues("vID")){
					Long vsID = Long.parseLong(vID);
					geboekteVoorstellingen.remove(vsID);
					session.setAttribute("winkelmand", geboekteVoorstellingen);
				}
			} catch(NumberFormatException numExc){
				redirectURL="/winkelmand";
			}
		}
		response.sendRedirect(response.encodeRedirectURL(
				request.getContextPath() + redirectURL));
	}
}


