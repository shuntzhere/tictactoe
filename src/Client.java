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
    public static void main(String[] args) {
        System.out.println("GAME STARTS!");
        Scanner scanner = new Scanner(System.in);

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

        Game game = Game.getBuilder().setDimension(dimension)
                                     .setPlayers(players)
                                     .setWinningStrategies(winningStrategies)
                                     .build();

        System.out.println("DEBUG");
    }
}