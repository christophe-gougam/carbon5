package r1Client.Modele;

import java.util.ArrayList;

/**
 * Class creates a parking
 * @author Carbon5
 */
public class Parking {
	
	private int NumParking;
	private String NameParking;
	private int Capacity;
    /**
     * Class constructor
     * @param newParking 
     */
	public Parking(int numParking, String nameParking, int capacity){
		this.NumParking = numParking;
		this.NameParking = nameParking;
		this.Capacity = capacity;
	}
	
    /**
     * Method get parking number
     * @return parking number
     */
	public int getNumParking(){
		return this.NumParking;
	}
	/**
     * Method get parking name
     * @return parking number
     */
	public String getNameParking(){
		return this.NameParking;
	}
	/**
     * Method get parking capacity
     * @return parking number
     */
	public int getCapacity(){
		return this.Capacity;
	}
	
    /**
     * Method set new parking number
     * @param newParking 
     */
	public void setNumParking(int numParking){
		this.NumParking = numParking;
	}
	/**
     * Method set new parking name
     * @param newParking 
     */
	public void setNameParking(String nameParking){
		this.NameParking = nameParking;
	}
	/**
     * Method set new parking Capacity
     * @param newParking 
     */
	public void setCapacity(int capacity){
		this.Capacity = capacity;
	}
	
	public static String serialize(Parking park){
		String carSerial = park.NumParking+"///"+park.NameParking+"///"+park.Capacity;
		return carSerial;
	}

	/**
	 * Method recreates object Parking from String
	 * @param serializedpark
	 * @return object park
	 */
	public static Parking unSerialize(String serializedpark){
		ArrayList<String> values = new ArrayList<String>();
		for (String retval: serializedpark.split("///")){
			values.add(retval);
	}
		Parking park = new Parking(Integer.parseInt(values.get(0)), values.get(1), Integer.parseInt(values.get(2)));
		return park;
	}

}
