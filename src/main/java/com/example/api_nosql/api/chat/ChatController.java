package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.ChatRequestDto;
import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController implements ChatApi {

    private final ChatService chatService;

    @Override
    public ResponseEntity<ChatResponse> create(@Valid @RequestBody ChatRequestDto request) {
        return ResponseEntity.ok(chatService.createChat(request));
    }

    @Override
    public ResponseEntity<List<ChatResponse>> findAll() {
        return ResponseEntity.ok(chatService.findAll());
    }

    @Override
    public ResponseEntity<ChatResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(chatService.findById(id));
    }

    @Override
    public ResponseEntity<ChatResponse> addMessage(
            @PathVariable String id,
            @Valid @RequestBody MessageRequest request) {

        return ResponseEntity.ok(chatService.addMessage(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }

}
