package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController implements ChatApi{

    private final ChatService service;

    @Override
    public ResponseEntity<List<ChatResponse>> findByPurchaserId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ChatResponse>> sendMessage(MessageRequest request) {
        return null;
    }
}
