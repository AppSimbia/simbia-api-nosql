package com.example.api_nosql.api.match;

import com.example.api_nosql.api.match.input.MatchRequest;
import com.example.api_nosql.api.match.output.MatchResponse;
import com.example.api_nosql.service.MatchService;
import com.example.api_nosql.validation.OnPayment;
import com.example.api_nosql.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Validator;
import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController implements MatchApi {

    private final MatchService matchService;
    private final Validator validator;

    @Override
    public ResponseEntity<MatchResponse> create(MatchRequest request) {
        return ResponseEntity.ok(matchService.save(request));
    }

    @Override
    public ResponseEntity<List<MatchResponse>> findBySellerId(Long id) {
        List<MatchResponse> list = matchService.findBySellerId(id);

        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<MatchResponse>> findBySellerIdAvailable(Long id) {
        List<MatchResponse> list = matchService.findBySellerIdAvailable(id);

        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<String> changeStatus(String chaId, String action, MatchRequest request) {
        if ("payment".equals(action)) {
            validator.validate(request, OnPayment.class);
        } else if ("update".equals(action)) {
            validator.validate(request, OnUpdate.class);
        }

        return ResponseEntity.ok("Status do Match foi alterado para: "+ matchService.changeStatus(chaId, request));
    }

    @Override
    public ResponseEntity<String> changeStatus(String chaId) {
        matchService.changeStatus(chaId);
        return ResponseEntity.ok("Status do Match foi alterado para: CANCELADO");
    }
}
