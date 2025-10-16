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
public class DesafioRequest {

    @Min(1)
    private Long idEmployeeQuestion;
    @NotBlank(message = "O titulo da pergunta não pode ser vazio.")
    private String title;
    private String text;

}
