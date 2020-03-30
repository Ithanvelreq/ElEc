import javax.swing.*;
import java.awt.*;

public class Trace_Circuit extends JPanel{

    //attributs
    int numCircuit;
    int hauteur;
    int largeur;

    //constructeur
    public Trace_Circuit(int i, int h,int l){
        numCircuit=i;
        hauteur=h;
        largeur=l;

        this.setLayout(null);
        this.setBounds(0,0,largeur,hauteur);
    }

    /**
     * on définit les 4 tracés de circuits différents
     * @param g1 : graphics du JPanel
     */
    public void paint(Graphics g1){

        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(4.0f));

        if(numCircuit==1){
            g.drawLine(largeur/9,hauteur/10,largeur*8/9,hauteur/10);
            g.drawLine(largeur*8/9,hauteur/10,largeur*8/9,hauteur*9/10);
            g.drawLine(largeur*8/9,hauteur*9/10,largeur/9,hauteur*9/10);
            g.drawLine(largeur/9,hauteur*9/10,largeur/9,hauteur/10);
        }
        if(numCircuit==2){

        }
        if(numCircuit==3){

        }
        if(numCircuit==4){

        }
    }
}
