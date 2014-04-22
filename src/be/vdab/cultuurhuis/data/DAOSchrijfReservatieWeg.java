package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.cultuurhuis.entities.Reservatie;
import be.vdab.cultuurhuis.utils.DAOException;

public class DAOSchrijfReservatieWeg extends DataAccesObject{
	private Connection connection = null;
	private PreparedStatement statement = null;
	DAOVoorstellingen voorstellingsDAO = new DAOVoorstellingen();

	public synchronized void schrijfWeg(long voorstellingsnr, long aantal, long klantnr) throws DAOException{

		final String
		QUERY_RES = "INSERT INTO reservaties (klantnr, voorstellingsnr, plaatsen) VALUES (?,?,?);",
		QUERY_VS = "UPDATE voorstellingen SET vrijeplaatsen = vrijeplaatsen - ? WHERE voorstellingsnr = ?;";
		

		long vrijePlaatsen = voorstellingsDAO.getVrijePlaatsen(voorstellingsnr);
		

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			if(vrijePlaatsen >= aantal){
				statement = connection.prepareStatement(QUERY_VS);
				statement.setLong(1, aantal);
				statement.setLong(2, voorstellingsnr);
				statement.executeUpdate();
				statement = connection.prepareStatement(QUERY_RES);
				statement.setLong(1, klantnr);
				statement.setLong(2, voorstellingsnr);
				statement.setLong(3, aantal);
				statement.executeUpdate();
				connection.commit();
			}else{
				throw new DAOException("Er zijn niet voldoende plaatsen beschikbaar.");
			}
		} catch (SQLException sqlExc) {
			throw new DAOException("Er is een fout opgetreden", sqlExc);
		}
	}

	

}
