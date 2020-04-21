package ElEc;
import javax.swing.*;
import java.awt.*;

/**
 * Classe utilisee pour tracer le circuit sur la fenetre d'affichage
 */
public class Trace_Circuit extends JPanel{

    //attributs
    /**
     * Indique le numero du circuit a dessiner
     */
    int numCircuit;
    /**
     * Hauteur du JPanel ou dessiner
     */
    int hauteur;
    /**
     * Largeur du JPanel ou dessiner
     */
    int largeur;

    /**
     * Constructeur du JPanel contenant le dessin du circiut
     * @param i Numero du circuit
     * @param h Hauteur JPanel
     * @param l Larguer JPanel
     */
    public Trace_Circuit(int i, int h,int l){
        numCircuit=i;
        hauteur=h;
        largeur=l;

        this.setLayout(null);
        this.setBounds(0,0,largeur,hauteur);
    }

    @Override
    public void paint(Graphics g1){

        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(4.0f));

        //tracé commun à tous (cadre du circuit)
        g.drawLine(largeur/9,hauteur/10,largeur*8/9,hauteur/10);
        g.drawLine(largeur*8/9,hauteur/10,largeur*8/9,hauteur*9/10);
        g.drawLine(largeur*8/9,hauteur*9/10,largeur/9,hauteur*9/10);
        g.drawLine(largeur/9,hauteur*9/10,largeur/9,hauteur/10);

        if(numCircuit==2){
            g.drawLine(largeur/2,hauteur/10,largeur/2,hauteur*9/10);
        }
        if(numCircuit==3){
            g.drawLine(largeur*10/27,hauteur/10,largeur*10/27,hauteur*9/10);
            g.drawLine(largeur*17/27,hauteur/10,largeur*17/27,hauteur*9/10);
        }
    }
}