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
                    infoCar.add(String.valueOf(result.getInt("IdCar")));
                    infoCar.add(String.valueOf(result.getInt("IdDegree")));
                    
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
            //Getting number 
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
        
//        public ArrayList<String> finded() throws SQLException{
//            ArrayList<String> repairCard = new ArrayList<String>();
//            RepairCard rc = new RepairCard();
//            try{
//                ResultSet result = this.connect
//                                       .createStatement(
//                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
//                                                ResultSet.CONCUR_READ_ONLY)
////                                       .executeQuery("SELECT * FROM repaircard WHERE Id = " + id);
//                                       .executeQuery("SELECT * FROM repaircard ");
//                if(result.first()){
////                    rc = new RepairCard(id);
//                    
//                    CardStateDAO stateDao = new CardStateDAO(this.connect);
//                    
//                    result = this.connect.createStatement().executeQuery(
////                         "SELECT Id, Description FROM cardstate" + 
////                         "INNER JOIN repaircard ON cardstate.Id = repaircard.IdCard" +
////                         "AND repaircard.Id = " + id
//                            "SELECT cardstate.Id, Description FROM cardstate, repaircard " + 
//                         "WHERE cardstate.Id = repaircard.IdCard"
//                    );
//                    
//                    while(result.next())
//                        rc.addState(stateDao.find(result.getInt("Id")));
//                    
//                    CarDAO carDao = new CarDAO(this.connect);
//                    
//                    result = this.connect.createStatement().executeQuery(
////                         "SELECT * FROM car" + 
////                         "INNER JOIN repaircard ON car.NumPuce = repaircard.IdCar" +
////                         "AND repaircard.Id = " + id
//                            "SELECT * FROM car, repaircard " + 
//                         "WHERE car.NumPuce = repaircard.IdCar" 
//                    );
//                    
//                    while(result.next())
//                        rc.addCar(carDao.find(result.getString("NumPuce")));
//                    
//                    UrgencyDegreeDAO udDao = new UrgencyDegreeDAO(this.connect);
//                    
//                    result = this.connect.createStatement().executeQuery(
////                         "SELECT * FROM urgencydegree" + 
////                         "INNER JOIN repaircard ON urgencydegree.Id = repaircard.IdDegree" +
////                         "AND repaircard.Id = " + id
//                            "SELECT * FROM urgencydegree, repaircard " + 
//                         "WHERE urgencydegree.Id = repaircard.IdDegree" 
//                    );
//                    
//                    while(result.next())
//                        rc.addUD(udDao.find(result.getInt("Id")));
//                    
//                    RepairCard.emptyCollection();
//                    while(result.next())
//                        RepairCard.addRepairCardToCo(rc);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            repairCard.add(String.valueOf(RepairCard.getInfoCars().size()));
//            for(RepairCard aRC : RepairCard.getInfoCars()){
//                    //adding parts
//                    repairCard.add(RepairCard.serialize(aRC));
//            }
//            return repairCard;
//        }
        
        /**
         * Method verify existance of repair card
         * @param numP
         * @return 
         */
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
