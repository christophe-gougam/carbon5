package r1Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class ConnectionPool creating the connection pool
 * @author Pierre
 * 
 */

public class ConnectionPool{
	
	/**
	 * Maximum number of connections allowed
	 */
	final int MAX_POOL_SIZE = 20;
	/**
	 * Array containing the connection objects
	 */
	static ArrayList<Connection> connectionsList = new ArrayList<Connection>();

	/**
	 * Class constructor, calls the method initializing the connections
	 * @see initializeConnectionPool()
	 */
	public ConnectionPool(){
		initializeConnectionPool();
	}

	/**
	 * Method allowing the creation of the connections by calling createConnectionForPool
	 * Creates connections until the pool is full
	 * @see checkIfConnectionPoolIsFull()
	 * @see connectionsList
	 */
	private void initializeConnectionPool()
	{
		while(!checkIfConnectionPoolIsFull())
		{
			System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
			//Adding new connection instance until the pool is full
			connectionsList.add(createNewConnectionForPool());
		}
		System.out.println("Connection Pool is full. "+ connectionsList.size() +" connections created");
		
	}

	/**
	 * Synchronized method to check if the pool is full, returns true if the pool is full, false otherwise
	 * @return boolean
	 */
	private synchronized boolean checkIfConnectionPoolIsFull()
	{

		//Check if the pool size
		if(connectionsList.size() < MAX_POOL_SIZE)
		{
			return false;
		}

		return true;
	}

	/**
	 * Method creating the connections for the pool
	 * @return object connection
	 * @throws exception if the properties file doesn't load
	 * @throws SQLexception if the connection fails to be created
	 * @throxs exception if the input doesn't close
	 */
	private Connection createNewConnectionForPool()
	{
		Connection connection = null;
		Properties prop = new Properties();
		InputStream input = null;
		
		String DriverName;
		String database;
		String dbuser;
		String dbpassword;

		String filename = "config.properties";
		input = ConnectionPool.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
		}
		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DriverName = prop.getProperty("DriverName");
		database = prop.getProperty("database");
		dbuser = prop.getProperty("dbuser");
		dbpassword = prop.getProperty("dbpassword");
		try
		{
			Class.forName(DriverName);
			connection = DriverManager.getConnection(database, dbuser, dbpassword);
			System.out.println("Connection: "+connection);
		}
		catch(SQLException e)
		{
			System.err.println("SQLException: "+e);
			return null;
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("ClassNotFoundException: "+e);
			return null;
		}
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * Synchronized method that gives a user a connection from the pool if not empty
	 * @return object connection
	 * @see connectionsList
	 */
	public static synchronized Connection getConnectionFromPool()
	{
		Connection connection = null;

		//Check if there is a connection available. There are times when all the connections in the pool may be used up
		if(connectionsList.size() > 0)
		{
			connection = connectionsList.get(0);
			connectionsList.remove(0);
		}
		//Giving away the connection from the connection pool
		return connection;
	}

	/**
	 * Synchronized method that returns the connection to the pool
	 * @param object connection
	 * @see connectionsList
	 */
	public static synchronized void returnConnectionToPool(Connection connection)
	{
		//Adding the connection from the client back to the connection pool
		connectionsList.add(connection);
	}
	/**
	 * Synchronized method that return the number of connections available in the pool
	 * @return number of connections available
	 * @see connectionsList
	 */
	public synchronized int sizeConnectionPool(){
		return connectionsList.size();
	}
	
	/**
	 * Close the connections at server shutdown
	 * @throws SQLexception if connection fails to close
	 */
	public static void closePool(){
		for (int i=0;i<connectionsList.size();i++){
			try {
				connectionsList.get(i).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
