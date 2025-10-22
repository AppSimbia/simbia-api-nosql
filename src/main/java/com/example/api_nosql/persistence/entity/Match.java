package com.example.api_nosql.persistence.entity;

import com.example.api_nosql.persistence.enums.MatchState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "matchs")
public class Match {

    @Id
    @Builder.Default
    private ObjectId id =  new ObjectId();
    private Long idPost;
    private Long idEmployeePurchaser;
    private Long idEmployeeSeller;
    private String idIndustryPurchaser;
    private String idIndustrySeller;
    private Double proposedValue;
    private Long quantity;
    private Long measureUnit;
    private String idChat;
    @Builder.Default
    private MatchState status = MatchState.AGUARDANDO_APROVACAO_CRIACAO;

}
