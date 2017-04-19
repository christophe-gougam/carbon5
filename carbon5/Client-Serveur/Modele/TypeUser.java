package Modele;

import java.util.ArrayList;

/**
 * Class TypeUser creating type user
 * @author Carbon5
 */
public class TypeUser {
	private int id;
	private String workingTypeUser;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
    /**
     * Class constructor
     * @param type 
     */
	public TypeUser(int id,String type){
		this.id = id;
		this.workingTypeUser = type;
	}
	
    /**
     * Method get type user
     * @return type user
     */
	public String getTypeUser(){
		return this.workingTypeUser;
	}
	
    /**
     * Method set new type user
     * @param type 
     */
	public void setTypeUser(String type){
		this.workingTypeUser = type;
	}
	
    /**
     * Method transform type user to String
     * @return String objetSerial 
     */
	public static String serialize(TypeUser type){
		String objetSerial = type.id+"///"+type.workingTypeUser;
		return objetSerial;
	}
	
    /**
     * Method recreate type user
     * @param objetSerial
     * @return new type user
     */
	public static TypeUser unSerialize(String objetSerial){
		ArrayList<String> values = new ArrayList<String>();
		for (String retval: objetSerial.split("///")){
			values.add(retval);
		}
		TypeUser newTypeUser = new TypeUser(Integer.parseInt(values.get(0)), String.valueOf(values.get(1)));
		return newTypeUser;
	}
}
