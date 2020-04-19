import java.util.Arrays;
import java.util.LinkedList;

public abstract class Circuit{
    LinkedList<Maille> mailles = new LinkedList<>();
    LinkedList<ItemElement> composants = new LinkedList<>();
    public double frequence;
    public double amplitude;
    /**
     * matrice comportant les coefficients du membres gauche des différentes équations
     */
    public Impedance[][] m1 = new Impedance[7][7];
    /**
     * matrice comportant les coefficients du membre droit des différentes éuqations
     */
    public Impedance[][] m2 = new Impedance[7][1];

    /**
     *Constructeur
     * @param Icomp Composants presents dans le circuit
     */
    public Circuit(ItemElement[] Icomp){
        composants.addAll(Arrays.asList(Icomp));
    }

    /**
     * méthode qui renvoie le tableau de correspondance des solutions
     * @return tableau de correspondance
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
     * méthode qui permet de résoudre toutes les inconnues
     * @return tableau de solutions
     */
    public Impedance [] solutions(){
        Matrix m = new Matrix(m1, m2);
        m.resolSys();
        Impedance [] w = m.solutions();
        return w;
    }

}
