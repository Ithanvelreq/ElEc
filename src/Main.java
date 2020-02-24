import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        Bobine L1 = new Bobine(new Impedance (0, 5), "L1");
        Resistance R1 = new Resistance(new Impedance (20, 0), "R1");
        Condensateur C1 = new Condensateur(new Impedance (0, 8), "C1");
        SourceTension e = new SourceTension(5, "U0", 500);

        LinkedList<Composant> composants = new LinkedList<>();
        composants.add(L1);
        composants.add(R1);
        composants.add(C1);
        composants.add(e);

        Circuit circuitA = new CircuitA(composants);

        FenetreMain FenetrePrincipale = new FenetreMain();





    }
}
