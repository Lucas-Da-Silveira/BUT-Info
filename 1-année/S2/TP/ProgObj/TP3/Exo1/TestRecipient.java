class TestRecipient{
    public static void main(String[] args){

        Recipient r1 = new Recipient();
        Recipient r2 = new Recipient();

        r1.setNom("r1");
        r1.setcapacité(10);
        r1.setcontenent(0);

        r2.setNom("r2");
        r2.setcapacité(15);
        r2.remplir();

        r1.setcontenent(r1.getcontenent() + 5);
        r2.setcontenent(r2.getcontenent() - 5);

        r1.afficher();
        r2.afficher();

        System.out.println("================================================");

        while(r2.getcontenent() > 0 && r1.getcontenent() < r1.getcapacité()){
            r2.setcontenent(r2.getcontenent() - 1);
            r1.setcontenent(r1.getcontenent() + 1);

            r1.afficher();
            r2.afficher();
        }

        System.out.println("================================================");
        
        r1.vider();
        r2.vider();

        Recipient r3 = new Recipient();

        r3.setNom("r3");
        r3.setcapacité(20);

        r1.setcontenent(9);
        r2.setcontenent(5);
        r3.setcontenent(15);

        int max = Math.max(r1.getcontenent(), Math.max(r2.getcontenent(), r3.getcontenent()));
        if(max == r1.getcontenent()){
            System.out.println(r1.getnom() + " est le plus grand");
        }else if(max == r2.getcontenent()){
            System.out.println(r2.getnom() + " est le plus grand");
        }else{
            System.out.println(r3.getnom() + " est le plus grand");
        }

    }
}