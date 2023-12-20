package org.kot.engine.users;

import org.kot.engine.Game;
import org.kot.engine.Move;
import org.kot.engine.states.UserState;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BotUser implements User {

    private final Random random;

    public BotUser() {
        this.random = new SecureRandom();
    }

    @Override
    public Optional<Move> move(List<Move> availableMoves, Game game) {
        // the simplest bot does not care about the game state
        int randomMove = random.nextInt(availableMoves.size());
        return Optional.of(availableMoves.get(randomMove));
    }

    @Override
    public UserState createUserState(String name) {
        return UserState.init("Computer");
    }
}
