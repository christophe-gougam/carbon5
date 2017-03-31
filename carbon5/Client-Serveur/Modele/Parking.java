package Modele;

import java.util.ArrayList;

/**
 * Class creates a parking
 * @author Carbon5
 */
public class Parking {
	
	private int numParking;
	private String nameParking;
	private int capacity;
    
    /**
     * Class constructor
     * @param newParking 
     */
	public Parking(int numParking, String nameParking, int capacity){
		this.numParking = numParking;
		this.nameParking = nameParking;
		this.capacity = capacity;
	}
	
        public Parking(){
            
        }
        
    /**
     * Method get parking number
     * @return parking number
     */
	public int getNumParking(){
		return this.numParking;
	}
	/**
     * Method get parking name
     * @return parking number
     */
	public String getNameParking(){
		return this.nameParking;
	}
	/**
     * Method get parking capacity
     * @return parking number
     */
	public int getCapacity(){
		return this.capacity;
	}
	
    /**
     * Method set new parking number
     * @param numParking 
     */
	public void setNumParking(int numParking){
		this.numParking = numParking;
	}
	/**
     * Method set new parking name
     * @param nameParking 
     */
	public void setNameParking(String nameParking){
		this.nameParking = nameParking;
	}
	/**
     * Method set new parking Capacity
     * @param capacity
     */
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	public static String serialize(Parking park){
		String carSerial = park.numParking+"///"+park.nameParking+"///"+park.capacity;
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
