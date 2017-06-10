package Modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JeudeTestRepairCard {
	//creating a testList with all RepairCards while waiting for others to finish their UseCase
	public JeudeTestRepairCard(){
		
	}
	

	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	
	public static ArrayList<RepairCard> getJeuDeTest(){
		
		Defect def = new Defect(1,"pneu",2, 1,new Part(1, "pneu", 2));
		Defect def2 = new Defect(1,"moteur", 5, 3, new Part(1, "moteur", 50));
		Defect def3 = new Defect(1,"frein", 3, 2, new Part(1, "frein", 20));
		Defect def4 = new Defect(1,"volant", 2, 1, new Part(1, "volant", 15));
		//Defect : int id, String description, Part partForRep, int criticity, int time
		//Part : int stock, String namePart, float purchasePrice
		UrgencyDegree urg1 = new UrgencyDegree(1,"urgent");
		
		CardState card = new CardState(1,"en cours");
		
		Car car1 = new Car("numpuce1", "voiture", "matricule");
		Car car2 = new Car("numpuce2", "voiture", "matricule");
		Car car3 = new Car("numpuce3", "voiture", "matricule");
		Car car4 = new Car("numpuce4", "voiture", "matricule");
		
		ArrayList<Repairs> reps = new ArrayList<Repairs>();
		
		ArrayList<Defect> defs1 = new ArrayList<Defect>();
		defs1.add(def);
		defs1.add(def2);
		
		ArrayList<Defect> defs2 = new ArrayList<Defect>();
		defs2.add(def3);
		
		ArrayList<Defect> defs3 = new ArrayList<Defect>();
		defs3.add(def3);
		defs3.add(def4);
		
		ArrayList<Defect> defs4 = new ArrayList<Defect>();
		defs4.add(def);
		
		Place place = new Place();
		
		String details = "";
		
		User user = new User();
		

		java.util.Date date = JeudeTestRepairCard.parseDate("2016-01-01");
		java.util.Date date1 = JeudeTestRepairCard.parseDate("2016-03-01");
		java.util.Date date2 = JeudeTestRepairCard.parseDate("2016-05-01");
		java.util.Date date3 = JeudeTestRepairCard.parseDate("2016-07-01");
		java.util.Date date4 = JeudeTestRepairCard.parseDate("2016-09-01");
		
//		Date date = new Date(2017,1,1);
//		Date date1 = new Date(2017,2,1);
//		Date date2 = new Date(2017,3,3);
//		Date date3 = new Date(2017,4,4);	
//		Date date4 = new Date(2017,5,5);

		
		RepairCard rep1 = new RepairCard(urg1, card, car1, reps, defs1, place, date1, date4, details, user);
		RepairCard rep2 = new RepairCard(urg1, card, car2, reps, defs2, place, date2, date4, details, user);
		RepairCard rep3 = new RepairCard(urg1, card, car3, reps, defs3, place, date3, date4, details, user);
		RepairCard rep4 = new RepairCard(urg1, card, car4, reps, defs4, place, date4, date4, details, user);
		
		ArrayList<RepairCard> repsTest = new ArrayList<RepairCard>();
		repsTest.add(rep1);
		repsTest.add(rep2);
		repsTest.add(rep3);
		repsTest.add(rep4);
		
		return repsTest;
	}
	
}
