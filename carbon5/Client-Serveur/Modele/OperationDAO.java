package Modele;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationDAO extends DAO<Operation> {

    final static Logger logger = Logger.getLogger(OperationDAO.class);

    public OperationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public ArrayList<String> create(Operation obj) {
        return null;
    }

    @Override
    public Operation find() {
        return null;
    }

    @Override
    public boolean update(Operation obj) {
        return false;
    }

    @Override
    public boolean delete(Operation obj) {
        return false;
    }

    public Operation getPerformance(int userId) {
        Operation result = null;
        String sql = "SELECT * FROM operation WHERE id_user = ?";
        try {
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Operation();
                result.setId(rs.getInt("id"));
                result.setIdUser(rs.getInt("id_user"));
                result.setDifficulte(rs.getInt("difficulte"));
                result.setDateOperation(rs.getDate("date_operation"));
                result.setTempsConsacre(rs.getInt("temps_consacre"));
                result.setTempsEstime(rs.getInt("temps_estime"));
                result.setCoutTotal(rs.getInt("cout_total"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

}
