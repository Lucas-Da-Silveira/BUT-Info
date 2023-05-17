package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.RacketModel;

public class RacketLook {
    private Group body;
    // shapes that constitute the body
    private Rectangle main;
    // dimensions
    private double width; // duplicate from model.getWidth()
    private double height;


    private Group hitBox;
    // shape that constitute the hitBox
    private Rectangle hitRectangle;

    private RacketModel racketModel;

    public RacketLook(RacketModel racketModel, double height) {

        this.racketModel = racketModel;
        width = racketModel.getWidth();
        this.height = height;
        // define the body
        body = new Group();
        main = new Rectangle(450,0,50, 10);
        main.setFill(Color.RED);
        main.setArcWidth(10);
        main.setArcHeight(10);
        main.setStroke(Color.BLACK);
        main.setStrokeWidth(1);
        body.getChildren().addAll(main);
        // define the hitbox : in the present case, a circle that matches outer boundaries.
        hitBox = new Group();
        hitRectangle = new Rectangle(0,0,width, height);
        hitRectangle.setFill(Color.TRANSPARENT);
        hitBox.getChildren().addAll(hitRectangle);
    }

    public Group getBody() {
        return body;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setHitBoxPosition(double x, double y) {
        hitBox.setTranslateX(x);
        hitBox.setTranslateY(y);
    }

    public Group getHitBox() { return hitBox; }

    public void update() {
        if (racketModel.isPositionChanged()) {
            body.setTranslateX(racketModel.getX());
            body.setTranslateY(racketModel.getY());
            hitBox.setTranslateX(racketModel.getX());
            hitBox.setTranslateY(racketModel.getY());
        }
        if (racketModel.isWidthChanged()) {
            double newWidth = racketModel.getWidth();
            main.setWidth(newWidth);
            hitRectangle.setWidth(newWidth);
        }
    }
}
