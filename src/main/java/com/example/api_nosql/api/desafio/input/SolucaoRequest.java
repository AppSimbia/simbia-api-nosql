package com.example.api_nosql.api.desafio.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolucaoRequest {

    @NotBlank(message = "O ID do Funcionário não pode ser vazio.")
    @Min(1)
    private Long idFuncionarioSolucao;
    @NotBlank(message = "O titulo da solucao não pode ser vazio.")
    private String titulo;
    @NotBlank(message = "O texto da solucao não pode ser vazio.")
    private String texto;

}
