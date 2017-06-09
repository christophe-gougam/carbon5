package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * Class interacting with database for table CardState
 * @author Carbon5
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
	public ArrayList<CardState> getCardState(int id){

		CardState res= new CardState(id,"");
		try {
                    ResultSet result = this .connect
                                            .createStatement(
                                                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                        ResultSet.CONCUR_UPDATABLE
                                                     ).executeQuery(
                                                        "Select * FROM cardstate"
                                                     );
                    CardState.emptyCollection();
                    if(result.first())
                        res = new CardState(result.getInt("Id"),result.getString("Description"));
                    listCardState.add(res);
        } catch (SQLException e) {
                e.printStackTrace();
        }
		return listCardState;
	}
	
	public ArrayList<String> getAllStates(){
    	
	    ArrayList<String> states = new ArrayList<String>();
	    	try {
	            ResultSet result = this .connect
	                                    .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT Id,Description FROM cardState"
	                                             );
	            CardState.emptyCollection();
	            while(result.next()){
	            	CardState.addStateToCo(new CardState(
	            			result.getInt("Id"),
							result.getString("Description")));
	            
	            		
	            }            
	        } catch (SQLException e) {
	                e.printStackTrace();
	        }
	    	//Getting number of parts
	    	states.add(String.valueOf(CardState.getAllState().size()));
	    	for(CardState aState : CardState.getAllState()){
	    		//adding parts
	    		states.add(CardState.serialize(aState));
	    	}
	        return states;
	    }
	
        @Override
	public CardState find() {
		// TODO Auto-generated method stub
		return null;
	}
	
        public CardState find(int id) throws SQLException{
            CardState state = new CardState();
            try{
                ResultSet result = this.connect
                                       .createStatement(
                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_READ_ONLY)
                                       .executeQuery("SELECT * FROM cardstate WHERE Id = " + id);
                if(result.first())
                    state = new CardState(
                    id,
                    result.getString("Description"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return state;
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
