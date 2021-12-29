package com.example.gerechtservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "gerechten")
public class Gerecht {
    @Id
    private String _id;
    @Indexed(unique=true)
    private String gerechtNummer;
    private String naam;
    private double prijs;

    public String getGerechtNummer() {
        return gerechtNummer;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getNaam() {
        return naam;
    }

    public Gerecht(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.gerechtNummer = generateGerechtnummer(naam);
    }

    public String generateGerechtnummer(String gerechtNaam) {
        String initialen = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String datestring = formatter.format(date).toString();

        String[] splitted = gerechtNaam.split("\\s+");

        initialen += datestring;

        for (String s : splitted) {
            initialen += s.toUpperCase().charAt(0);
        }

        return initialen;
    }
}
