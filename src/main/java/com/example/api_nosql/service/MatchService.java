package com.example.api_nosql.service;

import com.example.api_nosql.api.chat.input.ChatRequestDto;
import com.example.api_nosql.api.chat.output.ChatResponse;
import com.example.api_nosql.api.match.input.MatchRequest;
import com.example.api_nosql.api.match.output.MatchResponse;
import com.example.api_nosql.exception.ExistingMatch;
import com.example.api_nosql.mapper.MatchMapper;
import com.example.api_nosql.persistence.entity.Match;
import com.example.api_nosql.persistence.enums.MatchContext;
import com.example.api_nosql.persistence.enums.MatchState;
import com.example.api_nosql.persistence.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final ChatService chatService;

    public List<MatchResponse> findBySellerIdAvailable(final Long sellerId){
        List<Match> list = matchRepository.findByIdSeller(sellerId);

        return list.stream()
                .filter(match -> match.getStatus() != MatchState.CANCELADO && match.getStatus() != MatchState.CONCLUIDO)
                .map(MatchService::fromMatch)
                .collect(Collectors.toList());
    }

    public List<MatchResponse> findBySellerId(final Long sellerId){
        List<Match> list = matchRepository.findByIdSeller(sellerId);

        return list.stream().map(MatchService::fromMatch).collect(Collectors.toList());
    }

    public String changeStatus(final String id, MatchRequest request) {
        Match match = matchRepository.findById(new ObjectId(id)).orElseThrow(() -> new RuntimeException("Match not found"));
        MatchContext matchContext = new MatchContext(match.getStatus());
        matchContext.next();

        if (matchContext.getState() == MatchState.ANDAMENTO) {
            ChatResponse chat = chatService.createChat(new ChatRequestDto(match.getId().toString()));
            match.setIdChat(chat.getId());
            match.setIdEmployeeSeller(request.getIdEmployeeSeller());
            match.setIdIndustrySeller(request.getIdIndustrySeller());
        }else if (matchContext.getState() == MatchState.AGUARDANDO_PAGAMENTO) {
            match.setProposedValue(request.getProposedValue());
            match.setQuantity(request.getQuantity());
            match.setMeasureUnit(request.getMeasureUnit());
        }

        match.setStatus(matchContext.getState());
        matchRepository.save(match);
        return matchContext.getState().getStatus();
    }

    public void changeStatus(final String id) {
        Match match = matchRepository.findById(new ObjectId(id)).orElseThrow(() -> new RuntimeException("Match not found"));
        match.setStatus(MatchState.CANCELADO);
        matchRepository.save(match);
    }

    public MatchResponse save(final MatchRequest matchRequest){
        Match match = toMatch(matchRequest);

        if (matchRepository.existsMatch(
                matchRequest.getIdIndustryPurchaser(), matchRequest.getIdPost(), MatchState.CANCELADO.getStatus())) {
            throw new ExistingMatch("Match already exists");
        }

        return fromMatch(matchRepository.save(match));
    }

    private static MatchResponse fromMatch(final Match match){
        return MatchMapper.toResponse(match);
    }

    private static Match toMatch(final MatchRequest request){
        return MatchMapper.toEntity(request);
    }
}
