package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import Modele.Car;
import Modele.CarDAO;
import Modele.Defect;
import Modele.DefectDAO;
import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.PlaceDAO;
import Modele.RepairCard;
import Modele.RepairCardDAO;
import Modele.TypeCarDAO;
import Modele.User;

/**
 * Class CarController runs operation for car 
 * @author Carbon5
 */
public class CarController implements Runnable{
	String in=null;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(CarController.class);
	Connection con=null;
	String matriculation = null;
	String type = null;
	String numP = null;
	int place=0;
	Date date;
	LocalDate entranceDate;
	ArrayList<String> data = new ArrayList<String>();
	ArrayList<String> dataPanne = new ArrayList<String>();
	ArrayList<String> allPlace = new ArrayList<String>();
        ArrayList<String> allCar = new ArrayList<String>();
	ArrayList<Car> Carinfo = new ArrayList<Car>();
	boolean isIn=false;
	String JsonMessage;
	
	/**
	 * Method constructor of controller
	 */
	public CarController(Connection con, String in, PrintWriter out){
		 this.con=con;
		 this.in = in;
		 this.out=out;
	}
	/**
	 * Method run gets connection form pool and insert into database
     * @see LectureJson.LectureFichier
     * @see EcritureJson.WriteJson
     * @see pool.getConnectionFromPool
     * @see ConnectionPool.returnConnectionToPool
     * @throws Exception if JSON not read properly
	 */
	public void run() {
		
				CarDAO test = new CarDAO(con);
				RepairCardDAO test5 = new RepairCardDAO(con);
				TypeCarDAO test1=new TypeCarDAO(con);
				DefectDAO test2=new DefectDAO(con);
				PlaceDAO test3=new PlaceDAO(con);
				boolean ret = false;
				try{
					String identifier = LectureJson.Identifier(in);
					ArrayList<String> result = LectureJson.LectureFichier(in);
					switch(identifier){
					
					case("Search"):
						Carinfo=test.getCar(result.get(0));
						if(Carinfo.get(0).getNumePuce().equalsIgnoreCase(result.get(0)))
						{
							data.add("SearchOK");
							data.add(Car.serialize(Carinfo.get(0)));
						}
						else
							data.add(0, "SearchKO");
						
					break;
					
					case("LoadAllComboBox"):
						//TODO:
						//create object and add to waitList
						data=test1.getTypeCar();
                                                allCar = test.getAllCars();
						data.add(0, "LoadAllComboBoxOK");
						dataPanne=test2.getAllDefect();
						allPlace=null;
						allPlace=test3.getPlace();
						
						
					break;
                                        
                                        case("getInfoCar_query1"):
                                            data = test5.getInfoCar();
                                            data.add(0, "query1_OK");
                                        break;
                                        
//                                        case("getWorkflowCar_query2"):
//                                            data = test5.getInfoCar();
//                                            data.add(0, "query2_OK");
//                                        break;
//                                        
//                                        case("getCumulDay_query3"):
//                                            data = test5.getInfoCar();
//                                            data.add(0, "query3_OK");
//                                        break;
//                                        
//                                        case("getManutentionnaires_query4"):
//                                            data = test5.getInfoCar();
//                                            data.add(0, "query4_OK");
//                                        break;
					
					case("AjoutVehicule"):
						numP=result.get(0);
						type = result.get(1);
						matriculation = result.get(2);
						entranceDate=LocalDate.now();
						place=Integer.parseInt(result.get(3));
						String listPane= "";

						User user=User.unSerialize(result.get(result.size()-1));

						ArrayList<String> listePanneEntrance= new ArrayList<String>();
						for(int t=4; t<result.size()-1; t++){
							listePanneEntrance.add(result.get(t));
								if(t<(result.size()-2))
									listPane+=result.get(t)+"|";
								else
									listPane+=result.get(t);
						}
						double repairTime=0.0;
						ArrayList<Defect> defaut= new ArrayList<Defect>();
						defaut=test2.searchDefect();
						for (int t =0; t<listePanneEntrance.size();t++){
							
							for(int i =0; i<defaut.size();i++){
								
								if(listePanneEntrance.get(t).equalsIgnoreCase(defaut.get(i).getDescription())){
									repairTime+=defaut.get(i).getduration();
									break;
								}
							}
			    		}
						
						boolean yesdemi=false;
						LocalDate dat=null;
						if(repairTime%1==0.0){
							dat=entranceDate.plusDays((int) repairTime);
						}
						else{
							dat=entranceDate.plusDays(((int) repairTime)+1);
							yesdemi=true;
						}
						RepairCard carinfo=new RepairCard(1, String.valueOf(numP), place, java.sql.Date.valueOf(dat), listPane, user);
						Carinfo=test.getCar(numP);
						isIn=test5.existRepairCard(numP);
						if(Carinfo.get(0).getNumePuce().equalsIgnoreCase(numP) && isIn==false)
						{
							ret=test5.create(carinfo, java.sql.Date.valueOf(entranceDate));
							
							if (ret){
						
								data.add("OKCarInput");
								test3.updatePlace(place);
								data.add(RepairCard.serialize(carinfo));
								data.add(String.valueOf(java.sql.Date.valueOf(dat)));
								if(yesdemi)
									data.add("DemiJournee");
								else
									data.add("Journee");
							}else{
								data.add("KOCarInput");
							}
						}
						//a brancher
						else if(isIn==true){
							data.add("AlreadyAdded");
						}
						else
							data.add("CarNotExist");

					break;
					}
					
				}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			switch(data.get(0)){
			case("OKCarInput"):
				//TODO:
				//create object and add to waitList
				
				JsonMessage = EcritureJson.WriteJson(data.get(0), data);
				logger.info("Succes ajout");
				out.println(JsonMessage);
				logger.info("Sending JSON succ�s to Client");
				out.flush();
			break;
			case("KOCarInput"):
				JsonMessage = EcritureJson.WriteJson(data.get(0), data);
				logger.info("Erreur Ajout");
				out.println(JsonMessage);
				out.flush();
			break;
			case("LoadAllComboBoxOK"):
				JsonMessage = EcritureJson.writeJson(data.get(0), data, dataPanne, allPlace, allCar);
				logger.info("Sending list of type car to Client");
				logger.info(JsonMessage);
				out.println(JsonMessage);
				out.flush();
			break;
			
                        case("query1_OK"):
                                JsonMessage = EcritureJson.WriteJson("query1_OK", data);
                                logger.info("Sending info car to Client");
                                out.println(JsonMessage);
                                out.flush();
                        break;
                        
//                        case("query2_OK"):
//                                JsonMessage = EcritureJson.WriteJson("query2_OK", data);
//                                logger.info("Sending workflow car to Client");
//                                out.println(JsonMessage);
//                                out.flush();
//                        break;
//                        
//                        case("query3_OK"):
//                                JsonMessage = EcritureJson.WriteJson("query3_OK", data);
//                                logger.info("Sending cumulation day to Client");
//                                out.println(JsonMessage);
//                                out.flush();
//                        break;
//                        
//                        case("query4_OK"):
//                                JsonMessage = EcritureJson.WriteJson("query4_OK", data);
//                                logger.info("Sending warehousemen to Client");
//                                out.println(JsonMessage);
//                                out.flush();
//                        break;
                        
			case("SearchOK"):
				JsonMessage = EcritureJson.WriteJson("SearchOK", data);
				logger.info("Sending  car info to Client");
				logger.info(JsonMessage);
				out.println(JsonMessage);
				out.flush();
			break;
			case("SearchKO"):
				JsonMessage = EcritureJson.WriteJson("SearchKO", data);
				logger.info("Sending error to Client");
				logger.info(JsonMessage);
				out.println(JsonMessage);
				out.flush();
			break;
			case("CarNotExist"):
				JsonMessage = EcritureJson.WriteJson("CarNotExist", data);
				logger.info("Sending error to Client");
				logger.info(JsonMessage);
				out.println(JsonMessage);
				out.flush();
			break;
			case("AlreadyAdded"):
				JsonMessage = EcritureJson.WriteJson("AlreadyAdded", data);
				logger.info("Sending error to Client");
				logger.info(JsonMessage);
				out.println(JsonMessage);
				out.flush();
			break;
			}
			//returns connection to pool
			logger.info("Returning connection to pool");
		 	ConnectionPool.returnConnectionToPool(con);
	}
	
}
