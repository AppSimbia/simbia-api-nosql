package com.example.api_nosql.api.challenges.input;

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

    @Min(1)
    private Long idEmployeeSolution;
    @NotBlank(message = "O titulo da solucao não pode ser vazio.")
    private String title;
    @NotBlank(message = "O texto da solucao não pode ser vazio.")
    private String text;

}
