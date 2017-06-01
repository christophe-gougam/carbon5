/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.PartController;
/**
 *
 * @author Carbon5
 */
public class PartDAO extends DAO<Part> {

	final static Logger logger = Logger.getLogger(PartController.class);
	public PartDAO(Connection conn) {
        super(conn);
    }
    public ArrayList<String> getAllParts(){
    	
    ArrayList<String> parts = new ArrayList<String>();
    	try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT Id,Stock,NamePart,PurchasePrice FROM part"
                                             );
            Part.emptyCollection();
            while(result.next()){
            	Part.addPartToCo(new Part(
						String.valueOf(result.getInt("Id")),
						result.getInt("Stock"),
						result.getString("NamePart"), 
						result.getFloat("PurchasePrice")));
            
            		
            }            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    	//Getting number of parts
    	parts.add(String.valueOf(Part.getAllParts().size()));
    	for(Part aPart : Part.getAllParts()){
    		//adding parts
    		parts.add(Part.serialize(aPart));
    	}
        return parts;
    }
    /**
     * Allows to retrieve a part via it's name
     * @return part
     */
    public Part find(String name) {
        Part part = new Part();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM part where NamePart='"+name +"'"
                                             );
            if(result.first())
            		part = new Part(
            							result.getString("Id"),
                                        result.getInt("stock"),
                                        result.getString("namePart"), 
                                        result.getFloat("purchasePrice")
                        );            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return part;
    }
    
    /**
     * Creates an entry in the database relative to an object
     * @param obj
     * @return true
     */
    @Override
    public ArrayList<String> create(Part obj) {
    	ArrayList<String> queryResult = new ArrayList<String>();
    	try {
          java.sql.PreparedStatement prepare = this.connect
                                                   .prepareStatement(
                                                    "INSERT INTO part (Stock, NamePart, PurchasePrice) VALUES(?, ?, ?)"
                                                    );

          prepare.setInt(1, obj.getStock());
          prepare.setString(2, obj.getNamePart());
          prepare.setFloat(3, obj.getPurchasePrice());

          prepare.executeUpdate();
          //obj = this.find();
          
          String message = "CreatePartOK";
          queryResult.add(message);
          
        } catch (SQLException e) {
                e.printStackTrace();
                queryResult.add("CreatePartKO");
        }
        return queryResult;
    }

     /**
      * Allows to update the data of an entry in the database
      * @param obj
      * @return true
      */
    @Override
    public boolean update(Part obj) {
        try {	
            if (obj.getStock()!=1){
            	this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE part SET Stock ='" + obj.getStock() + "', " +
                    "PurchasePrice ='" + obj.getPurchasePrice() + "' " +
                    " WHERE NamePart = '" + obj.getNamePart()+ "' "
                 );
            	return true;
            }
            else{
            	this .connect	
                .createStatement(
                   ResultSet.TYPE_SCROLL_INSENSITIVE, 
                   ResultSet.CONCUR_UPDATABLE
                ).executeUpdate(
                   "UPDATE part SET "+
                   "NamePart ='" + obj.getNamePart() + "', " +
                   "PurchasePrice ='" + obj.getPurchasePrice() + "' " +
                   " WHERE Id = " + obj.getIdPart()
                );
            	return true;
            }
            		
        } catch (SQLException e) {
                e.printStackTrace();
                return false;
        }
        
    }

    /**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
    @Override
    public boolean delete(Part obj) {
    	try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM part WHERE NamePart = '"+obj.getNamePart()+"'"
               );
            return true;
        } catch (SQLException e) {
                e.printStackTrace();
                return false;
        }
    }
    
    public boolean addEntryStock(int userID, Part obj, int qte, LocalDate date){
    	try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "INSERT INTO orderpart(IdPart,IdUser,Qte,date) VALUES ('"+obj.getIdPart()+"','"+userID+"','"+qte+"','"+date+"')"
               );
            obj.setStock(obj.getStock()+qte);
            this.update(obj);
            return true;
        } catch (SQLException e) {
                e.printStackTrace();
                return false;
        }
    }
    
    public boolean addOutStock(int us, Part obj, int qte, LocalDate date){
    	try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "INSERT INTO orderpart(IdPart,IdUser,Qte,date) VALUES ('"+obj.getIdPart()+"','"+us+"','"+(qte*(-1))+"','"+date+"')"
               );
            obj.setStock(obj.getStock()-qte);
            this.update(obj);
            return true;
        } catch (SQLException e) {
                e.printStackTrace();
                return false;
        }
    }

	@Override
	public Part find() {
		// TODO Auto-generated method stub
		return null;
	} 
}
