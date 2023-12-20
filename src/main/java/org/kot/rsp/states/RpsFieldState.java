package org.kot.rsp.states;

import org.kot.engine.Move;
import org.kot.engine.states.FieldState;
import org.kot.engine.states.GameState;
import org.kot.engine.users.User;
import org.kot.rsp.RpsMoves;

import java.util.List;

public class RpsFieldState implements FieldState {

    @Override
    public List<Move> getAvailableMoves(GameState gameState, User user) {
        // available moves are the same for all users and states
        return List.of(RpsMoves.ROCK, RpsMoves.SCISSORS, RpsMoves.PAPER);
    }

    @Override
    public RpsFieldState reset() {
        return new RpsFieldState();
    }
}
