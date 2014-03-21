package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class DAOGenres extends DataAccesObject{

	private final String QUERY ="SELECT GenreNr, Naam FROM genres";

	public Map<Long,String> getGenreList() throws DAOException{
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

	private Map<Long,String> mapResultSet(ResultSet rs) throws SQLException{
		Map<Long,String> map = new LinkedHashMap<Long,String>();
		while(rs.next()){
			map.put(rs.getLong(1), rs.getString(2));
		}
		return map;	
	}


}
