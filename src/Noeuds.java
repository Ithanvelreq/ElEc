import java.util.LinkedList;

public class Noeuds {
    private LinkedList<Maille> mailles = new LinkedList<>();

    public Noeuds(LinkedList<Maille> mail){
        for (Maille m : mail){
            this.mailles.add(m);
        }
    }
}
