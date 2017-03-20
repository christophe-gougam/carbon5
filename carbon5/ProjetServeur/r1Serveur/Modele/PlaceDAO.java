/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Serveur.Modele;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carbon5
 */
public class PlaceDAO extends DAO<Place>{

    public PlaceDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
    
    @Override
    public Place find(long id) {
        Place ud = new Place();
        
        return ud;
    }

    @Override
    public boolean delete(Place obj) {
        return true;
    }

	@Override
	public boolean create(Place obj) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean update(Place obj) {
		// TODO Auto-generated method stub
		return true;
	}   
}
