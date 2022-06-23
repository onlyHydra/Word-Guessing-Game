package service;

import domain.Joc;
import domain.Jucator;
import domain.Runda;

public interface Service {
    public Jucator findOneJucatorByCredentials(String alias);
    void login(Jucator jucator, Observer client);

    Joc getJoc();

    public Runda addRezultat(Runda runda, Joc joc);


}
