package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * Class interacting with database for table CardState
 * @author Picture
 *
 */
/**
 * @author Utilisateur
 *
 */
public class CardStateDAO extends DAO<CardState>{
	
	public CardStateDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	private static ArrayList<CardState> listCardState = new ArrayList();
	
	final static Logger logger = Logger.getLogger(Serveur.class);
	
	/**
	 * returns all CardState
	 * @param con
	 * @return dataResult containing all serialized CardState
	 */
	public String getCardState(){

		String Res="";
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "Select * FROM cardState where "
                                             );

            	
        } catch (SQLException e) {
                e.printStackTrace();
        }
		return Res;
	}
	

	@Override
	public CardState find() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * request to insert a new CardState
	 * @param con
	 * @param desc
	 * @return dataResult confirming adding a CardState
	 */
	@Override
	public ArrayList<String> create(CardState obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * request to update a CardState
	 * @param con
	 * @param id
	 * @param desc
	 * @return dataResult confirming the update
	 */
	@Override
	public boolean update(CardState obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 
	 * request to remove a CardState
	 * @param con
	 * @param id
	 * @return dataResult confirming deletion of CardState
	 */
	@Override
	public boolean delete(CardState obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
