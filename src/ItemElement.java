import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Classe mere des elements utilises pour l'affichage et le traitement de donnees dans l'IHM
 */
public abstract class ItemElement extends JPanel {

    /**
     * Methode qui permet de dessiner le composant choisi
     * @param visibilite True s'il faut dessiner ou non l'element False sinon
     * @param vertical True si le dessin est vertical False sinon
     */
    public abstract void dessine(boolean visibilite, boolean vertical);
}
