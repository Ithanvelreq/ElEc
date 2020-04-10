import java.util.LinkedList;
public class Maille {
    /**
     * Liste des elements inclus dans la maille
     */
    public LinkedList<ItemElement> Icomposants = new LinkedList<>();

    /**
     * Constructeur
     * @param comp Liste des elements inclus dans la maille
     */
    public Maille(ItemElement[] comp) {
        for (ItemElement c : comp) {
            Icomposants.add(c);
        }
    }

}
