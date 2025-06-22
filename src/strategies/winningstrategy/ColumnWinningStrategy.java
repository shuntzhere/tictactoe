package strategies.winningstrategy;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{

    private final Map<Integer, HashMap<Character, Integer>> columnMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move){
        int column = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!columnMaps.containsKey(column)){
            columnMaps.put(column, new HashMap<>());
        }

        Map<Character, Integer> currentColumnMap = columnMaps.get(column);

        if(!currentColumnMap.containsKey(aChar)){
            currentColumnMap.put(aChar, 0);
        }

        currentColumnMap.put(aChar, currentColumnMap.get(aChar) + 1);

        if(currentColumnMap.get(aChar).equals(board.getDimension())){
            return true;
        }

        return false;
    }
}
