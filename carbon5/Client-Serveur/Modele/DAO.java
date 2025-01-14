/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.util.ArrayList;

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
     * @return 
     */
   public abstract T find();

   /**
    * Creates an entry in the database relative to an object
    * @param obj
    * @return 
    */
   public abstract ArrayList<String> create(T obj);

   /**
    * Allows to update the data of an entry in the database
    * @param obj
    * @return 
    */
   public abstract boolean update(T obj);

   /**
    * Allows to delete an entry from the database
    * @param obj 
    */
   public abstract boolean delete(T obj);
}
