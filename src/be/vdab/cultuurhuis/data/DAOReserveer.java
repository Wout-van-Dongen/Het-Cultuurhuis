package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import be.vdab.cultuurhuis.utils.DAOException;
import be.vdab.cultuurhuis.utils.RequestNotFoundException;

public class DAOReserveer extends DataAccesObject{



	public synchronized Boolean reserveer(String vID, int aantal) throws DAOException, RequestNotFoundException{
		String QUERY1 ="SELECT VrijePlaatsen FROM voorstellingen"
				+ "where VoorstellingsNr = ?;";
		String QUERY2 ="UPDATE voorstellingen"
				+ "SET VrijePlaatsen ?";
		int voorstellingsID = Integer.parseInt(vID); 
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY1);
			statement.setInt(1, voorstellingsID);
			rs = statement.executeQuery();
			if(rs.next()){ 
				int vrijePlaatsen = (int) rs.getLong("VrijePlaatsen");
				if(vrijePlaatsen >= aantal){
					statement = connection.prepareStatement(QUERY2);
					statement.setInt(1, (vrijePlaatsen-aantal));
					return statement.execute();
				}else{
					return false;
					}
			}else{
				throw new RequestNotFoundException(String.format("De voorstelling met voorstellingsID %d is niet gevonden.", voorstellingsID));
			}
		}catch(SQLException sqlExc){
			throw new DAOException("Kan genres niet lezen uit database", sqlExc);
		}catch(NumberFormatException numExc){

		}
		return null;
	}

}
