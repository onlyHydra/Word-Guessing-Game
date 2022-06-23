package dto;

import java.io.Serializable;

public class RundaDTO implements Serializable {

    //Elementele unei runde ;
        int linie,coloana;
        int x, y,comoara;
        int punctaj;

        String cuvant1,cuvant2,cuvant3;
        String cuvant_introdus;



    public RundaDTO(int linie, int coloana, int punctaj) {
        this.linie = linie;
        this.coloana = coloana;
        this.punctaj = punctaj;
    }

    public int getLinie() {
        return linie;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    int punctajCastig;




   public boolean verificaCastig(){
        punctajCastig = 0;
       if (cuvant_introdus.equals(cuvant1) ) {
           punctajCastig = cuvant1.length();
                return true;
       }
       if (cuvant_introdus.equals(cuvant2) ) {
           punctajCastig = cuvant1.length();
           return true;
       }
       if (cuvant_introdus.equals(cuvant3) ) {
           punctajCastig = cuvant1.length();
           return true;
       }

       return false;
   }

    public RundaDTO(String cuvant1, String cuvant2, String cuvant3, String cuvant_introdus) {
        this.cuvant1 = cuvant1;
        this.cuvant2 = cuvant2;
        this.cuvant3 = cuvant3;
        this.cuvant_introdus = cuvant_introdus;
    }

    public RundaDTO(int linie, int coloana, int x, int y, int punctaj, int punctajCastig) {
        this.linie = linie;
        this.coloana = coloana;
        this.x = x;
        this.y = y;
        this.punctaj = punctaj;
        this.punctajCastig = punctajCastig;
    }

    public RundaDTO(int coloana, int x, int y, int punctaj, int punctajCastig) {
        this.coloana = coloana;
        this.x = x;
        this.y = y;
        this.punctaj = punctaj;
        this.punctajCastig = punctajCastig;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCuvant_introdus() {
        return cuvant_introdus;
    }

    public void setCuvant_introdus(String cuvant_introdus) {
        this.cuvant_introdus = cuvant_introdus;
    }

    public int calculeazaPunctaj(){
       int punctaj = 0;
       int punctaj_interm;
       int maxim = 0;
       if(cuvant1.length() > 0){
            punctaj_interm=0;
           for(int index = 0 ; index < cuvant1.length() && index < cuvant_introdus.length(); index++){
               if(cuvant1.charAt(index) == cuvant_introdus.charAt(index))
                   punctaj_interm+=1;
           }
           if( maxim < punctaj_interm)
               maxim = punctaj_interm;
       }
        if(cuvant3.length() > 0){
            punctaj_interm=0;
            for(int index = 0 ; index < cuvant3.length()&& index < cuvant_introdus.length(); index++){
                if(cuvant3.charAt(index) == cuvant_introdus.charAt(index))
                    punctaj_interm+=1;
            }
            if( maxim < punctaj_interm)
                maxim = punctaj_interm;
        }
        if(cuvant3.length() > 0){
            punctaj_interm=0;
            for(int index = 0 ; index < cuvant3.length()&& index < cuvant_introdus.length(); index++){
                if(cuvant3.charAt(index) == cuvant_introdus.charAt(index))
                    punctaj_interm+=1;
            }
            if( maxim < punctaj_interm)
                maxim = punctaj_interm;
        }

        punctaj = maxim;

        if(punctajCastig != 0){
           punctaj = punctajCastig;
       }
       // formula punctaj



       return punctaj;
   }


    public int getPunctajCastig() {
        return punctajCastig;
    }

    public void setPunctajCastig(int punctajCastig) {
        this.punctajCastig = punctajCastig;
    }
}
