package com.example.api_nosql.state;

import com.example.api_nosql.model.Match;
import com.example.api_nosql.model.enums.StatusMatch;

public interface MatchState {
    StatusMatch getStatusMatch();
    void changeStatusMatch(Match match, StatusMatch statusMatch);
}
