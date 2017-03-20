/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Client.Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Class repair a vehicle
 * @author Carbon5
 */
public class repairs {
    private int Id;
    private Date DateRepair;
    private String Nature;
    private float TimeSpent;
    private String Description; 
    
    /**
     * Class constructor
     * @param Id
     * @param DateRepair
     * @param Nature
     * @param TimeSpent
     * @param Description 
     */
    public repairs (int id, Date dateRepair, String nature, float timeSpent, String description){
        this.Id = id;
        this.DateRepair=dateRepair;
        this.Nature=nature;
        this.TimeSpent = timeSpent;
        this.Description=description;
    }
    
    /**
     * Method get Id
     * @return Id
     */
    public int getId(){
        return Id;
    }
    /**
     * Method get DateRepair
     * @return DateRepair
     */
    public Date getDateRepair(){
        return DateRepair;
    }
    /**
     * Method get Nature
     * @return Nature
     */
    public String getNature(){
        return Nature;
    }
    /**
     * Method get TimeSpent
     * @return TimeSpent
     */
    public float getTimeSpent(){
        return TimeSpent;
    }
    /**
     * Method get Description
     * @return Description
     */
    public String getDescription(){
        return Description;
    }
    
    /**
     * Method set Id number
     * @param Id
     */
    public void setId(int id){
        this.Id = id;
    }
    
    /**
     * Method set DateRepair
     * @param DateRepair
     */
    public void setDateRepair(Date date){
        this.DateRepair=date;
    }
    
    /**
     * Method set Nature
     * @param  Nature
     */
    public void setNature(String nature){
        this.Nature = nature;
    }
    
    /**
     * Method set TimeSpent
     * @param TimeSpent
     */
    public void setTimeSpent(float time){
        this.TimeSpent=time;
    }
    
    /**
     * Method set new vehicle's Description
     * @param Description 
     */
    public void setDescription(String desc){
        this.Description = desc;
    }
    
    /**
     * Method transform object vehicle to String
     * @param Repairs
     * @return String carSerial
     */
    
    public static String serialize(repairs repair){
    	String carSerial = repair.Id+"///"+repair.DateRepair+"///"+repair.Nature+"///"+repair.TimeSpent+"///"+repair.Description;
    	return carSerial;
    }
    
    /**
     * Method recreates object repairs from String
     * @param serializedCar
     * @return object car
     */
    
    public static repairs unSerialize(String serializedrepair){
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

		repairs repair = new repairs(Integer.parseInt(values.get(0)) , date, values.get(2), Float.valueOf(values.get(3)), values.get(4));
		return repair;
    }
    
}  
