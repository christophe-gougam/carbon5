package r1Client.Modele;

public class TypeUser {
	private String workingTypeUser;
	
	public TypeUser(String type){
		this.workingTypeUser = type;
	}
	
	public String getTypeUser(){
		return this.workingTypeUser;
	}
	
	public void setTypeUser(String type){
		this.workingTypeUser = type;
	}
	
	public static String serialize(TypeUser type){
		String objetSerial = type.workingTypeUser;
		return objetSerial;
	}
	
	public static TypeUser unSerialize(String objetSerial){
		return new TypeUser(objetSerial);
	}
}
