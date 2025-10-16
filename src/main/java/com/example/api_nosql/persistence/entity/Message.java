package com.example.api_nosql.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String id;
    private String idChat;
    private String idEmployee;
    private String message;
    private Instant createdAt;
    private boolean read = false;
}

