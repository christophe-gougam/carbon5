package r1Client.Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Part {
	private int stock;
	private String namePart;
	private float purchasePrice;
	
	public Part(int stock, String namePart, float purchasePrice){
		this.stock = stock;
		this.namePart = namePart;
		this.purchasePrice = purchasePrice;
	}
	
	public int getStock(){
		return this.stock;
	}
	
	public void setStock(int stock){
		this.stock = stock;
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
	
	public static String serialize(Part part){
		String serialize = part.getStock()+"///"+part.getNamePart()+"///"+part.getPurchasePrice();
		return serialize;
	}
	
	public static Part unSerialize(String serial){
		System.out.println("Enter unserilization");
		ArrayList values = new ArrayList();
		for (String retval: serial.split("///")){
			values.add(retval);
		}

		System.out.println("Begin unserilization");
		Part part = new Part(Integer.parseInt(values.get(0).toString()),values.get(1).toString(),Float.parseFloat(values.get(2).toString()));
		System.out.println("Success unserilization");
		return part;
	}
	
	
}