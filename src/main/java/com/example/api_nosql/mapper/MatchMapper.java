package com.example.api_nosql.mapper;

import com.example.api_nosql.api.match.input.MatchRequest;
import com.example.api_nosql.api.match.output.MatchResponse;
import com.example.api_nosql.persistence.entity.Match;

public class MatchMapper {

    public static MatchResponse toResponse(final Match match){
        return MatchResponse.builder()
                .id(match.getId().toString())
                .idPost(match.getIdPost())
                .idEmployeePurchaser(match.getIdEmployeePurchaser())
                .idEmployeeSeller(match.getIdEmployeeSeller() != null ? match.getIdEmployeeSeller() : null)
                .idIndustryPurchaser(match.getIdIndustryPurchaser())
                .idIndustrySeller(match.getIdIndustrySeller() != null ? match.getIdIndustrySeller() : null)
                .idChat(match.getIdChat() != null ? match.getIdChat() : null)
                .solicitationText(match.getSolicitationText())
                .proposedValue(match.getProposedValue() != null ? match.getProposedValue() : null)
                .quantity(match.getQuantity() != null ? match.getQuantity() : null)
                .measureUnit(match.getMeasureUnit() != null ? match.getMeasureUnit() : null)
                .status(match.getStatus())
                .build();
    }

    public static Match toEntity(final MatchRequest request){
        return Match.builder()
                .idPost(request.getIdPost())
                .idEmployeePurchaser(request.getIdEmployeePurchaser())
                .idEmployeeSeller(request.getIdEmployeeSeller() != null ? request.getIdEmployeeSeller() : null)
                .idIndustryPurchaser(request.getIdIndustryPurchaser())
                .idIndustrySeller(request.getIdIndustrySeller() != null ? request.getIdIndustrySeller() : null)
                .solicitationText(request.getSolicitationText())
                .proposedValue(request.getProposedValue() != null ? request.getProposedValue() : null)
                .quantity(request.getQuantity() != null ? request.getQuantity() : null)
                .measureUnit(request.getMeasureUnit() != null ? request.getMeasureUnit() : null)
                .build();
    }

}
