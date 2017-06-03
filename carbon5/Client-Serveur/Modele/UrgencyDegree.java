package Modele;

import java.util.ArrayList;

public class UrgencyDegree {
        private int id;
	private String description;
        
        public UrgencyDegree(int id, String description){
            this.id = id;
            this.description = description;
        }

        public UrgencyDegree(int id){
            this.id = id;
        }
        
        public UrgencyDegree(String description){
            this.description = description;
        }
        
        public UrgencyDegree() {
            
        }
        
        public int getId(){
            return id;
        }
        
        public void setId(int id){
            this.id = id;
        }
        
	public String getDescription() {
            return description;
	}

	public void setDescription(String description) {
            this.description = description;
	}
	
	public static String serialize(UrgencyDegree ud){
		String serial = ud.id+"///"+ud.description;
		return serial;
	}
        
        public static UrgencyDegree unSerialize(String serialUD){
		ArrayList values = new ArrayList();
		for (String retval: serialUD.split("///")){
			values.add(retval);
		}
		UrgencyDegree newCS = new UrgencyDegree((int) values.get(0), (String) values.get(1));
		return newCS;
        }
}