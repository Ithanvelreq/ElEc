import javax.swing.*;
import java.util.LinkedList;
import java.util.Set;

public abstract class Circuit extends JFrame {
    LinkedList<Set> mailles = new LinkedList<>();
    LinkedList<Noeud> noeuds = new LinkedList<>();
    LinkedList<ItemElement> composants = new LinkedList<>();

    public Circuit(){

    }

    public Circuit(ItemElement[] Icomp){
        for (ItemElement c : Icomp){
            composants.add(c);
        }
    }

    public void findMailles(){

    }
}
