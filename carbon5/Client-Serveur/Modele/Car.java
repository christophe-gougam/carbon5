/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * Class creates a vehicle
 * @author Carbon5
 */
public class Car {
    private String NumePuce;
    private String matricule;
    private String TypeVehicule;
    private String listoperation;
    private String Car;
    private Date date;
    private int place;

	final static Logger logger = Logger.getLogger(Serveur.class);
	
    private static ArrayList<Car> listCar = new ArrayList<Car>();
    private static ArrayList<ResultSet> AllCar = new ArrayList<ResultSet>();
    
    /**
     * Class constructor
     * @param NumePuce;
     * @param TypeVehicule 
     */
    public Car (){}
        
    public Car (String numePuce, String typevehicule, String matricule, Date date, String listOp, int place){
        this.NumePuce = numePuce;
        this.matricule = matricule;
        this.TypeVehicule = typevehicule;
        this.listoperation=listOp;
        this.date=date;
        this.place=place;
    }
//<<<<<<< HEAD

    public Car(String numPuce, String typeVehicule, String matricule) {
        this.NumePuce = numPuce;
        this.matricule = matricule;
        this.TypeVehicule = typeVehicule;
//=======
//    public Car (String numePuce, String typevehicule, String matricule){
//        this.NumePuce = numePuce;
//        this.matricule = matricule;
//        this.TypeVehicule = typevehicule;
//>>>>>>> origin/Develop
    }
    
    public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public String getListoperation() {
		return listoperation;
	}
	public void setListoperation(String listoperation) {
		this.listoperation = listoperation;
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
     * Method set Matricule
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
    
    public static void emptyCollection(){
        listCar.clear();
    }
    
    public static void addToCollection(Car aCar){
    	listCar.add(aCar);
    }
    
    public static void addCarToCo(Car newCar){
		listCar.clear();
		listCar.add(newCar);
	}
<<<<<<< HEAD
    
    public static void addCar(Car newCar){
    	listCar.clear();
    	listCar.add(newCar);
	}
    public static ArrayList<ResultSet> getAllCar(){
		return AllCar;
		
    public static ArrayList<Car> getAllCar(){
		return listCar;
		
	}
    
    public static ArrayList<Car> getAlCar(){
		return listCar;
	}
    
    public static void removeFromCollection(Car aCar){
    	listCar.remove(aCar);
    }
    

	public static void setAlCar(ArrayList<Car> newc){
		listCar= newc;
	}
    public static boolean isInCollection(String numPuce){
		Boolean check = false;
		for(Car aCar: listCar){
			if (aCar.NumePuce.equalsIgnoreCase(numPuce)){
				check = true;
			}
		}
		return check;
	}

	public static void emptyCollection(){
		
		listCar.clear();
	}
    /**
     * Method transform object Car to String
     * @param car
     * @return String carSerial
     */
    public static String serialize(Car car){
    	String carSerial = car.NumePuce+"///"+car.TypeVehicule+"///"+car.matricule;
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
		Car car = new Car(values.get(0), values.get(1), values.get(2));
		logger.info(values.get(0)+values.get(1)+values.get(2));
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
