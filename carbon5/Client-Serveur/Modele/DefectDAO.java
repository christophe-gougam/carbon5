/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import com.mysql.jdbc.PreparedStatement;

import Serveur.Controlleurs.CarController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;


/**
 *
 * @author Carbon5
 */
public class DefectDAO extends DAO<Defect>{


	final static Logger logger = Logger.getLogger(CarController.class);
	
    public DefectDAO(Connection conn) {
        super(conn);
    }
    
    
    
    
    /**
	 * request to get all typecar from database
	 * @param con
	 * @return dataResult containing all serialized defect
	 */
	public ArrayList<String> getAllDefect(){
		ArrayList<String> pane = new ArrayList<String>();
		
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM defect"
                                             );
            Defect.emptyCollection();
            
            while(result.next()){
            	
            	Defect.addPartToCo(new Defect(
						result.getInt("Id"),
						result.getString("description"),
						result.getInt("RepairTime")));          		
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
		//Getting number of parts
		pane.add(String.valueOf(Defect.getAllDefect().size()));
    	for(Defect typeC : Defect.getAllDefect()){
    		//adding parts
    		pane.add(Defect.serialize(typeC));
    	}
        return pane;
	
	}
	public ArrayList<Defect> searchDefect(){
		ArrayList<Defect> pan = new ArrayList<Defect>();
		
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT Id, Description, RepairTime FROM defect"
                                             );
            
            while(result.next()){
            	
            	pan.add(new Defect(
						result.getInt("Id"),
						result.getString("Description"),
						result.getInt("RepairTime")));
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return pan;
	
	}
    /**
     * Allows to retrieve an object via its ID
     * @return object
     */
	
    @Override
    public Defect find() {
        Defect ud = new Defect(0, null, 0);
        
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM defect" 
                                             );
			while(result.next()){
			            	
				ud=new Defect(
					result.getInt("Id"),
					result.getString("description"),
					result.getInt("duration"));
					
			}          
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
    public ArrayList<String> create(Defect obj) {
    	ArrayList<String> queryResult = new ArrayList<String>();
        try {
            
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO defect (Description) VALUES(?)"
                                                              );

                    prepare.setString(1, obj.getDescription());

                    prepare.executeUpdate();
                    //obj = this.find();
                    queryResult.add("CreateDefectOK");
        } catch (SQLException e) {
                e.printStackTrace();
                queryResult.add("CreateDefectKO");
        }
        return queryResult;
    }

    /**
     * Allows to update the data of an entry in the database
     * @param obj
     * @return true
     */
    @Override
    public boolean update(Defect obj) {
        try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE defect SET Description = '" + obj.getDescription() + "'"+
                    " WHERE Id = " + obj.getid()
                 );
                    //obj = this.find();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }

    /**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
    @Override
    public boolean delete(Defect obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM defect WHERE Id = " + obj.getid()
               );

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }   
}
