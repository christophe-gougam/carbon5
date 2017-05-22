package Modele;

import java.util.ArrayList;

public class Preferences {
	
	private int id;
	
	private float indifDays;
	private float vetoDays;
	
	private float indifTimeRep;
	private float vetoTimeRep;
	
	public Preferences(int id, float indDays, float vetoDays, float indifTimeRep, float vetoTimeRep){
		this.id = id;
		this.indifDays = indDays;
		this.vetoDays = vetoDays;
		this.indifTimeRep = indifTimeRep;
		this.vetoTimeRep = vetoTimeRep;
	}
	
	public Preferences(){}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public float getIndifDays(){
		return this.indifDays;
	}
	
	public void setIndifDays(float indDays){
		this.indifDays = indDays;
	}
	
	public float getVetoDays(){
		return this.vetoDays;
	}
	
	public void setVetoDays(float vetoDays){
		this.vetoDays = vetoDays;
	}
	
	public float getIndifTimeRep(){
		return this.indifTimeRep;
	}
	
	public void setIndifTimeRep(float indTimeRep){
		this.indifTimeRep = indTimeRep;
	}
	
	public float getVetoTimeRep(){
		return this.vetoTimeRep;
	}
	
	public void setVetoTimeRep(float vetoTRep){
		this.vetoTimeRep = vetoTRep;
	}
	
	public static String serialize(Preferences aPref){
		String serial = "";
		serial += aPref.id+"///"+aPref.getIndifDays()+"///"+aPref.getVetoDays()+"///"+aPref.getIndifTimeRep()+"///"+aPref.getVetoTimeRep();
		return serial;
	}
	
	public static Preferences unSerialize(String serial){
		ArrayList values = new ArrayList();
		for (String retval: serial.split("///")){
			values.add(retval);
		}
		
		Preferences thePref = new Preferences(Integer.parseInt(values.get(0).toString()), Float.parseFloat(values.get(1).toString()), Float.parseFloat(values.get(2).toString()), Float.parseFloat(values.get(3).toString()), Float.parseFloat(values.get(4).toString()));
		return thePref;
	}
}
