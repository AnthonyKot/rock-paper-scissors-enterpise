package org.kot.engine.userinterface;

import org.kot.engine.Move;

import java.util.List;

public interface UserInputHandler {
    String readUserName();
    UserSelection readUserChoice(List<Move> availableMoves, List<Command> availableCommands);
    void finishTheGame();
}
