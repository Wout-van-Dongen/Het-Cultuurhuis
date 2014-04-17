package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.cultuurhuis.data.DAOSchrijfReservatieWeg;
import be.vdab.cultuurhuis.data.DAOVoorstellingen;
import be.vdab.cultuurhuis.entities.Reservatie;

@WebServlet("/SVTRegistreerReservatie")
public class SVTRegistreerReservatie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ArrayList<String> err_msgs = null;
    		   ArrayList<Reservatie>succes, fail;
   
    public SVTRegistreerReservatie() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		err_msgs = new ArrayList<String>();
		succes = new ArrayList<Reservatie>();
		fail = new ArrayList<Reservatie>();
		
	DAOVoorstellingen voorstellingsDAO = new DAOVoorstellingen();
	DAOSchrijfReservatieWeg reservatieDAO = new DAOSchrijfReservatieWeg();
		
	}

}
