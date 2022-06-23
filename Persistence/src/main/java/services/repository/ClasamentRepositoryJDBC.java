package services.repository;

import domain.Clasament;
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
public class ClasamentRepositoryJDBC implements  ClasamentRepository {
    private JdbcUtils dbUtils;

    public ClasamentRepositoryJDBC(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public Clasament findOne(Integer id) throws IllegalArgumentException {
        Connection con = dbUtils.getConnection();
        List<Clasament> clasements = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from clasament where id_jucator=?")) {
            preStmt.setInt(1, id);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int idJ = result.getInt("id_joc");
                    int idjucator= result.getInt("id_jucator");
                    String cuvinte_ghicite,numar_de_cuvinte;
                    cuvinte_ghicite = result.getString("Cuvinte_ghicite");
                    numar_de_cuvinte= result.getString("numar_cuvinte_ghicite");

                    Clasament jurat = new Clasament(idJ,idjucator,cuvinte_ghicite,numar_de_cuvinte);
                    jurat.setId(idJ);
                    clasements.add(jurat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return clasements.get(0);
    }

    @Override
    public Iterable<Clasament> findAll() {
        return null;
    }


    public Iterable<Clasament> findAll(int id) {
        Connection con = dbUtils.getConnection();
        List<Clasament> clasements = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from clasament where id_jucator=?")) {
            preStmt.setInt(1, id);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id_runda = result.getInt("id_clas");
                    int idJ = result.getInt("id_joc");
                    int idjucator= result.getInt("id_jucator");
                    int punctaj = result.getInt("punctaj_obtinut");
                    String cuvinte_ghicite,numar_de_cuvinte;
                    cuvinte_ghicite = result.getString("Cuvinte_ghicite");
                    numar_de_cuvinte= result.getString("numarul_de_cuvinte_ghicite");

                    Clasament jurat = new Clasament(idJ,idjucator,cuvinte_ghicite,numar_de_cuvinte);
                    jurat.setPunctaj(punctaj);
                    jurat.setId(id_runda);
                    clasements.add(jurat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return clasements;
    }

    @Override
    public void save(Clasament entity) {

    }
}
