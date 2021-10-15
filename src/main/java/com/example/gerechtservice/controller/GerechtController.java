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

    @GetMapping("/gerechten/{naam}")
    public Gerecht getGerechtenByNaam(@PathVariable String naam){
        return gerechtRepository.findGerechtByNaam(naam);
    }

    @GetMapping("/gerechten")
    public List<Gerecht> getGerechten() {
        return gerechtRepository.findAll();
    }

    @PostConstruct
    public void fillDB(){
        if(gerechtRepository.count()==0){
            gerechtRepository.save(new Gerecht("Pizza Margherita", 9.50));
            gerechtRepository.save(new Gerecht("Pizza Hawaï", 99.99));
            gerechtRepository.save(new Gerecht("Pizza Salami", 11.00));
        }
    }
}
