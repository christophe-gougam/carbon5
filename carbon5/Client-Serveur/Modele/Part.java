package Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

public class Part {
	final static Logger logger = Logger.getLogger(Serveur.class);
	private int id;
    private String IdPart;
	private int stock;
	private String namePart;
	private float purchasePrice;
	
	private static ArrayList<Part> parts= new ArrayList<Part>();
	
        public Part(){
            
        }
        
	public Part(int stock, String namePart, float purchasePrice){
		this.stock = stock;
		this.namePart = namePart;
		this.purchasePrice = purchasePrice;
	}
	public Part(String IdPart, String namePart, float purchasePrice){
		this.IdPart = IdPart;
		this.namePart = namePart;
		this.purchasePrice = purchasePrice;
	}
	
        public int getId(){
                return this.id;
        }
        
        public void setId(int id){
                this.id = id;
        }
        
	public int getStock(){
		return this.stock;
	}
	
	public void setStock(int stock){
		this.stock = stock;
	}
	public String getIdPart(){
		return this.IdPart;
	}
	
	public void setIdPart(String idpart){
		this.IdPart = idpart;
	}
	
	public String getNamePart(){
		return this.namePart;
	}
	
	public void setNamePart(String namePart){
		this.namePart = namePart;
	}
	
	public float getPurchasePrice(){
		return this.purchasePrice;
	}
	
	public void setPurchasePrice(float purchasePrice){
		this.purchasePrice = purchasePrice;
	}
	
	public static ArrayList<Part> getAllParts(){
		return parts;
	}
	
	public static void setAllParts(ArrayList<Part> newParts){
		parts = newParts;
	}
	
	public static void addPartToCo(Part newPart){
		parts.add(newPart);
	}
	
	public static String serialize(Part part){
		String serialize = part.getStock()+"///"+part.getNamePart()+"///"+part.getPurchasePrice();
		return serialize;
	}
	
	public static Part unSerialize(String serial){
		logger.info("Enter unserilization");
		ArrayList values = new ArrayList();
		for (String retval: serial.split("///")){
			values.add(retval);
		}

		logger.info("Begin unserilization");
		Part part = new Part(Integer.parseInt(values.get(0).toString()),values.get(1).toString(),Float.parseFloat(values.get(2).toString()));
		logger.info("Success unserilization");
		return part;
	}
	
	
}
