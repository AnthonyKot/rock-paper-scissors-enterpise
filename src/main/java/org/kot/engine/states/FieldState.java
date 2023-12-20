package org.kot.engine.states;

import org.kot.engine.Move;
import org.kot.engine.users.User;

import java.util.List;

public interface FieldState extends State {
    List<Move> getAvailableMoves(GameState gameState, User user);
}