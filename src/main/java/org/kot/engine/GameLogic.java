package org.kot.engine;

import org.kot.engine.states.GameState;
import org.kot.engine.users.User;

import java.util.Optional;

public interface GameLogic {

    Optional<User> determineRoundWinner(Game game);
    Optional<User> getNextUser(GameState gameState);
}