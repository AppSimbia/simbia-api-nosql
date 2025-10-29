package com.example.api_nosql.api.chat;

import com.example.api_nosql.api.chat.input.MessageRequest;
import com.example.api_nosql.api.chat.output.ChatResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chats")
public interface ChatApi {

    @Operation(summary = "List all chats by employee id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of chats successfully returned")
    })
    @GetMapping("/list/{id}")
    ResponseEntity<List<ChatResponse>> findAllByEmployee(@PathVariable("id") Long idEmployee);


    @Operation(summary = "Find a chat by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chat found successfully."),
            @ApiResponse(responseCode = "404", description = "Chat not found.")
    })
    @GetMapping("/{id}")
    ResponseEntity<ChatResponse> findById(@PathVariable String id);


    @Operation(summary = "Add a new message to a chat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message added successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid data provided."),
            @ApiResponse(responseCode = "404", description = "Chat not found.")
    })
    @PostMapping("/{id}/messages")
    ResponseEntity<ChatResponse> addMessage(
            @PathVariable String id,
            @Valid @RequestBody MessageRequest request);


    @Operation(summary = "Delete a chat by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Chat deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Chat not found.")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteChat(@PathVariable String id);

}
