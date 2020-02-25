public abstract class Dipole extends Composant {
    private Impedance z;
    private double tension;
    private double courant;

    public Dipole(){

    }

    public Dipole(Impedance a, String s){
        super(s);
        this.z = a;
    }
}
