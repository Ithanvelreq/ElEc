import java.util.LinkedList;

public class Maille {
    public LinkedList<ItemElement> Icomposants = new LinkedList<>();

    public Maille(ItemElement[] comp) {
        for (ItemElement c : comp) {
            Icomposants.add(c);
        }
    }
}
