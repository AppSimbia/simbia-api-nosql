package com.example.api_nosql.mapper;

import com.example.api_nosql.api.match.input.MatchRequest;
import com.example.api_nosql.api.match.output.MatchResponse;
import com.example.api_nosql.persistence.model.Match;
import org.bson.types.ObjectId;

public class MatchMapper {

    public static MatchResponse toResponse(final Match match){
        return MatchResponse.builder()
                .id(match.getId().toString())
                .idPost(match.getIdPost().toString())
                .idPurchaser(match.getIdPurchaser())
                .idSeller(match.getIdSeller() != null ? match.getIdSeller() : null)
                .idChat(match.getIdChat() != null ? match.getIdChat().toString() : null)
                .status(match.getStatus())
                .build();
    }

    public static Match toEntity(final MatchRequest matchRequest){
        return Match.builder()
                .idPost(new ObjectId(matchRequest.getIdPost()))
                .idPurchaser(matchRequest.getIdPurchaser())
                .idSeller(matchRequest.getIdSeller() != null ? matchRequest.getIdSeller() : null)
                .build();
    }

}
