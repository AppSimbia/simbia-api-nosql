package com.example.api_nosql.persistence.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchContext {
    private MatchState state;

    public MatchContext(MatchState state) {
        this.state = state;
    }

    public void next() {
        state.handle(this);
    }
}