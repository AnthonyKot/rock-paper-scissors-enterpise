package org.kot.engine.userinterface;

import org.kot.engine.Game;
import org.kot.engine.results.GameResult;
import org.kot.engine.states.GameState;

public interface UserOutputHandler {

    default GamePrinter gamePrinter() {
        return Record::toString;
    }

    void communicateGameStarts();

    void communicateGreetings(String userName);

    void communicateGameRules();

    void communicateError(String message);

    default void communicateGameState(Game game) {
        communicateGeneric(gamePrinter().print(game));
    }

    default void communicateEndOfGame(GameResult gameResult) {
        communicateGeneric(gameResult.toString());
    }

    void communicatePrompt();

    void communicateGeneric(String message);
}
