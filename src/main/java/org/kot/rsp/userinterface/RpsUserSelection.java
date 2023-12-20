package org.kot.rsp.userinterface;

import org.kot.engine.Move;
import org.kot.engine.userinterface.Command;
import org.kot.engine.userinterface.UserSelection;
import org.kot.rsp.RpsMoves;

import java.util.Optional;

public enum RpsUserSelection implements UserSelection {
    OK(null, null),
    ERROR(null, null),
    COMMAND(null, null);

    private Optional<Move> move;
    private Optional<Command> command;

    RpsUserSelection(Move move, Command command) {
        this.move = Optional.ofNullable(move);
        this.command = Optional.ofNullable(command);
    }

    public static RpsUserSelection MOVE(RpsMoves move) {
        var ok = RpsUserSelection.OK;
        ok.move = Optional.of(move);
        return ok;
    }

    public static RpsUserSelection NOT_A_MOVE(Command c) {
        var command = RpsUserSelection.COMMAND;
        command.command = Optional.of(c);
        return command;
    }

    public Optional<Move> getMove() {
        return move;
    }

    public Optional<Command> getCommand() {
        return command;
    }
}
