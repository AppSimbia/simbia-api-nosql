package com.example.api_nosql.persistence.enums;

import lombok.Getter;

@Getter
public enum StatusMatch {

    CONCLUIDO,
    CANCELADO,
    ANDAMENTO,
    EM_APROVACAO;

    private String status;

}
