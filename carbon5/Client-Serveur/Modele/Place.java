/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 * Class create place
 * @author Carbon5
 */
public class Place {
    private int numPlace;
    private int numPark;
    private int isOccupied;
    
    /*
    Class constructor
    */
    public Place(int numPlace,int numPark, int isOccupied){
        this.numPlace=numPlace;
        this.numPark = numPark;
        this.isOccupied = isOccupied;
    }
    public Place(){
        
    }
    
    /*
    Method gets numero du parking
    */
    public int getNumPark(){
        return numPark;
    }
    
    /*
    Method sets new numero du parking
    */
    public void setNumPark(int newNumPark){
        this.numPark = newNumPark;
    }
    /*
    Method gets num place
    */
    public int getNumPlace(){
        return numPlace;
    }
    
    /*
    Method sets new num place
    */
    public void setNumPlace(int newNumPlace){
        this.numPlace = newNumPlace;
    }
    
    /*
    Method gets status "occupied"
    */
    public int getIsOccupied(){
        return isOccupied;
    }
    
    /*
    Method sets new status "occupied"
    */
    public void setIsOccupied(int newIsOccupied){
        this.isOccupied = newIsOccupied;
    }
    
    /*
    * Method transform object place to String
    * @param place
    * @return String placeSerial
    */
    public static String serialize(Place place){
    	String placeSerial = place.numPlace+"///"+place.numPark+"///"+place.isOccupied;
    	return placeSerial;
    }
    
    /*
    * Method recreates object Place from String
    * @param serializedPlace
    * @return object place
    */
    public static Place unSerialize(String serializedPlace){
    	ArrayList<String> values = new ArrayList<String>();
		for (String retval: serializedPlace.split("///")){
			values.add(retval);
		}
                int numPlace = Integer.parseInt(values.get(0));
                int numPark = Integer.parseInt(values.get(1));
                int isOccupied = Integer.parseInt(values.get(2));
                Place place = new Place(numPlace, numPark, isOccupied);
		return place;
    }
}
