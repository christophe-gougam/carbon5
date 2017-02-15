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
 * Class creates a vehicle
 * @author Carbon5
 */
public class Car {
    private String matriculation;
    private String type;
    private String statut;
    private Parking parking;
    
    /**
     * Class constructor
     * @param matriculation
     * @param type
     * @param statut
     * @param parking 
     */
    public Car (String matriculation, String type, String statut, Parking parking){
        this.matriculation = matriculation;
        this.type = type;
        this.statut = statut;
        this.parking = parking;
    }
    
    /**
     * Method get matriculation number
     * @return matriculation number
     */
    public String getMatriculation(){
        return matriculation;
    }
    
    /**
     * Method set new matriculation number
     * @param newMatriculation 
     */
    public void setId(String newMatriculation){
        this.matriculation = newMatriculation;
    }
    
    /**
     * Method get type vehicle
     * @return type vehicle
     */
    public String getType(){
        return this.type;
    }
    
    /**
     * Method set new type vehicle
     * @param type 
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Method get vehicle's status
     * @return vehicle's status
     */
    public String getStatut(){
        return this.statut;
    }
    
    /**
     * Method set new vehicle's status
     * @param statut 
     */
    public void setStatut(String statut){
        this.statut = statut;
    }
    
    /**
     * Method get vehicle's parking
     * @return vehicle's parking
     */
    public Parking getParking(){
    	return this.parking;
    }
    
    /**
     * Method set new vehicle's parking
     * @param newParking 
     */
    public void setParking(Parking newParking){
    	this.parking = newParking;
    }
    
    /**
     * Method transform object vehicle to String
     * @param car
     * @return String carSerial
     */
    public static String serialize(Car car){
    	String carSerial = car.matriculation+"///"+car.type+"///"+car.statut+"///"+car.parking.getNumParking();
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
		int numPark = Integer.parseInt(values.get(3).toString());
		Car car = new Car(values.get(0).toString(), values.get(1).toString(), values.get(2).toString(), new Parking(numPark));
		return car;
    }
}
