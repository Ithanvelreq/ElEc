import java.util.LinkedList;

public class Noeuds {

    public Noeuds (ItemComposant a,ItemComposant b, ItemComposant c){
        LinkedList<ItemComposant> compN =  new LinkedList<>();
        compN.add(a);
        compN.add(b);
        compN.add(c);
    }

    public Noeuds (ItemComposant a,ItemComposant b,ItemComposant c,ItemComposant d){
        LinkedList<ItemComposant> compN = new LinkedList<>();
        compN.add(a);
        compN.add(b);
        compN.add(c);
        compN.add(d);
    }
}

