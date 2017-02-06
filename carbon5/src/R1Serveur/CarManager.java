package R1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarManager{

	public static void main(String[] args) {
		supprimerEnBase("Audi");
		//sauverEnBase("BMW");
		//modifierEnBase("BMW","Porsche");
		lireEnBase();
	}

	public static void sauverEnBase(String marque) {

		// Information d'accès à la base de données
		String url = "jdbc:mysql://localhost:3306/carbon5";
		String login = "root";
		String passwd = "";
		Connection cn =null;
		Statement st =null;

		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "INSERT INTO `car` (`marque`) VALUES ('"
					+ marque + "')";

			// Etape 4 : exécution requête
			st.executeUpdate(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<String> authentication(Connection con, String login, String mdp){
		Connection cn = con;
		Statement st = null;
		ResultSet rs = null;
		String nom = null;
		String prenom = null;
		String age = null;
		String mdpCheck = null;
		Boolean confirm = false;
		ArrayList<String> dataResult = null;
		
		try{
			st = cn.createStatement();
			String request = "SELECT * FROM User WHERE login ='"+login+"'";
			rs = st.executeQuery(request);
			while(rs.next()){
				mdpCheck = rs.getString("mdp");
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				age = rs.getString("age");		
			}
			if(mdp.equals(mdpCheck)){
				dataResult.add(nom);
				dataResult.add(prenom);
				dataResult.add(age);
				dataResult.add(login);
			}
			else{
				dataResult.add("Aucune donnée trouvée");
			}
		}
		catch(SQLException e){
			System.out.println("Problème de connexion à la base de donnée");
		}
		return dataResult;
	}
	
	public static void lireEnBase() {

		//Information d'accès à la base de données
		String url = "jdbc:mysql://localhost:3306/carbon5";
		String login = "root";
		String passwd = "";
		Connection cn =null;
		Statement st =null;
		ResultSet rs =null;
		
		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "SELECT * FROM car";

			// Etape 4 : exécution requête
			rs = st.executeQuery(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

			while (rs.next()) {
				System.out.println(rs.getString("marque"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void supprimerEnBase(String marque) {

		// Information d'accès à la base de données
		String url = "jdbc:mysql://localhost:3306/carbon5";
		String login = "root";
		String passwd = "";
		Connection cn =null;
		Statement st =null;

		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "DELETE FROM car WHERE marque='"+marque+"'";

			// Etape 4 : exécution requête
			st.executeUpdate(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void modifierEnBase(String marque1, String marque2) {

		// Information d'accès à la base de données
		String url = "jdbc:mysql://localhost:3306/carbon5";
		String login = "root";
		String passwd = "";
		Connection cn =null;
		Statement st =null;

		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "UPDATE car " + "SET marque ='"+marque2+"' " + " WHERE marque ='"+marque1+"' ";

			// Etape 4 : exécution requête
			st.executeUpdate(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}