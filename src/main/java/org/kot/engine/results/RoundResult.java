package org.kot.engine.results;

import org.kot.engine.users.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public record RoundResult(Map<User, RoundOutcome> outcomes) {
    public static RoundResult ofWinner(Optional<User> winner, List<User> allUsers) {
        return new RoundResult(
            allUsers.stream()
                .collect(Collectors.toMap(
                    user -> user,
                    user -> winner.map(w -> w.equals(user) ? RoundOutcome.WIN : RoundOutcome.LOSS)
                        .orElse(RoundOutcome.TIE)
                ))
        );

    }
}
