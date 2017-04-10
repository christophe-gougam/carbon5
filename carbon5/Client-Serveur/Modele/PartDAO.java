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
public class PartDAO extends DAO<Part> {

    public PartDAO(Connection conn) {
        super(conn);
    }
    
    /**
     * Allows to retrieve an part via its ID
     * @return part
     */
    @Override
    public Part find() {
        Part part = new Part();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM part" 
                                             );
            if(result.first())
            		part = new Part(
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
            //la prochaine valeur de la séquence correspondant à l'id de notre table
            ResultSet result = this.connect
                                   .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    "SELECT NEXTVAL('part_id_seq') as Id"
                                    );
            if(result.first()){
                    long id = result.getLong("id");
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO part (Id, Stock, NamePart, PurchasePrice) VALUES(?, ?, ?, ?)"
                                                              );
                    prepare.setLong(1, id);
                    prepare.setInt(2, obj.getStock());
                    prepare.setString(3, obj.getNamePart());
                    prepare.setFloat(4, obj.getPurchasePrice());

                    prepare.executeUpdate();
                    //obj = this.find();
                    
                    String message = "CreatePartOK";
                    queryResult.add(message);
            }
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
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE part SET Stock = '" + obj.getStock() + "'"+
                    "NamePart ='" + obj.getNamePart() + "' " +
                    "PurchasePrice ='" + obj.getPurchasePrice() + "' " +
                    " WHERE Id = " + obj.getId()
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
    public boolean delete(Part obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM part WHERE Id = " + obj.getId()
               );
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    } 
}
