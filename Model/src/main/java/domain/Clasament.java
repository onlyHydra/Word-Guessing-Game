package domain;

public class Clasament extends Entity<Integer> {
    int id_joc,id_jucator;
    String cuvinte_ghicite;
    String numarul_de_cuvinte_ghicite;
    int punctaj;

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public Clasament(int id_joc, int id_jucator, String cuvinte_ghicite, String numarul_de_cuvinte_ghicite) {
        this.id_joc = id_joc;
        this.id_jucator = id_jucator;
        this.cuvinte_ghicite = cuvinte_ghicite;
        this.numarul_de_cuvinte_ghicite = numarul_de_cuvinte_ghicite;
    }

    public int getId_joc() {
        return id_joc;
    }

    public void setId_joc(int id_joc) {
        this.id_joc = id_joc;
    }

    public int getId_jucator() {
        return id_jucator;
    }

    public void setId_jucator(int id_jucator) {
        this.id_jucator = id_jucator;
    }

    public String getCuvinte_ghicite() {
        return cuvinte_ghicite;
    }

    public void setCuvinte_ghicite(String cuvinte_ghicite) {
        this.cuvinte_ghicite = cuvinte_ghicite;
    }

    public String getNumarul_de_cuvinte_ghicite() {
        return numarul_de_cuvinte_ghicite;
    }

    public void setNumarul_de_cuvinte_ghicite(String numarul_de_cuvinte_ghicite) {
        this.numarul_de_cuvinte_ghicite = numarul_de_cuvinte_ghicite;
    }
}
