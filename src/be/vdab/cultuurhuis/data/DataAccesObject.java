package be.vdab.cultuurhuis.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import be.vdab.cultuurhuis.utils.DAOException;

public abstract class DataAccesObject {
	private static final String JNDI = "java:/comp/env/jdbc/cultuurhuis";

	public Connection getConnection() throws DAOException{
		Context context = null;
		try{
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(JNDI);
			return dataSource.getConnection();
		}catch(SQLException sqlExc){
			throw new DAOException("Kan geen connectie krijgen van " + JNDI);
		}catch(NamingException nmExc){
			throw new DAOException(JNDI + " is niet gevonden!", nmExc);
		}finally{
			try{
				if(context !=null){
					context.close();
				}
			}catch(NamingException nmExc){
throw new DAOException("Kan " + JNDI + " niet sluiten!", nmExc);
			}
		}

	}

}


