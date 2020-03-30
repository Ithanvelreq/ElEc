import java.util.LinkedList;

public class Dipole extends Composant {
    public Impedance z;
    private double tension;
    private double courant;

    public Dipole(){

    }


    public Dipole(Impedance a, String s){
        super(s);
        this.z = a;
    }


}
