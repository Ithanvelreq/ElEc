import java.util.LinkedList;

public class Noeud extends Composant{
    public LinkedList<ItemElement> Ivoisins;

    //public Noeud(String s){super(s);}

    public Noeud(LinkedList<ItemElement> CompV){
        for (ItemElement c : CompV){
            Ivoisins.add(c);
        }
    }

}
