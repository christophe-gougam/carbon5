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
         * Method collect cars informations 
         * @return 
         */
        public ArrayList<String> getInfoCar(){
        ArrayList<String> infoCar = new ArrayList<String>();
            try {
                ResultSet result = this .connect
                                        .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                 ).executeQuery(
                                                    "SELECT R.Id AS Clé, C.NumPuce, C.TypeVehicule, CS.Description, R.IdDegree AS Degree, U.Description AS Niveau_urgent FROM repaircard R " +
                                                    "INNER JOIN car C ON R.IdCar = C.NumPuce " +
                                                    "INNER JOIN cardstate CS ON R.IdCard = CS.Id " +
                                                    "INNER JOIN urgencydegree U ON R.IdDegree = U.Id"
                                                 );
                RepairCard.emptyCollection();
                while(result.next()){
                    RepairCard.addRepairCardToCo(new RepairCard(
                        result.getInt("Clé"),
                        result.getString("NumPuce"),
                        (new Car(result.getString("TypeVehicule"))),
                        (new UrgencyDegree(result.getInt("Degree"),result.getString("Niveau_urgent"))),
                        (new CardState(result.getString("CS.Description")))
                    ));
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            infoCar.add(String.valueOf(RepairCard.getInfoCars().size()));
            for (RepairCard aRC: RepairCard.getInfoCars()){
                infoCar.add(RepairCard.serialize_query1(aRC));
            }
            return infoCar;
        }
	
        /**
         * Method collect workflow complete car
         * @return 
         */
        public ArrayList<String> getWorkflowCar(){
            ArrayList<String> wfCar = new ArrayList<String>();
            try {
                ResultSet result = this .connect
                                        .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                 ).executeQuery(
                                                    "SELECT \n" +
                                                    "	RC.Id As Clé,\n" +
                                                    "	NumPuce, \n" +
                                                    "	TypeVehicule, \n" +
                                                    "	IdDegree,\n" +
                                                    "	CS.Description AS State,\n" +
                                                    "	D.Description AS Defect, \n" +
                                                    "	D.RepairTime,\n" +
                                                    "	D.Criticity,\n" +
                                                    "	NamePart,\n" +
                                                    "	R.DateRepair,\n" +
                                                    "	R.Nature,\n" +
                                                    "	R.TimeSpent,\n" +
                                                    "	R.Description,\n" +
                                                    "	Pl.NumPlace,\n" +
                                                    "	NumParking\n" +
                                                    "FROM \n" +
                                                    "	car C, \n" +
                                                    "	repaircard RC, \n" +
                                                    "	urgencydegree U, \n" +
                                                    "	place Pl,\n" +
                                                    "	cardstate CS,\n" +
                                                    "	carddefect CD,\n" +
                                                    "	defect D,\n" +
                                                    "	partdefect PD,\n" +
                                                    "	part Pa,\n" +
                                                    "	partrepairs PR,\n" +
                                                    "	repairs R,\n" +
                                                    "	cardrepairs CR,\n" +
                                                    "	parking Pk\n" +
                                                    "WHERE\n" +
                                                    "	C.NumPuce = RC.IdCar\n" +
                                                    "AND U.Id = RC.IdDegree\n" +
                                                    "AND CS.Id = RC.IdCard\n" +
                                                    "AND Pl.NumPlace = RC.IdParkPlace\n" +
                                                    "AND Pl.NumPark = Pk.NumParking\n" +
                                                    "AND CS.Id = CD.IdCard \n" +
                                                    "AND CD.IdDefect = D.Id\n" +
                                                    "AND D.Id = PD.IdDefect\n" +
                                                    "AND PD.IdPart = Pa.Id\n" +
                                                    "AND Pa.Id = PR.IdPart\n" +
                                                    "AND PR.IdRepair = R.Id\n" +
                                                    "AND R.Id = CR.IdRepair\n" +
                                                    "AND CR.IdCard = RC.Id"
                                                 );
                RepairCard.emptyCollection();
                while(result.next()){
                    RepairCard.addRepairCardToCo(new RepairCard(
                        result.getInt("Clé"),
                        (new Car(result.getString("TypeVehicule"))),
                        (new UrgencyDegree(result.getInt("Degree"),result.getString("Niveau_urgent"))),
                        (new CardState(result.getString("CS.Description"))),
                        (new Part(result.getString("NamePart"))),
                        (new Repairs(result.getString("Nature"),result.getFloat("Description"),result.getString("TimeSpent"))),
                        (new Defect(result.getString("Description"),result.getDouble("RepairTime"),result.getInt("Criticity"))),
                        (new Place(result.getInt("NumPlace"),result.getInt("NumParking"))))
                    );
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            wfCar.add(String.valueOf(RepairCard.getInfoCars().size()));
            for (RepairCard aRC: RepairCard.getInfoCars()){
                wfCar.add(RepairCard.serialize_query2(aRC));
            }
            return wfCar;
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
