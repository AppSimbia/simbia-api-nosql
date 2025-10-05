package com.example.api_nosql.persistence.repository;

import com.example.api_nosql.persistence.entity.Chat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

    List<Chat> findByParticipantsContains(String userId);

    @Query("{ 'participants': { '$all': ?#{[0]} } }")
    List<Chat> findByParticipantsContainingAll(String[] participantIds);
}