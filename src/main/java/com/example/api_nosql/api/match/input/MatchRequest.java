package com.example.api_nosql.api.match.input;

import com.example.api_nosql.validation.OnCreate;
import com.example.api_nosql.validation.OnPayment;
import com.example.api_nosql.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@AllArgsConstructor
public class MatchRequest {

    @Schema(example = "1", description = "ID of the associated post")
    @NotNull(message = "O ID do Post não pode ser NULL", groups = {OnCreate.class})
    private Long idPost;

    @Schema(example = "1", description = "ID of the associated employee purchaser")
    @NotNull(message = "O ID do Funcionário Comprador não pode ser NULL", groups = {OnCreate.class})
    private Long idEmployeePurchaser;

    @Schema(example = "1", description = "ID of the associated employee seller")
    @NotNull(message = "O ID do Funcionário Vendedor não pode ser NULL", groups = {OnUpdate.class})
    private Long idEmployeeSeller;

    @Schema(example = "12345678901234", description = "CNPJ of the associated industry purchaser")
    @NotNull(message = "O ID da Indústria Compradora não pode ser NULL", groups = {OnCreate.class})
    @CNPJ(groups = OnCreate.class)
    private String idIndustryPurchaser;

    @Schema(example = "12345678901234", description = "CNPJ of the associated industry seller")
    @NotNull(message = "O ID da Indústria Vendedora não pode ser NULL", groups = {OnUpdate.class})
    @CNPJ(groups = OnUpdate.class)
    private String idIndustrySeller;

    @Schema(example = "123.5", description = "Proposed closing value")
    @NotNull(message = "O valor proposto não pode ser NULL", groups = {OnPayment.class})
    private Double proposedValue;

    @Schema(example = "100", description = "Quantity of products for closing")
    @NotNull(message = "A quantidade de produtos para fechamento do match não pode ser NULL", groups = {OnPayment.class})
    private Long quantity;

    @Schema(example = "1", description = "Product measurement unit")
    @NotNull(message = "A unidade de medida do produto não pode ser NULL", groups = {OnPayment.class})
    private Long measureUnit;

}
