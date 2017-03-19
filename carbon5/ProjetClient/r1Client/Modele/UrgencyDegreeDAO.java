/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Client.Modele;

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
    
    @Override
    public UrgencyDegree find(long id) {
        UrgencyDegree ud = new UrgencyDegree();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM urgencydegree WHERE Id = " + id
                                             );
            if(result.first())
            		ud = new UrgencyDegree(
                                        (int) id, 
                                        result.getString("Description") 
                        );            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return ud;
    }

    @Override
    public UrgencyDegree create(UrgencyDegree obj) {
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
                    obj = this.find(id);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }

    @Override
    public UrgencyDegree update(UrgencyDegree obj) {
        try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE urgencydegree SET Description = '" + obj.getDescription() + "'"+
                    " WHERE Id = " + obj.getId()
                 );
                    obj = this.find(obj.getId());
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(UrgencyDegree obj) {
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
    }   
}
