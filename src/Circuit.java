import java.util.Arrays;
import java.util.LinkedList;

public abstract class Circuit{
    LinkedList<Maille> mailles = new LinkedList<>();
    LinkedList<Noeud> noeuds = new LinkedList<>();
    LinkedList<ItemElement> composants = new LinkedList<>();
    public double frequence;
    public double amplitude;
    public Impedance[][] m1 = new Impedance[7][7];
    public Impedance[][] m2 = new Impedance[7][1];

    /**
     *Constructeur
     * @param Icomp Composants presents dans le circuit
     */
    public Circuit(ItemElement[] Icomp){
        composants.addAll(Arrays.asList(Icomp));
    }

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

    public Impedance [] solutions(){
        Matrix m = new Matrix(m1, m2);
        m.resolSys();
        Impedance [] w = m.solutions();
        return w;
    }

}
