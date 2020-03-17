import java.util.LinkedList;

public class Maille {
    public LinkedList<ItemComposant> Icomposants = new LinkedList<>();

    public Maille(ItemComposant[] comp) {
        for (ItemComposant c : comp) {
            Icomposants.add(c);
        }
    }
}
