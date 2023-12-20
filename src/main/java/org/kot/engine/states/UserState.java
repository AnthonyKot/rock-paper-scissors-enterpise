package org.kot.engine.states;

import org.kot.engine.Move;
import java.util.Optional;

public record UserState(String name, Optional<Move> theLastMove) implements State {

    public static UserState updateLastMove(UserState state, Move move) {
        return new UserState(state.name, Optional.ofNullable(move));
    }

    public static UserState init(String name) {
        return new UserState(name, Optional.empty());
    }

    @Override
    public UserState reset() {
        return updateLastMove(this, null);
    }
}
