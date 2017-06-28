package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Modele.Defect;
import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.RepairCard;
import Modele.RepairCardDAO;

public class RepairCardController implements Runnable{

	String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(RepairCardController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;

	int indifDays = 0;
	int vetoDays = 0;
	int indifTime = 0;
	int vetoTime = 0;

	boolean ret;

	public RepairCardController(Connection con, String in, PrintWriter out){
		this.con=con;
		this.in = in;
		this.out=out;
	}

	public void run() {

		RepairCardDAO test = new RepairCardDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){

				case("SelectFirstFromWaitList"):
					
						RepairCard first = RepairCard.getWaitList().get(0);
						
						String firstSerialize = RepairCard.serialize_query5(first);
						data.add(firstSerialize);
					data.add(0, "SelectFirstFromWaitListOK");
				break;
				
//				case("SelectCarsDefect"):
//					
//					RepairCard first1 = RepairCard.getPrioritaryCard().getDefect();
//					
//					String first1Serialize = RepairCard.serialize_query6(first1);
//					data.add(first1Serialize);
//				data.add(0, "SelectCarsDefectOK");
//				break;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		switch(data.get(0)){
		case("SelectFirstFromWaitListOK"):
			JsonMessage = EcritureJson.WriteJson("SelectFirstFromWaitListOK", data);
		logger.info("Sending JSON succ�s selectRepairCard to Client");
		out.println(JsonMessage);
		out.flush();
		break;
		}
		logger.info("Returning connection to pool");
		ConnectionPool.returnConnectionToPool(con);
	}
}