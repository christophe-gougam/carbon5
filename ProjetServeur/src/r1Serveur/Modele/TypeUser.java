package r1Client.Modele;

/**
 * Class TypeUser creating type user
 * @author Carbon5
 */
public class TypeUser {
	private String workingTypeUser;
	
    /**
     * Class constructor
     * @param type 
     */
	public TypeUser(String type){
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
		String objetSerial = type.workingTypeUser;
		return objetSerial;
	}
	
    /**
     * Method recreate type user
     * @param objetSerial
     * @return new type user
     */
	public static TypeUser unSerialize(String objetSerial){
		return new TypeUser(objetSerial);
	}
}
