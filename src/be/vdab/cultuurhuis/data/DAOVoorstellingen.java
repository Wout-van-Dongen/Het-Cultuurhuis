package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.vdab.cultuurhuis.entities.Voorstelling;
import be.vdab.cultuurhuis.utils.DAOException;

public class DAOVoorstellingen extends DataAccesObject{

	public ArrayList<Voorstelling> getVoorstellingList(int id) throws DAOException{
		String QUERY
		= "SELECT VoorstellingsNr,genres.Naam,Titel,Uitvoerders,Datum,Prijs,VrijePlaatsen"
				+ " FROM voorstellingen INNER JOIN genres USING(GenreNr)"
				+" WHERE GenreNr = ? AND Datum >= NOW();";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			return mapResultSet(rs);
		}catch(SQLException sqlExc){
			throw new DAOException("Kan voorstellingen niet lezen uit database", sqlExc);
		}
	}

	public Voorstelling getVoorstelling(int vID) throws DAOException{
		String QUERY
		= "SELECT VoorstellingsNr,genres.Naam,Titel,Uitvoerders,Datum,Prijs,VrijePlaatsen"
				+ " FROM voorstellingen INNER JOIN genres USING(GenreNr)"
				+" WHERE voorstellingsNr = ?;";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setInt(1, vID);
			rs = statement.executeQuery();
			if(rs.next()){
				return new Voorstelling(rs.getLong("VoorstellingsNr"),rs.getString("Naam"), rs.getString("Titel"), rs.getString("Uitvoerders"), rs.getBigDecimal("Prijs"), rs.getLong("VrijePlaatsen") , rs.getTimestamp("Datum"));
			}
			return null;

		}catch(SQLException sqlExc){
			throw new DAOException("Kan genre niet lezen uit database", sqlExc);
		}

	}

	private ArrayList<Voorstelling> mapResultSet(ResultSet rs) throws SQLException{
		ArrayList<Voorstelling> voorstellingen = new ArrayList<Voorstelling>();
		while(rs.next()){
			voorstellingen.add(new Voorstelling(rs.getLong("VoorstellingsNr"),rs.getString("Naam"), rs.getString("Titel"), rs.getString("Uitvoerders"), rs.getBigDecimal("Prijs"), rs.getLong("VrijePlaatsen") , rs.getTimestamp("Datum")));
		}
		return voorstellingen;	
	}

	public int getVrijePlaatsen(long voorstellingsnr) throws DAOException{

		final String QUERY_CHECK = "SELECT vrijeplaatsen FROM voorstellingen WHERE voorstellingsnr = ?;";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		//Checks if there are enough seats available
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY_CHECK);
			statement.setLong(1, voorstellingsnr);
			System.out.println(statement);
			rs = statement.executeQuery();

			if(rs.next()){
				System.out.println("rs is not empty");
				return (int)  rs.getLong("vrijeplaatsen");
			}else{
				throw new DAOException("De opgevraagde voorstelling kan niet worden teruggevonden.");
			}
		}catch(SQLException sqlExc){
			throw new DAOException("Er is een fout opgetreden bij het zoeken naar de voorstelling", sqlExc);
		}
		//

	}
}
