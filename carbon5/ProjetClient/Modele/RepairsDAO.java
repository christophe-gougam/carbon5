/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <>
 * @author Carbon5
 */
public class RepairsDAO extends DAO<Repairs> {
    
    public RepairsDAO(Connection conn){
        super(conn);
    }
    
    /**
     * Allows to retrieve an object via its ID
     * @param id
     * @return 
     */
    @Override
    public Repairs find() {
        
        return null;
    }

   /**
    * Creates an entry in the database relative to an object
    * @param obj
    * @return 
    */
    @Override
    public boolean delete(Repairs obj) {
        return true;
    }

   /**
    * Allows to update the data of an entry in the database
    * @param obj
    * @return 
    */

	@Override
	public boolean create(Repairs obj) {
		// TODO Auto-generated method stub
		return true;
	}

   /**
    * Allows to delete an entry from the database
    * @param obj 
    */

	@Override
	public boolean update(Repairs obj) {
		// TODO Auto-generated method stub
		return true;
	}   
}
