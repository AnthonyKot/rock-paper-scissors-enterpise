package org.kot.rsp;

import org.kot.engine.Game;
import org.kot.engine.GameLogic;
import org.kot.engine.results.GameResult;
import org.kot.engine.results.RoundResult;
import org.kot.engine.states.UserState;
import org.kot.engine.users.HumanUser;
import org.kot.rsp.states.RpsFieldState;
import org.kot.rsp.userinterface.RpsUserInputHandler;
import org.kot.rsp.userinterface.RpsUserOutputHandler;

public class RockPaperScissorsGame {

    private static final int MAX_ROUNDS = 3;
    private final GameLogic gameLogic;
    private final RpsUserOutputHandler output;
    private Game thisRound;

    public RockPaperScissorsGame() {
        this.gameLogic = new RpsGameLogic();
        var user = new HumanUser(new RpsUserInputHandler(), new RpsUserOutputHandler());
        this.thisRound = Game.initGameWithBot(user, new RpsFieldState());
        this.output = new RpsUserOutputHandler();
    }

    public void startGame() {
        while (true) {
            var gameState = thisRound.gameState();
            var currentUser = gameLogic.getNextUser(thisRound.gameState());
            if (currentUser.isEmpty()) {
                var winner = gameLogic.determineRoundWinner(thisRound);
                var gameResult = GameResult.roundUpdate(thisRound.gameResult(), RoundResult.ofWinner(winner, thisRound.users()));
                if (gameResult.roundsPlayed() == MAX_ROUNDS) {
                    // end of the game
                    output.communicateEndOfGame(gameResult);
                    break;
                }
                // this is a new round
                thisRound = Game.recordRoundResult(thisRound, gameResult);
                continue;
            }
            var user = currentUser.get();
            var move = user.move(gameState.fieldState().getAvailableMoves(gameState, user), thisRound);
            if (move.isEmpty()) {
                // game has ended
                System.out.println("User " + gameState.userStates().get(user).name() + " has left the game.");
                break;
            }
            var validMove = (RpsMoves) move.get();
            var newUserState = UserState.updateLastMove(gameState.userStates().get(user), validMove);
            gameState.userStates().put(user, newUserState);
        }
    }

    public static void main(String[] args) {
        RockPaperScissorsGame game = new RockPaperScissorsGame();
        game.startGame();
    }
}
