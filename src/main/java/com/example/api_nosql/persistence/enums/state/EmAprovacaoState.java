package com.example.api_nosql.persistence.enums.state;

import com.example.api_nosql.exception.IlegalStatusChange;
import com.example.api_nosql.persistence.model.Match;
import com.example.api_nosql.persistence.enums.StatusMatch;

public class EmAprovacaoState implements MatchState {
    @Override
    public StatusMatch getStatusMatch() {
        return StatusMatch.EM_APROVACAO;
    }

    @Override
    public void changeStatusMatch(Match match, StatusMatch newStatus) {
        if  (newStatus == StatusMatch.CANCELADO ||  newStatus == StatusMatch.CONCLUIDO) {
            match.setStatus(newStatus);
            return;
        }
        throw new IlegalStatusChange("Não é possivel mudar do status " + match.getStatus().toString() + " para o status " + newStatus.toString());
    }
}
