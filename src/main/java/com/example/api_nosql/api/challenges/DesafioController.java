package com.example.api_nosql.api.challenges;

import com.example.api_nosql.api.challenges.input.DesafioRequest;
import com.example.api_nosql.api.challenges.input.SolucaoRequest;
import com.example.api_nosql.api.challenges.output.DesafioResponse;
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
        return ResponseEntity.ok(desafioService.create(request));
    }

    @Override
    public ResponseEntity<DesafioResponse> findById(String id) {
        DesafioResponse desafio = desafioService.findById(id);

        return desafio == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(desafio);
    }

    @Override
    public ResponseEntity<List<DesafioResponse>> findAll() {
        return ResponseEntity.ok(desafioService.findAll());
    }

    @Override
    public ResponseEntity<DesafioResponse> createSolution(SolucaoRequest request, String idDesafio) {
        return ResponseEntity.ok(desafioService.createSolucao(request,idDesafio));
    }
}
