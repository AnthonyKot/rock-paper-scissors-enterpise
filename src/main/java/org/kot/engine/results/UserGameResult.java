package org.kot.engine.results;

import org.kot.engine.users.User;

public record UserGameResult(User user, int wins, int losses, int ties) {
    public static UserGameResult of(User user) {
        return new UserGameResult(user, 0, 0, 0);
    }

    @Override
    public String toString() {
        return """
                User: %s
                Wins: %d
                Losses: %d
                Ties: %d
                """.formatted(user, wins, losses, ties);
    }
}
