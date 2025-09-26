package com.example.api_nosql.api.dto.match;

import com.example.api_nosql.api.validation.OnCreate;
import com.example.api_nosql.api.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@Validated
public class MatchRequest {

    @NotNull(message = "O ID do Match não pode ser NULL", groups = {OnUpdate.class})
    private String id;
    @NotNull(message = "O ID do Post não pode ser NULL", groups = {OnCreate.class})
    private String idPost;
    @NotNull(message = "O ID do Comprador não pode ser NULL", groups = {OnCreate.class})
    private Long idPurchaser;
    @NotNull(message = "O ID do Vendedor não pode ser NULL", groups = {OnUpdate.class})
    private Long idSeller;

}
