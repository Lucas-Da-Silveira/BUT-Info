class Match {
    private Club club1;
    private Club club2;

    public Match(Club club1, Club club2) {
        this.club1 = club1;
        this.club2 = club2;
    }

    public void afficher() {
        System.out.println("Match entre " + club1.getNom() + " et " + club2.getNom());
    }
}