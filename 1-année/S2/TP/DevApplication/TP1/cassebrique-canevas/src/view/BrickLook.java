package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.BrickModel;

public class BrickLook {
    private Group body;
    // shapes that constitute the body
    private Rectangle main;
    // dimensions
    private double width; // duplicate from model.getWidth()
    private double height;


    private Group hitBox;
    // shape that constitute the hitBox
    private Rectangle hitRectangle;

    private BrickModel brickModel;

    public BrickLook(BrickModel brickModel, double width, double height) {

        this.brickModel = brickModel;
        this.width = width;
        this.height = height;
        // define the body
        body = new Group();
        main = new Rectangle(1,1,50, 20);
        if (brickModel.getType() == BrickModel.BRICK_NORMAL) {
            main.setFill(Color.RED);
        }
        else if (brickModel.getType() == BrickModel.BRICK_BONUS_WIDTH) {
            if (brickModel.getType() == BrickModel.BRICK_BONUS_SPEED) {
                main.setFill(Color.YELLOW);
            }
            else {
                main.setFill(Color.GREEN);
            }
        }
        else {
            main.setFill(Color.BLUE);
        }
        main.setArcWidth(5);
        main.setArcHeight(5);
        main.setStroke(Color.BLACK);
        main.setStrokeWidth(1);
        body.getChildren().addAll(main);
        // define the hitbox : in the present case, a circle that matches outer boundaries.
        hitBox = new Group();
        hitRectangle = new Rectangle(0,0,50, 20);
        hitRectangle.setFill(Color.TRANSPARENT);
        hitBox.getChildren().addAll(hitRectangle);
    }

    public Group getBody() {
        return body;
    }

    public Group getHitBox() { return hitBox; }

    public void update() {
        if (brickModel.isPositionChanged()) {
            body.setTranslateX(brickModel.getX());
            body.setTranslateY(brickModel.getY());
            hitBox.setTranslateX(brickModel.getX());
            hitBox.setTranslateY(brickModel.getY());
        }
        // normally, there will be a single event that changes the visibility: when brick is hit
        if (brickModel.isVisibleChanged()) {
            main.setVisible(false);
        }
    }
}
