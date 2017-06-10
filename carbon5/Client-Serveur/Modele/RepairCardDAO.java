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
            //Getting number of parts
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
                                                    "SELECT R.Id AS Clé, "
                                                            + "IdCar AS ID_car,"
                                                            + "C.TypeVehicule, "
                                                            + "IdDegree AS Degre_urgent, "
                                                            + "U.Description AS Niveau_urgent, "
                                                            + "CS.Description As State, "
                                                            + "R.IdParkPlace AS Place,"
                                                            + "PK.NumParking, "
                                                            + "R.OverAllDetails, "
                                                            + "R.EntryDate, "
                                                            + "R.OutDate, "
                                                            + "REP.Nature AS Nature_repair,"
                                                            + "REP.DateRepair, "
                                                            + "REP.TimeSpent, "
                                                            + "REP.Description AS Repair_ops, "
                                                            + "D.Description AS Defect_description, "
                                                            + "D.RepairTime, "
                                                            + "D.criticity, "
                                                            + "PART.NamePart\n" +
                                                    "FROM repaircard R \n" +
                                                    "INNER JOIN car C ON R.IdCar = C.NumPuce \n" +
                                                    "INNER JOIN cardstate CS ON CS.Id = R.IdCard \n" +
                                                    "INNER JOIN urgencydegree U ON R.IdDegree = U.Id\n" +
                                                    "INNER JOIN cardrepairs CR ON R.Id = CR.IdCard\n" +
                                                    "INNER JOIN repairs REP ON CR.IdRepair = REP.Id\n" +
                                                    "INNER JOIN carddefect CD ON R.Id = CD.IdCard\n" +
                                                    "INNER JOIN defect D ON CD.IdDefect = D.Id\n" +
                                                    "INNER JOIN part PART ON D.partForRepair = PART.Id\n" +
                                                    "INNER JOIN place PL ON R.IdParkPlace = PL.NumPlace\n" +
"                                                    INNER JOIN parking PK ON PL.NumPark = PK.NumParking"
                                                 );
                RepairCard.emptyCollection();
                while(result.next()){
                    RepairCard.addRepairCardToCo(new RepairCard(
                        result.getInt("Clé"),
                        result.getDate("R.EntryDate"),
                        result.getDate("R.OutDate"),
                        result.getString("R.OverAllDetails"),
                        (new Car(result.getString("ID_car"),result.getString("C.TypeVehicule"))),
                        (new UrgencyDegree(result.getInt("Degre_urgent"),result.getString("Niveau_urgent"))),
                        (new CardState(result.getString("State"))),
                        (new Part(result.getString("PART.NamePart"))),
                        (new Repairs(result.getString("Nature_repair"),result.getDate("REP.DateRepair"),result.getFloat("REP.TimeSpent"),result.getString("Repair_ops"))),
                        (new Defect(result.getString("Defect_description"),result.getDouble("D.RepairTime"),result.getInt("D.criticity"))),
                        (new Place(result.getInt("Place"),result.getInt("PK.NumParking"))))
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
        
        public ArrayList<String> getCumulDay(){
            ArrayList<String> cumulDay = new ArrayList<String>();
            try {
                ResultSet result = this .connect
                                        .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                 ).executeQuery(
                                                    "SELECT COUNT(repaircard.id) AS Nombre, description AS Statut "
                                                  + "FROM repaircard, cardstate "
                                                  + "WHERE repaircard.IdCard = cardstate.Id "
                                                  + "GROUP BY description"
                                                 );
                RepairCard.emptyCollection();
                while(result.next()){
                    RepairCard.addRepairCardToCo(new RepairCard(
                        result.getInt("Nombre"),
                        result.getString("Statut")
                    ));
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            //Getting number of parts
            cumulDay.add(String.valueOf(RepairCard.getInfoCars().size()));
            for (RepairCard aRC: RepairCard.getInfoCars()){
                cumulDay.add(RepairCard.serialize_query3(aRC));
            }
            return cumulDay;
        }
        
        public ArrayList<String> getManu(){
            ArrayList<String> manu = new ArrayList<String>();
            try {
                ResultSet result = this .connect
                                        .createStatement(
                                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                    ResultSet.CONCUR_UPDATABLE
                                                 ).executeQuery(
                                                    "SELECT COUNT(IdRepair) AS Nombre, users.LastName AS Prenom, users.FirstName AS Nom "
                                                    + "FROM repaircard, cardrepairs, users "
                                                    + "WHERE repaircard.Id = cardrepairs.IdCard "
                                                    + "AND repaircard.IdUser = users.Id "
                                                    + "GROUP BY IdUser"
                                                 );
                RepairCard.emptyCollection();
                while(result.next()){
                    RepairCard.addRepairCardToCo(new RepairCard(
                        result.getInt("Nombre"),
                        result.getString("Nom"),
                        result.getString("Prenom")
                    ));
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            //Getting number of parts
            manu.add(String.valueOf(RepairCard.getInfoCars().size()));
            for (RepairCard aRC: RepairCard.getInfoCars()){
                manu.add(RepairCard.serialize_query4(aRC));
            }
            return manu;
        }
        
        public void ad(RepairCard a, ArrayList<String> b){
    		ArrayList<String> pa = new ArrayList<String>();
    		for (String retval: a.getOverAllDetails().split("|")){
    			pa.add(retval);
    		}
    		
    		Defect d=new Defect();
    		for(int i=0; i<pa.size()-1; i++){
		    				try{
				    			
				    		
		    					ResultSet result = this .connect
	                                    .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM defect WHERE Description='"+pa.get(i)+"'"
	                                             );
		    					
		    					while(result.next()){
		    		            	
		    		            	d=new Defect(
		    								result.getInt("Id"),
		    								result.getString("description"),
		    								result.getDouble("RepairTime"));          		
		    		            }
		    					
		    					java.sql.PreparedStatement prepare = this.connect
				                        .prepareStatement(
				                        		"INSERT INTO carddefect(IdDefect, IdCard, fixed) "
				                        		+ "VALUES(?, ?, ?)"
				                         );
				
				            	prepare.setInt(1, d.getid());
				            	prepare.setInt(2, a.getidcard());
				            	prepare.setInt(3, 0);
				    			
				    			prepare.executeUpdate();
				    			
				    		}catch(SQLException e){
				    			e.printStackTrace();
				    		}
    		}
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
        	prepare.setDate(6, (Date) obj.getOutDate());
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
                                     "SELECT idDefect FROM carddefect WHERE idCard='"+id+"' AND fixed = 0"
                                  );
            	 ArrayList<Defect> defects = new ArrayList<Defect>();
            	 DefectDAO defectDAO = new DefectDAO(this.connect);
            	 while(result2.next()){
            		 defects.add(defectDAO.getDefect(result2.getInt("idDefect")));
            	 }
            	 
            	 ResultSet result3 = this .connect
                         .createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                     ResultSet.CONCUR_UPDATABLE
                                  ).executeQuery(
                                     "SELECT idRepair FROM cardrepairs WHERE idCard='"+idCar+"'"
                                  );
            	 ArrayList<Repairs> reps = new ArrayList<Repairs>();
            	 RepairsDAO repairDAO = new RepairsDAO(this.connect);
            	 while(result3.next()){
            		 reps.add(repairDAO.getRepair(result3.getInt("idRepair")));
            	 }
            	 
            	 RepairCard rep = new RepairCard(uD, card, car, reps, defects, place, entry, outDate,overAllDets, user);   
            	 repCards.add(rep);
             }            
             
         } catch (SQLException e) {
                 e.printStackTrace();
         }
         return repCards;
	}
}
