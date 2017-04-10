/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Carbon5
 */
public class DefectDAO extends DAO<Defect>{

    public DefectDAO(Connection conn) {
        super(conn);
    }
    
    /**
     * Allows to retrieve an object via its ID
     * @return object
     */
    @Override
    public Defect find() {
        Defect ud = new Defect(0, null);
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM defect" 
                                             );
            if(result.first())
            		ud = new Defect(
                                        0, result.getString("Description") 
                        );            
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
            //Vu que nous sommes sous postgres, nous allons chercher manuellement
            //la prochaine valeur de la séquence correspondant à l'id de notre table
            ResultSet result = this.connect
                                   .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    "SELECT NEXTVAL('ud_id_seq') as Id"
                                    );
            if(result.first()){
                    long id = result.getLong("id");
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO defect (Id, Description) VALUES(?, ?)"
                                                              );
                    prepare.setLong(1, id);
                    prepare.setString(2, obj.getDescription());

                    prepare.executeUpdate();
                    //obj = this.find();
                    queryResult.add("CreateDefectOK");
            }
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
    public boolean delete(Defect obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM defect WHERE Id = " + obj.getId()
               );

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }   
}
