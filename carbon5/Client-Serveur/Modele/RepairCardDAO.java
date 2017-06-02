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
	
	public ArrayList<RepairCard> getAllRepairCards(){
		ArrayList<RepairCard> repCards = new ArrayList<RepairCard>();
		 try {
             ResultSet result = this .connect
                                     .createStatement(
                                                 ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                 ResultSet.CONCUR_UPDATABLE
                                              ).executeQuery(
                                                 "SELECT id,idDegree,idCard,idCar,idParkPlace,EntryDate,outDate,OverAllDetails,idUser FROM repaircard"
                                              );
             while(result.next()){
            	 int id = result.getInt("id");
            	 int idDegree = result.getInt("idDegree");
            	 int idCard = result.getInt("idCard");
            	 String idCar = result.getString("idCar");
            	 int idParkPlace = result.getInt("idParkPlace");
            	 Date entry = result.getDate("EntryDate");
            	 Date outDate = result.getDate("outDate");
            	 String overAllDets = result.getString("OverAllDetails");
            	 int idUser = result.getInt("idUser");
            	 
            	 UrgencyDegreeDAO uDDAO = new UrgencyDegreeDAO(this.connect);
            	 UrgencyDegree uD = uDDAO.getUD(idDegree).get(0);
            	 
            	 CardStateDAO cardDAO = new CardStateDAO(this.connect);
            	 CardState card = cardDAO.getCardState(idCard).get(0);
            	 
            	 CarDAO carDAO = new CarDAO(this.connect);
            	 Car car = carDAO.getCar(idCar).get(0);
            	 
            	 PlaceDAO placeDAO = new PlaceDAO(this.connect);
            	 Place place = placeDAO.getPlace(idParkPlace);
            	 
            	 UserDAO userDAO = new UserDAO(this.connect);
            	 User user = userDAO.getUser(idUser);
            	 
            	 ResultSet result2 = this .connect
                         .createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                     ResultSet.CONCUR_UPDATABLE
                                  ).executeQuery(
                                     "SELECT idDefect FROM carddefect WHERE idCard='"+idCar+"'"
                                  );
            	 ArrayList<Defect> defects = new ArrayList<Defect>();
            	 DefectDAO defectDAO = new DefectDAO(this.connect);
            	 while(result.next()){
            		 defects.add(defectDAO.getDefect(result2.getInt("idDefect")));
            	 }
            	 
            	 ArrayList<Repairs> reps = new ArrayList<Repairs>();
            	 RepairCard rep = new RepairCard(uD, card, car, reps, defects, place, entry, outDate,overAllDets, user);   
            	 repCards.add(rep);
             }            
             
         } catch (SQLException e) {
                 e.printStackTrace();
         }
         return repCards;
	}
}
