package com.example.api_nosql.api.desafio;

import com.example.api_nosql.api.desafio.input.DesafioRequest;
import com.example.api_nosql.api.desafio.input.SolucaoRequest;
import com.example.api_nosql.api.desafio.output.DesafioResponse;
import com.example.api_nosql.service.DesafioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DesafioController implements DesafioApi {

    private final DesafioService desafioService;

    @Override
    public ResponseEntity<DesafioResponse> create(DesafioRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<DesafioResponse>> findByEmployeeId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DesafioResponse> createSolution(SolucaoRequest request) {
        return null;
    }
}
