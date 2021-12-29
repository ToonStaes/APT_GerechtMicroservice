package com.example.gerechtservice.repository;

import com.example.gerechtservice.model.Gerecht;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerechtRepository extends MongoRepository<Gerecht, String> {
    Gerecht findGerechtByGerechtNummer(String gerechtnummer);
}
