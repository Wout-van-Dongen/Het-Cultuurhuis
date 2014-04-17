package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.utils.DAOException;

public class DAOSchrijfReservatieWeg extends DataAccesObject{

	public synchronized boolean schrijfWeg(Reservatie reservatie, String klantnr) throws DAOException{
		Connection connection = null;
		PreparedStatement statement = null;
		final String QUERY_RES = "INSERT INTO reservaties (klantnr, voorstellingsnr, plaatsen)"
				+ " VALUES (?,?,?)";
		final String QUERY_VS = "UPDATE voorstellingen SET vrijeplaatsen = vrijeplaatsen - ?"
				+ " WHERE voorstellingsnr = ? AND vrijeplaatsen >= ?";

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			connection = getConnection();			
			statement = connection.prepareStatement(QUERY_VS);
			statement.setLong(1, reservatie.getVoorstelling().getVrijePlaatsen());
			statement.setLong(2, reservatie.getVoorstelling().getVoorstellingsNr());
			statement.setLong(3, reservatie.getVoorstelling().getVrijePlaatsen());
			if(statement.executeUpdate()> 0){
				statement = connection.prepareStatement(QUERY_RES);
				statement.setLong(1, reservatie.getVoorstelling().getVrijePlaatsen());
				statement.setLong(2, reservatie.getVoorstelling().getVoorstellingsNr());
				statement.setLong(3, reservatie.getVoorstelling().getVrijePlaatsen());
			}
			
			connection.commit();
		} catch (SQLException sqlExc) {

		}
	}

}
