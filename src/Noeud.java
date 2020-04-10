import java.util.LinkedList;

public class Noeud{
    /**
     * Liste des composants branches sur le noeud
     */
    public LinkedList<ItemElement> Ivoisins;

    /**
     * Constructeur
     * @param CompV composants branches sur le noeud
     */
    public Noeud(LinkedList<ItemElement> CompV){
        for (ItemElement c : CompV){
            Ivoisins.add(c);
        }
    }

}
