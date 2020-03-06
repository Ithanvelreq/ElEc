import javax.swing.*;
import java.util.LinkedList;

public abstract class Circuit extends JFrame {
    LinkedList<Maille> mailles = new LinkedList<>();
    LinkedList<Noeuds> noeuds = new LinkedList<>();
    LinkedList<Composant> composants = new LinkedList<>();

    public Circuit(){

    }

    public Circuit(LinkedList<Composant> comp){
        for (Composant c : comp){
            composants.add(c);
        }
    }
}
