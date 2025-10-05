package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.ChatRequestDto;
import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> createChat(@Valid @RequestBody ChatRequestDto request) {
        return ResponseEntity.ok(chatService.createChat(request));
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getAllChats() {
        return ResponseEntity.ok(chatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatResponse> getChat(@PathVariable String id) {
        return ResponseEntity.ok(chatService.findById(id));
    }

    @PostMapping("/{id}/messages")
    public ResponseEntity<ChatResponse> addMessage(
            @PathVariable String id,
            @Valid @RequestBody MessageRequest request) {

        return ResponseEntity.ok(chatService.addMessage(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}
