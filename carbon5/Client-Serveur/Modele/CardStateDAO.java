package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class interacting with database for table CardState
 * @author Picture
 *
 */
public class CardStateDAO {
	private static ArrayList<CardState> listCardState = new ArrayList();
	
	/**
	 * returns all CardState
	 * @param con
	 * @return dataResult containing all serialized CardState
	 */
	public static ArrayList<String> getAllCardState(Connection con){
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			String request = "Select * FROM CardState";
			rs = st.executeQuery(request);
			
			while(rs.next()){
				int idNew = Integer.parseInt(rs.getString("id"));
				listCardState.add(new CardState(idNew, rs.getString("description")));
				dataResult.add(rs.getString("description")+"///");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return dataResult;
	}
	
	/**
	 * request to insert a new CardState
	 * @param con
	 * @param desc
	 * @return dataResult confirming adding a CardState
	 */
	public static ArrayList<String> addCardState(Connection con, String desc, int id){
		Connection cn = con;
		Statement st = null;
		String request = "INSERT INTO CardState(id,descrition) VALUES ('"+id+"','"+desc+"')";
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			st.executeUpdate(request);
			dataResult.add("Success Insert");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dataResult;
	}
	
	/**
	 * request to update a CardState
	 * @param con
	 * @param id
	 * @param desc
	 * @return dataResult confirming the update
	 */
	public static ArrayList<String> updateCardState(Connection con, int id, String desc){
		Connection cn = con;
		Statement st = null;
		String request = "UPDATE CardState SET description = '"+desc+"' WHERE id='"+id+"'";
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			st.executeUpdate(request);
			dataResult.add("Success Update");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dataResult;
	}
	
	/** 
	 * request to remove a CardState
	 * @param con
	 * @param id
	 * @return dataResult confirming deletion of CardState
	 */
	public static ArrayList<String> removeCardState(Connection con, int id){
		Connection cn = con;
		Statement st = null;
		String request = "DELETE FROM CardState WHERE id='"+id+"'";
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			st.executeUpdate(request);
			dataResult.add("Success Removal");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dataResult;
	}
}
