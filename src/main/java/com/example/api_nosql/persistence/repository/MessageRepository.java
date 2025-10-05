package com.example.api_nosql.persistence.repository;

import com.example.api_nosql.persistence.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByChatIdOrderByCreatedAtDesc(String chatId);

    long countByChatIdAndIdFuncionarioNotAndReadFalse(String chatId, String idFuncionario);
}
