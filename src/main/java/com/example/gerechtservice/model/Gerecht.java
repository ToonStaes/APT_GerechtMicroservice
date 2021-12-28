package com.example.gerechtservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gerechten")
public class Gerecht {
    @Id
    private String id;
    @Indexed(unique=true)
    private String naam;
    private double prijs;

    public Gerecht(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public String getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }


    public double getPrijs() {
        return prijs;
    }

}
