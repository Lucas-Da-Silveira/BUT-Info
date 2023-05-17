package model;

public class RacketModel {

    public final static double baseSpeed = 8;
    private double speedX;
    /* in case of collision on wall, must store the current speed to reset it when moving once again
       NB: prevSpeedX will be equals to 0 only when the game begins
     */
    private double prevSpeedX;
    private double x;
    private double y;
    private double initialWidth; // the initial width of the racket
    private double width; // the current width of the racket
    // flags for different events
    private boolean positionChanged;
    private boolean widthChanged;
    // counter for events
    private int counterWidth;
    private int counterSpeed;

    public RacketModel(double x, double y, double width) {
        this.x = x;
        this.y = y;
        this.width = width;
        initialWidth = width;
        speedX = 0.0;
        prevSpeedX = 0.0;
        positionChanged = false;
        widthChanged = false;
        counterWidth = 0;
        counterSpeed = 0;
    }
    public RacketModel(double width) {
        this(0.0 , 0.0, width);
    }

    public void reset() {
        width = initialWidth;
        speedX = 0.0;
        prevSpeedX = 0.0;
        positionChanged = false;
        widthChanged = true;
        counterWidth = 0;
        counterSpeed = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public boolean isPositionChanged() {
        return positionChanged;
    }

    public double getWidth() {
        return width;
    }

    /*
    NB: setting width may leads to an overlap on boundaries walls
    thus, the controller which set the size when there is a bonus size must check
    that it there is effectively an overlap, the racket must be moved to left to remove
    the overlap.
     */
    public void setWidth(double width) {
        if (this.width != width) {
            this.width = width;
            counterWidth = 500;
            widthChanged = true;
        }
    }

    public boolean isWidthChanged() {
        return widthChanged;
    }

    public void resetFlags() {
        positionChanged = false;
        widthChanged = false;
    }

    public void put(double x, double y) {
        if ((this.x != x) || (this.y != y)) {
            positionChanged = true;
            this.x = x;
            this.y = y;
        }
    }

    public void move() {
        if (speedX != 0.0)  {
            positionChanged = true;
            x += speedX;
        }
    }

    public double[] getNextPosition() {
        double[] tab = {x+speedX, y};
        return tab;
    }

    public void stop() {
        prevSpeedX = speedX;
        speedX = 0.0;
    }

    public void goLeft() {
        // if already going to the left, do nothing
        if (speedX < 0.0) return;
        // if going to the right, just reverse the speed
        else if (speedX > 0.0) {
            speedX = -speedX;
        }
        // current speed is 0
        else {
            // if racket is already blocked at left => prevSpeedX < 0. Thus, do nothing
            // else go left either at base speed
            if (prevSpeedX == 0.0) {
                speedX = -baseSpeed;
            }
            // blocked at right
            else if (prevSpeedX > 0.0) {
                speedX = -prevSpeedX;
            }
        }
    }

    public void goRight() {
        // if already going to the right, do nothing
        if (speedX > 0.0) return;
            // if going to the left, just reverse the speed
        else if (speedX < 0.0) {
            speedX = -speedX;
        }
        // current speed is 0
        else {
            // if racket is already blocked at right => prevSpeedX > 0. Thus, do nothing
            // else go left either at base speed
            if (prevSpeedX == 0.0) {
                speedX = baseSpeed;
            }
            // blocked at left
            else if (prevSpeedX < 0.0) {
                speedX = -prevSpeedX;
            }
        }
    }

    public void increaseSpeed(double inc) {
        if (speedX > 0) {
            speedX += inc;
        }
        else if (speedX <0 ){
            speedX -= inc;
        }
        else {
            if (prevSpeedX < 0.0) {
                prevSpeedX -= inc;
            }
            else if (prevSpeedX > 0.0) {
                prevSpeedX += inc;
            }
        }
        counterSpeed = 500;
    }

    public void update() {
        move();
        if (counterSpeed > 0) {
            counterSpeed--;
            if (counterSpeed == 0) {
                System.out.println("back to original speed");
                if (speedX > 0.0) {
                    speedX = baseSpeed;
                }
                else if (speedX < 0.0) {
                    speedX = -baseSpeed;
                }
                else {
                    speedX = 0.0;
                }
            }
        }
        if (counterWidth > 0) {
            counterWidth--;
            if (counterWidth == 0) {
                System.out.println("back to original size");
                width = initialWidth;
                widthChanged = true;
            }
        }
    }

    public void setX(double v) {
        if (x != v) {
            positionChanged = true;
            x = v;
        }
    }
}
