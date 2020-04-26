import java.util.LinkedList;

/**
 * Classe qui stocke les differents dipoles presents dans une maille. Cette classe est utile pour resoudre le systeme lineaire d'equations
 */
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
