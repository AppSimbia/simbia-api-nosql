package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.persistence.entity.Message;
import com.example.api_nosql.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
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
    public ResponseEntity<Message> addMessage(
            String id,
            @Valid @RequestBody MessageRequest request) {

        Message response = chatService.addMessage(id, request);
        template.convertAndSend("/topic/chat." + id, response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Boolean> readMessage(String id, Long idEmployee, String createAt) {
        return ResponseEntity.ok(chatService.readMessage(id, idEmployee, Instant.parse(createAt)));
    }

    @Override
    public ResponseEntity<Void> deleteChat(String id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }

}
