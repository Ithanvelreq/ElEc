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


        FenetreMain FenetrePrincipale = new FenetreMain();

        Impedance [][] c = {{new Impedance(3,0), new Impedance(2,0), new Impedance(1,0), new Impedance(1,0)},
                {new Impedance(2,0), new Impedance(1,0), new Impedance(0,0), new Impedance(0,0)},
                {new Impedance(0,0), new Impedance(0,0), new Impedance(2,0), new Impedance(-1,0)},
                {new Impedance(1,0), new Impedance(2,0), new Impedance(1,0), new Impedance(0,0)}};
        Impedance [][] d = {{new Impedance(2,0)}, {new Impedance(9,0)}, {new Impedance(0,0)}, {new Impedance(5,0)}};
        Matrix m = new Matrix(c, d);
        m.resolSys();
        for(int i = 0; i<c.length; i++){
            for(int k = 0; k<c[0].length; k++){
                //if(c[i][k].getRho() != 0){
                    System.out.print(c[i][k]+"    ");
                //}
            }
            System.out.println();
        }

        for(int i = 0; i<d.length; i++){
            System.out.println(d[i][0]);
        }





    }
}
