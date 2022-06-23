package domain;

public class Jucator extends Entity<Integer>{

    private String alias;

    public Jucator(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Jucator{" +
                "alias='" + alias + '\'' +
                '}';
    }
}
