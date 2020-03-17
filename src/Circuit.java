import javax.swing.*;
import java.util.LinkedList;

public abstract class Circuit extends JFrame {
    LinkedList<Maille> mailles = new LinkedList<>();
    LinkedList<Noeuds> noeuds = new LinkedList<>();
    LinkedList<ItemElement> composants = new LinkedList<>();

    public Circuit(){

    }

    public Circuit(ItemElement[] Icomp){
        for (ItemElement c : Icomp){
            composants.add(c);
        }
    }
}
