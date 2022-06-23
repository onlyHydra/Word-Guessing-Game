package dto;

import java.io.Serializable;

public class RezultatDTO implements Serializable {

        int punctaj;
        int coloana;
        int linie;
        int id_jucator;
        int id_joc;


        String cuvant;

        boolean castigat ;

    public String getCuvant() {
        return cuvant;
    }

    public void setCuvant(String cuvant) {
        this.cuvant = cuvant;
    }

    public boolean isCastigat() {
        return castigat;
    }

    public void setCastigat(boolean castigat) {
        this.castigat = castigat;
    }

    public RezultatDTO(int punctaj, boolean castigat, String cuvant) {
        this.punctaj = punctaj;
        this.cuvant = cuvant;
        this.castigat = castigat;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public int getLinie() {
        return linie;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public int getId_jucator() {
        return id_jucator;
    }

    public void setId_jucator(int id_jucator) {
        this.id_jucator = id_jucator;
    }

    public int getId_joc() {
        return id_joc;
    }

    public void setId_joc(int id_joc) {
        this.id_joc = id_joc;
    }

    public RezultatDTO(int punctaj, int coloana, int linie, int id_jucator) {
        this.punctaj = punctaj;
        this.coloana = coloana;
        this.linie = linie;
        this.id_jucator = id_jucator;

    }

    public RezultatDTO() {
    }

    public RezultatDTO(int punctaj, int coloana, int linie) {
        this.punctaj = punctaj;
        this.coloana = coloana;
        this.linie = linie;
    }
}
