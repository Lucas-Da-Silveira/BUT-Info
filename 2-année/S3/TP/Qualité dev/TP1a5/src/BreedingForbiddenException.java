class BreedingForbiddenException extends Exception {

    protected Humain[] source;

    public BreedingForbiddenException(Humain h1, Humain h2) {
        super("\u001B[33mCONTRE NATURE (NO HOMO): "+h1.getNom()+" et "+h2.getNom()+" sont de meme sexe");
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }

    public Humain[] getHumain() {
        return source;
    }
}