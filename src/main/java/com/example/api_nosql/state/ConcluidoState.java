package com.example.api_nosql.state;

import com.example.api_nosql.exception.IlegalStatusChange;
import com.example.api_nosql.model.Match;
import com.example.api_nosql.model.enums.StatusMatch;

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
