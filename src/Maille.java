import java.util.LinkedList;

public class Maille {
    private LinkedList<Composant> composants = new LinkedList<>();

    public Maille(LinkedList<Composant> comp) {
        for (Composant c : comp) {
            composants.add(c);
        }
    }
}
