package com.example.api_nosql.api.contract;

import com.example.api_nosql.api.dto.match.MatchRequest;
import com.example.api_nosql.api.dto.match.MatchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MatchApi {

    @Operation(summary = "Create a new match")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Match successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    @PostMapping
    ResponseEntity<MatchResponse> create(@Valid @RequestBody MatchRequest request);

    @Operation(summary = "List all matchs by seller ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of matchs successfully returned")
    })
    @GetMapping("/{id}")
    ResponseEntity<List<MatchResponse>> findBySellerId(@PathVariable Long id);

    @Operation(summary = "List all matchs available by seller ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of matchs successfully returned")
    })
    @GetMapping("/available/{id}")
    ResponseEntity<List<MatchResponse>> findBySellerIdAvailable(@PathVariable Long id);

    @Operation(summary = "Change Status Match")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Change Status match successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status change")
    })
    @GetMapping("/{id}/{status}")
    ResponseEntity<String> changeStatus(@PathVariable("id") String chaId, @PathVariable("status") String newStatus);

}
