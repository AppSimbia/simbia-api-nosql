package com.example.api_nosql.persistence.repository;

import com.example.api_nosql.persistence.entity.Chat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, ObjectId> {
}