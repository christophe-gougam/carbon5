package r1Serveur.Modele;

/**
 * Class creates a parking
 * @author Carbon5
 */
public class Parking {
	
	private int numParking;
	
    /**
     * Class constructor
     * @param newParking 
     */
	public Parking(int newParking){
		this.numParking = newParking;
	}
	
    /**
     * Method get parking number
     * @return parking number
     */
	public int getNumParking(){
		return this.numParking;
	}
	
    /**
     * Method set new parking number
     * @param newParking 
     */
	public void setNumParking(int newParking){
		this.numParking = newParking;
	}
	

}
