package com.example.api_nosql.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String id;
    private String chatId;
    private String idFuncionario;
    private String mensagem;
    private Instant createdAt;
    private boolean read = false;
}

