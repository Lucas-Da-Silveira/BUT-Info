class RunReveil{

    public static void main(String[] args){

        Reveil r1 = new Reveil();
        Reveil r2 = new Reveil();

        r1.setHorraireCourant(12, 58, 58);
        System.out.println(r1.heureCourante + ":" + r1.minuteCourante + ":" + r1.secondeCourante);
        r1.incrementer();
        System.out.println(r1.heureCourante + ":" + r1.minuteCourante + ":" + r1.secondeCourante);

        
        r2.setHorraireCourant(12, 58, 59);
        r1.comparerA(r2);
        r1.differenceAvec(r2);
        System.out.println(r1.heureCourante + ":" + r1.minuteCourante + ":" + r1.secondeCourante);
        r1.incrementer();
        System.out.println(r1.heureCourante + ":" + r1.minuteCourante + ":" + r1.secondeCourante);
        r1.comparerA(r2);
        r1.differenceAvec(r2);

        r1.setHorraireAlarme(6, 30, 0);
        r1.allumerAlarme();
        //r1.eteindreAlarme();
        r1.controlerAlarme();
        r1.setHorraireCourant(6, 28, 30);

        for (int i = 0 ; i <151; i++ ){
            r1.incrementer();
            r1.controlerAlarme();
        }

    }
}