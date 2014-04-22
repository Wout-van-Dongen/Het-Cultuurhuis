package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.cultuurhuis.entities.Persoon;
import be.vdab.cultuurhuis.utils.DAOException;

public class DAOUsers extends DataAccesObject{


	public boolean login(String gebruikersnaam, String password) throws DAOException{
		String QUERY ="SELECT paswoord FROM klanten"
				+ " WHERE gebruikersnaam = ?";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setString(1,gebruikersnaam.toLowerCase());
			rs = statement.executeQuery();
			if(rs.next()){
				System.out.println("Result: " +rs.toString());
				if(password.equals(rs.getString("paswoord"))){
					return true;
				}
			}
			return false;
		}catch(SQLException sqlExc){
			throw new DAOException("Kan gebruikers niet lezen uit database", sqlExc);
		}
	}

	public synchronized long getKlantNr(String username) throws DAOException{
		String QUERY ="SELECT klantnr FROM klanten WHERE gebruikersnaam = ?;";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if(rs.next()){
				return rs.getLong(1);
			}else{
				return 0;
			}
		} catch (SQLException sqlExc){
			throw new DAOException("Kan geen verbinding maken met klanten!",sqlExc);
		}
	}


	public synchronized boolean addUser(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente, String gebruikersnaam, String pass) throws DAOException{
		final String
		QUERY1 ="SELECT gebruikersnaam FROM klanten"
				+ " WHERE gebruikersnaam =  ?",
				QUERY2 ="INSERT INTO klanten (voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY1);
			statement.setString(1,gebruikersnaam.toLowerCase().toLowerCase());
			System.out.println("Query: " + statement.toString()
					+"\n\tsrc:DAOUsers.java");
			rs = statement.executeQuery();
			if(rs.next()){//When the username already exists;
				return false;
			}else{
				statement = connection.prepareStatement(QUERY2);
				statement.setString(1,voornaam);
				statement.setString(2,familienaam);
				statement.setString(3,straat);
				statement.setString(4,huisnr);
				statement.setString(5,postcode);
				statement.setString(6,gemeente);
				statement.setString(7,gebruikersnaam.toLowerCase());
				statement.setString(8,encrypt(gebruikersnaam.toLowerCase(), pass));
				System.out.println("Query: " + statement.toString()
						+"\n\tsrc:DAOUsers.java");
				if(0<statement.executeUpdate()){
					return true;
				}else{
					return false;
				}


			}

		}catch(SQLException sqlExc){
			throw new DAOException("Kan users niet schrijven naar database", sqlExc);
		}

	}

	private String encrypt(String user, String pass){
		return pass;
		/*long passCode=0,userCode=0;
		for(char c: pass.toCharArray()){
			passCode = passCode*777 + (int)c;
		}
		for(char c: user.toCharArray()){
			userCode = userCode*777 + (int)c;
		}
String conversion =""+passCode%(userCode);
		return conversion.substring(conversion.length()-50, conversion.length());
		 */
	}

	public Persoon getUserData(String user) throws DAOException{
		final String QUERY="SELECT voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam	"
				+ " FROM klanten"
				+ " where gebruikersnaam = ?";
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setString(1, user);
			rs = statement.executeQuery();
			if(rs.next()){
				return new Persoon(
						rs.getString("voornaam"),
						rs.getString("familienaamnaam"),
						rs.getString("straat"),
						rs.getString("huisnr"),
						rs.getString("postcode"),
						rs.getString("gemeente"),
						rs.getString("gebruikersnaam.toLowerCase()")
						);
			}else{
				return null;
			}
		} catch (SQLException sqlExc) {
			throw new DAOException("Kan geen verbinding maken met gebruikers in de databank",sqlExc);
		}





	}

}
