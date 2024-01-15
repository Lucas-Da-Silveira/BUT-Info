public enum Valeur32 {

    SEPT(0),
    HUIT(1),
    NEUF(2),
    DIX(3),
    VALET(4),
    DAME(5),
    ROI(6),
    AS(7);

    private int force;
    Valeur32(int force){
        this.force = force;
    }

    public int getForce() {
        return force;
    }
}