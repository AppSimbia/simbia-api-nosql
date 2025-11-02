package com.example.api_nosql.service;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
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

    public ChatResponse createChat(List<String> participants) {
        Chat chat = Chat.builder().participants(participants).build();
        return toResponse(chatRepository.save(chat));
    }

    public List<ChatResponse> findAllByEmployee(String idEmployee) {
        return chatRepository.findAllByEmployeeId(idEmployee).stream()
                .map(this::toResponse)
                .sorted()
                .collect(Collectors.toList());
    }

    public ChatResponse findById(String id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado: " + id));
        return toResponse(chat);
    }

    public Message addMessage(String chatId, MessageRequest messageRequest) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado: " + chatId));

        Message message = Message.builder()
                .idEmployee(messageRequest.getIdEmployee())
                .message(messageRequest.getMessage())
                .createdAt(Instant.now())
                .build();

        chat.getMessages().add(message);
        chatRepository.save(chat);
        return message;
    }

    public boolean readMessage(String id, Long idEmployee, Instant createdAt) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado: " + id));
        int count = 0;

        for (int i = chat.getMessages().size()-1; i >= 0; i--) {
            Message message = chat.getMessages().get(i);

            if (message.getIdEmployee().equals(idEmployee) && message.getCreatedAt().compareTo(createdAt) == 0) {
                message.setRead(true);
                count++;
                break;
            }
        }
        if (count>0) {
            chatRepository.save(chat);
            return true;
        }
        return false;
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