package com.example.api_nosql.api.chat.output;

import com.example.api_nosql.persistence.entity.Message;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {
    private String id;
    private String matchId;
    private List<Message> mensagens;
}
