package com.example.api_nosql.api.dto.match;

import com.example.api_nosql.model.enums.StatusMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MatchResponse {

    private String id;
    private String idPost;
    private Long idPurchaser;
    private Long idSeller;
    private String idChat;
    private StatusMatch status;

}
