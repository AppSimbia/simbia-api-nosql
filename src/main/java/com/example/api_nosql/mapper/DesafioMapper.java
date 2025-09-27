package com.example.api_nosql.mapper;

import com.example.api_nosql.api.desafio.input.DesafioRequest;
import com.example.api_nosql.api.desafio.input.SolucaoRequest;
import com.example.api_nosql.api.desafio.output.DesafioResponse;
import com.example.api_nosql.persistence.model.Desafio;

public class DesafioMapper {

    public static DesafioResponse toResponse(final Desafio desafio){
        return DesafioResponse.builder()
                .idFuncionarioPergunta(desafio.getIdFuncionarioPergunta())
                .titulo(desafio.getTitulo())
                .texto(desafio.getTexto())
                .solucoes(desafio.getSolucoes())
                .build();
    }

    public static Desafio toEntity(final DesafioRequest request){
        return Desafio.builder()
                .idFuncionarioPergunta(request.getIdFuncionarioPergunta())
                .titulo(request.getTitulo())
                .texto(request.getTexto())
                .build();
    }

    public static Desafio.Solucao toSolucao(final SolucaoRequest solucao){
        return Desafio.Solucao.builder()
                .idFuncionarioResposta(solucao.getIdFuncionarioSolucao())
                .titulo(solucao.getTitulo())
                .texto(solucao.getTexto())
                .build();
    }

}
