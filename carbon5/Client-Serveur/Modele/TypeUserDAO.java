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
public class TypeUserDAO extends DAO<TypeUser>{

    public TypeUserDAO(Connection conn) {
        super(conn);
    }
    
    /**
     * Allows to retrieve an object via its ID
     * @return object
     */
    @Override
    public TypeUser find() {
    	TypeUser ud = null;
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM typeUser" 
                                             );
            if(result.first())
            		ud = new TypeUser(
            							result.getInt("Id"),
                                        result.getString("Profil") 
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
    public ArrayList<String> create(TypeUser obj) {
    	ArrayList<String> queryResult = new ArrayList<String>();
        try {

                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO typeUser (workingTypeUser) VALUES(?)"
                                                              );
                    prepare.setString(1, obj.getTypeUser());

                    prepare.executeUpdate();
                    //obj = this.find();
                    queryResult.add("CreateTypeUserOK");
        } catch (SQLException e) {
                e.printStackTrace();
                queryResult.add("CreateTypeUserKO");
        }
        return queryResult;
    }

    /**
     * Allows to update the data of an entry in the database
     * @param obj
     * @return true
     */
    @Override
    public boolean update(TypeUser obj) {
        try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE typeUser SET Description = '" + obj.getTypeUser() + "'"+
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
    public boolean delete(TypeUser obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM typeUser WHERE Id = " + obj.getId()
               );

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }


}
