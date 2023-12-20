package org.kot.engine.userinterface;

import org.kot.engine.Move;
import java.util.Optional;

public interface UserSelection {
    Optional<Move> getMove();
    Optional<Command> getCommand();
}
