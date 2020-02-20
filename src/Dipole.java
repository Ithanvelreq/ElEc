public abstract class Dipole extends Composant {
    private Complexe z;
    private double tension;
    private double courrant;

    public Dipole(){

    }

    public Dipole(Complexe a, String s){
        super(s);
        this.z = a;
    }
}
