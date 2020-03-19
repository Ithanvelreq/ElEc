public class Dipole extends Composant {
    public Impedance z;
    public Fil [] fils = {null, null};
    private double tension;
    private double courant;

    public Dipole(){

    }


    public Dipole(Impedance a, String s){
        super(s);
        this.z = a;
    }

    public void link(Fil f){
        if(fils[0] == null){
            fils[0] = f;
            f.link(this);
        }else if(fils [1] == null){
            fils[1] = f;
            f.link(this);
        }
    }
}
