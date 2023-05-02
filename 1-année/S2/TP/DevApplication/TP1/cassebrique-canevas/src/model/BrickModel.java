package model;

public class BrickModel {

    // values for brick types
    public final static int BRICK_NORMAL = 0;
    public final static int BRICK_BONUS_SPEED = 1;
    public final static int BRICK_BONUS_WIDTH = 2;

    // position in absolute space (NB: scene space = absolute space for convenience)
    private double x;
    private double y;
    // type of brick
    private int type;
    // visibility
    private boolean visible; // if hit => not visible and thus, not taken into account in collisions.
    // flags for different events
    private boolean positionChanged; // if brick does not move, only usefull for the initial placement
    private boolean visibleChanged;

    public BrickModel(int type, double x, double y) {
        if ((type < 0) || (type > BRICK_BONUS_WIDTH)) {
            this.type = BRICK_NORMAL;
        }
        else {
            this.type = type;
        }
        this.x = x;
        this.y = y;
        positionChanged = true;
        visible = true;
    }

    public BrickModel(int type) {
        this(type, 0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (this.visible != visible) {
            this.visible = visible;
            visibleChanged = true;
        }
    }

    public boolean isPositionChanged() {
        return positionChanged;
    }

    public boolean isVisibleChanged() {
        return visibleChanged;
    }

    public void setVisibleChanged(boolean visibleChanged) {
        this.visibleChanged = visibleChanged;
    }

    public void put(double x, double y) {
        if ((this.x != x) || (this.y != y)) {
            positionChanged = true;
            this.x = x;
            this.y = y;
        }
    }
    public void resetFlags() {
        positionChanged = false;
        visibleChanged = false;
    }

    public void udpate() {
        /*
         since the only event is hiding a collided brick, there is nothing to update
         at each frame. The controller will set itself the visibleChanged flag when
         the brick is hit
         */

    }
}
