import java.util.LinkedList;

public class Main {
    public static void main(String[] args){

        FenetreMain FenetrePrincipale = new FenetreMain();

        Impedance a = new Impedance(55, 2);


        Dipole [] d = {new Dipole(a, "d1"), new Dipole(a, "d2"), new Dipole(a, "d3"), new Dipole(a, "d4"), new Dipole(a, "d5"), new Dipole(a, "d6")};
        Fil [] f = {new Fil(), new Fil(), new Fil(), new Fil(), new Fil(), new Fil()};
        d[0].link(f[0]);
        d[0].link((f[5]));
        for(int i =1; i<d.length; i++){
            d[i].link(f[(i-1)%f.length]);
            d[i].link((f[i]));
        }


    }
}
