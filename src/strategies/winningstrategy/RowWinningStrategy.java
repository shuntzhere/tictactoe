package strategies.winningstrategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{

    private final Map<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move){
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }

        Map<Character, Integer> currentRowMap = rowMaps.get(row);

        if(!currentRowMap.containsKey(aChar)){
            currentRowMap.put(aChar, 0);
        }
        currentRowMap.put(aChar, currentRowMap.get(aChar) + 1);

        return currentRowMap.get(aChar).equals(board.getDimension());
    }

}
