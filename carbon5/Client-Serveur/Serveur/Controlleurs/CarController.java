package Serveur.Controlleurs;

import java.io.BufferedReader;



import Modele.LectureJson;
import Modele.Part;
import Modele.PartDAO;
import Modele.PlaceDAO;
import Modele.RepairCard;
import Modele.RepairCardDAO;
import Modele.TypeCar;
import Modele.TypeCarDAO;
import Modele.User;
import Modele.UserDAO;
import Modele.Car;
import Modele.CarDAO;
import Modele.CardState;
import Modele.CardStateDAO;
import Modele.Defect;
import Modele.DefectDAO;
import Modele.EcritureJson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;

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
//<<<<<<< HEAD
        ArrayList<String> allCar = new ArrayList<String>();
//=======
	ArrayList<Car> Carinfo = new ArrayList<Car>();
//>>>>>>> origin/Develop
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
				CardStateDAO test4= new CardStateDAO(con);
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
					
					case("AjoutVehicule"):
						String status="Attente";
						int userid=0;
						
						numP=result.get(0);
						type = result.get(1);
						matriculation = result.get(2);
						entranceDate=LocalDate.now();
						place=Integer.parseInt(result.get(3));
						String listPane= "";
						
						ArrayList<String> listePanneEntrance= new ArrayList<String>();
						for(int t=4; t<result.size(); t++){
							listePanneEntrance.add(result.get(t));
								listPane+=result.get(t)+"|";
						}
						int repairTime=0;
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
						//date = LocalDate.now();
//						Date dat = new Date();
//						Calendar cc = Calendar.getInstance(); 
//						cc.setTime(dat); 
//						cc.add(Calendar.DATE, repairTime);
//						dat = cc.getTime();
						
						LocalDate dat;
						dat=entranceDate.plusDays(repairTime);
						
						///verifier le type date et adapter
						
						
						
						///recuperer le id user
						User testUser = test.auth(login, mdp);
						if(testUser.getFirstName() != null){
							data.add("GrantAuth");
							data.add(User.serialize(testUser));
						}else{
							data.add("Erreur de mot de passe");
						}
						
						
						
						
						
						
						RepairCard carinfo=new RepairCard(1, Integer.parseInt(numP), place, java.sql.Date.valueOf(dat), listPane, userid);
						//RepairCard carinfo=new RepairCard();
						Carinfo=test.getCar(numP);
						if(Carinfo.get(0).getNumePuce().equalsIgnoreCase(numP))
						{
							
							ret=test5.create(carinfo,  java.sql.Date.valueOf(entranceDate));
							
							if (ret){
						
							data.add("OKCarInput");
							test3.updatePlace(place);
							//data.add(Car.serialize(carinfo));
							data.add(String.valueOf(java.sql.Date.valueOf(dat)));
							}else{
								data.add("KOCarInput");
							}
						}
						//a brancher
						else{
							data.add("CarNotExist");
						}

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
			}
			//returns connection to pool
			logger.info("Returning connection to pool");
		 	ConnectionPool.returnConnectionToPool(con);
	}
	
}
