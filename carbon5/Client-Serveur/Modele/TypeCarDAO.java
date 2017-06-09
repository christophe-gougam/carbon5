package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * Class interacting with database for table TypeCar
 * @author carbon5
 *
 */
public class TypeCarDAO extends DAO<TypeCar>{
	
	public TypeCarDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	final static Logger logger = Logger.getLogger(Serveur.class);
	
	
	/**
	 * request to get all typecar from database
	 * @param con
	 * @return dataResult containing all serialized typecar
	 */
	public ArrayList<String> getTypeCar(){
		ArrayList<String> type_car = new ArrayList<String>();
		
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM typecar"
                                             );
            TypeCar.emptyCollection();
            
            while(result.next()){
            	
            	TypeCar.addPartToCo(new TypeCar(
						result.getInt("Id"),
						result.getString("Type")));           		
            }   
        } catch (SQLException e) {
                e.printStackTrace();
        }
		//Getting number of parts
		type_car.add(String.valueOf(TypeCar.getAllTypeCar().size()));
    	for(TypeCar typeC : TypeCar.getAllTypeCar()){
    		//adding parts
    		type_car.add(TypeCar.serialize(typeC));
    	}
        return type_car;
	
	}
	

	@Override
	public TypeCar find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> create(TypeCar obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TypeCar obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TypeCar obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
