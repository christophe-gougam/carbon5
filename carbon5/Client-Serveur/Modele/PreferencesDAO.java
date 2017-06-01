package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferencesDAO extends DAO<Preferences>{
	
	public PreferencesDAO(Connection conn){
		super(conn);
	}
	
	/**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
	@Override
	public boolean delete(Preferences obj){
		try{
			this.connect
				.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE
				).executeUpdate(
						"DELETE FROM Prefrences WHERE id = "+ obj.getId()
				);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	 /**
     * Allows to update the data of an entry in the database
     * @param obj
     * @return true
     */
    @Override
    public boolean update(Preferences obj) {
            try {	
        this .connect	
             .createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE
             ).executeUpdate(
                "UPDATE Preferences SET indifDays = '" + obj.getIndifDays() + "' "+
                "vetoDays ='" + obj.getVetoDays() + "' " +
                "indifTimeRep ='" + obj.getIndifTimeRep() + "' " +
                "vetoTimeRep ='" + obj.getVetoTimeRep() + "' "+ 
                " WHERE id = " + obj.getId()
             );
    } catch (SQLException e) {
            e.printStackTrace();
    }
    return true;
    }
    
    /**
     * Allows to retrieve a place via its ID
     * @return object
     */
    @Override
    public Preferences find() {
            Preferences ud = new Preferences();
    try {
        ResultSet result = this .connect
                                .createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_UPDATABLE
                                         ).executeQuery(
                                            "SELECT * FROM preferences" 
                                         );
        if(result.first())
                    ud = new Preferences(result.getInt("id"), result.getFloat("indifDays"), result.getFloat("vetoDays"), result.getFloat("indifTimeRep"), result.getFloat("vetoTimeRep")); 
    } catch (SQLException e) {
            e.printStackTrace();
    }
    return ud;
    }
    
    /**
     * Creates an entry in the database relative to an object
     * @param obj
     * @return true
     */
    @Override
    public ArrayList<String> create(Preferences obj){
    	ArrayList<String> queryResult = new ArrayList<String>();
    	try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "INSERT INTO preferences(id,indifDays, vetoDays, indifTimeRep, vetoTimeRep) VALUES('"+obj.getId()+"', '"+obj.getIndifDays()+"', '"+obj.getVetoDays()+"', '"+obj.getIndifTimeRep()+"', '"+obj.getVetoTimeRep()+"')"
               );
            queryResult.add("addPreferencesOK");
    	}catch (SQLException e){
    		queryResult.add("addPreferencesKO");
    		e.printStackTrace();
    	}
    	return queryResult;
    }
    
    public ArrayList<String> getAllPreferences(){
    	ArrayList<String> prefsSerial = new ArrayList<String>();
    	Preferences pref = new Preferences();
        	try {
                ResultSet result = this .connect
                                        .createStatement(
                                                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                 ).executeQuery(
                                                    "SELECT Id,indifDays, vetoDays, indifTimeRep, vetoTimeRep FROM preferences"
                                                 );
                while(result.next()){

                	pref.setId(Integer.parseInt(result.getString("Id")));
                	pref.setIndifDays(Float.parseFloat(result.getString("indifDays")));
                	pref.setVetoDays(Float.parseFloat(result.getString("vetoDays")));
                	pref.setIndifTimeRep(Float.parseFloat(result.getString("indifTimeRep")));
                	pref.setVetoTimeRep(Float.parseFloat(result.getString("vetoTimeRep")));
                	
                	}            
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            prefsSerial.add(Preferences.serialize(pref));
        	return prefsSerial;
        }
}



