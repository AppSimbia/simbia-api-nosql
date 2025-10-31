package com.example.api_nosql.api.challenges;

import com.example.api_nosql.api.challenges.input.DesafioRequest;
import com.example.api_nosql.api.challenges.input.SolucaoRequest;
import com.example.api_nosql.api.challenges.output.DesafioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/desafios")
public interface DesafioApi {

    @Operation(summary = "Create a new challenge")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Challenge successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    @PostMapping
    ResponseEntity<DesafioResponse> create(@Valid @RequestBody DesafioRequest request);

    @Operation(summary = "Get challenge by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get challenge successfully returned")
    })
    @GetMapping("/{id}")
    ResponseEntity<DesafioResponse> findById(@PathVariable String id);

    @Operation(summary = "List all challenges")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of challenges successfully returned")
    })
    @GetMapping("/list")
    ResponseEntity<List<DesafioResponse>> findAll();

    @Operation(summary = "Create a new solution")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Solution successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    @PostMapping("/create/solucao")
    ResponseEntity<DesafioResponse> createSolution(
            @Valid @RequestBody SolucaoRequest request,
            @NotNull @RequestParam String id);

}
