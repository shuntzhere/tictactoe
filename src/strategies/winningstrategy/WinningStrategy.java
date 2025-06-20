package strategies.winningstrategy;

import models.Board;
import models.Cell;
import models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move, Cell cell);
}
