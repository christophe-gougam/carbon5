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
	
	public String serialize(){
		String objetSerial = workingTypeUser;
		return objetSerial;
	}
	
	public TypeUser unSerialize(String objetSerial){
		return new TypeUser(objetSerial);
	}
}
