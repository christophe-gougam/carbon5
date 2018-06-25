/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Serveur.Controlleurs.SalaireController;
import org.apache.log4j.Logger;

/**
 *
 * @author Carbon5
 */
public abstract class SalaireDAO extends DAO<Salaire> {

    final static Logger logger = Logger.getLogger(SalaireController.class);
    public SalaireDAO(Connection conn) {
        super(conn);
    }

    /**
     * Allows to retrieve a Salaire via its users id
     * @return Salaire
     */
    public Salaire find(int id) {
        Salaire Salaire = new Salaire();
        try {
            ResultSet result = this .connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM Salaire where id='"+id +"'"
                    );
            if(result.first())
                Salaire = new Salaire(
                        result.getInt("id"),
                        result.getInt("id_user"),
                        result.getInt("salaire_brut"),
                        result.getDate("date_debut"),
                        result.getDate("date_fin"),
                        result.getInt("temps_contrat_mois")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Salaire;
    }


   // /**
     //* Allows to update the data of an entry in the database
     //* @param obj
     //* @return true
     //*/
    /*@Override
    public boolean update(Salaire obj) {
        try {
            if (obj.getSalaireBrut()!=1){
                this .connect
                        .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE
                        ).executeUpdate(
                        "UPDATE Salaire SET salaire_brut ='" + obj.getSalaireBrut() + "' " +
                                " WHERE id_user = '" + obj.getIdUser()+ "' "
                );
                return true;
            }
            else{
                this .connect
                        .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE
                        ).executeUpdate(
                        "UPDATE Salaire SET "+
                                "NameSalaire ='" + obj.getNameSalaire() + "', " +
                                "PurchasePrice ='" + obj.getPurchasePrice() + "' " +
                                " WHERE Id = " + obj.getIdSalaire()
                );
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }*/

    @Override
    public Salaire find() {
        // TODO Auto-generated method stub
        return null;
    }
}
