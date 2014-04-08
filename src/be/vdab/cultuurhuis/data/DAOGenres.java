package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import be.vdab.cultuurhuis.utils.DAOException;

public class DAOGenres extends DataAccesObject{



	public Map<Long,String> getGenreList() throws DAOException{
		String QUERY ="SELECT GenreNr, Naam FROM genres";
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		try{
			connection = getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(QUERY);
			return mapResultSet(rs);
		}catch(SQLException sqlExc){
			throw new DAOException("Kan genres niet lezen uit database", sqlExc);
		}
	}

	public String getGenre(int vID) throws DAOException{
		String QUERY ="SELECT Naam FROM genres WHERE genrenr = ?";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setInt(1, vID);
			rs = statement.executeQuery();
			if(rs.next()){
			System.out.println(rs.getString(1));
			return rs.getString(1);
			}else{
				return null;
			}
						
		}catch(SQLException sqlExc){
			throw new DAOException("Kan genre niet lezen uit database", sqlExc);
		}

	}

	private Map<Long,String> mapResultSet(ResultSet rs) throws SQLException{
		Map<Long,String> map = new LinkedHashMap<Long,String>();
		while(rs.next()){
			map.put(rs.getLong(1), rs.getString(2));
		}
		return map;	
	}



}
