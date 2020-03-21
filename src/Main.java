import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){

        FenetreMain FenetrePrincipale = new FenetreMain();

        Impedance a = new Impedance(55, 2);


        Dipole [] d = {new Dipole(a, "d1"), new Dipole(a, "d2"), new Dipole(a, "d3"), new Dipole(a, "d4"), new Dipole(a, "d5"), new Dipole(a, "d6")};
        Noeud [] n = {new Noeud("n1"), new Noeud("n2")};
        d[0].link(n[0]);
        d[1].link(n[0]);
        d[1].link(d[2]);
        d[2].link(n[1]);
        d[3].link(n[1]);
        d[3].link(d[0]);
        d[4].link(n[0]);
        d[4].link(n[1]);

        LinkedList<Dipole> w = new LinkedList<>();
        for(int i = d.length - 1; i>=0; i--){
            w.add(d[i]);
        }

        w.sort(Composant::compareTo);

    }
}
