/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.ParkingDAO;

/**
 *
 * @author Carbon5
 */
public class ParkingController implements Runnable {
    String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(ParkingController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	LocalDate date;
	int quantite = 0;
	int NumParking = 0;
	String namePart = null;
	int Capacity = 0;
	boolean ret;
	public ParkingController(Connection con, String in, PrintWriter out){
		this.con=con;
		this.in = in;
		this.out=out;
	}
    
    @Override
    public void run() {
        ParkingDAO test = new ParkingDAO(con);
            try{
                String identifier = LectureJson.Identifier(in);
//                ArrayList<String> result = LectureJson.LectureFichier(in);
                switch(identifier){
                case("SelectAllParkings"):
                        data = test.getAllParkings();
                        data.add(0, "SelectAllParkingsOK");
                break;
                }
            }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
            
            switch(data.get(0)){
		case("SelectAllParkingsOK"):
			JsonMessage = EcritureJson.WriteJson("SelectAllParkingsOK", data);
			logger.info("Sending list of parking to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
    }
}
    

