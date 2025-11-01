package com.example.api_nosql.api.chat.output;

import com.example.api_nosql.persistence.entity.Message;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse implements Comparable<ChatResponse>{
    private String id;
    private List<String> participants;
    private List<Message> messages;


    @Override
    public int compareTo(ChatResponse o) {
        long newMessages1 = this.messages.stream().filter(message -> !message.isRead()).count();
        long newMessages2 = o.messages.stream().filter(message -> !message.isRead()).count();

        return newMessages1 < newMessages2 ? 1 : 0;
    }
}
