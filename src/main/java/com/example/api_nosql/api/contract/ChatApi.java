package com.example.api_nosql.api.contract;

import com.example.api_nosql.api.dto.chat.ChatResponse;
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

@RequestMapping("/chat")
public interface ChatApi {

    @Operation(summary = "List all chats by purchaser ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of chats successfully returned")
    })
    @GetMapping("/{id}")
    ResponseEntity<List<ChatResponse>> findByPurchaserId(@PathVariable Long id);

    @Operation(summary = "Send a new message")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message sent successfully."),
            @ApiResponse(responseCode = "400", description = "Invalidated data provided.")
    })
    @PostMapping("/create")
    ResponseEntity<List<ChatResponse>> create(@Valid @RequestBody );
}
