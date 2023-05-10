package model;

import java.util.*;

public class Model {
    private static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    public final static int STATE_PAUSE = 0;
    public final static int STATE_PLAY = 1;
    public final static int STATE_WIN = 2;
    public final static int STATE_LOST = 3;

    private BallModel ballModel;
    private RacketModel racketModel;
    private BrickModel[][] bricks;
    private int brickRows;
    private int brickCols;
    // number of visible bricks (i.e. not destroyed by the ball)
    private int nbBricks;

    // frame management
    private long lastFrame;
    private long frameGap;

    // dimensions of the model space
    private int width;
    private int height;

    // state of the game
    private int state;

    public Model(int width, int height, int brickRows, int brickCols) {
        this.width = width;
        this.height = height;
        this.brickRows = brickRows;
        this.brickCols = brickCols;
        nbBricks = brickCols*brickRows;
        lastFrame = -1;
        frameGap = 15000000;
        state = STATE_PAUSE;

        // NB: the initial position will be set by the controller in initGame()
        racketModel = new RacketModel(200);
        ballModel = new BallModel();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BallModel getBallModel() {
        return ballModel;
    }

    public RacketModel getRacketModel() {
        return racketModel;
    }

    public BrickModel[][] getBricks() {
        return bricks;
    }

    public int getNbBricks() {
        return nbBricks;
    }

    public void decNbBricks() {
        nbBricks--;
        if (nbBricks == 0) {
            state = STATE_WIN;
        }
    }

    public int getBrickRows() {
        return brickRows;
    }

    public int getBrickCols() {
        return brickCols;
    }

    public void setLastFrame(long lastFrame) {
        this.lastFrame = lastFrame;
    }

    public long getLastFrame() {
        return lastFrame;
    }

    public long getFrameGap() {
        return frameGap;
    }

    public void update() {
        ballModel.update();
        racketModel.update();
    }

    public void resetFlags() {
        ballModel.resetFlags();
        racketModel.resetFlags();
    }

    public void createBricks() {
        // position of bricks are computed from the width of the model space
        // bricks are supposed to be placed every 20 pixels in height. width is dependent on space width.
        bricks = new BrickModel[brickRows][brickCols];
        nbBricks = brickCols*brickRows;

        /* A COMPLETER :
           - creer les modèles des briques, en les positionnant par rapport à with & brickCol
             ATTENTION : laisser un espace vide entre les bords et les brique, par exemple de 50 pixels

           - choisir aléatoirement 5% des briques et leur mettre un type bonus vitesse
           - choisir aléatoirement 10% des briques et leur mettre un type bonus taille
         */

        int brickWidth = (width - 100) / brickCols;
        int brickHeight = 20;
        double brickX = 50;
        double brickY = 50;
        int type = 0;

        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickCols; j++) {
                bricks[i][j] = new BrickModel(0,brickX, brickY);
                brickX += brickWidth;
        }
            brickY += brickHeight;
            brickX = 50;
        }
    }

    public BrickModel getBrickModel(int i, int j) {
        return bricks[i][j];
    }
}
