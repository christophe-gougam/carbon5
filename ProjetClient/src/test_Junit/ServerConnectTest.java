/**
 * 
 */
package test_Junit;
import java.io.IOException;

import r1Client.ServerConnect;
import r1Client.*;

import org.junit.Test;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.IOException;

import javax.swing.JFrame;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Utilisateur
 *
 */
public class ServerConnectTest {
	
	private static Socket socket = null;
	private static int port =50000;
	private static String identifier;
	private static String serverAddress = "";	//"127.0.0.1"
	private static JFrame frame=null;
	private static Fenetre fen;
	private static Authentication auth;
	private static ServerConnect con;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serverAddress = InetAddress.getLocalHost().getHostAddress().toString();
		fen= new Fenetre();
		fen.setVisible(true);
		Thread.sleep(5000);
		Authentication auth=new Authentication();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(5000);
		fen.dispose();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	}

}
