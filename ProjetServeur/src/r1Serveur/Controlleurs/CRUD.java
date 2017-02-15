package r1Serveur.Controlleurs;

import java.io.IOException;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import r1Serveur.Modele.TypeUser;
import r1Serveur.Modele.User;
import r1Serveur.Modele.Car;
import r1Serveur.Modele.Parking;


/**
 * Class CarManager manage the vehicles
 * @author Carbon5
 */
public class CRUD{

	
	/**
	 * Method adds a car into database, executes the query
	 * @param con
	 * @param matricule
	 * @param type
	 * @param statut
	 * @param parking
	 * @return dataResult (containing car information)
	 * @throws SQLException if query fails
	 */
	public static ArrayList<String> addCar(Connection con, String matricule, String type, String statut, String parking){
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			System.out.println("insert execution");
			System.out.println("Statement created");
			//insert request to create new car in database
			String request = "INSERT INTO car(IDVehicule,TypeVehicule,statut,NumParking) VALUES('"+matricule+"','"+type+"','"+statut+"','"+parking+"')";
			System.out.println(request);
			st.executeUpdate(request);
			System.out.println("insert execution");
		}catch(SQLException e){
			e.printStackTrace();
			dataResult.add("Erreur lors de l'execution de la requête");
		}
		int park = Integer.parseInt(parking);
		dataResult.add("Voiture enregistrée");
		Car newCar = new Car(matricule, type, statut, new Parking(park));
		dataResult.add(Car.serialize(newCar));
		return dataResult;
	}
	/**
	 * Method checks authentication by reading the database and retrieving user information
	 * @param con
	 * @param login
	 * @param mdp
	 * @return dataResult (Array containing information)
	 * @throws SQLException if query fails
	 */
	public static ArrayList<String> authentication(Connection con, String login, String mdp){
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		String firstName = null;
		String lastName = null;
		String address = null;
		String town = null;
		int postCode = 0;
		String email = null;
		Date hire = null;
		String type = null;
		float income = 0;
		String mdpCheck = null;

		ArrayList<String> dataResult = new ArrayList();
		
		try{
			st = cn.createStatement();
			System.out.println("Statement created");
			//request to give to database to see if user exists and retrieve its information
			String request = "SELECT firstName, lastName, address, town, postalCode, email, hiringDate, password, incomingPerHour,workingTypeUser FROM Users,TypeUser WHERE Users.idTypeUser = TypeUser.id AND login ='"+login+"'";
			rs = st.executeQuery(request);
			System.out.println("Query execution");
			//PROBLEM HERE
			while(rs.next()){
				mdpCheck = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				address = rs.getString("address");
				town = rs.getString("town");
				postCode = rs.getInt("postalCode");
				email = rs.getString("email");
				hire = rs.getDate("hiringDate");
				income = rs.getFloat("incomingPerHour");
				type = rs.getString("workingTypeUser");
				System.out.println("Retrieved data from bdd");
			}
			System.out.println("password= "+mdp);
			//check if login and password match a user
			if(mdp.equals(mdpCheck)){
				User user = new User(firstName,lastName,address,town,postCode,login,email,hire,income,new TypeUser(type));
				System.out.println(user.getIncome());
				dataResult.add("GrantAuth");
				dataResult.add(User.serialize(user));
			}
			else{
				dataResult.add("Erreur de mot de passe");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dataResult;
		}
	}
	