package Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * 
 * @author carbon5
 * class creating the card referring to a vehicule 
 */
public class RepairCard {
	final static Logger logger = Logger.getLogger(Serveur.class);
	private UrgencyDegree degree;
	private CardState card;
	private Car car;
	private ArrayList<Repairs> repairs;
	private ArrayList<Defect> defects;
	private Place park;
	private Date entryDate;
	private Date outDate;
	private String overAllDetails;
	private User user;
	
	/**
	 * Constructor of th class
	 * @param urgence
	 * @param card
	 * @param car
	 * @param repairs
	 * @param defects
	 * @param park
	 * @param entry
	 * @param out
	 * @param details
	 * @param user
	 */
	public RepairCard(UrgencyDegree urgence, CardState card, Car car, ArrayList<Repairs> repairs, ArrayList<Defect> defects, Place park, Date entry, Date out, String details, User user){
		this.degree = urgence;
		this.card = card;
		this.car = car;
		this.repairs = repairs;
		this.defects = defects;
		this.park = park;
		this.entryDate = entry;
		this.outDate = out;
		this.overAllDetails = details;
		this.user = user;
	}
	/**
	 * Method get UrgencyDegree
	 * @return degree
	 */
	public UrgencyDegree getDegree(){
		return this.degree;
	}
	/**
	 * Method set the Urgency degree
	 * @param deg
	 */
	public void setDegree(UrgencyDegree deg){
		this.degree = deg;
	}
	/**
	 * Method get the card State
	 * @return card
	 */
	public CardState getCard(){
		return this.card;
	}
	/**
	 * Method set the card State 
	 * @param newCard
	 */
	public void setCard(CardState newCard){
		this.card = newCard;
	}
	/**
	 * Method get Car
	 * @return car
	 */
	public Car getCar(){
		return this.car;
	}
	/**
	 * Method set car
	 * @param newCar
	 */
	public void setCar(Car newCar){
		this.car = newCar;
	}
	/**
	 * Method get repairs for the vehicule
	 * @return repairs
	 */
	public ArrayList<Repairs> getRepairs(){
		return this.repairs;
	}
	/**
	 * Method set the repairs for a car
	 * @param liste
	 */
	public void setRepairs(ArrayList<Repairs> liste){
		this.repairs = liste;
	}
	/**
	 * Method get the defect from a vehicule
	 * @return defects
	 */
	public ArrayList<Defect> getDefects(){
		return this.defects;
	}
	/**
	 * Method to set the defect for a vehicule
	 * @param liste
	 */
	public void setDefects(ArrayList<Defect> liste){
		this.defects = liste;
	}
	/**
	 * Method to get the parking space
	 * @return park
	 */
	public Place getPark(){
		return this.park;
	}
	/**
	 * Method to set the parking space
	 * @param park
	 */
	public void setPark(Place park){
		this.park = park;
	}
	/**
	 * Method to get the entry date
	 * @return entryDate
	 */
	public Date getEntryDate(){
		return this.entryDate;
	}
	/**
	 * Method to set the entry Date 
	 * @param newEntry
	 */
	public void setEntryDate(Date newEntry){
		this.entryDate = newEntry;
	}
	/**
	 * Method to get the out Date 
	 * @return outDate
	 */
	public Date getOutDate(){
		return this.outDate;
	}
	/**
	 * Method to set the out date
	 * @param newOutDate
	 */
	public void setOutDate(Date newOutDate){
		this.outDate = newOutDate;
	}
	/**
	 * Method to get the details
	 * @return overAllDetails
	 */
	public String getOverAllDetails(){
		return this.overAllDetails;
	}
	/**
	 * Method to set the details
	 * @param details
	 */
	public void setOverAllDetails(String details){
		this.overAllDetails = details;
	}
	/**
	 * Method to get the user
	 * @return user
	 */
	public User getUser(){
		return this.user;
	}
	/**
	 * Method to set the User
	 * @param user
	 */
	public void setUser(User user){
		this.user = user;
	}
	/**
	 * Method to serialize the repairCard
	 * @param rep
	 * @return serialized
	 */
	public static String serialize(RepairCard rep){
		String serialRepairs = null;
		int i = 0;
		for (Repairs darep:rep.getRepairs()){
			serialRepairs += Repairs.serialize(darep)+"///";
			i++;
		}
		serialRepairs = i+"///"+serialRepairs;
		String serialDefects = null;
		int j = 0;
		for (Defect dadefect:rep.getDefects()){
			serialDefects += Defect.serialize(dadefect)+"///";
			j++;
		}
		serialDefects = j+"///"+serialDefects;
		String serialized = rep.getDegree().getDescription()+"///"+rep.getCard().getDescription()+"///"+Car.serialize(rep.getCar())+"///"+serialRepairs+serialDefects+Place.serialize(rep.getPark())+"///"+rep.entryDate+"///"+rep.outDate+"///"+User.serialize(rep.user);
		return serialized;
	}
	/**
	 * Method to unserialize the card and to create the object
	 * @param serial
	 * @return repairCard
	 */
	public static RepairCard unSerialize(String serial){
		logger.info("Enter RepairCard unserilization");
		ArrayList values = new ArrayList();
		//get all the information from the string
		for (String retval: serial.split("///")){
			values.add(retval);
		}
		//setting the format for the various dates
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		Date date = new Date();

		logger.info("Begin RepairCard unserilization");
		//retrieving info in the right order to create all the objects
		//creating object urgencyDegree
		UrgencyDegree degree = new UrgencyDegree(values.get(0).toString());
		//creating object cardState
		CardState card = new CardState(Integer.parseInt(values.get(1).toString()), values.get(2).toString());
		//Creating objet car
		String numpuce = values.get(3).toString();
		String matricule = values.get(4).toString();
		String typevehic = values.get(5).toString();
		Car car = new Car(numpuce,matricule,typevehic);
		//creating repairs, retrieving the number of repairs to create as many objects as necessary
		int numObjRep = Integer.parseInt(values.get(5).toString());
		int numIndice = numObjRep;
		ArrayList<Repairs> rep = new ArrayList();
		for (int i = 0;i<numObjRep;i++){
				rep.add(new Repairs((Integer) values.get(numIndice),(Date) values.get(numIndice+1), String.valueOf(values.get(numIndice+2)), (float) values.get(numIndice+3), String.valueOf(values.get(numIndice+4))));
				numIndice += 5;
		}
		//creating defects, retrieving the number of defects to create as many objects as necessary
		int numObjDefect = Integer.parseInt(values.get(numIndice).toString());
		ArrayList<Defect> def = new ArrayList();
		for (int i = 0;i<numObjDefect;i++){
			def.add(new Defect(Integer.parseInt(values.get(numIndice).toString()),(String) values.get(numIndice+1)));
			numIndice +=2;
		}
		//creating object Place (referring to the parking)
		Place park = new Place((int) values.get(numIndice), (int) values.get(numIndice+1), (boolean) values.get(numIndice+2));
		numIndice +=3;
		//creating objects for the various dates
		Date num1 = (Date) values.get(numIndice); numIndice +=1;
		Date num2 = (Date) values.get(numIndice); numIndice +=1;
		String dets = (String) values.get(numIndice); numIndice +=1;
		//creating the user
		User user = new User((String) values.get(numIndice), (String) values.get(numIndice+1), (String) values.get(numIndice+2), (String) values.get(numIndice+3), (int) values.get(numIndice+4), (String) values.get(numIndice+5), (String) values.get(numIndice+6), (Date) values.get(numIndice+7), (Float) values.get(numIndice+8), new TypeUser(Integer.parseInt(values.get(numIndice+9).toString()),(String) values.get(numIndice+10)));
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(degree, card, car, rep, def, park,num1,num2,dets,user);
		logger.info("Success RepairCard unserilization");
		return repairCard;
	}
}
