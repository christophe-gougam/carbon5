package Modele;

import java.util.ArrayList;

public class Preferences {
	
	private int id;
	
	private int indifDays;
	private int vetoDays;
	
	private int indifTimeRep;
	private int vetoTimeRep;
	
	private static Preferences prefs;
	
	public Preferences(int id, int indDays, int vetoDays, int indifTimeRep, int vetoTimeRep){
		this.id = id;
		this.indifDays = indDays;
		this.vetoDays = vetoDays;
		this.indifTimeRep = indifTimeRep;
		this.vetoTimeRep = vetoTimeRep;
	}
	
	public Preferences(){}
	
	public static void chargePrefs(Preferences pref){
		prefs = pref;
	}
	
	public static Preferences getPrefs(){
		return prefs;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getIndifDays(){
		return this.indifDays;
	}
	
	public void setIndifDays(int indDays){
		this.indifDays = indDays;
	}
	
	public int getVetoDays(){
		return this.vetoDays;
	}
	
	public void setVetoDays(int vetoDays){
		this.vetoDays = vetoDays;
	}
	
	public int getIndifTimeRep(){
		return this.indifTimeRep;
	}
	
	public void setIndifTimeRep(int indTimeRep){
		this.indifTimeRep = indTimeRep;
	}
	
	public int getVetoTimeRep(){
		return this.vetoTimeRep;
	}
	
	public void setVetoTimeRep(int vetoTRep){
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
		
		Preferences thePref = new Preferences(Integer.parseInt(values.get(0).toString()), Integer.parseInt(values.get(1).toString()), Integer.parseInt(values.get(2).toString()), Integer.parseInt(values.get(3).toString()), Integer.parseInt(values.get(4).toString()));
		return thePref;
	}
}
