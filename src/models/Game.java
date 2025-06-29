package models;

import exception.InvalidMoveException;
import strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;

    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void printBoard() {
        board.printBoard();
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            throw new RuntimeException("Invalid move: Cell out of bounds");
        }

        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }

        return true;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayerIndex);

        System.out.println("This is " + currentPlayer.getName() + "'s turn");
        Move move = currentPlayer.makeMove(board);

        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid move: Cell already occupied");
        }

        //valid move, apply move to board
        Cell cell = board.getBoard().get(move.getCell().getRow()).get(move.getCell().getCol());
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move newMove = new Move(currentPlayer, cell);
        moves.add(newMove);

        nextMovePlayerIndex = (nextMovePlayerIndex + 1) % players.size();

        if (checkWinner(newMove)){
            winner = currentPlayer;
            gameState = GameState.ENDED;
        }else if (moves.size() == board.getDimension() * board.getDimension()){
            gameState = GameState.DRAW;
        }

    }



    public boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }


    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

//        private List<Move> moves;
//        private GameState gameState;
//        private Player winner;
//        private int nextMovePlayerIndex;

        private Builder(){
            this.dimension = 0;
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        private void validateBotCount(){
            int count = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    count += 1;
                    if(count > 1){
                        throw new RuntimeException("Only 1 BOT allowed");
                    }
                }
            }
        }

        private void validateUniqueSymbol(){
            Set<Character> symbolSet = new HashSet<>();
            for(Player player : players){
                symbolSet.add(player.getSymbol().getaChar());
            }

            if(symbolSet.size() != dimension-1){
                throw new RuntimeException("Every player should have a unique symbol");
            }
        }

        private void validations(){
//            if(dimension <= 0) {
//                throw new RuntimeException("Dimension can't be negative");
//            }
            validateBotCount();
            validateUniqueSymbol();

        }

        public Game build(){
            //validation starts
            validations();
            return new Game(dimension, players, winningStrategies);
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }
}
