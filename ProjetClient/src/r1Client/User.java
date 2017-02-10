package r1Client;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private String firstName;
	private String lastName;
	private String address;
	private String town;
	private int postCode;
	private String login;
	private String email;
	private Date hireDate;
	private float income;
	
	public User(String firstname, String lastname, String address, String town, int postcode, String login, String email, Date hire, float income){
		this.firstName = firstname;
		this.lastName = lastname;
		this.address = address;
		this.town = town;
		this.postCode = postcode;
		this.login = login;
		this.email = email;
		this.hireDate = hire;
		this.income = income;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setLastName(String newLastName){
		this.lastName = newLastName;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String newAddress){
		this.address = newAddress;
	}
	
	public String getTown(){
		return this.town;
	}
	
	public void setTown(String newTown){
		this.town = newTown;
	}
	
	public int getPostCode(){
		return this.postCode;
	}
	
	public void setPostCode(int newPostCode){
		this.postCode = newPostCode;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String newLogin){
		this.login = newLogin;
	}
		
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String newEmail){
		this.email = newEmail;
	}
	
	public Date getHireDate(){
		return this.hireDate;
	}
	
	public float getIncome(){
		return this.income;
	}
	
	public void setIncome(float newIncome){
		this.income = newIncome;
	}
	
	public static String serialize(User user){
		String serialUser = user.firstName+"///"+user.lastName+"///"+user.address+"///"+user.town+"///"+user.postCode+"///"+user.login+"///"+user.email+"///"+user.hireDate+"///"+user.income;
		return serialUser;
	}
	
	public static User unSerialize(String serializedUser){
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
		User user = new User(values.get(0).toString(),values.get(1).toString(),values.get(2).toString(),values.get(3).toString(),Integer.parseInt(values.get(4).toString()),values.get(5).toString(),values.get(6).toString(),date,Float.parseFloat(values.get(8).toString()));
		return user;
	}
	
}
