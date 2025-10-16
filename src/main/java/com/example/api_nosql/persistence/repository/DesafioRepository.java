package com.example.api_nosql.persistence.repository;

import com.example.api_nosql.persistence.entity.Desafio;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesafioRepository extends MongoRepository<Desafio, ObjectId> {

    List<Desafio> findByIdEmployeeQuestion(Long id);

}
