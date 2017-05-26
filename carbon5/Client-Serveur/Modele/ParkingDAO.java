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
public class ParkingDAO extends DAO<Parking> {

    public ParkingDAO(Connection conn) {
        super(conn);
    }
    
    public ArrayList<String> getAllParkings(){
    	
    ArrayList<String> parking = new ArrayList<String>();
    	try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT NumParking, NomParking, Capacity FROM parking"
                                             );
            Parking.emptyCollection();
            while(result.next()){
            	Parking.addParkingToCo(new Parking(result.getInt("NumParking"),
						result.getString("NomParking"),                        
						result.getInt("Capacity")));
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    	//Getting number of parts
    	parking.add(String.valueOf(Parking.getAllParkings().size()));
    	for(Parking aParking : Parking.getAllParkings()){
    		//adding parts
    		parking.add(Parking.serialize(aParking));
    	}
        return parking;
    }
    
    @Override
    public Parking find() {
        Parking park = new Parking();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM parking" 
                                             );
            if(result.first())
            		park = new Parking(
                                        result.getInt("numParking"),
                                        result.getString("nameParking"), 
                                        result.getInt("capacity")
                        );            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return park;
    }

    @Override
    public ArrayList<String> create(Parking obj) {
    	ArrayList<String> queryResult = new ArrayList<String>();
        try {
            
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO parking (NomParking, Capacity) VALUES(?, ?)"
                                                              );

                    prepare.setString(1, obj.getNameParking());
                    prepare.setInt(2, obj.getCapacity());
                    
                    prepare.executeUpdate();
                    //obj = this.find();
                    queryResult.add("CreateParkOK");
        } catch (SQLException e) {
                e.printStackTrace();
                queryResult.add("CreateParkKO");
        }
        return queryResult;
    }

    @Override
    public boolean update(Parking obj) {
        try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE parking SET Capacity = '" + obj.getCapacity() + "'"+
                    "NameParking ='" + obj.getNameParking() + "' " +
                    " WHERE NumParking = " + obj.getNumParking()
                 );
                    //obj = this.find();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Parking obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM parking WHERE NumParking = " + obj.getNumParking()
               );
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }
}
