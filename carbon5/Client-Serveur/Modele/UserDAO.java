package Modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DAO<User>{

	/**
	 *
	 * @author Carbon5
	 */
	public UserDAO(Connection conn) {
        super(conn);
    }
	
	public User auth(String login, String mdp){
		User ud = null;
		
		try{
			ResultSet result = this .connect
                    .createStatement(
                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_UPDATABLE
                             	).executeQuery(
                            		 "SELECT * FROM users JOIN typeuser where users.TypeUser = typeuser.id AND login='"+login+"' AND PasswordUser='"+mdp+"'"
                             );
				if(result.first())
				ud = new User(
                        //result.getString("id"),
                        result.getInt("Id"),
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("Address"),
                        result.getString("Town"),
                        result.getInt("PostalCode"),
                        result.getString("login"),
                        result.getString("Email"),
                        result.getDate("HiringDate"),
                        result.getFloat("IncomingPerHour"),
                        (new TypeUser(result.getInt("typeuser.id"),result.getString("Profil")))
						); 
				
				User.emptyCollection();

	            	User.addAUserToCo(ud);          		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ud;
	}

	
	/**
     * Allows to retrieve an object via its ID
     * @return object
     */
    @Override
    public User find() {
        User ud = null;
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                            		 "SELECT * FROM users JOIN typeuser where users.TypeUser = typeuser.id"
                                             );
            if(result.first())
            		ud = new User(
                                        //result.getString("id"),
            							result.getInt("Id"),
                                        result.getString("FirstName"),
                                        result.getString("LastName"),
                                        result.getString("Address"),
                                        result.getString("Town"),
                                        result.getInt("PostalCode"),
                                        result.getString("login"),
                                        result.getString("Email"),
                                        result.getDate("HiringDate"),
                                        result.getFloat("IncomingPerHour"),
                                        (new TypeUser(result.getInt("typeuser.id"),result.getString("Profil")))
                        );            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return ud;
    }
    
    /**
     * Creates an entry in the database relative to an object
     * @param obj
     * @return true
     */
    @Override
    public ArrayList<String> create(User obj) {
    	ArrayList<String> queryResult = new ArrayList<String>();
        try {
            //Vu que nous sommes sous postgres, nous allons chercher manuellement
            //la prochaine valeur de la séquence correspondant à l'id de notre table
            ResultSet result = this.connect
                                   .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    "SELECT NEXTVAL('ud_id_seq') as Id"
                                    );
            if(result.first()){
                    long id = result.getLong("id");
                    java.sql.PreparedStatement prepare = this.connect
                                                             .prepareStatement(
                                                              "INSERT INTO user (Id, TypeUser, FirstName, LastName, Address, Town, PostalCode, Login, PasswordUser, Email, HiringDate, IncomingPerHour) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                                                              );
                    prepare.setLong(1, id);
                    prepare.setInt(2, obj.getTypeUser().getId());
                    prepare.setString(3, obj.getFirstName());
                    prepare.setString(4, obj.getLastName());
                    prepare.setString(5, obj.getAddress());
                    prepare.setString(6, obj.getTown());
                    prepare.setInt(7, obj.getPostCode());
                    prepare.setString(8, obj.getLogin());
                    prepare.setString(9, "password");
                    prepare.setString(10, obj.getEmail());
                    prepare.setDate(11, (Date) obj.getHireDate());
                    prepare.setFloat(12, obj.getIncome());

                    prepare.executeUpdate();
                    //obj = this.find();
                    queryResult.add("CreateUserOK");
            }
        } catch (SQLException e) {
                e.printStackTrace();
                queryResult.add("CreateUserKO");
        }
        return queryResult;
    }

    /**
     * Allows to update the data of an entry in the database
     * @param obj
     * @return true
     */
    @Override
    public boolean update(User obj) {
        try {	
            this .connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE user SET Login = '" + obj.getLogin() + "'"+
                    " WHERE Login = " + obj.getLogin()
                 );
                    //obj = this.find();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }

    /**
     * Allows to delete an entry from the database
     * @param obj
     * @return true
     */
    @Override
    public boolean delete(User obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
               ).executeUpdate(
                    "DELETE FROM user WHERE Login = " + obj.getLogin()
               );

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return true;
    }   
    
    public User getUser(int id){
    	User ud = null;
        try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                            		 "SELECT * FROM users JOIN typeuser where users.TypeUser = typeuser.id WHERE id='"+id+"'"
                                             );
            if(result.first())
            		ud = new User(
                                        //result.getString("id"),
            							result.getInt("Id"),
                                        result.getString("FirstName"),
                                        result.getString("LastName"),
                                        result.getString("Address"),
                                        result.getString("Town"),
                                        result.getInt("PostalCode"),
                                        result.getString("login"),
                                        result.getString("Email"),
                                        result.getDate("HiringDate"),
                                        result.getFloat("IncomingPerHour"),
                                        (new TypeUser(result.getInt("typeuser.id"),result.getString("Profil")))
                        );            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return ud;
    }
}


