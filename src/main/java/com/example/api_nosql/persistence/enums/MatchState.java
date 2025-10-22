package com.example.api_nosql.persistence.enums;

import com.example.api_nosql.exception.IlegalStatusChange;
import lombok.Getter;

import java.util.Locale;

@Getter
public enum MatchState {

    AGUARDANDO_APROVACAO_CRIACAO {
        @Override
        public void handle(MatchContext context) {
            context.setState(MatchState.ANDAMENTO);
        }
    },
    AGUARDANDO_APROVACAO_FECHAMENTO {
        @Override
        public void handle(MatchContext context) {
            context.setState(MatchState.AGUARDANDO_PAGAMENTO);
        }
    },
    AGUARDANDO_PAGAMENTO {
        @Override
        public void handle(MatchContext context) {
            context.setState(MatchState.CONCLUIDO);
        }
    },
    ANDAMENTO {
        @Override
        public void handle(MatchContext context) {
            context.setState(MatchState.AGUARDANDO_APROVACAO_FECHAMENTO);
        }
    },
    CANCELADO {
        @Override
        public void handle(MatchContext context) {
            throw new IlegalStatusChange("It is not possible to change the status of a canceled match.");
        }
    },
    CONCLUIDO {
        @Override
        public void handle(MatchContext context) {
            throw new IlegalStatusChange("It is not possible to change the status of a concluded match.");
        }
    };

    public abstract void handle(MatchContext context);

    public String getStatus() {
        return this.name().toUpperCase(Locale.ROOT);
    }
}

