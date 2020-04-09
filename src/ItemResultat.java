import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ItemResultat extends JPanel {

    //attributs
    JLabel intensite;
    JLabel ValIntensite;
    JLabel tension;
    JLabel ValTension;

    //constructeur
    public ItemResultat(double U , double I){

        this.setBackground(new Color(238,238,238));
        this.setSize(140, 55);
        this.setLayout(null);

        intensite = new JLabel("I max (A) = ");
        intensite.setBounds(0,0,75,25);
        ValIntensite = new JLabel(String.valueOf(I));
        ValIntensite.setBounds(77,0,60,25);
        tension = new JLabel("U max (V) = ");
        tension.setBounds(0,27,75,25);
        ValTension = new JLabel(String.valueOf(U));
        ValTension.setBounds(77,27,60,25);

        this.add(intensite);
        this.add(ValIntensite);
        this.add(tension);
        this.add(ValTension);
    }
}
