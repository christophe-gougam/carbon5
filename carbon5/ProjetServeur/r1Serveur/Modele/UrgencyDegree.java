package r1Serveur.Modele;

public class UrgencyDegree {
        private int id;
	private String description;
        
        public UrgencyDegree(int id, String description){
            
        }

        public UrgencyDegree() {
            
        }
        
        public int getId(){
            return id;
        }
        
        public void setId(int newId){
            this.id = newId;
        }
        
	public String getDescription() {
            return description;
	}

	public void setDescription(String description) {
            this.description = description;
	}
	
	
}
