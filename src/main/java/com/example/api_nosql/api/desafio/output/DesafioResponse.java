package com.example.api_nosql.api.desafio.output;

import com.example.api_nosql.persistence.model.Desafio;
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

    private Long idFuncionarioPergunta;
    private String titulo;
    private String texto;
    private List<Desafio.Solucao> solucoes;

}
