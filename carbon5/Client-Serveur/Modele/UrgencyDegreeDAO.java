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

/**
 *
 * @author Carbon5
 */
public class UrgencyDegreeDAO extends DAO<UrgencyDegree>{

    public UrgencyDegreeDAO(Connection conn) {
        super(conn);
    }
    
    /**
     * Allows to retrieve an object via its ID
     * @return object
     */
    @Override
    public UrgencyDegree find() {
        UrgencyDegree ud = new UrgencyDegree();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM urgencydegree" 
                                             );
            if(result.first())
            		ud = new UrgencyDegree(
                                        result.getString("Description") 
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
    public boolean create(UrgencyDegree obj) {
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
                                                              "INSERT INTO urgencydegree (Id, Description) VALUES(?, ?)"
                                                              );
                    prepare.setLong(1, id);
                    prepare.setString(2, obj.getDescription());

                    prepare.executeUpdate();
                    //obj = this.find();
            }
        } catch (SQLException e) {
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
    public boolean update(UrgencyDegree obj) {
        try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE urgencydegree SET Description = '" + obj.getDescription() + "'"+
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
    public boolean delete(UrgencyDegree obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM urgencydegree WHERE Id = " + obj.getId()
               );

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }   
}
