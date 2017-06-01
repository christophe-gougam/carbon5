package Modele;

import java.util.ArrayList;

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
	public Part(String IdPart, int stock, String namePart, float purchasePrice){
		this.IdPart = IdPart;
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
	
	public static boolean isInCollection(String id){
		Boolean check = false;
		for(Part aPart: parts){
			if (aPart.IdPart.equalsIgnoreCase(id)){
				check = true;
			}
		}
		return check;
	}
	
	public static Part getPartFromCollection(Part thePart){
		Part toSend = new Part();
		
		for (Part aPart: Part.parts){
			if(aPart.equals(thePart)){
				toSend = aPart;
			}
		}
		
		return toSend;
	}
	
	public static void emptyCollection(){
		
		Part.parts.clear();
	}
	
	public static String serialize(Part part){
		String serialize = part.IdPart+"///"+part.getStock()+"///"+part.getNamePart()+"///"+part.getPurchasePrice();
		return serialize;
	}
	
	public static Part unSerialize(String serial){
		logger.info("Enter unserilization");
		ArrayList values = new ArrayList();
		for (String retval: serial.split("///")){
			values.add(retval);
		}

		logger.info("Begin unserilization");
		Part part = new Part(values.get(0).toString(),Integer.parseInt(values.get(1).toString()),values.get(2).toString(),Float.parseFloat(values.get(3).toString()));
		logger.info("Success unserilization");
		return part;
	}
	
	
}
