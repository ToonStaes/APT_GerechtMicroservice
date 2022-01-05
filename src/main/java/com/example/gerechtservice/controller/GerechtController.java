package com.example.gerechtservice.controller;

import com.example.gerechtservice.model.Gerecht;
import com.example.gerechtservice.repository.GerechtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class GerechtController {
    @Autowired
    private GerechtRepository gerechtRepository;

    @GetMapping("/gerechten/{gerechtnummer}")
    public Gerecht getGerechtenByGerechtnummer(@PathVariable String gerechtnummer){
        return gerechtRepository.findGerechtByGerechtNummer(gerechtnummer);
    }

    @GetMapping("/gerechten")
    public List<Gerecht> getGerechten() {
        return gerechtRepository.findAll();
    }

    @PostConstruct
    public void fillDB(){
        if(gerechtRepository.count()==0){
            Gerecht margherita = new Gerecht("Pizza Margherita", 9.50);
            margherita.setGerechtNummer("20220103PM");

            Gerecht hawaï = new Gerecht("Pizza Hawaï", 99.99);
            hawaï.setGerechtNummer("20200103PH");

            Gerecht salami = new Gerecht("Pizza Salami", 11.00);
            salami.setGerechtNummer("20200103PS");

            Gerecht stagione = new Gerecht("Pizza Quattro Stagione", 12.00);
            stagione.setGerechtNummer("20220103PQS");

            Gerecht formaggi = new Gerecht("Pizza Quattro Formaggi", 12.00);
            formaggi.setGerechtNummer("20200103PQF");

            Gerecht pepperoni = new Gerecht("Pizza Pepperoni", 11.00);
            pepperoni.setGerechtNummer("20200103PP");

            gerechtRepository.save(margherita);
            gerechtRepository.save(hawaï);
            gerechtRepository.save(salami);
            gerechtRepository.save(stagione);
            gerechtRepository.save(formaggi);
            gerechtRepository.save(pepperoni);
        }
    }
}
