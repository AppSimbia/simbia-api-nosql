package com.example.api_nosql.persistence.repository;

import com.example.api_nosql.persistence.model.Match;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, ObjectId> {

    @Query("{ 'idSeller' :  ?0 }")
    List<Match> findByIdSeller(Long sellerId);

    @Query("{ 'idChat' :  ?0}")
    Optional<Match> findByChatId(ObjectId chatId);

    @Query("{ 'idPurchaser' : ?0, 'idSeller' : ?1, 'idPost' : ?2, 'status' :  { $ne :  ?3 }}")
    Boolean existsMatch(Long idPurchaser, Long idSeller, ObjectId idPost, String status);

}
