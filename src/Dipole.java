package elec;
/**
 * Composant passif du circuit
 */
public class Dipole{
    /**
     * Impedance du Dipole
     */
    public Impedance z;
    /**
     * Nom du Dipole
     */
    public String nom;

    /**
     * Constructeur
     * @param a Impedance du dipole
     * @param s Nom du dipole
     */
    public Dipole(Impedance a, String s){
        nom = s;
        this.z = a;
    }
}
