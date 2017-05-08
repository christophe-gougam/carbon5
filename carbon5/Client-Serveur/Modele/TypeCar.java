package Modele;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

public class TypeCar {
	final static Logger logger = Logger.getLogger(Serveur.class);
	private int id;
    private String Type;
	
	
	private static ArrayList<TypeCar> typecar= new ArrayList<TypeCar>();
	
    public TypeCar(){
        
    }

	public TypeCar(int Id, String type){
		this.id = Id;
		this.Type = type;
	}
	
    public int getId(){
            return this.id;
    }
    
    public void setId(int id){
            this.id = id;
    }
        
	public String getType(){
		return this.Type;
	}
	
	public void setType(String type){
		this.Type = type;
	}

	
	public static ArrayList<TypeCar> getAllTypeCar(){
		return typecar;
	}
	public static void setAllParts(ArrayList<TypeCar> newtype){
		typecar = newtype;
	}
	
	public static void addPartToCo(TypeCar newtypecar){
		typecar.add(newtypecar);
	}
	
	public static boolean isInCollection(String type){
		Boolean check = false;
		for(TypeCar atypeC: typecar){
			if (atypeC.Type.equalsIgnoreCase(type)){
				check = true;
			}
		}
		return check;
	}

	public static void emptyCollection(){
		
		TypeCar.typecar.clear();
	}
	
	public static String serialize(TypeCar c){
		String serialize = c.id+"///"+c.getType();
		return serialize;
	}
	
	public static TypeCar unSerialize(String serial){
		logger.info("Enter unserilization");
		ArrayList<String> values = new ArrayList<String>();
		for (String retval: serial.split("///")){
			values.add(retval);
		}

		logger.info("Begin unserilization");
		TypeCar care = new TypeCar(Integer.parseInt(values.get(0)),values.get(1));
		logger.info("Success unserilization");
		return care;
	}
	
	
}