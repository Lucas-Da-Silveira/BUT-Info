package model;

public class BallModel {

    public static final double baseSpeed = 8;
    private double speed; // the absolute speed in 1D
    private double speedX;
    private double speedY;
    private double angle; // for convenience, not to recompute it from speedX and speedY;
    // position in absolute space (NB: scene space = absolute space for convenience)
    private double x;
    private double y;
    // flags for different events
    private boolean positionChanged;
    private boolean collisionOccurred; // true when a collision just occurred
    private boolean endCollisionOccurred; // true when counterCollision comes back to 0
    // counter to keep the state collisionOccurred during 10 frames
    private int counterCollision;

    public BallModel(double x, double y, double angle, double speed) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        updateXYSpeed();
        positionChanged = false;
        collisionOccurred = false;
        counterCollision = 0;
    }
    public BallModel() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public void reset() {
        angle = 45;
        speed = baseSpeed;
        updateXYSpeed();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isPositionChanged() {
        return positionChanged;
    }

    public boolean isCollisionOccurred() {
        return collisionOccurred;
    }

    public void setCollisionOccurred(boolean collisionOccurred) {
        if (this.collisionOccurred != collisionOccurred) {
            this.collisionOccurred = collisionOccurred;
            counterCollision = 10;
        }
    }

    public boolean isEndCollisionOccurred() {
        return endCollisionOccurred;
    }

    public void resetFlags() {
        positionChanged = false;
        collisionOccurred = false;
        endCollisionOccurred = false;
    }

    public void put(double x, double y) {
        if ((this.x != x) || (this.y != y)) {
            positionChanged = true;
            this.x = x;
            this.y = y;
        }
    }

    public void move() {
        if ((speedX != 0.0) || (speedY != 0.0)) {
            positionChanged = true;
            x += speedX;
            y += speedY;
        }
    }

    public double[] getNextPosition() {
        double[] tab = {x+speedX, y+speedY};
        return tab;
    }

    public void stop() {
        speedX = 0.0;
        speedY = 0.0;
    }

    public void push(double angle) {
        if (speed == 0.0) speed = baseSpeed;
        this.angle = angle;
        updateXYSpeed();
    }

    private void updateXYSpeed() {
        speedX = Math.cos(2.0*Math.PI*angle/360.0)*speed;
        speedY = -Math.sin(2.0*Math.PI*angle/360.0)*speed;
    }

    public void increaseSpeed(double inc) {
        speed += inc;
        if (speed > 50) {
            speed = 50;
        }
        updateXYSpeed();
    }
    public void decreaseSpeed(double inc) {
        speed -= inc;
        if (speed < 0) {
            speed = 0;
        }
        updateXYSpeed();
    }

    public void update() {
        move();
        if ( counterCollision > 0) {
            counterCollision--;
            if ( counterCollision == 0) {
                endCollisionOccurred = true;
            }
        }
    }

    public void reverseXSpeed() {
        if (angle <= 180) {
            push(180-angle);
        }
        else {
            push(540-angle);
        }
    }
    public void reverseYSpeed() {
        push(360-angle);
    }

    /*
       overloaded method, used when there is a collision with the racket
       Must take into account the speed of the racket to determine the rebound angle.
     */
    public void reverseYSpeed(double racketSpeed) {
        double newAngle = 360.0 - angle;

        /* A COMPLETER :
           - modifier newAngle en fonction de la vitesse de la raquette, et de la
           direction de déplacement de la balle & raquette

         */

        push(newAngle);
    }
}
