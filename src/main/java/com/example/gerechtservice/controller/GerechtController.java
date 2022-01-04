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
            margherita.setGerechtNummer("20220104PM");

            Gerecht hawaï = new Gerecht("Pizza Hawaï", 99.99);
            hawaï.setGerechtNummer("20220104PH");

            Gerecht salami = new Gerecht("Pizza Salami", 11.00);
            salami.setGerechtNummer("20220104PS");

            gerechtRepository.save(margherita);
            gerechtRepository.save(hawaï);
            gerechtRepository.save(salami);
        }
    }
}
