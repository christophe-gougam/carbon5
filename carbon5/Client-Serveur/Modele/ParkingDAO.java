/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carbon5
 */
public class ParkingDAO extends DAO<Parking> {

    public ParkingDAO(Connection conn) {
        super(conn);
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
    public boolean create(Parking obj) {
        try {
            //la prochaine valeur de la séquence correspondant à l'id de notre table
            ResultSet result = this.connect
                                   .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    "SELECT NEXTVAL('parking_id_seq') as Id"
                                    );
            if(result.first()){
                    int id = result.getInt("numParking");
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO parking (NumParking, NomParking, Capacity) VALUES(?, ?, ?)"
                                                              );
                    prepare.setInt(1, id);
                    prepare.setString(2, obj.getNameParking());
                    prepare.setInt(3, obj.getCapacity());
                    
                    prepare.executeUpdate();
                    //obj = this.find();
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
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
