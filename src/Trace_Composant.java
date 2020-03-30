import javax.swing.*;
import java.awt.*;

public class Trace_Composant extends JPanel{

    //attributs
    String composant;
    boolean estVertical;
    int hauteur;
    int largeur;

    //constructeur
    public Trace_Composant(String s,boolean b,int h,int l){
        composant=s;
        hauteur=h;
        largeur=l;
        estVertical = b;

        this.setLayout(null);
        this.setBounds(0,0,largeur,hauteur);
        this.setBackground(Color.pink);

        System.out.println("passege 2");
    }

    public void tracer(Graphics g1){

        //g1.setColor();
        g1.fillRect(0,0,largeur,hauteur);

        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(4.0f));

        if(composant.equals("Résistance")){

        }
        if(composant.equals("Condensateur")){

        }
        if(composant.equals("Bobine")){

        }
        if(composant.equals("generateur")){
            g.drawLine(80,0,80,120);
            g.drawOval(110,70,100,100);
        }
    }
}
