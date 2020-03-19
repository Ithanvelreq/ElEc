import java.util.LinkedList;

public class Noeud {
    private LinkedList<Fil> sorties;
    private LinkedList<Composant> composants;

    public Noeud(){

    }

    public void addSortie(Fil f){
        sorties.add(f);
        addComposant(f);
    }

    private void addComposant(Fil f){
        composants.add((Composant) f.autreBout(this));
    }
}
