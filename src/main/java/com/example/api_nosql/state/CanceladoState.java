package com.example.api_nosql.state;

import com.example.api_nosql.exception.IlegalStatusChange;
import com.example.api_nosql.model.Match;
import com.example.api_nosql.model.enums.StatusMatch;

public class CanceladoState implements MatchState {
    @Override
    public StatusMatch getStatusMatch() {
        return StatusMatch.CANCELADO;
    }

    @Override
    public void changeStatusMatch(Match match, StatusMatch statusMatch) {
        throw new IlegalStatusChange("Não é possivel fazer nenhuma mudança. O match já foi Cancelado.");
    }
}
