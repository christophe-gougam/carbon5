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
        
        public ArrayList<String> getInfoCar(){
        ArrayList<String> infoCar = new ArrayList<String>();
            try {
                ResultSet result = this .connect
                                        .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                 ).executeQuery(
                                                    "SELECT * FROM repaircard"
                                                 );
                
                if(result.first()){
//                    infoCar.add(String.valueOf(result.getInt("IdCar")));
//                    infoCar.add(String.valueOf(result.getInt("IdDegree")));
                    
                    CarDAO carDao = new CarDAO(this.connect);
                    
                    result = this.connect
                                 .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                  )
                                 .executeQuery("SELECT NumPuce, TypeVehicule\n" +
                                                "FROM repaircard R, car C\n" +
                                                "WHERE R.IdCar = C.NumPuce");
                    
                    while(result.next())
                        infoCar.add(String.valueOf(carDao.getCar(result.getString("NumPuce"))));
                        
                    CardStateDAO cardStateDao = new CardStateDAO(this.connect);
                    
                    result = this.connect
                                 .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                  )
                                 .executeQuery("SELECT C.Id, Description\n" +
                                                "FROM repaircard R, cardstate C\n" +
                                                "WHERE R.IdCar = C.Id");
                    
                    while(result.next())
                        infoCar.add(String.valueOf(cardStateDao.getCardState(result.getInt("Id"))));
                    
                    UrgencyDegreeDAO udDao = new UrgencyDegreeDAO(this.connect);
                    
                    result = this.connect
                                 .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                  )
                                 .executeQuery("SELECT U.Id, Description\n" +
                                                "FROM repaircard R, urgencydegree U\n" +
                                                "WHERE R.IdCar = U.Id");
                    while(result.next())
                        infoCar.add(String.valueOf(udDao.getUD(result.getInt("Id"))));
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            //Getting number of parts
            infoCar.add(String.valueOf(RepairCard.getInfoCars().size()));
            for(RepairCard aInfoCar : RepairCard.getInfoCars()){
                    //adding parts
                    infoCar.add(RepairCard.serialize(aInfoCar));
            }
            return infoCar;
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
        	prepare.setString(3, obj.getidcar());
        	prepare.setInt(4, obj.getidparkplace());
        	prepare.setDate(5, dat);
        	prepare.setDate(6, obj.getOutDate());
			prepare.setString(7, obj.getOverAllDetails());
			prepare.setInt(8, obj.getUser().getId());
			
			prepare.executeUpdate();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
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
	public boolean existRepairCard(String numP){
		boolean ret=false;
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT IdCar FROM repaircard WHERE IdCar='"+numP+"'"
                                             );
            if(result.first())
            	ret= true;
            			

            	
        } catch (SQLException e) {
                e.printStackTrace();
                ret= false;
        }
		return ret;
	}
    

    /**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
    @Override
    public boolean delete(RepairCard obj) {

                return false;
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
		
                return false;
        
    }

	@Override
	public ArrayList<String> create(RepairCard obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
