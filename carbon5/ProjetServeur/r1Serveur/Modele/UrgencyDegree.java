package r1Client.Modele;

public class UrgencyDegree {
        private int id;
	private String description;
        
        public UrgencyDegree(String description){
            
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
	
	
}