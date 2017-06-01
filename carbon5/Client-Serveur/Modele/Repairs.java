/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Class repair a vehicle
 * @author Carbon5
 */
public class Repairs {
    private int id;
    private Date dateRepair;
    private String nature;
    private float timeSpent;
    private String description; 
    
    /**
     * Class constructor
     * @param id
     * @param dateRepair
     * @param nature
     * @param timeSpent
     * @param description 
     */
    public Repairs (int id, Date dateRepair, String nature, float timeSpent, String description){
        this.id = id;
        this.dateRepair=dateRepair;
        this.nature=nature;
        this.timeSpent = timeSpent;
        this.description=description;
    }
    public Repairs(){
    	
    }
    
    /**
     * Method get Id
     * @return Id
     */
    public int getId(){
        return id;
    }
    /**
     * Method get DateRepair
     * @return DateRepair
     */
    public Date getDateRepair(){
        return dateRepair;
    }
    /**
     * Method get Nature
     * @return Nature
     */
    public String getNature(){
        return nature;
    }
    /**
     * Method get TimeSpent
     * @return TimeSpent
     */
    public float getTimeSpent(){
        return timeSpent;
    }
    /**
     * Method get Description
     * @return Description
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * Method set Id number
     * @param Id
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * Method set DateRepair
     * @param DateRepair
     */
    public void setDateRepair(Date date){
        this.dateRepair=date;
    }
    
    /**
     * Method set Nature
     * @param  Nature
     */
    public void setNature(String nature){
        this.nature = nature;
    }
    
    /**
     * Method set TimeSpent
     * @param TimeSpent
     */
    public void setTimeSpent(float time){
        this.timeSpent=time;
    }
    
    /**
     * Method set new vehicle's Description
     * @param Description 
     */
    public void setDescription(String desc){
        this.description = desc;
    }
    
    /**
     * Method transform object vehicle to String
     * @param Repairs
     * @return String carSerial
     */
    
    public static String serialize(Repairs repair){
    	String carSerial = repair.id+"///"+repair.dateRepair+"///"+repair.nature+"///"+repair.timeSpent+"///"+repair.description;
    	return carSerial;
    }
    
    /**
     * Method recreates object repairs from String
     * @param serializedCar
     * @return object car
     */
    
    public static Repairs unSerialize(String serializedrepair){
    	ArrayList<String> values = new ArrayList<String>();
		for (String retval: serializedrepair.split("///")){
			values.add(retval);
		}
		Date date=null;
		String testDate = values.get(1);
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		try {
			date = formatter.parse(testDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Repairs repair = new Repairs(Integer.parseInt(values.get(0)) , date, values.get(2), Float.valueOf(values.get(3)), values.get(4));
		return repair;
    }
    
}  
