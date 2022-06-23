package domain;

public class Runda extends Entity<Integer>{

    private String cuvant_introdus;

    private int punctaj;

    private boolean castigat;


    public Runda() {
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public boolean isCastigat() {
        return castigat;
    }

    public void setCastigat(boolean castigat) {
        this.castigat = castigat;
    }

    public Runda(String cuvant_introdus, int punctaj, boolean castigat) {
        this.cuvant_introdus = cuvant_introdus;
        this.punctaj = punctaj;
        this.castigat = castigat;
    }

    public Runda(String cuvant_introdus) {
        this.cuvant_introdus = cuvant_introdus;
    }

    public String getCuvant_introdus() {
        return cuvant_introdus;
    }

    public void setCuvant_introdus(String cuvant_introdus) {
        this.cuvant_introdus = cuvant_introdus;
    }
}
