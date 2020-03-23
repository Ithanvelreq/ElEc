import java.util.LinkedList;
import java.util.Set;
public class Maille {

    public LinkedList<ItemElement> Icomposants;

    public Maille(ItemElement[] comp) {
        for (ItemElement c : comp) {
            Icomposants.add(c);
        }
    }
}
