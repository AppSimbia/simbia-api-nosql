package com.example.api_nosql.api;

import com.example.api_nosql.api.contract.MatchApi;
import com.example.api_nosql.api.dto.match.MatchRequest;
import com.example.api_nosql.api.dto.match.MatchResponse;
import com.example.api_nosql.model.enums.StatusMatch;
import com.example.api_nosql.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController implements MatchApi {

    private final MatchService matchService;

    @Override
    public ResponseEntity<MatchResponse> create(MatchRequest request) {
        return ResponseEntity.ok(matchService.save(request));
    }

    @Override
    public ResponseEntity<MatchResponse> update(MatchRequest request) {
        return ResponseEntity.ok(matchService.update(request));
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
    public ResponseEntity<String> changeStatus(String chaId, String newStatus) {
        matchService.changeStatus(chaId, StatusMatch.valueOf(newStatus));
        return ResponseEntity.ok("Status do Match foi alterado.");
    }
}
