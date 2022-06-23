package services.repository;

import domain.Joc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateRepository implements JocRepository {
    @Override
    public Joc findOne(Integer integer) throws IllegalArgumentException {
        initialize();
        try(Session session= sessionFactory.openSession()){
            session.beginTransaction();
            Query query=session.createQuery("from  Joc where id_joc=:id");
            query.setParameter("id", integer);
            Joc participant= (Joc) query.uniqueResult();
            session.getTransaction().commit();
            close();
            return  participant;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            close();
        }
        return null;
    }

    @Override
    public Iterable<Joc> findAll() {
        initialize();
        try(Session session= sessionFactory.openSession()){
            session.beginTransaction();
            List<Joc> participanti =
                    session.createQuery("from Joc", Joc.class)
                            .list();
            session.getTransaction().commit();
            close();
            return  participanti;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            close();
        }
        return null;
    }

    @Override
    public void save(Joc entity) {
        initialize();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Joc joc = new Joc(entity.getCuvant1(),entity.getCuvant2(), entity.getCuvant3(), entity.getLitera1(), entity.getLitera2(), entity.getLitera3());
                session.save(joc);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }



}
