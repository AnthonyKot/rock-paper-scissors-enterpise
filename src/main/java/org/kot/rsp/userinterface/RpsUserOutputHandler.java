package org.kot.rsp.userinterface;

import org.kot.engine.userinterface.GamePrinter;
import org.kot.engine.userinterface.UserOutputHandler;

public class RpsUserOutputHandler implements UserOutputHandler {

    @Override
    public GamePrinter gamePrinter() {
        return new RpsGamePrinter();
    }

    @Override
    public void communicateGameStarts() {
        System.out.println("Please enter your name:");
    }

    @Override
    public void communicateGreetings(String userName) {
        System.out.println("Welcome dear " + userName + ". Let's play a game!");
        System.out.println();
    }

    @Override
    public void communicateGameRules() {
        System.out.println("Game rules:");
        System.out.println("1. You can choose one of the following moves:");
        System.out.println("   - rock");
        System.out.println("   - paper");
        System.out.println("   - scissors");
        System.out.println("2. Rock beats scissors.");
        System.out.println("3. Scissors beats paper.");
        System.out.println("4. Paper beats rock.");
        System.out.println("5. If both players choose the same move, the round is tied.");
        System.out.println("6. The first player to win 3 rounds wins the game.");
    }

    @Override
    public void communicateError(String message) {
        System.err.println(message);
    }

    @Override
    public void communicatePrompt() {
        System.out.print("> ");
    }

    public void communicateGeneric(String message) {
        System.out.println(message);
    }
}
