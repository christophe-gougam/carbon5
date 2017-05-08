/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 * Class creates a vehicle
 * @author Carbon5
 */
public class Car {
    private String NumePuce;
    private String matricule;
    private String TypeVehicule;
    
    private static ArrayList<Car> listCar = new ArrayList();
    
    /**
     * Class constructor
     * @param NumePuce;
     * @param TypeVehicule 
     */
    public Car (){}
    public Car (String numePuce, String matricule, String typevehicule){
        this.NumePuce = numePuce;
        this.matricule = matricule;
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
     * Method get Matricule
     * @return matricule
     */
    public String getMatricule(){
    	return this.matricule;
    }
    
    /**
     * Method set matricule
     * @param newMatricule
     */
    public void setMatricule(String newMatricule){
    	this.matricule = newMatricule;
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
    
    public static void addToCollection(Car aCar){
    	listCar.add(aCar);
    }
    
    public static void removeFromCollection(Car aCar){
    	listCar.remove(aCar);
    }
    
    /**
     * Method transform object Car to String
     * @param car
     * @return String carSerial
     */
    public static String serialize(Car car){
    	String carSerial = car.NumePuce+"///"+car.matricule+"///"+car.TypeVehicule;
    	return carSerial;
    }
    
    /**
     * Method recreates object Car from String
     * @param serializedCar
     * @return object car
     */
    public static Car unSerialize(String serializedCar){
    	ArrayList values = new ArrayList();
		for (String retval: serializedCar.split("///")){
			values.add(retval);
		}
		Car car = new Car(String.valueOf(values.get(0)), String.valueOf(values.get(1)), String.valueOf(values.get(2)));
		return car;
    }
    /**
     * Method toString
     * 
     */
    public String toString(Object values) {
        return String.valueOf(values);
    }
}
