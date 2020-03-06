import java.util.LinkedList;

public class CircuitA extends Circuit {

    public CircuitA(LinkedList<Composant> comp){
        super(comp);
        super.mailles.add(new Maille(comp));
        super.noeuds.add(new Noeuds(super.mailles));
    }
}
