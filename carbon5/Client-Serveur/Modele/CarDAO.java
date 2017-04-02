package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modele.Car;
import Modele.Parking;

/**
 * Class interacting with database for table Car
 * @author carbon5
 *
 */
public class CarDAO {
	
	private static ArrayList<Car> listCar = new ArrayList();
	
	/**
	 * Method to retrieve one car from numPuce id
	 * @param con
	 * @param numP
	 * @return dataResult containing serialized car
	 */
	public static ArrayList<String> getCar(Connection con, String numP){
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		String numPuce = null;
		String matricule = null;
		String typeVehicule = null;
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			System.out.println("Statement created");
			//request to give to database to see if user exists and retrieve its information
			String request = "SELECT NumPuce, Matricule, typeVehicule FROM Car WHERE NumPuce='"+numP+"'";
			rs = st.executeQuery(request);
			System.out.println("Query execution");
			//
			while(rs.next()){
				numPuce = rs.getString("NumPuce");
				matricule = rs.getString("matricule");
				typeVehicule = rs.getString("typeVehicule");
				
			}
			listCar.add(new Car(numPuce,matricule,typeVehicule));
			dataResult.add(Car.serialize(new Car(numPuce,matricule, typeVehicule)));
			System.out.println("Retrieved data from bdd");
		}catch(SQLException e){
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
	public static ArrayList<String> addCar(Connection con, String numP, String matricule, String type){
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			System.out.println("insert execution");
			System.out.println("Statement created");
			//insert request to create new car in database
			String request = "INSERT INTO car(NumPuce,Matricule,TypeVehicule) VALUES('"+numP+"','"+matricule+"','"+type+"')";
			System.out.println(request);
			st.executeUpdate(request);
			System.out.println("insert execution");
		}catch(SQLException e){
			e.printStackTrace();
			dataResult.add("Erreur lors de l'execution de la requête");
		}
		dataResult.add("Voiture enregistrée");
		Car newCar = new Car(numP, matricule, type);
		dataResult.add(Car.serialize(newCar));
		return dataResult;
	}
	
	/**
	 * request to get all cars from database
	 * @param con
	 * @return dataResult containing all serialized car
	 */
	public static ArrayList<String> getAllCars(Connection con){
		
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		String numPuce = null;
		String matricule = null;
		String typeVehicule = null;
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			System.out.println("Statement created");
			//request to give to database to see if user exists and retrieve its information
			String request = "SELECT NumPuce, Matricule, typeVehicule FROM Car";
			rs = st.executeQuery(request);
			System.out.println("Query execution");
			//
			while(rs.next()){
				numPuce = rs.getString("NumPuce");
				matricule = rs.getString("matricule");
				typeVehicule = rs.getString("typeVehicule");
				listCar.add(new Car(numPuce,matricule,typeVehicule));
				dataResult.add(Car.serialize(new Car(numPuce,matricule, typeVehicule)));
			}
			System.out.println("Retrieved data from bdd");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dataResult;
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
		
		ArrayList<String> dataResult = new ArrayList();
		
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
		ArrayList<String> dataResult = new ArrayList();
		
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
}
