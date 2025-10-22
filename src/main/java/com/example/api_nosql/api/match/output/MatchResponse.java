package com.example.api_nosql.api.match.output;

import com.example.api_nosql.persistence.enums.MatchState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MatchResponse {

    private String id;
    private Long idPost;
    private Long idEmployeePurchaser;
    private Long idEmployeeSeller;
    private String idIndustryPurchaser;
    private String idIndustrySeller;
    private String idChat;
    private Double proposedValue;
    private Long quantity;
    private Long measureUnit;
    private MatchState status;

}
