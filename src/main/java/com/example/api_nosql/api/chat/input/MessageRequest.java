package com.example.api_nosql.api.chat.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {

    @NotBlank(message = "A mensagem não pode ser vazia")
    private String message;

    @NotBlank(message = "O id do funcionário é obrigatório")
    private String idEmployee;

    @NotBlank(message = "O id do chat é obrigatório")
    private String idChat;

    @Builder.Default
    @NotNull(message = "A data de criação não deve ser nula")
    private Instant createdAt = Instant.now();
}
