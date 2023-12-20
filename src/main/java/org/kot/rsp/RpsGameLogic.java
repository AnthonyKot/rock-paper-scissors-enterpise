package org.kot.rsp;

import org.kot.engine.Game;
import org.kot.engine.GameLogic;
import org.kot.engine.states.GameState;
import org.kot.engine.userinterface.UserOutputHandler;
import org.kot.engine.users.User;
import org.kot.rsp.userinterface.RpsUserOutputHandler;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class RpsGameLogic implements GameLogic {

    private final Random random;
    private final UserOutputHandler output;

    public RpsGameLogic() {
        this.random = new SecureRandom();
        this.output = new RpsUserOutputHandler();
    }

    @Override
    public Optional<User> determineRoundWinner(Game game) {
        var userStates = game.gameState().userStates();
        if (!userStates.values().stream().allMatch(userState -> userState.theLastMove().isPresent())) {
            throw new IllegalArgumentException("Not all users made their moves");
        }
        // this specific game is for two players only
        var human = game.getHumanUsers().get(0);
        var bot = game.getBotUsers().get(0);
        var humanChoice = userStates.get(human).theLastMove().orElseThrow(() -> new IllegalStateException("Human did not make a move"));
        var botChoice = userStates.get(bot).theLastMove().orElseThrow(() -> new IllegalStateException("Bot did not make a move"));

        // we need to reveal the computer's move
        output.communicateGeneric("%s chose %s ".formatted(userStates.get(bot).name(), botChoice));

        if (humanChoice.equals(botChoice)) {
            output.communicateGeneric("It's a tie!");
            // there is no winner so it's a tie
            return Optional.empty();
        }
        if ((humanChoice.equals(RpsMoves.ROCK) && botChoice.equals(RpsMoves.SCISSORS)) ||
                (humanChoice.equals(RpsMoves.PAPER) && botChoice.equals(RpsMoves.ROCK)) ||
                (humanChoice.equals(RpsMoves.SCISSORS) && botChoice.equals(RpsMoves.PAPER))) {
            output.communicateGeneric("%s won!".formatted(userStates.get(human).name()));
            output.communicateGeneric("Congratulations!");
            output.communicateGeneric("");
            return Optional.of(human);
        } else {
            output.communicateGeneric("%s won!".formatted(userStates.get(bot).name()));
            output.communicateGeneric("AGI is coming!");
            output.communicateGeneric("");
            return Optional.of(bot);
        }
    }

    @Override
    public Optional<User> getNextUser(GameState gameState) {
        var usersMadeNoMoves = gameState.userStates().entrySet().stream()
                .filter(entry -> entry.getValue().theLastMove().isEmpty())
                .map(Map.Entry::getKey)
                .toList();
        if (usersMadeNoMoves.isEmpty()) {
            return Optional.empty();
        }
        int randomMove = random.nextInt(usersMadeNoMoves.size());
        return Optional.of(usersMadeNoMoves.get(randomMove));
    }
}