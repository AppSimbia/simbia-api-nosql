package com.example.api_nosql.persistence.enums;

import lombok.Getter;

@Getter
public enum StatusMatch {

    CONCLUIDO,
    CANCELADO,
    ANDAMENTO,
    AGUARDANDO_APROVACAO_CRIAÇÃO,
    AGUARDANDO_APROVACAO_FECHAMENTO,
    AGUARDANDO_SOLICITAÇÃO_FECHAMENTO,
    AGUARDANDO_PAGAMENTO;

    private String status;

}
