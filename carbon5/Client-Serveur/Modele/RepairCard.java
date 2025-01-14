package Modele;

import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.ConnectionPool;
import Serveur.Controlleurs.Serveur;

/**
 * 
 * @author carbon5
 * class creating the card referring to a vehicule 
 */
public class RepairCard {

	private static ArrayList<RepairCard> waitList = new ArrayList<RepairCard>();
	private static ArrayList<RepairCard> repairCard = new ArrayList<RepairCard>();

	private Set<Car> listCar = new HashSet<Car>();
	private Set<CardState> listState = new HashSet<CardState>();
	private Set<UrgencyDegree> listUD = new HashSet<UrgencyDegree>();
	public static RepairCard prioritaryCard;
	public static RepairCard carsDefect;

	final static Logger logger = Logger.getLogger(Serveur.class);
	private UrgencyDegree degree;
	private UrgencyDegree description;
	private CardState card;
	private CardState statut2;
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
	private int cumulCar;
	private String statut;
	private int numRep;
	private String name;
	private String lname;
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

	public RepairCard(int IdCard, Date enDate, Date outDate, String text, Car car, UrgencyDegree ud, CardState cs, Part part, Repairs rep, Defect def, Place place){
		this.idcard = IdCard;
		this.entryDate = enDate;
		this.outDate = outDate;
		this.overAllDetails = text;
		this.car = car;
		this.degree = ud;
		this.card = cs;
		this.part = part;
		this.repair = rep;
		this.defect = def;
		this.park = place;
	}

//	public RepairCard(int count, String state){
//		this.cumulCar = count;
//		this.statut = state;
//	}

	public RepairCard(int num, String nom, String prenom){
		this.numRep = num;
		this.name = nom;
		this.lname = prenom;
	}
	
	public RepairCard(int IdCard, String IdCar){
				this.idcard=IdCard;
				this.idcar = IdCar;
			}
	
	public RepairCard(int IdCard, Repairs rep, Defect def){
		this.idcard=IdCard;
		this.repair=rep;
		this.defect=def;
	}

	public int getNbCar(){
		return this.cumulCar;
	}

	public String getStatutCar(){
		return this.statut;
	}

	public void setStatutCar(String newStatut){
		this.statut = newStatut;
	}
	
	public int getNumRep(){
		return this.numRep;
	}

	public String getName(){
		return this.name;
	}

	public String getLastName(){
		return this.lname;
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

	 public static RepairCard getPrioritaryCard() {
		 		return prioritaryCard;
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
	 * @throws IOException 
	 */
	public static void addToWaitList(RepairCard vehicule) throws IOException{
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
	public static int timeWaiting(Date entry){
		Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		java.util.Date instante = java.util.Date.from(instant);
		Long t1 = entry.getTime();
		Long t2 = Date.from(instant).getTime();

		Long milliDiff = Date.from(instant).getTime() - entry.getTime();

		long  milliPerDay = 1000 * 60 * 60 * 24;

		milliDiff = milliDiff/milliPerDay;

		int diff = milliDiff.intValue();
		return diff;
	}



	/**
	 * Method to determine the order for repairs, executed each time a repairCard is added to the waitList
	 * @throws IOException 
	 */
	public static void determineWaitList() throws IOException{
		//algorithm determining order for waitList with ELECTRE method
		//criterias: entryDate, parts available, time for repair, criticity
		if(!waitList.isEmpty()){
			waitList.removeAll(getWaitList());
		}

		Connection con = ConnectionPool.getConnectionFromPool();
		RepairCardDAO repDAO = new RepairCardDAO(con);
		ArrayList<RepairCard> reps = repDAO.getAllRepairCards();
		//		ArrayList<RepairCard> reps = new ArrayList<RepairCard>();
		//		reps = JeudeTestRepairCard.getJeuDeTest();
		//create list car with parts for repairs available (prioritized) and not available
		ArrayList<RepairCard> partsNotAvailable = new ArrayList<RepairCard>();		
		ArrayList<RepairCard> partsAvailable = new ArrayList<RepairCard>();


		float time1 = RepairCard.getTimeRep(reps.get(0));
		float time2 = RepairCard.getTimeRep(reps.get(1));
		float time3 = RepairCard.getTimeRep(reps.get(2));
		float time4 = RepairCard.getTimeRep(reps.get(3));

		for(RepairCard rep : reps){
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

		//get all preferences for prioritizing
		PreferencesDAO test = new PreferencesDAO(ConnectionPool.getConnectionFromPool());
		ArrayList<String> prefsStr = test.getAllPreferences();
		Preferences prefs = Preferences.unSerialize(prefsStr.get(0));

		double[][] globalConcordance = RepairCard.getGlobalConcordanceMatrix(partsAvailable, prefs);
		double[][] globalDiscordance = RepairCard.getGlobalDiscordanceMatrix(partsAvailable, prefs);

		RepairCard.getFinalDecision(globalConcordance, globalDiscordance, partsAvailable);

		double[][] globalConcordanceNotAvailable = RepairCard.getGlobalConcordanceMatrix(partsNotAvailable, prefs);
		double[][] globalDiscordanceNotAvailable = RepairCard.getGlobalConcordanceMatrix(partsNotAvailable, prefs);

		RepairCard.getFinalDecision(globalConcordanceNotAvailable, globalDiscordanceNotAvailable, partsNotAvailable);

		ArrayList<RepairCard> testcggg = RepairCard.getWaitList();

		System.out.println("Finished prioritizing");

		for (RepairCard aRep: testcggg){
			System.out.println("Nombre de jours dans le d�p�t : " + RepairCard.timeWaiting(aRep.getEntryDate()));
			System.out.println("Temps de r�paration estim� : " + RepairCard.getTimeRep(aRep));
			System.out.println("Criticit� des pannes : " +RepairCard.getCriticity(aRep));
			System.out.println("*******************");
		}
	}

	//Method to calculate the time necessary for all reparations
	public static float getTimeRep(RepairCard aRep){
		float time = 0;
		for (Defect aDef: aRep.getDefects()){
			time += aDef.getduration(); 
		}
		return time;
	}

	public static int getCriticity(RepairCard aRep){
		int crit = 0;
		for (Defect aDef:aRep.getDefects()){
			crit+= aDef.getCriticity();
		}
		return crit;
	}

	//method fusioning the global concordance and global discordance matrix
	//to create a matrix with the priority percentage of one vehicule over another for all vehicules
	//determines the waiting list by searching for a vehicule that isn't prioritized by any other (in a loop)
	public static void getFinalDecision(double[][] globalConcordance, double[][] globalDiscordance, ArrayList<RepairCard> list){

		double[][] finalMatrix = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				finalMatrix[i][j] = globalConcordance[i][j]*(1-globalDiscordance[i][j]);
			}
		}

		for (int k = 0; k<list.size(); k++){
			finalMatrix[k][k] = 0;
		}

		for (double lambda=0.1; lambda<1; lambda += 0.1){
			boolean prioritary = true;

			for (int j=0; j<list.size(); j++){
				for (int i=0; i<list.size(); i++){
					if(finalMatrix[i][j] >= lambda){
						prioritary = false;
					}
				}
				if(prioritary){
					if(!waitList.contains(list.get(j))){
						waitList.add(list.get(j));
						for (int i=0; i<list.size(); i++){
							finalMatrix[i][j] = 0;
							finalMatrix[j][i] = 0;
						}
					}				
				}
			}
		}
	}

	//method that puts a priority veto of one car over the other depending on the preferences saved
	//returns a discordance matrix with all the vetos
	public static double[][] getGlobalDiscordanceMatrix(ArrayList<RepairCard> list, Preferences prefs) throws IOException{
		double[][] matrixDate = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				if(list.get(i).timeWaiting(list.get(i).getEntryDate()) <= list.get(j).timeWaiting(list.get(j).getEntryDate()) - prefs.getVetoDays()){
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
				if(timeRep <= timeRep2 - prefs.getVetoTimeRep()){
					matrixTimeRep[i][j] = 1;
				}else{
					matrixTimeRep[i][j] = 0;
				}
			}
		}

		double[][] matrixCriticity = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			float criticity = 0;
			for (Defect aDef: list.get(i).getDefects()){
				criticity += aDef.getCriticity();
			}			

			for (int j=0; j<list.size(); j++){
				float criticity2 = 0;
				for (Defect aDef: list.get(j).getDefects()){
					criticity2 += aDef.getCriticity(); 
				}
				if(criticity <= criticity2 - 2){
					matrixCriticity[i][j] = 1;
				}else{
					matrixCriticity[i][j] = 0;
				}
			}
		}

		//fills Global Discordance matrix with info from all criterias
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

	//method that creates a matrix with priority percentage of one car over another depending on preferences saved
	public static double[][] getGlobalConcordanceMatrix(ArrayList<RepairCard> list, Preferences prefs) throws IOException{
		double[][] matrixDate = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			for (int j=0; j<list.size(); j++){
				if(RepairCard.timeWaiting(list.get(i).getEntryDate()) >= RepairCard.timeWaiting(list.get(j).getEntryDate()) - prefs.getIndifDays()){
					matrixDate[i][j] = 1;
				}else{
					matrixDate[i][j] = 0;
				}

			}
		}

		double[][] matrixTimeRep = new double[list.size()][list.size()];
		for (int i=0; i<list.size(); i++){
			float timeRep = 0;
			for (Defect aDef: list.get(i).getDefects()){
				timeRep += aDef.getduration(); 
			}			

			for (int j=0; j<list.size(); j++){
				float timeRep2 = 0;
				for (Defect aDef: list.get(j).getDefects()){
					timeRep2 += aDef.getduration(); 
				}
				if(timeRep >= timeRep2 - prefs.getIndifTimeRep()){
					matrixTimeRep[i][j] = 1;
				}else{
					matrixTimeRep[i][j] = 0;
				}
			}
		}



		//fills Global Concordance matrix with info from all criterias
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
		String serialized = rep.idcard +"///"+ rep.entryDate+"///"+ rep.outDate+"///"+rep.overAllDetails+"///"
				+rep.car.serialize(rep.getCar())+"///"+rep.degree.serialize(rep.getDegree())
				+"///"+rep.card.serialize(rep.getCard())+"///"+rep.part.serialize(rep.getPart())
				+"///"+rep.repair.serialize(rep.getRepair())+"///"+rep.defect.serialize(rep.getDefect())
				+"///"+rep.park.serialize(rep.getPark());
		return serialized;
	}

	public static String serialize_query3(RepairCard rep){
		String serialized = rep.cumulCar + "//////" + rep.statut;
		return serialized;
	}

	public static String serialize_query4(RepairCard rep){
		String serialized = rep.name + "///" + rep.lname + "///" + rep.numRep;
		return serialized;
	}
	
	public static String serialize_query5(RepairCard rep){
		
				String serialized = rep.getidcard()+"///"+rep.getCar().getNumePuce();
				return serialized;
			}
	
	public static String serialize_query6(RepairCard rep){
		String serialized = rep.idcard +"///"+rep.repair.serialize(rep.getRepair())+"///"+rep.defect.serialize(rep.getDefect());
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
		int idcard = Integer.parseInt(values.get(0));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date entryDate = format.parse(values.get(1));
		Date outDate = format.parse(values.get(2));
		String detailOps = values.get(3);
		Car car = new Car(values.get(4),values.get(5),values.get(6));
		UrgencyDegree ud = new UrgencyDegree(Integer.parseInt(values.get(7)),values.get(8));
		CardState cs = new CardState(Integer.parseInt(values.get(9)),values.get(10));
		Part part = new Part(values.get(11),Integer.parseInt(values.get(12)),values.get(13),Float.parseFloat(values.get(14)));                

		Date date=null;
		String testDate = values.get(16);
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		try {
			date = formatter.parse(testDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Repairs rep = new Repairs(Integer.parseInt(values.get(15)),date,values.get(17),Float.parseFloat(values.get(18)),values.get(19));
		Defect def = new Defect(Integer.parseInt(values.get(20)),values.get(21),Double.parseDouble(values.get(22)));
		Place place = new Place(Integer.parseInt(values.get(23)),Boolean.parseBoolean(values.get(24)),Integer.parseInt(values.get(25)));

		logger.info("Begin unserilization");
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(idcard, entryDate, outDate, detailOps, car,ud,cs,part,rep,def,place);
		logger.info("Success RepairCard unserilization");

		return repairCard;
	}

	public static RepairCard unSerialize_query3(String serialized) throws ParseException{
		ArrayList<String> values = new ArrayList<String>();
		logger.info("Enter unserilization");
		for (String retval: serialized.split("//////")){
			values.add(retval);
		}
		int idcard = Integer.parseInt(values.get(0));
		String cs = values.get(1);

		logger.info("Begin unserilization");
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(idcard, cs);
		logger.info("Success RepairCard unserilization");

		return repairCard;
	}

	public static RepairCard unSerialize_query4(String serialized) throws ParseException{
		ArrayList<String> values = new ArrayList<String>();
		logger.info("Enter unserilization");
		for (String retval: serialized.split("///")){
			values.add(retval);
		}
		String nom = values.get(0);
		String prenom = values.get(1);
		int num = Integer.parseInt(values.get(2));
		logger.info("Begin unserilization");
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(num, nom, prenom);
		logger.info("Success RepairCard unserilization");

		return repairCard;
	}
	
	public static RepairCard unSerialize_query5(String serialized) throws ParseException{
		ArrayList<String> values = new ArrayList<String>();
		logger.info("Enter unserilization");
		for (String retval: serialized.split("///")){
			values.add(retval);
		}
		int idcard = Integer.parseInt(values.get(0));
		String idcar = values.get(1);
		logger.info("Begin unserilization");
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(idcard, idcar);
		logger.info("Success RepairCard unserilization");

		return repairCard;
	}
	
	public static RepairCard unSerialize_query6(String serialized) throws ParseException{
		ArrayList<String> values = new ArrayList<String>();
		logger.info("Enter unserilization");
		for (String retval: serialized.split("///")){
			values.add(retval);
		}
		int idcard = Integer.parseInt(values.get(0));
		String repair = values.get(1);
		String defect = values.get(2);
		logger.info("Begin unserilization");
		//creating the object repairCard with all other objects
		RepairCard repairCard = new RepairCard(idcard, repair, defect);
		logger.info("Success RepairCard unserilization");

		return repairCard;
	}
	
	/**
	 * Method toString
	 * 
	 */
	public String toString(Object values) {
		return String.valueOf(values);
	}
	
	public static ArrayList<RepairCard> getAllRepairCard() {
				// TODO Auto-generated method stub
				return repairCard;
			}
}
