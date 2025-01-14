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
public class PartController implements Runnable{
	
	String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(PartController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	LocalDate date;
	int quantite = 0;
	String IdPart = null;
	String namePart = null;
	Float purchasePrice = null;
	boolean ret;
	public PartController(Connection con, String in, PrintWriter out){
		this.con=con;
		this.in = in;
		this.out=out;
	}
	
	public void run() {
		

		PartDAO test = new PartDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			
			case("CreatePart"):
	    		namePart = result.get(0);
	    		purchasePrice = Float.parseFloat(result.get(1));
				Part partToAdd = new Part(1, namePart, purchasePrice);
				logger.info("Putting through DAO");
				data = test.create(partToAdd);
			break;
			case("ModificationPart"):
				namePart = result.get(0);
				purchasePrice = Float.parseFloat(result.get(1));
				Part partUpdate = new Part(IdPart, namePart, purchasePrice);
				logger.info("Updating through DAO");
				ret=test.update(partUpdate);
				if (ret)
					data.add("ModificationPartOK");
				else
					data.add("ModificationPartKO");
			break;
			case("DeletePart"):
				namePart = result.get(0);
				Part partDelete = new Part("1", namePart, 2);
				logger.info("Updating through DAO");
				ret=test.delete(partDelete);
				if (ret){
					data.add("DeletePartOK");
				}
				else{
					data.add("DeletePartKO");
				}
			break;
			case("SelectAllParts"):
				data = test.getAllParts();
				data.add(0, "SelectAllPartsOK");
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		case("CreatePartOK"):
			JsonMessage = EcritureJson.WriteJson("CreatePartOK", data);
			logger.info("Sending JSON succ�s createPart to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("CreatePartKO"):
			JsonMessage = EcritureJson.WriteJson("CreatePartKO", data);
			logger.info("Erreur lors de l'ajout de Part");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartOK"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartOK", data);
			logger.info("Sending JSON succ�s to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartKO"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartKO", data);
			logger.info("Erreur lors de la mise � jour de cette pi�ce");
			out.println(JsonMessage);
			out.flush();
		break;
		case("SelectAllPartsOK"):
			JsonMessage = EcritureJson.WriteJson("SelectAllPartsOK", data);
			logger.info("Sending list of part to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("DeletePartOK"):
			JsonMessage = EcritureJson.WriteJson("DeletePartOK", data);
			logger.info("Sending list of part to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}
