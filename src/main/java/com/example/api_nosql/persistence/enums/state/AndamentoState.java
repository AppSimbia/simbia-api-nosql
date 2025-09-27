package com.example.api_nosql.persistence.enums.state;

import com.example.api_nosql.exception.IlegalStatusChange;
import com.example.api_nosql.persistence.model.Match;
import com.example.api_nosql.persistence.enums.StatusMatch;

public class AndamentoState implements MatchState {
    @Override
    public StatusMatch getStatusMatch() {
        return StatusMatch.ANDAMENTO;
    }

    @Override
    public void changeStatusMatch(Match match, StatusMatch newStatus) {
        if  (newStatus == StatusMatch.CANCELADO ||  newStatus == StatusMatch.EM_APROVACAO) {
            match.setStatus(newStatus);
            return;
        }
        throw new IlegalStatusChange("Não é possivel mudar do status " + match.getStatus().toString() + " para o status " + newStatus.toString());
    }
}
