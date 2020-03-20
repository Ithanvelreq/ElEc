import java.util.LinkedList;

public class Noeud {
    private LinkedList<Fil> sorties = new LinkedList<>();
    private LinkedList<Composant> composants = new LinkedList<>();

    public Noeud(){

    }

    public void link(Fil f){
        sorties.add(f);
        f.link(this);
    }

    public void addComposant(){
        for(Fil f : sorties){
            composants.add((Composant) f.autreBout(this));
        }
    }
}
