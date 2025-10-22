package com.example.api_nosql.api.match;

import com.example.api_nosql.api.match.input.MatchRequest;
import com.example.api_nosql.api.match.output.MatchResponse;
import com.example.api_nosql.validation.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MatchApi {

    @Operation(summary = "Create a new match")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Match successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    @PostMapping
    ResponseEntity<MatchResponse> create(@Validated({OnCreate.class, Default.class}) @RequestBody MatchRequest request);

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

    @Operation(summary = "Change match status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Change match status successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status change")
    })
    @PostMapping("/status/{id}")
    ResponseEntity<String> changeStatus(
            @PathVariable("id") String chaId,
            @RequestParam("action") @Nullable String action,
            @RequestBody @Nullable MatchRequest request);

    @Operation(summary = "Change match status to canceled")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Change match status successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status change")
    })
    @DeleteMapping("/status/{id}")
    ResponseEntity<String> changeStatus(
            @PathVariable("id") String chaId);
}
