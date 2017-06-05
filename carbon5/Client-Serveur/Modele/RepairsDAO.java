/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <>
 * @author Carbon5
 */
public class RepairsDAO extends DAO<Repairs> {
    
    public RepairsDAO(Connection conn){
        super(conn);
    }

   /**
    * Allows to delete an entry from the database
    * @param obj
    * @return 
    */
    @Override
    public boolean delete(Repairs obj) {
    	try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM repairs WHERE Id = " + obj.getId()
               );
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }

        /**
         * Creates an entry in the database relative to an object
         * @param obj
         * @return 
         */
	@Override
	public ArrayList<String> create(Repairs obj) {
		ArrayList<String> queryResult = new ArrayList<String>();
		try {
           
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO repairs (DateRepair, Nature, TimeSpent, Description) VALUES(?,?,?,?)"
                                                              );

                    prepare.setDate(1, (java.sql.Date) obj.getDateRepair());
                    prepare.setString(2, obj.getNature());
                    prepare.setFloat(3, obj.getTimeSpent());
                    prepare.setString(4, obj.getDescription());

                    prepare.executeUpdate();
                    queryResult.add("CreateRepairOK");
        } catch (SQLException e) {
                e.printStackTrace();
                queryResult.add("CreateRepairKO");
        }
        return queryResult;
	}

        /**
         * Allows to update the data of an entry in the database
         * @param obj 
         */
	@Override
	public boolean update(Repairs obj) {
		try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE repairs SET Description = '" + obj.getDescription() + "'"+
                    "Nature ='" + obj.getNature() + "' " +
                    "TimeSpent ='" + obj.getTimeSpent() + "' " +
                    " WHERE Id = " + obj.getId()
                 );
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
	}
	
	/**
        * Allows to retrieve an object via its ID
        * @param id
        * @return 
        */
	@Override
	public Repairs find() {
		Repairs ud = new Repairs();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM repairs" 
                                             );
            if(result.first())
            		ud = new Repairs(result.getInt("Id"), result.getDate("DateRepair"),
            						 result.getString("Nature"), result.getFloat("TimeSpent "), result.getString("Description"));            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return ud;
	}   
	
	public Repairs getRepair(int id){
		Repairs ud = new Repairs();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM repairs WHERE id='"+id+"'" 
                                             );
            if(result.first())
            		ud = new Repairs(result.getInt("Id"), result.getDate("DateRepair"),
            						 result.getString("Nature"), result.getFloat("TimeSpent "), result.getString("Description"));            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return ud;
	}
}
