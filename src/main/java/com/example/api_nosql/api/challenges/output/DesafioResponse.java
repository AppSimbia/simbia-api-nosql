package com.example.api_nosql.api.challenges.output;

import com.example.api_nosql.persistence.entity.Desafio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesafioResponse {

    private String id;
    private Long idEmployeeQuestion;
    private String title;
    private String text;
    private List<Desafio.Solucao> solutions;

}
