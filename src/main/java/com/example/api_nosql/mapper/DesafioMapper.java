package com.example.api_nosql.mapper;

import com.example.api_nosql.api.challenges.input.DesafioRequest;
import com.example.api_nosql.api.challenges.input.SolucaoRequest;
import com.example.api_nosql.api.challenges.output.DesafioResponse;
import com.example.api_nosql.persistence.entity.Desafio;

public class DesafioMapper {

    public static DesafioResponse toResponse(final Desafio desafio){
        return DesafioResponse.builder()
                .id(desafio.getId().toString())
                .idEmployeeQuestion(desafio.getIdEmployeeQuestion())
                .title(desafio.getTitle())
                .text(desafio.getText())
                .solutions(desafio.getSolutions())
                .build();
    }

    public static Desafio toEntity(final DesafioRequest request){
        return Desafio.builder()
                .idEmployeeQuestion(request.getIdEmployeeQuestion())
                .title(request.getTitle())
                .text(request.getText())
                .build();
    }

    public static Desafio.Solucao toSolucao(final SolucaoRequest solucao){
        return Desafio.Solucao.builder()
                .idEmployeeSolution(solucao.getIdEmployeeSolution())
                .title(solucao.getTitle())
                .text(solucao.getText())
                .build();
    }

}
