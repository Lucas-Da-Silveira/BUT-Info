class BreedingForbiddenException extends Exception {

    protected Humain[] source;

    public BreedingForbiddenException(Humain h1, Humain h2) {
        if (h1.isHomme() && h2.isHomme()){
            System.out.println("Les hommes ne peuvent pas se reproduire entre eux");
        }
        if (h1.isFemme() && h2.isFemme()){
            System.out.println("Les femmes ne peuvent pas se reproduire entre elles");
        }
        if (h1.getPoids() > 100 || h2.getPoids() > 100){
            System.out.println("Les personnes de plus de 100kg ne peuvent pas se reproduire");
        }
        if (h1.getAge() < 15 || h2.getAge() < 15){
            System.out.println("Les personnes de moins de 15 ans ne peuvent pas se reproduire");
        }
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }

    public Humain[] getHumain() {
        return source;
    }
}