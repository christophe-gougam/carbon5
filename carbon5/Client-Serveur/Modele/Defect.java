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
    private double duration;
    private Part partForRepair;
    private int criticity;

	private static ArrayList<Defect> panne= new ArrayList<Defect>();
    /*
    Class constructor
    */
    public Defect(int id, String description, Part partForRep, int criticity){
    	this.id = id;
        this.description = description;
        this.partForRepair = partForRep;
        this.criticity = criticity;
    }
    
    public Defect(int id, String description, double repairTime, int criticity, Part part){
    	this.id = id;
        this.description = description;
        this.partForRepair = part;
        this.criticity = criticity;
        this.duration = repairTime;
    }
    
    public Defect(String description, double repairTime, int criticity){
        this.description = description;
        this.criticity = criticity;
        this.duration = repairTime;
    }
    
    public Defect(int id, String description, double time){
    	this.id = id;
    	this.description = description;
    	this.duration=time;
    }
    
    public Defect(){}
    
    /**
     * Method get id
     * @return id
     */
    public int getid(){
        return id;
    }
    
    /**
     * Method set Id
     * @param newId 
     */
    public void setid(int newId){
        this.id = newId;
    }
    
    /**
     * Method get id
     * @return id
     */
    public double getduration(){
        return duration;
    }
    
    /**
     * Method set duration
     * @param newtime
     */
    public void setduration(double newtime){
        this.duration = newtime;
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
    
    public int getCriticity(){
    	return this.criticity;
    }
    
    public void setCriticity(int crit){
    	this.criticity = crit;
    }
    
	public static ArrayList<Defect> getAllDefect(){
		return panne;
	}
	public static void setAllDefect(ArrayList<Defect> newdefect){
		panne = newdefect;
	}
	
	public static void addPartToCo(Defect newpanne){
		panne.add(newpanne);
	}
	
	public static boolean isInCollection(String type){
		Boolean check = false;
		for(Defect atypeC: panne){
			if (atypeC.description.equalsIgnoreCase(type)){
				check = true;
			}
		}
		return check;
	}

	public static void emptyCollection(){
		
		Defect.panne.clear();
	}
    /*
    * Method transform object defect to String
    * @param defect
    * @return String defectSerial
    */
	public static String serialize(Defect defect){
    	String defectSerial = defect.id+"///"+defect.description+"///"+defect.duration;
    	return defectSerial;
    }
	
	public static Defect unSerialize(String serializedDefect){
    	ArrayList<String> values = new ArrayList<String>();
		for (String retval: serializedDefect.split("///")){
			values.add(retval);
		}
                int id = Integer.parseInt(values.get(0).toString());
                double Time = Double.parseDouble(values.get(2).toString());
		Defect defect = new Defect(id, values.get(1).toString(), Time);
		return defect;
    }
	/*
	
	public static String serialize(Defect defect){
    	String defectSerial = defect.id+"///"+defect.description+"///"+defect.partForRepair.serialize(defect.getPartForRepair());
    	return defectSerial;
    }
    */
    /*
    * Method recreates object Defect from String
    * @param serializedDefect
    * @return object defect
    */
	
	/*
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
    */
}
