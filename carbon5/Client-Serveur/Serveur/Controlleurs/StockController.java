package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.Part;
import Modele.PartDAO;
import Modele.User;
import Modele.UserDAO;

public class StockController implements Runnable{
	
	String in;
	private PrintWriter out = null;
	public Thread t2;
	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	final static Logger logger = Logger.getLogger(Serveur.class);
	int quantite = 0;
	String namePart = null;
	LocalDate date;
	boolean ret;
	
	public StockController(Connection con, String in, PrintWriter out){
		this.con=con;
		 this.in = in;
		 this.out=out;
	}
	
public void run() {
		User us;
		PartDAO test = new PartDAO(con);
		UserDAO user = new UserDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			
			case("addEntryStock"):
				date = LocalDate.now();
	    		namePart = result.get(0);
	    		Part obj = test.find(namePart);
	    		int userId = Integer.parseInt(result.get(2));
	    		quantite = Integer.parseInt(result.get(1));
	    		
	    		ret = test.addEntryStock(userId, obj, quantite, date);
				if (ret){
					data.add("addEntryStockOK");
				}else{
					data.add("addEntryStockKO");
				}
			break;
			
			case("addOutStock"):
				date = LocalDate.now();
				namePart = result.get(0);
				Part obj2 = test.find(namePart);
				quantite = Integer.parseInt(result.get(1));
				int userId2 = Integer.parseInt(result.get(2));
				ret = test.addOutStock(userId2, obj2, quantite, date);
				if (ret){
					data.add("addOutStockOK");
				}else{
					data.add("addOutStockKO");
				}
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		
		case("addEntryStockOK"):	case ("addEntryStockKO"):
			JsonMessage = EcritureJson.WriteJson(data.get(0), data);
			logger.info("Sending result entry stock");
			out.println(JsonMessage);
			out.flush();
		break;
		
		case("addOutStockOK"):	case("addOutStockKO"):
			JsonMessage = EcritureJson.WriteJson(data.get(0), data);
			logger.info("Sending result out stock");
			out.println(JsonMessage);
			out.flush();
			break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}


