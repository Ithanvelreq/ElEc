import java.util.LinkedList;

public class Noeud extends Composant{
    public LinkedList<ItemElement> Ivoisins;

    //public Noeud(String s){super(s);}

    public Noeud(ItemElement a, ItemElement b, ItemElement c, ItemElement d){
        Ivoisins.add(a);
        Ivoisins.add(b);
        Ivoisins.add(c);
        Ivoisins.add(d);

    }

    public Noeud(ItemElement a, ItemElement b, ItemElement c) {
        Ivoisins.add(a);
        Ivoisins.add(b);
        Ivoisins.add(c);

    }

}
