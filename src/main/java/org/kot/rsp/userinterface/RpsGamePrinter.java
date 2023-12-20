package org.kot.rsp.userinterface;

import org.kot.engine.Game;
import org.kot.engine.userinterface.GamePrinter;

public class RpsGamePrinter implements GamePrinter {

    public String print(Game game) {
        return """
                Round # %d.
                We have two players
                %s
                and
                %s
                """.formatted(
                game.gameResult().roundsPlayed() + 1,
                game.gameState().userStates().get(game.getHumanUsers().get(0)).name(),
                game.gameState().userStates().get(game.getBotUsers().get(0)).name()
        );
    }
}
