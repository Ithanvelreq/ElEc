import javax.swing.*;
import java.awt.*;

public class Trace_Composant extends JPanel{

    //attributs
    /**
     * Nom du composant
     */
    String composant;
    /**
     * True si le composant doit etre place a la verticale false sinon
     */
    boolean estVertical;
    /**
     * True si le composant doit etre dessine false sinon
     */
    boolean aDessiner;
    /**
     * A COMMENTER
     */
    int hauteur;
    /**
     * A COMMENTER
     */
    int largeur;

    /**
     * Constructeur A COMMENTER
     * @param s
     * @param b
     * @param h
     * @param l
     * @param a
     */
    public Trace_Composant(String s,boolean b,int h,int l,boolean a){
        composant=s;
        hauteur=h;
        largeur=l;
        estVertical = b;
        aDessiner=a;

        this.setLayout(null);
        this.setBounds(0,0,largeur,hauteur);

    }

    @Override
    public void paint(Graphics g1){

        Graphics2D g = (Graphics2D) g1;
        g.setColor(new Color(238,238,238));
        g.fillRect(0,0,largeur,hauteur);            //on unifie le fond

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(4.0f)); //taille ligne

        if(aDessiner) {
            if (composant.equals("Resistance")) {
                if(!estVertical) {
                    g.drawRect(2,hauteur/4,largeur-4,hauteur/2);
                }else {
                    g.drawRect(largeur/3,2,largeur/3,hauteur-4);
                }
            }
            if (composant.equals("Condensateur")) {
                if(!estVertical){
                    g.drawLine(0,hauteur/2,largeur/3,hauteur/2);
                    g.drawLine(largeur*2/3,hauteur/2,largeur,hauteur/2);
                    g.drawLine(largeur/3,hauteur/4,largeur/3,hauteur*3/4);
                    g.drawLine(largeur*2/3,hauteur/4,largeur*2/3,hauteur*3/4);
                }else{
                    g.drawLine(largeur/2,0,largeur/2,hauteur/4);
                    g.drawLine(largeur/2,hauteur*3/4,largeur/2,hauteur);
                    g.drawLine(largeur/3,hauteur/4,largeur*2/3,hauteur/4);
                    g.drawLine(largeur/3,hauteur*3/4,largeur*2/3,hauteur*3/4);
                }
            }
            if (composant.equals("Bobine")) {
                if(!estVertical){
                    g.drawOval(0,3,(largeur/3)-2,hauteur);
                    g.drawOval(largeur/3,3,largeur/3,hauteur);
                    g.drawOval(largeur*2/3,3,(largeur/3)-2,hauteur);
                    g.setColor(new Color(238,238,238));
                    g.fillRect(0,hauteur/2+2,largeur,hauteur);
                }else {
                    g.drawOval(largeur/4,0,largeur/2,hauteur/3);
                    g.drawOval(largeur/4,hauteur/3,largeur/2,hauteur/3);
                    g.drawOval(largeur/4,hauteur*2/3,largeur/2,hauteur/3);
                    g.setColor(new Color(238,238,238));
                    g.fillRect(largeur/2,0,largeur/2,hauteur);
                }
            }
            if (composant.equals("generateur")) {
                g.drawLine(largeur / 2, 0, largeur / 2, hauteur);
                g.drawOval(largeur / 2 - 40, hauteur / 2 - 40, 80, 80);
            }
        }
    }
}