package com.example.api_nosql.api.chat.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {

//    @NotBlank(message = "A mensagem não pode ser vazia")
    private String message;

//    @NotNull(message = "O id do funcionário é obrigatório")
    private Long idEmployee;

    private String idChat;

}
