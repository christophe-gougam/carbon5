package Modele;

import java.awt.List;



import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.sql.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import java.sql.Connection;
import Serveur.Controlleurs.Serveur;

import Modele.Preferences;
import Modele.PreferencesDAO;
import Modele.User;
import static Modele.User.logger;
import Serveur.Controlleurs.ConnectionPool;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author carbon5
 * class creating the card referring to a vehicule 
 */
public class RepairCard {
	
	private static ArrayList<RepairCard> waitList;
        private static ArrayList<RepairCard> repairCard = new ArrayList<RepairCard>();
        
        private Set<Car> listCar = new HashSet<Car>();
        private Set<CardState> listState = new HashSet<CardState>();
        private Set<UrgencyDegree> listUD = new HashSet<UrgencyDegree>();
        
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
        private Part part;
        private Repairs repair;
        private Defect defect;
	private int ponderation;
	int idcard;
	private String idcar;
	int idparkplace;
        
	/**
	 * Constructor of this class
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
	
	public RepairCard(int IdCard,String IdCar, int IdParkPlac, Date out, String details, User userid){
		this.idcard=IdCard;
		this.idcar = IdCar;
		this.idparkplace=IdParkPlac;
		this.outDate = out;
		this.overAllDetails = details;
		this.user = userid;
	}
	
	public RepairCard(){
		
	}
        
        /**
         * Constructor of query 1
         * @param IdCard
         * @param IdCar
         * @param typeVehicule
         * @param IdDegree
         * @param card 
         */
        public RepairCard(int IdCard, String IdCar, Car typeVehicule , UrgencyDegree IdDegree, CardState card) {
            this.idcard = IdCard;
            this.idcar = IdCar;
            this.car = typeVehicule;
            this.degree = IdDegree;
            this.card = card;
        }
        
        public RepairCard(int IdCard, Car car, UrgencyDegree ud, CardState cs, Part part, Repairs rep, Defect def, Place place){
            this.idcard = IdCard;
            this.car = car;
            this.degree = ud;
            this.card = cs;
            this.part = part;
            this.repair = rep;
            this.defect = def;
            this.park = place;
        }
        
	public int getidcard(){
		return this.idcard;
	}
	/**
	 * Method set the card State 
	 * @param newCard
	 */
	public void setidcard(int newidCard){
		this.idcard = newidCard;
	}
	public String getidcar(){
		return this.idcar;
	}
	/**
	 * Method set the cardid 
	 * @param newCar
	 */
	public void setidcar(String newidCar){
		this.idcar = newidCar;
	}
	
	public int getidparkplace(){
		return this.idparkplace;
	}
	/**
	 * Method set the card State 
	 * @param newCard
	 */
	public void setidparkplace(int idparkplac){
		this.idparkplace = idparkplac;
	}
	
	
	public static ArrayList<RepairCard> getWaitList(){
		return waitList;
	}
	
	public static void setWaitList(ArrayList<RepairCard> list){
		waitList = list;
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
        
        public Part getPart(){
            return this.part;
        }
        
        public void setPart(Part part){
            this.part = part;
        }
        
        public Repairs getRepair(){
            return this.repair;
        }
        
        public void setRepair(Repairs repair){
            this.repair = repair;
        }
        
        public Defect getDefect(){
            return this.defect;
        }
        
        public void setDefect(Defect defect){
            this.defect = defect;
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
	
        public Set<CardState> getListState(){
            return listState;
        }
        
        public void setListState(Set<CardState> listState){
            this.listState = listState;
        }
        
        public void addState(CardState state){
            if(!listState.contains(state))
                listState.add(state);
        }
        
        public void removeState(CardState state){
            this.listState.remove(state);
        }
        
        public Set<Car> getListCar(){
            return listCar;
        }
        
        public void setListCar(Set<Car> listCar){
            this.listCar = listCar;
        }
        
        public void addCar(Car car){
            if(!listCar.contains(car))
                listCar.add(car);
        }
        
        public void removeCar(Car car){
            this.listCar.remove(car);
        }
        
        public Set<UrgencyDegree> getListUD(){
            return listUD;
        }
        
        public void setListUD(Set<UrgencyDegree> listUD){
            this.listUD = listUD;
        }
        
        public void addUD(UrgencyDegree ud){
            if(!listUD.contains(ud))
                listUD.add(ud);
        }
        
        public void removeUD(UrgencyDegree ud){
            this.listUD.remove(ud);
        }
        
        public boolean equals(RepairCard rc){
            return this.getidcard() == rc.getidcard();
        }
	/**
	 * Method to add a vehicule to the waitList
	 * @param vehicule
	 */
	public static void addToWaitList(RepairCard vehicule){
		waitList.add(vehicule);
		//every time a vehicule is added to the waitList, the order of repairs is re-calculated
		RepairCard.determineWaitList();
	}
	
	/**
	 * Method to check if the parts are available for the repairs
	 * @param aCard
	 * @return arePartsAvailable
	 */
	public Boolean availableParts(){
		Boolean arePartsAvailable = true;
		
		for (Defect aDefect: this.getDefects()){
			if(Part.getPartFromCollection(aDefect.getPartForRepair()).getStock()<=0){
				arePartsAvailable = false;
			}
		}
		
		return arePartsAvailable;
	}
	
	/**
	 * Method to determine how long a vehicule has been waiting for repairs
	 * @param entryDate
	 * @return timeWaited
	 */
	public int timeWaiting(Date entryDate){
		int timeWaited;
		
		Calendar calendarToday = Calendar.getInstance();
		calendarToday.setTime(calendarToday.getTime());
		
		Calendar calendarEntry = Calendar.getInstance();
		calendarEntry.setTime(entryDate);
		
		timeWaited = calendarToday.compareTo(calendarEntry);
	    
	    return timeWaited;
	}
	
	/**
	 * Method to determine the order for repairs, executed each time a repairCard is added to the waitList
	 */
	public static void determineWaitList(){
		//algorithm determining order for waitList with ELECTRE method
		//criterias: entryDate, parts available, time for repair, criticity
		if(!waitList.isEmpty()){
			waitList.removeAll(getWaitList());
		}
		
		Connection con = ConnectionPool.getConnectionFromPool();
		RepairCardDAO repDAO = new RepairCardDAO(con);
		ArrayList<RepairCard> allRepairCards = repDAO.getAllRepairCards();
		
		//create list car with parts for repairs available (prioritized) and not available
		ArrayList<RepairCard> partsNotAvailable = new ArrayList<RepairCard>();		
		ArrayList<RepairCard> partsAvailable = new ArrayList<RepairCard>();
		
		for(RepairCard rep : allRepairCards){
			boolean bol = true;
			for(Defect def: rep.getDefects()){
				if(def.getPartForRepair().getStock()<=0){
					bol = false;
				}
			}
			if(bol){
				partsAvailable.add(rep);
			}else{
				partsNotAvailable.add(rep);
			}
		}
		
		//get all preferences fort prioritizing
		PreferencesDAO test = new PreferencesDAO(ConnectionPool.getConnectionFromPool());
		ArrayList<String> prefsStr = test.getAllPreferences();
		Preferences prefs = Preferences.unSerialize(prefsStr.get(0));
		
		double[][] globalConcordance = RepairCard.getGlobalConcordanceMatrix(partsAvailable, prefs);
		double[][] globalDiscordance = RepairCard.getGlobalDiscordanceMatrix(partsAvailable, prefs);
		
		RepairCard.getFinalDecision(globalConcordance, globalDiscordance, partsAvailable);
		
		double[][] globalConcordanceNotAvailable = RepairCard.getGlobalConcordanceMatrix(partsNotAvailable, prefs);
		double[][] globalDiscordanceNotAvailable = RepairCard.getGlobalConcordanceMatrix(partsNotAvailable, prefs);
		
		RepairCard.getFinalDecision(globalConcordanceNotAvailable, globalDiscordanceNotAvailable, partsNotAvailable);
		
		System.out.println("Finished prioritizing");
	}
	
	public static void getFinalDecision(double[][] globalConcordance, double[][] globalDiscordance, ArrayList<RepairCard> list){
		ArrayList<RepairCard> finalOrder = new ArrayList<RepairCard>();
		
		double[][] finalMatrix = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				finalMatrix[i][j] = globalConcordance[i][j]*(1-globalDiscordance[i][j]);
			}
		}
		
		for (double lambda=0.9; lambda>0.1; lambda -= 0.1){
			boolean prioritary = true;
			
			for (int i=0; i<list.size(); i++){
				for (int j=0; j<list.size(); j++){
					if(finalMatrix[j][i] <= lambda){
						prioritary = false;
					}
				}
				if(prioritary){
					if(!waitList.contains(list.get(i))){
						waitList.add(list.get(i));
						for (int j=0; j<list.size(); j++){
								finalMatrix[i][j] = 2;
								finalMatrix[j][i] = 2;
						}
					}				
				}
			}
		}
	}
	
	public static double[][] getGlobalDiscordanceMatrix(ArrayList<RepairCard> list, Preferences prefs){
		double[][] matrixDate = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				if(list.get(i).timeWaiting(list.get(i).getEntryDate()) >= list.get(j).timeWaiting(list.get(j).getEntryDate()) + prefs.getVetoDays()){
					matrixDate[i][j] = 1;
				}else{
					matrixDate[i][j] = 0;
				}
				
			}
		}
		
		double[][] matrixTimeRep = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			float timeRep = 0;
			float timeRep2 = 0;
			for (Defect aDef: list.get(i).getDefects()){
				timeRep += aDef.getduration(); 
			}			
			 
			for (int j=0; j<list.size(); j++){
				for (Defect aDef: list.get(j).getDefects()){
					timeRep2 += aDef.getduration(); 
				}
				if(timeRep >= timeRep2 + prefs.getVetoTimeRep()){
					matrixTimeRep[i][j] = 1;
				}else{
					matrixTimeRep[i][j] = 0;
				}
			}
		}
		
		double[][] matrixCriticity = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			float criticity = 0;
			float criticity2 = 0;
			for (Defect aDef: list.get(i).getDefects()){
				criticity += aDef.getCriticity();
			}			
			 
			for (int j=0; j<list.size(); j++){
				for (Defect aDef: list.get(j).getDefects()){
					criticity2 += aDef.getCriticity(); 
				}
				if(criticity >= criticity2){
					matrixCriticity[i][j] = 1;
				}else{
					matrixCriticity[i][j] = 0;
				}
			}
		}
		
		//fill Global Concordance matrix with info from all criterias
		double[][] matrixGlobal = new double[list.size()][list.size()];
		for(int i = 0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				if(matrixDate[i][j] == 1 || matrixTimeRep[i][j] == 1 || matrixCriticity[i][j] == 1){
					matrixGlobal[i][j] = 0;
				}
			}
		}
		
		return matrixGlobal;
	}
	
	public static double[][] getGlobalConcordanceMatrix(ArrayList<RepairCard> list, Preferences prefs){
		double[][] matrixDate = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				if(list.get(i).timeWaiting(list.get(i).getEntryDate()) >= list.get(j).timeWaiting(list.get(j).getEntryDate()) + prefs.getIndifDays()){
					matrixDate[i][j] = 1;
				}else{
					matrixDate[i][j] = 0;
				}
				
			}
		}
		
		double[][] matrixTimeRep = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			float timeRep = 0;
			float timeRep2 = 0;
			for (Defect aDef: list.get(i).getDefects()){
				timeRep += aDef.getduration(); 
			}			
			 
			for (int j=0; j<list.size(); j++){
				for (Defect aDef: list.get(j).getDefects()){
					timeRep2 += aDef.getduration(); 
				}
				if(timeRep >= timeRep2 + prefs.getIndifTimeRep()){
					matrixTimeRep[i][j] = 1;
				}else{
					matrixTimeRep[i][j] = 0;
				}
			}
		}
		

		
		//fill Global Concordance matrix with info from all criterias
		double[][] matrixGlobal = new double[list.size()][list.size()];
		for(int i = 0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				matrixGlobal[i][j] = (matrixDate[i][j]*0.7) + (matrixTimeRep[i][j]*0.3);
			}
		}
		
		return matrixGlobal;
	}
        
        public static void emptyCollection() {
            repairCard.clear();
        }
	
        public static ArrayList<RepairCard> getInfoCars() {
            return repairCard;
        }
        
        public static void addRepairCardToCo(RepairCard newRC){
		repairCard.add(newRC);
	}
	
	public static boolean isInCollection(int id){
		Boolean check = false;
		for(RepairCard aRC: repairCard){
			if (aRC.idcard == id){
				check = true;
			}
		}
		return check;
	}
        
	/**
	 * Method to serialize the repairCard
	 * @param rep
	 * @return serialized
	 */
	public static String serialize(RepairCard rep){

		String serialized = rep.getidcard()+"///"+rep.getidcar()+"///"+rep.getidparkplace()+"///"+
		rep.getOutDate()+"///"+rep.getOverAllDetails()+"///"+User.serialize(rep.getUser());
		return serialized;
	}
	
        public static String serialize_query1(RepairCard rep){

		String serialized = rep.idcar+"///"
                +rep.car.serialize(rep.getCar())+"///"+rep.degree.serialize(rep.getDegree())
                +"///"+rep.card.serialize(rep.getCard())+"///"+rep.idcard;
		return serialized;
	}
        
        public static String serialize_query2(RepairCard rep){
                String serialized = rep.idcard +"///"+ rep.idcar+"///"
                +rep.car.serialize(rep.getCar())+"///"+rep.degree.serialize(rep.getDegree())
                +"///"+rep.card.serialize(rep.getCard())+"///"+rep.part.serialize(rep.getPart())
                +"///"+rep.repair.serialize(rep.getRepair())+"///"+rep.defect.serialize(rep.getDefect())
                +"///"+rep.park.serialize(rep.getPark());
		return serialized;
        }
	
	/**
	 * Method to unserialize the card and to create the object
	 * @param serial
	 * @return repairCard
	 * @throws ParseException 
	 */
	
	public static RepairCard unSerialize(String serialized) throws ParseException{
    	ArrayList<String> values = new ArrayList<String>();
		for (String retval: serialized.split("///")){
			values.add(retval);
		}
                int idcd = Integer.parseInt(values.get(0).toString());
                String idc = (values.get(1).toString());
                int pplace = Integer.parseInt(values.get(2).toString());
                //dd MMM yyyy
                java.util.Date utilDate = new SimpleDateFormat("YYYY-MM-DD").parse(values.get(3).toString());
                Date dat = new Date(utilDate.getTime());
                String detail =values.get(4);
                User user=User.unSerialize(values.get(5));
               
        		user = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getTown(), user.getPostCode(), 
        				user.getLogin(), user.getEmail(), user.getHireDate(), user.getIncome(), user.getTypeUser());
        		
                //creating the object repairCard with all other objects
        		RepairCard repairCard = new RepairCard(idcd, idc, pplace, dat, detail, user);
        		logger.info("Success RepairCard unserilization");
		return repairCard;
        }
        
        public static RepairCard unSerialize_query1(String serialized) throws ParseException{
                ArrayList<String> values = new ArrayList<String>();
                logger.info("Enter unserilization");
		for (String retval: serialized.split("///")){
			values.add(retval);
		}
                String idcar = (values.get(0));
                Car car = new Car(values.get(1),values.get(2),values.get(3));
                UrgencyDegree ud = new UrgencyDegree(Integer.parseInt(values.get(4)),values.get(5));
                CardState cs = new CardState(Integer.parseInt(values.get(6)),values.get(7));
                int idcard = Integer.parseInt(values.get(8));
                logger.info("Begin unserilization");
                //creating the object repairCard with all other objects
                RepairCard repairCard = new RepairCard(idcard, idcar, car, ud, cs);
                logger.info("Success RepairCard unserilization");
		
                return repairCard;
        }
        
        public static RepairCard unSerialize_query2(String serialized) throws ParseException{
                ArrayList<String> values = new ArrayList<String>();
                logger.info("Enter unserilization");
		for (String retval: serialized.split("///")){
			values.add(retval);
		}
                String idcar = (values.get(0));
                Car car = new Car(values.get(1),values.get(2),values.get(3));
                UrgencyDegree ud = new UrgencyDegree(Integer.parseInt(values.get(4)),values.get(5));
                CardState cs = new CardState(Integer.parseInt(values.get(6)),values.get(7));
                int idcard = Integer.parseInt(values.get(8));
                logger.info("Begin unserilization");
                //creating the object repairCard with all other objects
                RepairCard repairCard = new RepairCard(idcard, idcar, car, ud, cs);
                logger.info("Success RepairCard unserilization");
		
                return repairCard;
        }
	/*
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
		Date Entrydate=null;
		String Loperation = "";
		int place=0;
		Car car=new Car();
		//Car car = new Car(numpuce, matricule,typevehic, Entrydate, Loperation, place);
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
		User user = new User((int) values.get(numIndice),(String) values.get(numIndice), (String) values.get(numIndice+1), (String) values.get(numIndice+2), (String) values.get(numIndice+3), (int) values.get(numIndice+4), (String) values.get(numIndice+5), (String) values.get(numIndice+6), (Date) values.get(numIndice+7), (Float) values.get(numIndice+8), new TypeUser(Integer.parseInt(values.get(numIndice+9).toString()),(String) values.get(numIndice+10)));
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(degree, card, car, rep, def, park,num1,num2,dets,user);
		logger.info("Success RepairCard unserilization");
		return repairCard;
	}
	*/
	/**
     * Method toString
     * 
     */
    public String toString(Object values) {
        return String.valueOf(values);
    }
}
