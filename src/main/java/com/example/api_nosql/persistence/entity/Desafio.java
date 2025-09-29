package com.example.api_nosql.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("desafios")
public class Desafio {

    @Id
    private ObjectId id;
    private Long idFuncionarioPergunta;
    private String titulo;
    private String texto;
    @Builder.Default
    private List<Solucao> solucoes =  new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Solucao {

        private Long idFuncionarioResposta;
        private String titulo;
        private String texto;

    }

}
