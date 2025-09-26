package com.example.api_nosql.service;

import com.example.api_nosql.api.match.input.MatchRequest;
import com.example.api_nosql.api.match.output.MatchResponse;
import com.example.api_nosql.exception.ExistingMatch;
import com.example.api_nosql.mapper.MatchMapper;
import com.example.api_nosql.persistence.enums.state.*;
import com.example.api_nosql.persistence.model.Match;
import com.example.api_nosql.persistence.enums.StatusMatch;
import com.example.api_nosql.persistence.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    private final Map<StatusMatch, MatchState> status = Map.of(
            StatusMatch.ANDAMENTO, new AndamentoState(),
            StatusMatch.CANCELADO, new CanceladoState(),
            StatusMatch.CONCLUIDO, new ConcluidoState(),
            StatusMatch.EM_APROVACAO, new EmAprovacaoState()
    );

    public List<MatchResponse> findBySellerIdAvailable(final Long sellerId){
        List<Match> list = matchRepository.findByIdSeller(sellerId);

        return list.stream()
                .filter(match -> match.getStatus() != StatusMatch.CANCELADO && match.getStatus() != StatusMatch.CONCLUIDO)
                .map(MatchService::fromMatch)
                .collect(Collectors.toList());
    }

    public List<MatchResponse> findBySellerId(final Long sellerId){
        List<Match> list = matchRepository.findByIdSeller(sellerId);

        return list.stream().map(MatchService::fromMatch).collect(Collectors.toList());
    }

    public void changeStatus(final String chatId, final StatusMatch statusChange){
        Match match = matchRepository.findByChatId(new ObjectId(chatId)).orElseThrow(() -> new RuntimeException("Match not found"));
        MatchState state = status.get(match.getStatus());
        state.changeStatusMatch(match, statusChange);
        matchRepository.save(match);
    }

    private void changeStatus(final String idMatch){
        Match match = matchRepository.findById(new ObjectId(idMatch)).orElseThrow(() -> new RuntimeException("Match not found"));
        MatchState state = status.get(match.getStatus());
        state.changeStatusMatch(match, StatusMatch.CANCELADO);
        matchRepository.save(match);
    }

    public MatchResponse save(final MatchRequest matchRequest){
        Match match = toMatch(matchRequest);
        return fromMatch(matchRepository.save(match));
    }

    public MatchResponse update(final MatchRequest matchRequest){
        Match match = matchRepository.findById(new ObjectId(matchRequest.getId())).orElseThrow(() -> new RuntimeException("Match not found"));
        if (matchRepository.existsMatch(
                matchRequest.getIdPurchaser(), matchRequest.getIdSeller(), new ObjectId(matchRequest.getIdPost()), StatusMatch.CANCELADO.getStatus())) {
            changeStatus(matchRequest.getId());
            throw new ExistingMatch("Match already exists");
        }
        match.setIdSeller(matchRequest.getIdSeller());
        match.setIdChat(new ObjectId());

        return fromMatch(matchRepository.save(match));
    }

    private static MatchResponse fromMatch(final Match match){
        return MatchMapper.toResponse(match);
    }

    private static Match toMatch(final MatchRequest request){
        return MatchMapper.toEntity(request);
    }
}
