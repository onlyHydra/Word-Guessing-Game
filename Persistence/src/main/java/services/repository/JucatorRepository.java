package services.repository;

import domain.Jucator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Component
public class JucatorRepository {

    private JdbcUtils dbUtils;

    public JucatorRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }


    public Jucator findOne(Integer integer) throws IllegalArgumentException {
        Connection con = dbUtils.getConnection();
        List<Jucator> participants = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Jucator where unique_id=?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int idJ = result.getInt("unique_id");
                    String nume = result.getString("alias");
                    Jucator p=new Jucator(nume);
                    p.setId(idJ);
                    participants.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return participants.get(0);
    }


    public Iterable<Jucator> findAll() {
        Connection con = dbUtils.getConnection();
        List<Jucator> jucatori = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Jucator")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int idJ = result.getInt("unique_id");
                    String nume = result.getString("alias");
                    Jucator jucator = new Jucator(nume);
                    jucator.setId(idJ);
                    jucatori.add(jucator);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return jucatori;
    }


    public void save(Jucator entity) {

    }
}
