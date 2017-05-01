/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 * Class create defect
 * @author Carbon5
 */
public class Defect {
    private String description;
    private int id;
    private Part partForRepair;
    /*
    Class constructor
    */
    public Defect(int id, String description, Part partForRep){
    	this.id = id;
        this.description = description;
        this.partForRepair = partForRep;
    }
    
    public Defect(int id, String description){
    	this.id = id;
    	this.description = description;
    }
    
    /**
     * Method get id
     * @return id
     */
    public int getId(){
        return id;
    }
    
    /**
     * Method set Id
     * @param newId 
     */
    public void setId(int newId){
        this.id = newId;
    }
    /*
    Method get description
    */
    public String getDescription(){
        return description;
    }
    
    /*
    Method set new description 
    */
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    
    public Part getPartForRepair(){
    	return this.partForRepair;
    }
    
    public void setPartForRepairs(Part thePart){
    	this.partForRepair = thePart;
    }
    
    /*
    * Method transform object defect to String
    * @param defect
    * @return String defectSerial
    */
    public static String serialize(Defect defect){
    	String defectSerial = defect.id+"///"+defect.description+"///"+defect.partForRepair.serialize(defect.getPartForRepair());
    	return defectSerial;
    }
    
    /*
    * Method recreates object Defect from String
    * @param serializedDefect
    * @return object defect
    */
    public static Defect unSerialize(String serializedDefect){
    	ArrayList values = new ArrayList();
		for (String retval: serializedDefect.split("///")){
			values.add(retval);
		}
                int id = Integer.parseInt(values.get(0).toString());
                Part thePart = new Part( values.get(2).toString(), Integer.parseInt(values.get(3).toString()), values.get(4).toString(), Float.valueOf(values.get(5).toString()) );
		Defect defect = new Defect(id, values.get(1).toString(), thePart);
		return defect;
    }
}
