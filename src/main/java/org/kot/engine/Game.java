package org.kot.engine;

import org.kot.engine.results.UserGameResult;
import org.kot.engine.states.FieldState;
import org.kot.engine.results.GameResult;
import org.kot.engine.states.GameState;
import org.kot.engine.users.BotUser;
import org.kot.engine.users.HumanUser;
import org.kot.engine.users.User;

import java.util.List;
import java.util.stream.Collectors;

public record Game(GameState gameState, List<User> users, GameResult gameResult) {

    public static Game initGameWithBot(User user1, FieldState fieldState) {
        return initTwoPlayersGame(user1, new BotUser(), fieldState);
    }

    public static Game initTwoPlayersGame(User user1, User user2, FieldState fieldState) {
        var emptyField = (FieldState) fieldState.reset();
        var users = List.of(user1, user2);
        var initialUserStates = users.stream().collect(Collectors.toMap(user -> user, user -> user.createUserState("empty")));
        var initialGameState = new GameState(emptyField, initialUserStates);
        var initialGameResults = users.stream().map(UserGameResult::of).toList();
        return new Game(initialGameState, users, new GameResult(0, initialGameResults));
    }

    public static Game recordRoundResult(Game game, GameResult gameResult) {
        return new Game(GameState.resetRound(game.gameState), game.users(), gameResult);
    }

    public List<User> getHumanUsers() {
        return getUsersByType(HumanUser.class);
    }

    public List<User> getBotUsers() {
        return getUsersByType(BotUser.class);
    }

    private List<User> getUsersByType(Class<?> type) {
        return this.users.stream().filter(type::isInstance).toList();
    }
}
