public class NoBreedingException extends Exception{

    protected Humain[] source;

    public NoBreedingException(Humain h1, Humain h2){

        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }

    public Humain[] getHumain(){
        return source;
    }

}
