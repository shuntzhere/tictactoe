package models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private static Scanner scanner =new Scanner(System.in);
    //executed once in the entire scope

    public Player(Symbol symbol, String name, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    Move makeMove(Board board){
        System.out.println("Enter the row number");
        int row = scanner.nextInt();

        System.out.println("Enter the column number");
        int column = scanner.nextInt();

        return new Move(this, new Cell(row, column));
    }
}
