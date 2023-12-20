package org.kot.engine.users;

import org.kot.engine.Game;
import org.kot.engine.Move;
import org.kot.engine.states.UserState;
import org.kot.engine.userinterface.UserInputHandler;
import org.kot.engine.userinterface.UserOutputHandler;
import org.kot.rsp.userinterface.RpsCommands;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HumanUser implements User {

    private final UserInputHandler userInputHandler;
    private final UserOutputHandler userOutputHandler;

    public HumanUser(UserInputHandler userInputHandler, UserOutputHandler userOutputHandler) {
        this.userInputHandler = userInputHandler;
        this.userOutputHandler = userOutputHandler;
    }

    @Override
    public Optional<Move> move(List<Move> availableMoves, Game game) {
        userOutputHandler.communicateGameState(game);
        userOutputHandler.communicateGeneric("Please enter one of those options:");
        availableMoves.forEach(option -> userOutputHandler.communicateGeneric(option.toString()));
        userOutputHandler.communicatePrompt();
        var selection = userInputHandler.readUserChoice(availableMoves, Arrays.asList(RpsCommands.values()));
        if (selection.getMove().isPresent()) {
            return Optional.of(selection.getMove().get());
        }
        if (selection.getCommand().isPresent()) {
            return switch ((RpsCommands) selection.getCommand().get()) {
                // handle command here
                case EXIT -> {
                    userInputHandler.finishTheGame();
                    userOutputHandler.communicateEndOfGame(game.gameResult());
                    yield Optional.empty();
                }
                case HELP -> {
                    userOutputHandler.communicateGameRules();
                    yield move(availableMoves, game);
                }
            };
        } else {
            userOutputHandler.communicateError("Invalid input, please try again.");
        }
        return move(availableMoves, game);
    }

    @Override
    public UserState createUserState(String name) {
        userOutputHandler.communicateGameStarts();
        // should be validated
        var userName = userInputHandler.readUserName();
        userOutputHandler.communicateGreetings(userName);
        return UserState.init(userName);
    }
}
