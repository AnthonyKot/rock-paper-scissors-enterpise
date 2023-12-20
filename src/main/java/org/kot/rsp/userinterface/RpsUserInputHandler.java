package org.kot.rsp.userinterface;

import org.kot.engine.Move;
import org.kot.engine.userinterface.Command;
import org.kot.engine.userinterface.UserInputHandler;
import org.kot.engine.userinterface.UserSelection;
import org.kot.rsp.RpsMoves;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RpsUserInputHandler implements UserInputHandler {
    private final Scanner scanner;

    public RpsUserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readUserName() {
        return scanner.nextLine();
    }

    @Override
    public UserSelection readUserChoice(List<Move> availableMoves, List<Command> commands) {
        var userChoice = scanner.nextLine().toLowerCase();
        var isCommand = Arrays.stream(RpsCommands.values())
                .anyMatch(command -> command.getExpectedInput().equals(userChoice));
        if (isCommand) {
            return Arrays.stream(RpsCommands.values())
                    .filter(command -> command.getExpectedInput().equals(userChoice))
                    .findFirst()
                    .map(RpsUserSelection::NOT_A_MOVE)
                    .orElse(RpsUserSelection.ERROR);
        }
        // it either matches one of the available moves or it's an error
        return availableMoves
                .stream()
                .map(move -> RpsMoves.valueOf(move.toString()))
                .filter(move -> move.toString().toLowerCase().equals(userChoice))
                .findFirst()
                .map(RpsUserSelection::MOVE)
                .orElse(RpsUserSelection.ERROR);
    }

    @Override
    public void finishTheGame() {
        scanner.close();

    }
}