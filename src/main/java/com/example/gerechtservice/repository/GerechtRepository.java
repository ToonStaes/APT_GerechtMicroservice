package com.example.gerechtservice.repository;

import com.example.gerechtservice.model.Gerecht;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GerechtRepository extends MongoRepository<Gerecht, String> {
    Gerecht findGerechtByNaam(String naam);
}
