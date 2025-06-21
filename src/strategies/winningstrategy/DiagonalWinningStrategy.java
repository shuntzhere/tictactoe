package strategies.winningstrategy;

import models.Board;
import models.Move;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move){
        return false;
    }
}
