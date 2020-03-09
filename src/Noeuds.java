import java.util.LinkedList;

public class Noeuds {

    public Noeuds (Composant a,Composant b, Composant c){
        LinkedList<Composant> compN = new LinkedList<>();
        compN.add(a);
        compN.add(b);
        compN.add(c);
    }

    public Noeuds (Composant a,Composant b,Composant c,Composant d){
        LinkedList<Composant> compN = new LinkedList<>();
        compN.add(a);
        compN.add(b);
        compN.add(c);
        compN.add(d);
    }
}

