package org.kot.engine.states;

import org.kot.engine.Game;
import org.kot.engine.users.User;
import java.util.Map;

public record GameState(FieldState fieldState, Map<User, UserState> userStates) {

    public static GameState resetRound(GameState gameState) {
        gameState.userStates().forEach((key, value) -> gameState.userStates.put(key, value.reset()));
        return new GameState((FieldState) gameState.fieldState().reset(), gameState.userStates);
    }
}