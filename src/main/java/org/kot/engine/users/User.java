package org.kot.engine.users;

import org.kot.engine.Game;
import org.kot.engine.Move;
import org.kot.engine.states.UserState;

import java.util.List;
import java.util.Optional;

public interface User {
    Optional<Move> move(List<Move> availableMoves, Game game);
    default UserState createUserState(String name) {
        return UserState.init(name);
    }
}
