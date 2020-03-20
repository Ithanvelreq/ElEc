import java.util.LinkedList;

public class Main {
    public static void main(String[] args){

        FenetreMain FenetrePrincipale = new FenetreMain();

        Impedance a = new Impedance(55, 2);


        Dipole [] d = {new Dipole(a, "d1"), new Dipole(a, "d2"), new Dipole(a, "d3"), new Dipole(a, "d4"), new Dipole(a, "d5"), new Dipole(a, "d6")};
        Fil [] f = {new Fil(), new Fil(), new Fil(), new Fil(), new Fil(), new Fil(), new Fil(), new Fil()};
        Noeud [] n = {new Noeud(), new Noeud(), new Noeud(), new Noeud(), new Noeud()};
        d[0].link(f[0]);
        n[0].link(f[0]);
        n[0].link(f[1]);
        n[0].link(f[6]);
        d[1].link(f[1]);
        d[1].link(f[2]);
        d[2].link(f[2]);
        d[2].link(f[3]);
        n[1].link(f[3]);
        n[1].link(f[4]);
        n[1].link(f[7]);
        d[3].link(f[4]);
        d[3].link(f[5]);
        d[0].link((f[5]));
        d[4].link(f[6]);
        d[4].link(f[7]);
        n[0].addComposant();
        n[1].addComposant();

    }
}
