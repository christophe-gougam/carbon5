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
 *
 * @author Carbon5
 */
public class PlaceDAO extends DAO<Place>{

    public PlaceDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

    
    
    public ArrayList<String> getPlace(){
        ArrayList<String> allplace = new ArrayList<String>();
	    try {
		        ResultSet result = this .connect
		                                .createStatement(
		                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
		                                            ResultSet.CONCUR_UPDATABLE
		                                         ).executeQuery(
		                                            "SELECT * FROM place where IsOccupied="+false 
		                                         );
		        Place.emptyCollection();
		        
		        while(result.next()){
		        	
		        	Place.addplaceToCo(new Place(
							result.getInt("NumPlace"),
							result.getBoolean("IsOccupied"),     
		        			result.getInt("NumPark")));
		        }
	       	}
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
		    //Getting number of place
	    	allplace.add(String.valueOf(Place.getAllplace().size()));
		  	for(Place typeC : Place.getAllplace()){
		  		//adding parts
		  		allplace.add(Place.serialize(typeC));
		  	}
		    return allplace;
	}
    
    /**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
    @Override
    public boolean delete(Place obj) {
    	try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM place WHERE NumPlace = " + obj.getNumPlace()
               );

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }

    /**
     * Creates an entry in the database relative to an object
     * @param obj
     * @return true
     */
    @Override
public ArrayList<String> create(Place obj) {
    	ArrayList<String> queryResult = new ArrayList<String>();
            try {
                   
            java.sql.PreparedStatement prepare= 
                            this.connect.prepareStatement("INSERT INTO place (NumPlace, NumPark, IsOccupied , IdParking) VALUES(?, ?, ?, ?)"
                                                                                            );
            prepare.setInt(1, obj.getNumPlace());
            prepare.setInt(2, obj.getNumPark());
            prepare.setBoolean(3, obj.getIsOccupied());

            prepare.executeUpdate();
            queryResult.add("CreatePlaceOK");
    } catch (SQLException e) {
            e.printStackTrace();
            queryResult.add("CreatePlaceKO");
    }
    return queryResult;
    }

    /**
     * Allows to update the data of an entry in the database
     * @param obj
     * @return true
     */
    public void updatePlace(int obj) {
            try {	
		        this .connect	
		             .createStatement(
		                ResultSet.TYPE_SCROLL_INSENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE
		             ).executeUpdate(
		                "UPDATE place SET IsOccupied = " + true+
		                " WHERE NumPlace = " + obj
		             );
            }catch (SQLException e) {
            e.printStackTrace();
            }
    }

    /**
     * Allows to retrieve a place via its ID
     * @return object
     */
    @Override
    public Place find() {
            Place ud = new Place();
    try {
        ResultSet result = this .connect
                                .createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_UPDATABLE
                                         ).executeQuery(
                                            "SELECT * FROM place where IsOccupied=" 
                                         );
        if(result.first())
                    ud = new Place(result.getInt("NumPlace"), result.getBoolean("IsOccupied"), result.getInt("NumPark")); 
    } catch (SQLException e) {
            e.printStackTrace();
    }
    return ud;
    }
    
    public Place getPlace(int id){
    	Place place = new Place();
    	
    	try{
    		ResultSet result = this .connect
                    .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_UPDATABLE
                             ).executeQuery(
                                "SELECT NumPlace,isOccupied,NumPark FROM place where NumpLace='"+id+"'"
                             );
    		if(result.first())
    			place = new Place(result.getInt("NumPlace"), result.getBoolean("IsOccupied"), result.getInt("NumPark")); 
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return place;
    }



	@Override
	public boolean update(Place obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
