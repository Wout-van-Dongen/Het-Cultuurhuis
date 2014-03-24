package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.vdab.cultuurhuis.entities.Voorstelling;

public class DAOVoorstellingen extends DataAccesObject{

	private final String QUERY
	= "SELECT VoorstellingsNr,genres.Naam,Titel,Uitvoerders,Datum,Prijs,VrijePlaatsen"
			+ " FROM voorstellingen INNER JOIN genres USING(GenreNr)"
			+" WHERE GenreNr = ? AND Datum >= NOW();";

	public ArrayList<Voorstelling> getVoorstellingList(int id) throws DAOException{
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

	private ArrayList<Voorstelling> mapResultSet(ResultSet rs) throws SQLException{
		ArrayList<Voorstelling> voorstellingen = new ArrayList<Voorstelling>();
		while(rs.next()){
			voorstellingen.add(new Voorstelling(rs.getLong("VoorstellingsNr"),rs.getString("Naam"), rs.getString("Titel"), rs.getString("Uitvoerders"), rs.getBigDecimal("Prijs"), rs.getLong("VrijePlaatsen") , rs.getTimestamp("Datum")));
		}
		return voorstellingen;	
	}
}
