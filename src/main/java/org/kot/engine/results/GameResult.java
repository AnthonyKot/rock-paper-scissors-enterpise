package org.kot.engine.results;

import java.util.List;

public record GameResult(int roundsPlayed, List<UserGameResult> userResults) {

    public static GameResult roundUpdate(GameResult gameResult, RoundResult roundResult) {
        var updateGameResult = gameResult.userResults.stream().map(userGameResult -> {
            RoundOutcome roundOutcome = roundResult.outcomes().get(userGameResult.user());
            return switch (roundOutcome) {
                case WIN -> new UserGameResult(userGameResult.user(), userGameResult.wins() + 1, userGameResult.losses(), userGameResult.ties());
                case LOSS -> new UserGameResult(userGameResult.user(), userGameResult.wins(), userGameResult.losses() + 1, userGameResult.ties());
                case TIE -> new UserGameResult(userGameResult.user(), userGameResult.wins(), userGameResult.losses(), userGameResult.ties() + 1);
            };
        }).toList();
        return new GameResult(gameResult.roundsPlayed() + 1, updateGameResult);
    }

    @Override
    public String toString() {
        return """
                Game result:
                %s
                """.formatted(
                userResults.stream().map(UserGameResult::toString).reduce("", (s, s2) -> s + "\n" + s2)
        );
    }
}