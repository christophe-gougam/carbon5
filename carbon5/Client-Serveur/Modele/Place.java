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
    private boolean isOccupied;
    
    /*
    Class constructor
    */
    public Place(int numPlace,int numPark, boolean isOccupied){
        this.numPark = numPark;
        this.isOccupied = isOccupied;
    }
    public Place(){
        
    }
    
    /**
     * Method gets number of place
     * @return 
     */
    public int getNumPlace(){
        return numPlace;
    }
    
    /**
     * Method sets new number of place
     * @param newNumPlace 
     */
    public void setNumPlace(int newNumPlace){
        this.numPlace = newNumPlace;
    }
    
    /*
    Method gets number of parking
    */
    public int getNumPark(){
        return numPark;
    }
    
    /*
    Method sets new number of parking
    */
    public void setNumPark(int newNumPark){
        this.numPark = newNumPark;
    }
    
    /*
    Method gets status "occupied"
    */
    public boolean getIsOccupied(){
        return isOccupied;
    }
    
    /*
    Method sets new status "occupied"
    */
    public void setIsOccupied(boolean newIsOccupied){
        this.isOccupied = isOccupied;
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
    	ArrayList values = new ArrayList();
		for (String retval: serializedPlace.split("///")){
			values.add(retval);
		}
                int numPlace = Integer.parseInt(values.get(0).toString());
                int numPark = Integer.parseInt(values.get(1).toString());
                boolean isOccupied = Boolean.parseBoolean(values.get(2).toString());
                Place place = new Place(numPlace, numPark, isOccupied);
		return place;
    }
}
