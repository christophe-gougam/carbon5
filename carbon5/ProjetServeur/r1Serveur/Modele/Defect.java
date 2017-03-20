/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Serveur.Modele;

import java.util.ArrayList;

/**
 * Class create defect
 * @author Carbon5
 */
public class Defect {
    private String description;
    private int id;
    
    /*
    Class constructor
    */
    public Defect(int id, String description){
        this.description = description;
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
    
    /*
    * Method transform object defect to String
    * @param defect
    * @return String defectSerial
    */
    public static String serialize(Defect defect){
    	String defectSerial = defect.id+"///"+defect.description;
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
		Defect defect = new Defect(id, values.get(1).toString());
		return defect;
    }
}
