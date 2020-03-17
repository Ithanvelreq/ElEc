import javax.swing.*;
import java.util.LinkedList;

public abstract class Circuit extends JFrame {
    LinkedList<Maille> mailles = new LinkedList<>();
    LinkedList<Noeuds> noeuds = new LinkedList<>();
    LinkedList<ItemComposant> composants = new LinkedList<>();

    public Circuit(){

    }

    public Circuit(ItemComposant[] Icomp){
        for (ItemComposant c : Icomp){
            composants.add(c);
        }
    }
}
