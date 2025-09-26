package com.example.api_nosql.persistence.enums.state;

import com.example.api_nosql.exception.IlegalStatusChange;
import com.example.api_nosql.persistence.model.Match;
import com.example.api_nosql.persistence.enums.StatusMatch;

public class ConcluidoState implements MatchState {
    @Override
    public StatusMatch getStatusMatch() {
        return StatusMatch.CONCLUIDO;
    }

    @Override
    public void changeStatusMatch(Match match, StatusMatch statusMatch) {
        throw new IlegalStatusChange("Não é possivel fazer nenhuma mudança. O match já foi Concluído.");
    }
}
