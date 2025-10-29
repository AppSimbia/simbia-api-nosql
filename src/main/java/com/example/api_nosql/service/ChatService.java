package com.example.api_nosql.service;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.config.redis.RedisMessagePublisher;
import com.example.api_nosql.persistence.entity.Chat;
import com.example.api_nosql.persistence.entity.Message;
import com.example.api_nosql.persistence.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final RedisMessagePublisher redisMessagePublisher;

    public ChatResponse createChat(List<Long> participants) {
        Chat chat = Chat.builder().participants(participants).build();
        return toResponse(chatRepository.save(chat));
    }

    public List<ChatResponse> findAllByEmployee(Long idEmployee) {
        return chatRepository.findAllByEmployeeId(idEmployee).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ChatResponse findById(String id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado: " + id));
        return toResponse(chat);
    }

    public ChatResponse addMessage(String chatId, MessageRequest messageRequest) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado: " + chatId));

        Message message = Message.builder()
                .idEmployee(messageRequest.getIdEmployee())
                .message(messageRequest.getMessage())
                .createdAt(Instant.now())
                .build();

        chat.getMessages().add(message);
        chatRepository.save(chat);

        redisMessagePublisher.publish("chat." + chatId, message);

        return toResponse(chat);
    }

    public void deleteChat(String id) {
        chatRepository.deleteById(id);
    }

    private ChatResponse toResponse(Chat chat) {
        return ChatResponse.builder()
                .id(chat.getId())
                .participants(chat.getParticipants())
                .messages(chat.getMessages())
                .build();
    }
}