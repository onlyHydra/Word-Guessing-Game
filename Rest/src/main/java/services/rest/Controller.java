package services.rest;


import domain.Clasament;
import domain.Joc;
import domain.Jucator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.repository.ClasamentRepositoryJDBC;
import services.repository.HibernateRepository;
import services.repository.JocRepositoryJDBC;
import services.repository.JucatorRepository;


@CrossOrigin
@RestController
@RequestMapping("/examen")
public class Controller {

    @Autowired
    private JocRepositoryJDBC jocRepository;
   @Autowired
    private JucatorRepository jucatorRepository;
   @Autowired
   private ClasamentRepositoryJDBC clasamentRepositoryJDBC;


   @RequestMapping(value="/clasament/{alias}" ,method = RequestMethod.GET)
   public Iterable<Clasament> restul2(@PathVariable String alias){
       int id = 0;
       for(Jucator jucator: jucatorRepository.findAll()){
           if (jucator.getAlias().equals(alias))

               id = jucator.getId();
       }
       return clasamentRepositoryJDBC.findAll(id);
   }


   @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Joc joc){
        jocRepository.save(joc);
        return new  ResponseEntity<Joc>(joc,HttpStatus.OK);
    }

    @RequestMapping(method =RequestMethod.GET)
    public Iterable<Joc> getAll(){return jocRepository.findAll();}




}

