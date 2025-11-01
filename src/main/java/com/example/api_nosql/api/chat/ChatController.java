package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController implements ChatApi {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @Override
    public ResponseEntity<List<ChatResponse>> findAllByEmployee(String idEmployee) {
        return ResponseEntity.ok(chatService.findAllByEmployee(idEmployee));
    }

    @Override
    public ResponseEntity<ChatResponse> findById(String id) {
        return ResponseEntity.ok(chatService.findById(id));
    }

    @Override
    public ResponseEntity<ChatResponse> addMessage(
            String id,
            @Valid @RequestBody MessageRequest request) {

        ChatResponse response = chatService.addMessage(id, request);
        template.convertAndSend("/topic/chat." + id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteChat(String id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }

}
