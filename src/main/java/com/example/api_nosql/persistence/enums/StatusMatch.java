package com.example.api_nosql.persistence.enums;

import lombok.Getter;

@Getter
public enum StatusMatch {

    CONCLUIDO,
    CANCELADO,
    ANDAMENTO,
    AGUARDANDO_APROVACAO_CRIACAO,
    AGUARDANDO_APROVACAO_FECHAMENTO,
    AGUARDANDO_SOLICITACAO_FECHAMENTO,
    AGUARDANDO_PAGAMENTO;

    private String status;

}
