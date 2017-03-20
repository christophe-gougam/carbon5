/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Serveur.Modele;

import java.util.ArrayList;

/**
 * Class creates a vehicle
 * @author Carbon5
 */
public class Car {
    private String NumePuce;
    private String TypeVehicule;
    
    /**
     * Class constructor
     * @param NumePuce;
     * @param TypeVehicule 
     */
    public Car (String numePuce, String typevehicule){
        this.NumePuce = numePuce;;
        this.TypeVehicule = typevehicule;
    }
    
    /**
     * Method get NumePuce
     * @return NumePuce;
     */
    public String getNumePuce(){
        return NumePuce;
    }
    
    /**
     * Method set NumePuce
     * @param numepuce
     */
    public void setNumePuce(String numepuce){
        this.NumePuce = numepuce;
    }
    
    /**
     * Method get type vehicle
     * @return type vehicle
     */
    public String getTypeVehicule(){
        return this.TypeVehicule;
    }
    
    /**
     * Method set new type vehicle
     * @param type 
     */
    public void setTypeVehicule(String type){
        this.TypeVehicule = type;
    }
    
    /**
     * Method transform object Car to String
     * @param car
     * @return String carSerial
     */
    public static String serialize(Car car){
    	String carSerial = car.NumePuce+"///"+car.TypeVehicule;
    	return carSerial;
    }
    
    /**
     * Method recreates object Car from String
     * @param serializedCar
     * @return object car
     */
    public static Car unSerialize(String serializedCar){
    	ArrayList<String> values = new ArrayList<String>();
		for (String retval: serializedCar.split("///")){
			values.add(retval);
		}
		Car car = new Car(values.get(0), values.get(1));
		return car;
    }
}
