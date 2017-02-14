/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Serveur.Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author CongThuan
 */
public class Car {
    private String matriculation;
    private String type;
    private String statut;
    private Parking parking;
    
    public Car (String matriculation, String type, String statut, Parking parking){
        this.matriculation = matriculation;
        this.type = type;
        this.statut = statut;
        this.parking = parking;
    }
    
    public String getMatriculation(){
        return matriculation;
    }
    
    public void setId(String newMatriculation){
        this.matriculation = newMatriculation;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getStatut(){
        return this.statut;
    }
    
    public void setStatut(String statut){
        this.statut = statut;
    }
    
    public Parking getParking(){
    	return this.parking;
    }
    
    public void setParking(Parking newParking){
    	this.parking = newParking;
    }
    
    public static String serialize(Car car){
    	String carSerial = car.matriculation+"///"+car.type+"///"+car.statut+"///"+car.parking.getNumParking();
    	return carSerial;
    }
    
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
