/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Serveur.Modele;

import java.sql.Connection;

/**
 * <>
 * @author Carbon5
 */
public abstract class DAO<T> {
    public Connection connect = null;
    
    public DAO(Connection conn){
        this.connect = conn;
    }
    
    /**
     * Allows to retrieve an object via its ID
     * @param id
     * @return 
     */
   public abstract T find(long id);

   /**
    * Creates an entry in the database relative to an object
    * @param obj
    * @return 
    */
   public abstract T create(T obj);

   /**
    * Allows to update the data of an entry in the database
    * @param obj
    * @return 
    */
   public abstract T update(T obj);

   /**
    * Allows to delete an entry from the database
    * @param obj 
    */
   public abstract void delete(T obj);
}
