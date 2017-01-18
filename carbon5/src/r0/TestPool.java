package r0;

import java.util.*;
import java.io.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class TestPool{
	public static void main(String[] args) {
		System.out.println("choisir l'opération :");
		System.out.println("1 - envoie d'une requête");
		//Get the input from the user
		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine();
		//SQL String to test the connection with BDD
		String requete = "SELECT * FROM test";
		Connection con=null;
		Statement s=null;
		ResultSet rs=null;
		new ConnectionPool();
		System.out.println(saisie);
		
		if (saisie.equals("1")){
			try {
				//récupération de la Connection depuis le DataSource
				con = ConnectionPool.getConnectionFromPool();
				s = con.createStatement();
				rs = s.executeQuery(requete);
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Exception sur l'accès à la BDD " + e);
			}finally {
				if (rs != null)
				{
					try {
						rs.close();
					} catch (SQLException e) {}
					rs = null;
				}
				if (s != null) {
					try {
						s.close();
					} catch (SQLException e) {}
					s = null;
				}
				if (con != null) {	
						ConnectionPool.returnConnectionToPool(con);
				}
			}
		}
	}
}
