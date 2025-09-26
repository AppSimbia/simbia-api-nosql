package com.example.api_nosql.mapper;

import com.example.api_nosql.api.match.dto.MatchRequest;
import com.example.api_nosql.api.match.dto.MatchResponse;
import com.example.api_nosql.persistence.model.Match;
import org.bson.types.ObjectId;

public class MatchMapper {

    public static MatchResponse toMatchResponse(final Match match){
        return MatchResponse.builder()
                .id(match.getId().toString())
                .idPost(match.getIdPost().toString())
                .idPurchaser(match.getIdPurchaser())
                .idSeller(match.getIdSeller() != null ? match.getIdSeller() : null)
                .idChat(match.getIdChat() != null ? match.getIdChat().toString() : null)
                .status(match.getStatus())
                .build();
    }

    public static Match toMatch(final MatchRequest matchRequest){
        return Match.builder()
                .idPost(new ObjectId(matchRequest.getIdPost()))
                .idPurchaser(matchRequest.getIdPurchaser())
                .idSeller(matchRequest.getIdSeller() != null ? matchRequest.getIdSeller() : null)
                .build();
    }

}
