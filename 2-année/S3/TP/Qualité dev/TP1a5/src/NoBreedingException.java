public class NoBreedingException extends Exception{

    protected Humain source;

    public NoBreedingException(Humain h, String type, String message){
        super("Rencontre" + type + "impossible entre " + h.getNom() + " : " + message);
        source = h;
    }

}
