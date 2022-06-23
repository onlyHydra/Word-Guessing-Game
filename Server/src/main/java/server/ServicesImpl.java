package server;

import domain.Joc;
import domain.Jucator;
import domain.Runda;
import dto.RezultatDTO;
import services.repository.HibernateRepository;
import services.repository.JucatorRepository;
import service.Observer;
import service.Service;
import service.TalentShowException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServicesImpl implements Service {

        private JucatorRepository jucatorRepository;
        private HibernateRepository jocRepository;
    private Map<Integer, Observer> loggedClients;

    public ServicesImpl(JucatorRepository jucatorRepository, HibernateRepository jocRepository) {
        this.jucatorRepository = jucatorRepository;
        this.jocRepository = jocRepository;
        loggedClients=new ConcurrentHashMap<>();;
    }


    @Override
    public void login(Jucator user, Observer client){
        Jucator userR=findOneJucatorByCredentials(user.getAlias());
        if (userR!=null){
            if(loggedClients.size()!=0 && loggedClients.get(user.getId())!=null)
                throw new TalentShowException("User already logged in.");
            loggedClients.put(user.getId(), client);
        }else
            throw new TalentShowException("Authentication failed.");
    }

    @Override
    public Runda addRezultat(Runda runda, Joc joc) {
        return new Runda();
    }


    @Override
    public Jucator findOneJucatorByCredentials(String alias) {
        for (Jucator jurat : jucatorRepository.findAll()) {
            if (jurat.getAlias().compareTo(alias) == 0 )
                return jurat;
        }
        return null;
    }

    @Override
    public Joc getJoc(){
        int random =1 + (int)(Math.random() * ((3 - 1) + 1));
        return jocRepository.findOne(random);
    }
}
