package com.example.api_nosql.api.chat.output;

import com.example.api_nosql.persistence.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {

    private String id;
    private String matchId;
    private List<Message> messages;

}
