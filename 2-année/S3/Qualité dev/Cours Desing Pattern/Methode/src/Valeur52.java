public enum Valeur52 {

    DEUX (0),
    TROIS(1),
    QUATRE(2),
    CINQ(3),
    SIX(4),
    SEPT(5),
    HUIT(6),
    NEUF(7),
    DIX(8),
    VALET(9),
    DAME(10),
    ROI(11),
    AS(12);

    private int force;
    Valeur52(int force){
        this.force = force;
    }

    public int getForce() {
        return force;
    }
}