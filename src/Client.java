import controllers.GameController;
import exception.InvalidMoveException;
import models.*;
import strategies.winningstrategy.ColumnWinningStrategy;
import strategies.winningstrategy.DiagonalWinningStrategy;
import strategies.winningstrategy.RowWinningStrategy;
import strategies.winningstrategy.WinningStrategy;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("GAME STARTS!");
        Scanner scanner = new Scanner(System.in);

        GameController gameController = new GameController();

//        int dimension = scanner.nextInt();
        int dimension = 3;

        List<Player> players = new ArrayList<>();
        players.add(
                new Player(new Symbol('X'), "Jay", PlayerType.HUMAN)
        );

        players.add(
                new Bot(new Symbol('O'), "Jarvis", PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        Game game = GameController.startGame(
                dimension,
                players,
                winningStrategies
        );

        while(gameController.gameState(game).equals(GameState.IN_PROGRESS)){
            //show board
            gameController.printBoard(game);
            System.out.println("Do you want to undo the last move? (yes/no)");
            String undoResponse = scanner.nextLine();

            if(undoResponse.equalsIgnoreCase("yes")){
                gameController.undoLastMove(game);
            }

            gameController.makeMove(game);
            //make a move
        }

        gameController.printBoard(game);

        if(gameController.gameState(game).equals(GameState.ENDED)){
            System.out.println(gameController.getWinner(game).getName() + " has won the game.");
        }else {
            System.out.println("Game is a draw.");
        }
//        System.out.println("DEBUG");
    }
}