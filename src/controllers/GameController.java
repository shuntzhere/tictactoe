package controllers;

import models.Game;
import models.GameState;
import models.Player;
import strategies.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {
     public static Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
         return Game.getBuilder().setDimension(dimension)
                 .setPlayers(players)
                 .setWinningStrategies(winningStrategies)
                 .build();
     }

     public void makeMove(Game game) {

     }

     public GameState gameState(Game game) {
         return game.getGameState();
     }

     public Player getWinner(Game game) {
         return game.getWinner();
     }

     public void printBoard(Game game) {
         game.printBoard();
     }

     public void undoLastMove(Game game) {
         // Logic to undo the last move
         // This would typically involve removing the last move from the moves list
         // and updating the board accordingly.
     }
}
