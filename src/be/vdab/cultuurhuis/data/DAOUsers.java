package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.cultuurhuis.utils.DAOException;

public class DAOUsers extends DataAccesObject{



	public boolean login(String user, String password) throws DAOException{
		String QUERY ="SELECT paswoord FROM klanten"
				+ " WHERE gebruikersnaam = ?";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY);
			statement.setString(1,user);
			rs = statement.executeQuery();
			if(rs.next()){
				if(encrypt(user, password) == rs.getString("paswoord")){
					return true;
				}
			}
			return false;
		}catch(SQLException sqlExc){
			throw new DAOException("Kan users niet lezen uit database", sqlExc);
		}
	}

	public boolean addUser(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente, String gebruikersnaam, String pass) throws DAOException{
		final String
		QUERY1 ="SELECT gebruikersnaam FROM klanten"
				+ " WHERE gebruikersnaam =  ?",
		QUERY2 ="INSERT INTO klanten (voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord)"
				+ " SET (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = getConnection();
			statement = connection.prepareStatement(QUERY1);
			statement.setString(1,gebruikersnaam);
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
				statement.setString(7,gebruikersnaam);
				statement.setString(8,pass);
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
		long passCode=0,userCode=0;
		for(char c: pass.toCharArray()){
			passCode = passCode*7 + (int)c;
		}
		for(char c: user.toCharArray()){
			userCode = userCode*3 + (int)c;
		}
		
		return ""+passCode%(userCode%10000);

	}

}
