package Serveur.Controlleurs;

import java.io.BufferedReader;


import Modele.LectureJson;
import Modele.Part;
import Modele.PartDAO;
import Modele.PlaceDAO;
import Modele.TypeCar;
import Modele.TypeCarDAO;
import Modele.User;
import Modele.UserDAO;
import Modele.Car;
import Modele.CarDAO;
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

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.PrintWriter;

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
				TypeCarDAO test1=new TypeCarDAO(con);
				DefectDAO test2=new DefectDAO(con);
				PlaceDAO test3=new PlaceDAO(con);
				boolean ret = false;
				try{
					String identifier = LectureJson.Identifier(in);
					ArrayList<String> result = LectureJson.LectureFichier(in);
					switch(identifier){
					
					
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
						Date dat = new Date();
						Calendar cc = Calendar.getInstance(); 
						cc.setTime(dat); 
						cc.add(Calendar.DATE, repairTime);
						dat = cc.getTime();
						Car car=new Car(numP, type, matriculation, java.sql.Date.valueOf(entranceDate), listPane, place);
						ret=test.addCar(car, entranceDate);
						if (ret){
							data.add("OKCarInput");
							test3.updatePlace(place);
							logger.info("\n"+"Date previsionnelle  : "+dat+"\n");
							data.add(Car.serialize(car));
							data.add(String.valueOf(dat));
						}else{
							data.add("KOCarInput");
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
			}
			//returns connection to pool
			logger.info("Returning connection to pool");
		 	ConnectionPool.returnConnectionToPool(con);
	}
	
}
