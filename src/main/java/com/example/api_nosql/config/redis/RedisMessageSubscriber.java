package com.example.api_nosql.config.redis;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RedisMessageSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void onMessage(String message, String channel) {
        try {
            MessageRequest dto = objectMapper.readValue(message, MessageRequest.class);
            messagingTemplate.convertAndSend("/topic/chat." + dto.getIdChat(), dto);
        } catch (Exception e) {
            throw new RuntimeException("Could not deserialize Redis message", e);
        }
    }
}