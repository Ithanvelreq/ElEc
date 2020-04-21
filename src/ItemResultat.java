package ElEc;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Outil utilise pour afficher la tension et le courrant a travers chaque dipole
 */
public class ItemResultat extends JPanel {

    /**
     * Affiche la partie de gauche de l'egalite
     */
    JLabel intensite;
    /**
     * Valeur reele du courrant
     */
    JLabel ValIntensite;
    /**
     * Affiche la partie de gauche de l'egalite
     */
    JLabel tension;
    /**
     * Valeur reele de la tension
     */
    JLabel ValTension;

    /**
     * Constructeur
     * @param U Tension complexe aux bornes du dipole
     * @param I Courrant complexe aux bornes du dipole
     */
    public ItemResultat(Impedance U , Impedance I){

        this.setBackground(new Color(238,238,238));
        this.setSize(180, 55);
        this.setLayout(null);

        String s;
        NumberFormat formatterm = new DecimalFormat("0.##E0");
        NumberFormat formattera = new DecimalFormat("0.##");

        intensite = new JLabel("I (A) = ");
        intensite.setBounds(0,0,50,25);
        s = formatterm.format(I.module()) + " . cos(wt "+signe(I.argument()) + formattera.format(Math.abs(I.argument())) + ")";
        ValIntensite = new JLabel(s);
        ValIntensite.setBounds(37,0,2000,25);
        tension = new JLabel("U (V) = ");
        tension.setBounds(0,27,50,25);
        s = formatterm.format(U.module()) + " . cos(wt "+signe(U.argument()) + formattera.format(Math.abs(U.argument())) + ")";
        ValTension = new JLabel(s);
        ValTension.setBounds(37,27,2000,25);

        this.add(intensite);
        this.add(ValIntensite);
        this.add(tension);
        this.add(ValTension);
    }

    /**
     * Renvoie le signe de x
     * @param x reel qu'il faut analyser
     * @return signe de x
     */
    private char signe(double x){
        char sgn = '+';
        if (x<0){
            sgn = '-';
        }
        return sgn;
    }
}
