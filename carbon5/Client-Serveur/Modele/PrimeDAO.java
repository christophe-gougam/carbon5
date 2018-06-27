package Modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PrimeDAO extends DAO<Prime> {


    public PrimeDAO(Connection conn) {
        super(conn);
    }

    /**
     * Creates an entry in the database relative to an object
     * @param obj
     * @return true
     */
    @Override
    public ArrayList<String> create(Prime obj) {
        ArrayList<String> queryResult = new ArrayList<String>();
        try {
            java.sql.PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO prime (date_attribution, montant_prime) VALUES(?, ?)"
                    );

            prepare.setDate(1, Date.valueOf((LocalDate) obj.getDate_attribution()));
            prepare.setInt(2, obj.getMontant_prime());

            prepare.executeUpdate();
            //obj = this.find();

            String message = "CreatePrimeOK";
            queryResult.add(message);

        } catch (SQLException e) {
            e.printStackTrace();
            queryResult.add("CreatePrimeKO");
        }
        return queryResult;
    }

    @Override
    public Prime find() {
        return null;
    }


    @Override
    public boolean update(Prime obj) {
        return false;
    }

    @Override
    public boolean delete(Prime obj) {
        return false;
    }
}
