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
    private Long idEmployee;
    private String message;
    @Builder.Default
    private Instant createdAt = Instant.now();
    @Builder.Default
    private boolean read = false;
    @Builder.Default
    private boolean isSpecialMessage = false;
}

