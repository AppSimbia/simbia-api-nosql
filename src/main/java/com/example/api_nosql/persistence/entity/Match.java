package com.example.api_nosql.persistence.entity;

import com.example.api_nosql.persistence.enums.StatusMatch;
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
    private ObjectId idPost;
    private Long idPurchaser;
    private Long idSeller;
    private ObjectId idChat;
    @Builder.Default
    private StatusMatch status = StatusMatch.ANDAMENTO;

}
