package Modele;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * Class User creating the user
 * @author Carbon5
 */
public class User {
	final static Logger logger = Logger.getLogger(Serveur.class);
	private String firstName;
	private String lastName;
	private String address;
	private String town;
	private int postCode;
	private String login;
	private String email;
	private Date hireDate;
	private float income;
	private TypeUser type;
	
	private static ArrayList<User> usersCo = new ArrayList<User>();
	
    /**
     * Class constructor
     * @param firstname
     * @param lastname
     * @param address
     * @param town
     * @param postcode
     * @param login
     * @param email
     * @param hire
     * @param income
     * @param type 
     */
	public User(String firstname, String lastname, String address, String town, int postcode, String login, String email, Date hire, float income, TypeUser type){
		this.firstName = firstname;
		this.lastName = lastname;
		this.address = address;
		this.town = town;
		this.postCode = postcode;
		this.login = login;
		this.email = email;
		this.hireDate = hire;
		this.income = income;
		this.type = type;
	}
	
	public User(){
		
	}
	
	public static ArrayList<User> getAllUsers(){
		return usersCo;
	}
	
	public static void addAUserToCo(User us){
		usersCo.add(us);
	}
	
	public static User getManager(){
		User userToSend = new User();
		for (User us: usersCo){
			if (us.getTypeUser().getTypeUser()=="manager"){
				userToSend = us;
			}
		}
		return userToSend;
	}
    /**
     * Method get first name 
     * @return first name
     */
	public String getFirstName(){
		return this.firstName;
	}
	
    /**
     * Method set new first name
     * @param newFirstName 
     */
	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}
	
    /**
     * Method get last name
     * @return last name
     */
	public String getLastName(){
		return this.lastName;
	}
	
    /**
     * Method set new last name
     * @param newLastName 
     */
	public void setLastName(String newLastName){
		this.lastName = newLastName;
	}
	
    /**
     * Method get address
     * @return address
     */
	public String getAddress(){
		return this.address;
	}
	
    /**
     * Method set new address
     * @param newAddress 
     */
	public void setAddress(String newAddress){
		this.address = newAddress;
	}
	
    /**
     * Method get town
     * @return town
     */
	public String getTown(){
		return this.town;
	}
	
    /**
     * Method set new town
     * @param newTown 
     */
	public void setTown(String newTown){
		this.town = newTown;
	}
	
    /**
     * Method get postal code
     * @return postal code
     */
	public int getPostCode(){
		return this.postCode;
	}
	
    /**
     * Method set new postal code 
     * @param newPostCode 
     */
	public void setPostCode(int newPostCode){
		this.postCode = newPostCode;
	}
	
    /**
     * Method get login
     * @return login
     */
	public String getLogin(){
		return this.login;
	}
	
    /**
     * Method set new login
     * @param newLogin 
     */
	public void setLogin(String newLogin){
		this.login = newLogin;
	}
	
    /**
     * Method get email
     * @return email
     */
	public String getEmail(){
		return this.email;
	}
	
    /**
     * Method set new email
     * @param newEmail 
     */
	public void setEmail(String newEmail){
		this.email = newEmail;
	}
	
    /**
     * Method get hire date
     * @return hire date
     */
	public Date getHireDate(){
		return this.hireDate;
	}
	
    /**
     * Method get income
     * @return incime
     */
	public float getIncome(){
		return this.income;
	}
	
    /**
     * Method set new income
     * @param newIncome 
     */
	public void setIncome(float newIncome){
		this.income = newIncome;
	}
	
    /**
     * Method get type user
     * @return type user
     */
	public TypeUser getTypeUser(){
		return this.type;
	}
	
    /**
     * Method set new type user 
     * @param type 
     */
	public void setTypeUser(TypeUser type){
		this.type = type;
	}
	
    /**
     * Method create serial for user
     * @param user
     * @return String serial user
     */
	public static String serialize(User user){
		String serialUser = user.firstName+"///"+user.lastName+"///"+user.address+"///"+user.town+"///"+user.postCode+"///"+user.login+"///"+user.email+"///"+user.hireDate+"///"+user.income+"///"+user.type.serialize(user.getTypeUser());
		return serialUser;
	}
	
    /**
     * Method unSerialize to recreate object User
     * @param serializedUser
     * @return user
     */
	public static User unSerialize(String serializedUser){
		logger.info("Enter unserilization");
		ArrayList values = new ArrayList();
		for (String retval: serializedUser.split("///")){
			values.add(retval);
		}
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		Date date = new Date();
		try {
			date = format.parse(values.get(7).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Begin unserilization");
		User user = new User(values.get(0).toString(),values.get(1).toString(),values.get(2).toString(),values.get(3).toString(),Integer.parseInt(values.get(4).toString()),values.get(5).toString(),values.get(6).toString(),date,Float.parseFloat(values.get(8).toString()), new TypeUser(Integer.parseInt(values.get(9).toString()),values.get(10).toString()));
		logger.info("Success unserilization");
		return user;
	}
	
}
