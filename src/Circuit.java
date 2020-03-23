import javax.swing.*;
import java.util.LinkedList;
import java.util.Set;

public abstract class Circuit extends JFrame {
    LinkedList<Maille> mailles = new LinkedList<>();
    LinkedList<Noeud> noeuds = new LinkedList<>();
    LinkedList<ItemElement> composants = new LinkedList<>();

    public Circuit(){

    }

    public Circuit(ItemElement[] Icomp){

        //a modifier
        /*for (ItemElement c : Icomp){
            composants.add(c);
        }*/
    }

    public void findMailles(){
        LinkedList<Composant> cdv = new LinkedList<>();
        LinkedList<Noeud> ndv = new LinkedList<>();
        ndv.add(noeuds.get(0));

    }

}
