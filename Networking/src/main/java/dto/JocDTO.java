package dto;

import java.io.Serializable;

public class JocDTO implements Serializable {

    String cuvant1,cuvant2,cuvant3,litera1,litera2,litera3;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuvant1() {
        return cuvant1;
    }

    public void setCuvant1(String cuvant1) {
        this.cuvant1 = cuvant1;
    }

    public String getCuvant2() {
        return cuvant2;
    }

    public void setCuvant2(String cuvant2) {
        this.cuvant2 = cuvant2;
    }

    public String getCuvant3() {
        return cuvant3;
    }

    public void setCuvant3(String cuvant3) {
        this.cuvant3 = cuvant3;
    }

    public String getLitera1() {
        return litera1;
    }

    public void setLitera1(String litera1) {
        this.litera1 = litera1;
    }

    public String getLitera2() {
        return litera2;
    }

    public void setLitera2(String litera2) {
        this.litera2 = litera2;
    }

    public String getLitera3() {
        return litera3;
    }

    public void setLitera3(String litera3) {
        this.litera3 = litera3;
    }

    public JocDTO(String cuvant1, String cuvant2, String cuvant3, String litera1, String litera2, String litera3) {
        this.cuvant1 = cuvant1;
        this.cuvant2 = cuvant2;
        this.cuvant3 = cuvant3;
        this.litera1 = litera1;
        this.litera2 = litera2;
        this.litera3 = litera3;
    }
}
