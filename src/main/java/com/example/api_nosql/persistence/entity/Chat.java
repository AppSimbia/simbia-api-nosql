package com.example.api_nosql.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chats")
public class Chat {
    @Id
    private String id;

    private String matchId;

    @Builder.Default
    private List<String> participants = new ArrayList<>();

    @Builder.Default
    private List<Message> mensagens = new ArrayList<>();
}
