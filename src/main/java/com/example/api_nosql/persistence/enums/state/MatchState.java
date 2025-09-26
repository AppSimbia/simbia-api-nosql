package com.example.api_nosql.persistence.enums.state;

import com.example.api_nosql.persistence.model.Match;
import com.example.api_nosql.persistence.enums.StatusMatch;

public interface MatchState {
    StatusMatch getStatusMatch();
    void changeStatusMatch(Match match, StatusMatch statusMatch);
}
