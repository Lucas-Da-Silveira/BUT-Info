package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;
import boardifier.model.animation.AnimationStep;
import boardifier.view.GridGeometry;

public class Pawn extends GameElement {

    private int number;
    private int color;
    public static int PAWN_BLACK = 0;
    public static int PAWN_RED = 1;

    public Pawn(int number, int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        this.number = number;
        this.color = color;
        /*
        TO FULFILL:
            - register a new type of element for the pawns
            - initialize attributes
         */

    }

    public int getNumber() {
        return number;
    }
    public int getColor() {
        return color;
    }
}
