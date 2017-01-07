package r0;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christophe G
 */
public class Connexion {
    
    private String url= "jdbc:mysql://localhost:3306/carbon5";
    private String user = "root";
    private String password = "";
    private static Connection conn;
    
    
    
    private Connexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok");
            conn=DriverManager.getConnection(url,user,password);
            System.out.println("Connexion effective");
            
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public static Connection getInstance(){
        if(conn==null){
            new Connexion();
        }
        return conn;
    }

    
}