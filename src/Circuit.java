import java.util.Arrays;
import java.util.LinkedList;

/**
 * Classe mere des classes Circuit qui correspondent a chaque circuit affiche sur la fenetre principale
 */
public abstract class Circuit{
    /**
     * Liste de mailles presentes dans le circuit
     */
    LinkedList<Maille> mailles = new LinkedList<>();
    /**
     * Liste de composants presents dans le circuit
     */
    LinkedList<ItemElement> composants = new LinkedList<>();
    /**
     * Frequence imposee par le generateur du circuit
     */
    public double frequence;
    /**
     * Amplitude imposee par le generateur du circuit
     */
    public double amplitude;
    /**
     * Matrice comportant les coefficients du membres gauche des differentes equations
     */
    public Impedance[][] m1 = new Impedance[7][7];
    /**
     * Matrice comportant les coefficients du membre droit des differentes equations
     */
    public Impedance[][] m2 = new Impedance[7][1];

    /**
     * Constructeur
     * @param Icomp Composants presents dans le circuit
     */
    public Circuit(ItemElement[] Icomp){
        composants.addAll(Arrays.asList(Icomp));
    }

    /**
     * Methode qui renvoie le tableau de correspondance des solutions
     * @return Tableau de correspondance
     */
    public String [] inconnues (){
        String[] i = new String[7];
        i[0]="U";
        i[1]="I1";
        i[2]="I2";
        i[3]="I3";
        i[4]="U1";
        i[5]="U2";
        i[6]="U3";
        return i;
    }

    /**
     * Methode qui permet de resoudre toutes les inconnues
     * @return Tableau de solutions
     */
    public Impedance [] solutions(){
        Matrix m = new Matrix(m1, m2);
        m.resolSys();
        Impedance [] w = m.solutions();
        return w;
    }

}
