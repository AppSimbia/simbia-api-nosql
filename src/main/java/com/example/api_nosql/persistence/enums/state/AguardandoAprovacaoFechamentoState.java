package com.example.api_nosql.persistence.enums.state;

import com.example.api_nosql.exception.IlegalStatusChange;
import com.example.api_nosql.persistence.entity.Match;
import com.example.api_nosql.persistence.enums.StatusMatch;

public class AguardandoAprovacaoFechamentoState implements MatchState {
    @Override
    public StatusMatch getStatusMatch() {
        return StatusMatch.AGUARDANDO_APROVACAO_FECHAMENTO;
    }

    @Override
    public void changeStatusMatch(Match match, StatusMatch newStatus) {
        if  (newStatus == StatusMatch.CANCELADO ||  newStatus == StatusMatch.AGUARDANDO_APROVACAO_FECHAMENTO) {
            match.setStatus(newStatus);
            return;
        }
        throw new IlegalStatusChange("Não é possivel mudar do status " + match.getStatus().toString() + " para o status " + newStatus.toString());
    }
}
