package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.ChatRequestDto;
import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/chats")
public interface ChatApi {

    @Operation(summary = "Create a new chat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chat created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided.")
    })
    @PostMapping
    ResponseEntity<ChatResponse> create(@Valid @RequestBody ChatRequestDto request);


    @Operation(summary = "List all chats by employee ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of chats successfully returned"),
            @ApiResponse(responseCode = "404", description = "List of chats not found.")
    })
    @GetMapping("/employee/{id}")
    ResponseEntity<List<ChatResponse>> findAllByEmployeeId(@PathVariable("id") Long id);


    @Operation(summary = "Find a chat by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chat found successfully."),
            @ApiResponse(responseCode = "404", description = "Chat not found.")
    })
    @GetMapping("/{id}")
    ResponseEntity<ChatResponse> findById(@PathVariable String id);


    @Operation(summary = "Send a new message to a chat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message added successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided."),
            @ApiResponse(responseCode = "404", description = "Chat not found.")
    })
    @PostMapping("/{id}/messages")
    ResponseEntity<ChatResponse> sendMessage(
            @PathVariable String id,
            @Valid @RequestBody MessageRequest request);

}
