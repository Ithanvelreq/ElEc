import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class ItemElement extends JPanel {

    /**
     * méthode qui permet de dessiner la composant choisi
     * @param visibilite : boolean s'il faut dessiner ou non l'élément
     * @param vertical : boolean sur l'emplacement
     */
    public abstract void dessine(boolean visibilite, boolean vertical);
}
