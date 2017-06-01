package Modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Serveur.Controlleurs.Serveur;

/**
 * Class interacting with database for table Car
 * @author carbon5
 *
 */
public class CarDAO extends DAO<Car>{
	String listop = null;
	Date Entrydate;
	int place=0;
	public CarDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	final static Logger logger = Logger.getLogger(Serveur.class);
	
	/**
	 * Method to retrieve one car from numPuce id
	 * @param con
	 * @param numP
	 * @return dataResult containing serialized car
	 */
	public ArrayList<Car> getCar(String numP){
		ArrayList<Car> dataResult = new ArrayList<Car>();
		Car c=new Car("", "", "");
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT NumPuce, TypeVehicule, matricule FROM car WHERE NumPuce='"+numP+"'"
                                             );
            Car.emptyCollection();
            if(result.first())
            	c=new Car(result.getString("NumPuce"), result.getString("TypeVehicule"), result.getString("matricule"));
            Car.addCar(c);
            dataResult.add(c);            	
        } catch (SQLException e) {
                e.printStackTrace();
        }
	  	
		return dataResult;
	}
	
	/**
	 * request to add a Car
	 * @param con
	 * @param numP
	 * @param type
	 * @return dataResult confirming addition of car
	 */
	public boolean addCar(Car car, LocalDate dat){
		Date date = java.sql.Date.valueOf(dat);
        try{
        	java.sql.PreparedStatement prepare = this.connect
                    .prepareStatement(
                    		"INSERT INTO car(NumPuce,TypeVehicule,matricule, EntranceDate, ListeOperations, Emplacement) VALUES(?, ?, ?, ?, ?, ?)"
                     );

			prepare.setString(1, car.getNumePuce());
			prepare.setString(2, car.getTypeVehicule());
			prepare.setString(3, car.getMatricule());
			prepare.setDate(4, date);
			prepare.setString(5, car.getListoperation());
			prepare.setInt(6, car.getPlace());
			logger.info(car.getNumePuce()+"  "+car.getTypeVehicule()+"  "+date+"  "+car.getListoperation()+"  "+car.getPlace());
			prepare.executeUpdate();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * request to get all cars from database
	 * @param con
	 * @return dataResult containing all serialized car
	 */
	public ArrayList<String> getAllCars(){
            ArrayList<String> cars = new ArrayList<String>();
		try {
                ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM car"
                                             );
            Car.emptyCollection();
            while(result.next()){
            	Car.addCarToCo(new Car(
						String.valueOf(result.getString("NumPuce")),
						result.getString("TypeVehicule"),
						result.getString("matricule")));		
            }            
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            //Getting number of parts
            cars.add(String.valueOf(Car.getAllCar().size()));
            for(Car aCar: Car.getAllCar()){
                    //adding parts
                    cars.add(Car.serialize(aCar));
            }
            return cars;
        }
	
	/**
	 * request to delete a car from database
	 * @param con
	 * @param carDel
	 * @return dataResult confirming deletion of car
	 */
	public static ArrayList<String> removeCar(Connection con, Car carDel){
		Connection cn = con;
		Statement st = null;
		
		ArrayList<String> dataResult = new ArrayList<String>();
		
		try{
			st = cn.createStatement();
			String request = "DELETE FROM Car WHERE numPuce='"+carDel.getNumePuce()+"'";
			st.executeUpdate(request);
		}catch(SQLException e){
			e.printStackTrace();
		}
		dataResult.add("Succes Removal");
		return dataResult;
	}
	
	/**
	 * request to update a car
	 * @param con
	 * @param car
	 * @param numP
	 * @param type
	 * @return dataResult confirming update of car
	 */
	public static ArrayList<String> updateCar(Connection con, Car car,String numP,String matricule, String type){
		Connection cn = con;
		Statement st = null;
		ArrayList<String> dataResult = new ArrayList<String>();
		
		String request = "UPDATE Car SET Numpuce ='"+numP+"',Matricule = '"+matricule+"' TypeVehicule ='"+type+"' WHERE NumPuce = '"+car.getNumePuce()+"'";
		try{
			st = cn.createStatement();
			st.executeUpdate(request);
		}catch(SQLException e){
			e.printStackTrace();
		}
		dataResult.add("Succes Update");
		return dataResult;
		
		
	}
	

	@Override
	public Car find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> create(Car obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Car obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Car obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
