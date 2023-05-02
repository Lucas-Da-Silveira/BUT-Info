package view;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.BallModel;

public class BallLook {
    private Group body;
    // shapes that constitute the body
    private Circle outer;
    private Circle inner;
    private double radius;

    private Group hitBox;
    // shape that constitute the hitBox
    private Circle hitCircle;

    private BallModel ballModel;

    public BallLook(BallModel ballModel, double radius) {

        this.ballModel = ballModel;
        // define the body
        body = new Group();
        this.radius = radius;
        outer = new Circle();
        outer.setRadius(radius);
        outer.setFill(Color.BLACK);
        outer.setCenterX(radius);
        outer.setCenterY(radius);
        inner = new Circle();
        inner.setRadius(5);
        inner.setFill(Color.WHITE);
        inner.setCenterX(radius);
        inner.setCenterY(radius);
        body.getChildren().addAll(outer, inner);
        // define the hitbox : in the present case, a circle that matches outer boundaries.
        hitBox = new Group();
        hitCircle = new Circle();
        hitCircle.setRadius(radius);
        hitCircle.setFill(Color.TRANSPARENT);
        hitCircle.setCenterX(radius);
        hitCircle.setCenterY(radius);
        hitBox.getChildren().addAll(hitCircle);
    }

    public Group getBody() {
        return body;
    }

    public double getRadius() {
        return radius;
    }

    public void setHitBoxPosition(double x, double y) {
        hitBox.setTranslateX(x);
        hitBox.setTranslateY(y);
    }

    public Group getHitBox() { return hitBox; }

    public void update() {
        if (ballModel.isPositionChanged()) {
            body.setTranslateX(ballModel.getX());
            body.setTranslateY(ballModel.getY());
            hitBox.setTranslateX(ballModel.getX());
            hitBox.setTranslateY(ballModel.getY());
        }
        if (ballModel.isCollisionOccurred()) {
            outer.setFill(Color.RED);
        }
        if (ballModel.isEndCollisionOccurred()) {
            outer.setFill(Color.BLACK);
        }
    }
}
