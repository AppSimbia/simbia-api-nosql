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
        return ResponseEntity.ok(desafioService.create(request));
    }

    @Override
    public ResponseEntity<List<DesafioResponse>> findByEmployeeId(Long id) {
        List<DesafioResponse> list = desafioService.findByEmployeeId(id);

        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<DesafioResponse> createSolution(SolucaoRequest request, String idDesafio) {
        return ResponseEntity.ok(desafioService.createSolucao(request,idDesafio));
    }
}
