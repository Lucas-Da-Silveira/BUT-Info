package model;

import boardifier.model.GameStageModel;
import boardifier.model.GridElement;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class HoleBoard extends GridElement {
    public HoleBoard(int x, int y, GameStageModel gameStageModel) {
        // call the super-constructor to create a 3x3 grid, named "holeboard", and in x,y in space
        super("holeboard", x, y, 3 , 3, gameStageModel);
        resetReachableCells(false);
    }

    public void setValidCells(int number) {
        resetReachableCells(false);
        List<Point> valid = computeValidCells(number);
        if (valid != null) {
            for(Point p : valid) {
                reachableCells[p.y][p.x] = true;
            }
        }
    }
    public List<Point> computeValidCells(int number) {
        List<Point> lst = new ArrayList<>();
         /*
        TO FULFILL:
            - compute the list of cells that are valid to play taking the pawn value (i.e. number) into account.
            each Point in this list consists in couple x,y, where x is a column and y a row in the board.
         */
        return lst;
    }
}
