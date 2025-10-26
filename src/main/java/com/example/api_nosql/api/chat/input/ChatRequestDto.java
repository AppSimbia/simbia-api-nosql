package com.example.api_nosql.api.chat.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRequestDto {

    @NotBlank(message = "O id do match é obrigatório")
    private String idMatch;

    @NotBlank(message = "A lista de participantes é obrigatório")
    private List<Long> participants;
}
