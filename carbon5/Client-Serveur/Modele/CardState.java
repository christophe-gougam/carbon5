package Modele;

import java.util.ArrayList;
/**
 * Class for the state of the vehicule
 * @author carbon5
 *
 */
public class CardState {
	
	private int id;
	private String description;
	/**
	 * Constructor
	 * @param id
	 * @param desc
	 */
	public CardState(int id,String desc){
		this.id = id;
		this.description = desc;
	}
	/**
	 * Method get the id
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
	/**
	 * Method to set the id
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	/**
	 * Method to get the description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Method to set the description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Method to serialize the card state
	 * @param card
	 * @return serial
	 */
	public static String serialize(CardState card){
		String serial = card.id+"///"+card.description;
		return serial;
	}
	/**
	 * Method to create the object card state from the serialized object
	 * @param serialCard
	 * @return newCS
	 */
	public static CardState unSerialize(String serialCard){
		ArrayList values = new ArrayList();
		for (String retval: serialCard.split("///")){
			values.add(retval);
		}
		CardState newCS = new CardState((int) values.get(0), (String) values.get(1));
		return newCS;
	}
}
