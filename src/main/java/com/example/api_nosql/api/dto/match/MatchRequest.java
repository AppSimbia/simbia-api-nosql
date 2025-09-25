package com.example.api_nosql.api.dto.match;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@Validated
public class MatchRequest {
    
    @NotNull(message = "O ID do Post não deve ser NULL")
    private String idPost;
    @NotNull(message = "O ID do Comprador não deve ser NULL")
    private Long idPurchaser;
    @NotNull(message = "O ID do Vendedor não deve ser NULL")
    private Long idSeller;

}
