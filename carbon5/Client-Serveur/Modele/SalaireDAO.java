package Modele;

import Serveur.Controlleurs.SalaireController;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Carbon5
 */
public class SalaireDAO extends DAO<Salaire> {

    final static Logger logger = Logger.getLogger(SalaireController.class);

    public SalaireDAO(Connection conn) {
        super(conn);
    }

    /**
     * Allows to retrieve a Salaire via its users id
     *
     * @return Salaire
     */
    public Salaire find(int id) {
        Salaire Salaire = new Salaire();
        try {
            ResultSet result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM Salaire where id='" + id + "'"
                    );
            if (result.first())
                Salaire = new Salaire(
                        result.getInt("id"),
                        result.getInt("id_user"),
                        result.getInt("salaire_brut"),
                        result.getDate("date_debut"),
                        result.getDate("date_fin"),
                        result.getInt("temps_contrat_mois")
                );
        } catch (SQLException e) {
            logger.error(e);
        }
        return Salaire;
    }


    /**
     * Allows to update the salary in the database.
     *
     * @param salaire the new employee salary.
     * @return true in case of successful update.
     */
    @Override
    public boolean update(Salaire salaire) {
        String sql1 = "UPDATE Salaire SET date_fin = ? WHERE id_user = ?";
        try {
            PreparedStatement ps = this.connect.prepareStatement(sql1);
            ps.setDate(1, new Date(new java.util.Date().getTime()));
            ps.setInt(2, salaire.getIdUser());
            ps.executeUpdate();
            create(salaire);
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Salaire find() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * Creates a new row in table Salaire.
     *
     * @param salaire - the new salaire.
     * @return the inserted ID in an ArrayList.
     */
    @Override
    public ArrayList<String> create(Salaire salaire) {
        ArrayList<String> result = new ArrayList<>();
        String sql2 = "INSERT INTO Salaire (id_user, salaire_brut, date_debut, temps_contrat_mois) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.connect.prepareStatement(sql2);
            ps.setInt(1, salaire.getIdUser());
            ps.setInt(2, salaire.getSalaireBrut());
            ps.setDate(3, new Date(new java.util.Date().getTime()));
            ps.setInt(4, salaire.getTempsContratMois());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            result.add(String.valueOf(rs.getInt(1)));
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean delete(Salaire obj) {
        return false;
    }
}
