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
@Document("challenges")
public class Desafio {

    @Id
    private ObjectId id;
    private Long idEmployeeQuestion;
    private String title;
    private String text;
    @Builder.Default
    private List<Solucao> solutions =  new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Solucao {

        private Long idEmployeeSolution;
        private String title;
        private String text;

    }

}
