package Modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepairCardDAO extends DAO<RepairCard>{
	
	public RepairCardDAO(Connection conn) {
        super(conn);
    }
	
	/**
     * Creates an entry in the database relative to an object
     * @param obj
     * @return true
     */
	public boolean create(RepairCard obj, Date dat) {
        try{
        	java.sql.PreparedStatement prepare = this.connect
                    .prepareStatement(
                    		"INSERT INTO repaircard(IdDegree,IdCard,IdCar, IdParkPlace, EntryDate, OUtDate, OverAllDetails, IdUser) "
                    		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                     );

        	prepare.setInt(1, 1);
        	prepare.setInt(2, obj.getidcard());
        	prepare.setInt(3, obj.getidcar());
        	prepare.setInt(4, obj.getidparkplace());
        	prepare.setDate(5, dat);
        	prepare.setDate(6, obj.getOutDate());
			prepare.setString(7, obj.getOverAllDetails());
			prepare.setInt(8, obj.getuserId());
			
			prepare.executeUpdate();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	
    public ArrayList<String> getAllRepairCard(){
    	
    ArrayList<String> repCards = new ArrayList<String>();
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
            //if(result.first())
            	
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

	@Override
	public RepairCard find() {
		// TODO Auto-generated method stub
		return null;
	} 
	
    public RepairCard find(String name) {
        RepairCard part = new RepairCard();
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM part where NamePart='"+name +"'"
                                             );
            if(result.first());            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return part;
    }
    

    /**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
    @Override
    public boolean delete(RepairCard obj) {
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

    
    /**
     * Allows to update the data of an entry in the database
     * @param obj
     * @return true
     */
  
	@Override
	public boolean update(RepairCard obj) {
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

	@Override
	public ArrayList<String> create(RepairCard obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
