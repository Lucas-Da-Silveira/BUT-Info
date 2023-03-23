package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;

public class HoleStageFactory extends StageElementsFactory {
    private HoleStageModel stageModel;

    public HoleStageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (HoleStageModel) gameStageModel;
    }

    @Override
    public void setup() {
        stageModel.setBoard(new HoleBoard(0, 0, stageModel));
        //create the black pot, , top-left char is in (18,0)
        HolePawnPot blackPot = new HolePawnPot(18,0, stageModel);
        stageModel.setBlackPot(blackPot);
        // create the black pawns
        Pawn[] blackPawns = new Pawn[4];
        for(int i=0;i<4;i++) {
            blackPawns[i] = new Pawn(i + 1, Pawn.PAWN_BLACK, stageModel);
        }
        stageModel.setBlackPawns(blackPawns);
        // assign black pawns to their pot
        for (int i=0;i<4;i++) {
            blackPot.putElement(blackPawns[i], i,0);
        }
        /*
        TO FULFILL:
            - create the board, pots, pawns and set them in the stage model
            - assign the pawns to their cells in the pots
         */
        HolePawnPot redPot = new HolePawnPot(18,6, stageModel);
        stageModel.setRedPot(redPot);
        Pawn[] redPawns = new Pawn[4];
        for(int i=0;i<4;i++){
            redPawns[i] = new Pawn[i+1, Pawn,PAWN_RED, stageModel];


        }
    }
}
