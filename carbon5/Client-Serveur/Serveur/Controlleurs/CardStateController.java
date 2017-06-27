package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Modele.CardStateDAO;
import Modele.EcritureJson;
import Modele.LectureJson;

public class CardStateController implements Runnable{
	
	String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(CardStateController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	LocalDate date;
	Float purchasePrice = null;
	boolean ret;
	public CardStateController(Connection con, String in, PrintWriter out){
		this.con=con;
		this.in = in;
		this.out=out;
	}
	
	public void run() {
		

		CardStateDAO test = new CardStateDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			
			
			case("SelectAllStates"):
				data = test.getAllStates();
				data.add(0, "SelectAllStatesOK");
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		case("SelectAllStatesOK"):
			JsonMessage = EcritureJson.WriteJson("SelectAllStatesOK", data);
			logger.info("Sending list of states to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}