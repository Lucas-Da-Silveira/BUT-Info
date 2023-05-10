package control;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import model.BrickModel;
import model.Model;
import view.BallLook;
import view.BrickLook;
import view.RacketLook;
import view.View;

import java.util.Calendar;
import java.util.Random;

public class Controller {

    private static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected Model model;
    protected View view;
    protected ControllerKeyboard keyboard;
    protected ControllerAnimation animator;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        keyboard = new ControllerKeyboard(model,view,this);
        animator = new ControllerAnimation(model, view, this);

    }

    public void startAnimation() {
        animator.start();
    }
    public void stopAnimation() {
        animator.stop();
    }

    public void initGame() {
        // set the racket and ball in the scene, from their visual sizes
        model.setState(Model.STATE_PAUSE);
        model.getRacketModel().reset();
        model.getBallModel().reset();

        /* A COMPLETER :
           - positionner la raquette
           - positionner la balle
           - créer le modèle des briques
           - créer la vue des briques
         */
        // positionner la raquette
        double racketX = (view.getWidth() - view.getRacketLook().getWidth()) / 2;
        double racketY = (view.getHeight() - view.getRacketLook().getHeight() - 10);
        model.getRacketModel().getNextPosition()[0] = racketX;
        model.getRacketModel().getNextPosition()[1] = racketY;

        // positionner la balle

        double ballX = (view.getWidth() - view.getBallLook().getRadius()) / 2;
        double ballY = (view.getHeight() - view.getBallLook().getRadius() - 10);
        model.getBallModel().getNextPosition()[0] = ballX;
        model.getBallModel().getNextPosition()[1] = ballY;

        // créer le modèle des briques
        model.createBricks();
        // créer la vue des briques
        view.createBricks();

        view.hideResultMessage();
        view.update();
    }

    public void step() {
        checkCollisions();
        model.update();
        view.update();
        model.resetFlags();
        // check state
        if (model.getState() == Model.STATE_LOST) {
            stopGame();
            view.showLostMessage();
        }
        else if (model.getState() == Model.STATE_WIN) {
            stopGame();
            view.showWinMessage();
        }
    }

    public void togglePauseGame() {
        if (model.getState() == Model.STATE_PAUSE) {
            model.setState(Model.STATE_PLAY);
            startAnimation();
        }
        else if (model.getState() == Model.STATE_PLAY ) {
            model.setState(Model.STATE_PAUSE);
            stopAnimation();

        }
        // if win/lost, restart the game
        else {
            initGame();
        }

    }

    private void stopGame() {
        stopAnimation();
        model.setState(Model.STATE_LOST);
    }

    public void pushRacketLeft() {
        model.getRacketModel().goLeft();
    }
    public void pushRacketRight() {
        model.getRacketModel().goRight();
    }

    public void checkCollisions() {
        // set hitbox to their next positions
        double[] nextPosRacket = model.getRacketModel().getNextPosition();
        view.getRacketLook().setHitBoxPosition(nextPosRacket[0], nextPosRacket[1]);
        double[] nextPosBall = model.getBallModel().getNextPosition();
        view.getBallLook().setHitBoxPosition(nextPosBall[0], nextPosBall[1]);
        // now check collisions
        checkRacketCollisions();
        checkBallCollisions();
    }

    public void checkRacketCollisions() {
        RacketLook look = view.getRacketLook();
        if ( (checkCollisionGroupWithShape(look.getHitBox(), view.getWestWall())) ||
                (checkCollisionGroupWithShape(look.getHitBox(), view.getEastWall())) ) {
            model.getRacketModel().stop();
        }
    }

    public void checkBallCollisions() {
        BallLook look = view.getBallLook();
        // check collisions between ball & walls & racket:

        /* PARTIE 1, A COMPLETER :

          - si collision avec le mur sud : changer l'état du model en "perdu"
          - si collision avec le mur nord, inverser la vitesse en X
          - si collision avec le mur/est/ouest, inverser la vitesse en Y
          - si collision avec la raquette, inverser la vitesse en Y, en passant la vitesse de la raquette en paramètre.
         */

        if (checkCollisionGroupWithShape(look.getHitBox(), view.getSouthWall())) {
            model.setState(Model.STATE_LOST);
        }
        if (checkCollisionGroupWithShape(look.getHitBox(), view.getNorthWall())) {
            model.getBallModel().reverseXSpeed();
        }
        if (checkCollisionGroupWithShape(look.getHitBox(), view.getEastWall())) {
            model.getBallModel().reverseYSpeed();
        }
        if (checkCollisionGroupWithShape(look.getHitBox(), view.getWestWall())) {
            model.getBallModel().reverseYSpeed();
        }
        if (checkCollisionGroupWithShape(look.getHitBox(), view.getRacketLook().getBody())){
            model.getBallModel().reverseYSpeed();
        }

        BrickLook[][] bricks = view.getBricks();
        for(int i=0;i<model.getBrickRows();i++) {
            for(int j=0;j<model.getBrickCols();j++) {
                BrickModel brickModel = model.getBricks()[i][j];
                /* A COMPLETER :
                - si la brique est visible :
                   - vérifier la collision de la balle avec la brique (cf. checkCollisionBallWithBrick() )
                   - si collision avec les côtés de la brique :
                      - inverser vitesse en X,
                      - rendre la brique invisible.
                      - décrémenter le nombre de brique visibles (cf. decNbBricks() )
                   - sinon si collision avec le haut/bas de la brique :
                      - inverser vitesse en Y,
                      - rendre la brique invisible.
                      - décrémenter le nombre de brique visibles (cf. decNbBricks() )
                    - si collision :
                       - si brique avec bonus largeur :
                          - augmenter taille raquette
                          - vérifier si cette nouvelle taille ne fait aps dépasser les bords
                          - si c'est le cas, repositionner la raquette pour qu'elle touche le bord
                       - si brique avec bonus vitesse :
                          - augmenter vitesse raquette
                 */

            }
        }
    }

    /* In order to know how to rebound, we must decide on which side the ball hits a brick
       For that, we use the geometry of the intersection. If it is mainly vertical, we consider
       the collision occurs with left/right sides, and if it is horizontal, with top/bottom sides.
     */
    private int checkCollisionBallWithBrick(BrickLook brick) {
        // we know that there is a single child in both hitbox groups, so we can get the single hitbox shape directly
        Shape hitBoxBall = (Shape)(view.getBallLook().getHitBox().getChildren().get(0));
        Shape hitBoxBrick = (Shape)(brick.getHitBox().getChildren().get(0));
        Shape inter = Shape.intersect(hitBoxBall, hitBoxBrick);
        Bounds b = inter.getBoundsInParent();
        if (b.getWidth() != -1) {
            // bounds higher than large => collision on left/right sides
            if (b.getWidth() < b.getHeight()) return 0;
                // bounds larger that high => collision on top/bottom sides
            else if (b.getWidth() >= b.getHeight()) return 1;
        }
        return -1;
    }

    /*
      Helpers methods to detect collisions between:
         - hitbox group with a single Shape
         - 2 hitbox groups
     */

    private boolean checkCollisionGroupWithShape(Group group, Shape shape) {
        for(Node node : group.getChildren()) {
            Shape s = (Shape)node;
            Shape inter = Shape.intersect(s, shape);
            Bounds b = inter.getBoundsInParent();
            if (b.getWidth() != -1) return true;
        }
        return false;
    }

    private boolean checkCollisionGroupWithGroup(Group group1, Group group2) {
        for(Node node1 : group1.getChildren()) {
            Shape s1 = (Shape)node1;
            for(Node node2 : group2.getChildren()) {
                Shape s2 = (Shape)node2;
                Shape inter = Shape.intersect(s1, s2);
                Bounds b = inter.getBoundsInParent();
                if (b.getWidth() != -1) return true;
            }
        }
        return false;
    }
}
