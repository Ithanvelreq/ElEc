import javax.swing.*;
import java.awt.*;

public class Trace_Composant extends JPanel{

    //attributs
    String composant; //nom du composant
    boolean estVertical;    // doit être tracé à la verticale ou non
    boolean aDessiner;      // doit être dessiné ou non
    int hauteur;
    int largeur;

    //constructeur
    public Trace_Composant(String s,boolean b,int h,int l,boolean a){
        composant=s;
        hauteur=h;
        largeur=l;
        estVertical = b;
        aDessiner=a;

        this.setLayout(null);
        this.setBounds(0,0,largeur,hauteur);

    }

    public void paint(Graphics g1){

        Graphics2D g = (Graphics2D) g1;
        g.setColor(new Color(228,229,230));
        g.fillRect(0,0,largeur,hauteur);

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(4.0f));

        if(aDessiner) {
            if (composant.equals("Resistance")) {
                if(!estVertical) {
                    g.drawRect(2,hauteur/4,largeur-5,hauteur/2);
                }else {
                    g.drawRect(largeur/3,2,largeur/3,hauteur-5);
                }
            }
            if (composant.equals("Condensateur")) {

            }
            if (composant.equals("Bobine")) {

            }
            if (composant.equals("generateur")) {
                g.drawLine(largeur / 2, 0, largeur / 2, hauteur);
                g.drawOval(largeur / 2 - 50, hauteur / 2 - 50, 100, 100);
            }
        }
    }
}
