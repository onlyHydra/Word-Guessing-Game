package services.repository;

import domain.Joc;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Component
public class JocRepositoryJDBC implements JocRepository{
    private JdbcUtils dbUtils;

    public JocRepositoryJDBC(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public Joc findOne(Integer id) throws IllegalArgumentException {
        Connection con = dbUtils.getConnection();
        List<Joc> jocuri = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Joc where id_joc=?")) {
            preStmt.setInt(1, id);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int idJ = result.getInt("id_joc");
                    String cuvant1,cuvant2,cuvant3,litera1,litera2,litera3;
                    cuvant1 = result.getString("cuvant1");
                    cuvant2 = result.getString("cuvant2");
                    cuvant3 = result.getString("cuvant3");
                    litera1 = result.getString("litera1");
                    litera2 = result.getString("litera2");
                    litera3 = result.getString("litera3");
                    Joc jurat = new Joc(cuvant1,cuvant2,cuvant3,litera1,litera2,litera3);
                    jurat.setId(idJ);
                    jocuri.add(jurat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return jocuri.get(0);
    }

    @Override
    public Iterable<Joc> findAll() {
        Connection con = dbUtils.getConnection();
        List<Joc> jocuri = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Joc")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int idJ = result.getInt("id_joc");
                    String cuvant1,cuvant2,cuvant3,litera1,litera2,litera3;
                    cuvant1 = result.getString("cuvant1");
                    cuvant2 = result.getString("cuvant2");
                    cuvant3 = result.getString("cuvant3");
                    litera1 = result.getString("litera1");
                    litera2 = result.getString("litera2");
                    litera3 = result.getString("litera3");
                    Joc jurat = new Joc(cuvant1,cuvant2,cuvant3,litera1,litera2,litera3);
                    jurat.setId(idJ);
                    jocuri.add(jurat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return jocuri;
    }

    @Override
    public void save(Joc entity) {
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("insert into Joc (id_joc, cuvant1, cuvant2,cuvant3,litera1,litera2,litera3) values (?, ?, ?,?,?,?,?)")) {
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getCuvant1());
            preStmt.setString(3,entity.getCuvant2());
            preStmt.setString(4,entity.getCuvant3());
            preStmt.setString(5,entity.getLitera1());
            preStmt.setString(6,entity.getLitera1());
            preStmt.setString(7,entity.getLitera1());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }

    }


}
