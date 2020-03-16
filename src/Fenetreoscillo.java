import java.awt.*;
import javax.swing.*;

public class Fenetreoscillo extends JFrame {

    private static final long serialVersionUID = -3914578220391097071L;
    private dessinoscillo dessin = new dessinoscillo(); //creation de la courbe

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight();
    int largeur = (int)tailleEcran.getWidth();

    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h= (double) 0.25*hauteur;
    double h1= (double) 0.75*hauteur;

    public Fenetreoscillo() {
        super( "Oscilloscope" );
        this.setDefaultCloseOperation( HIDE_ON_CLOSE );
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setSize(largeur,hauteur);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setLocationRelativeTo( null );

        JPanel Panneaumain = (JPanel) this.getContentPane(); // creation panneaumain

        //creation d'un panneau accueillera les valeurs des composants choisis avant
        JPanel panneaudubas = new JPanel();
        panneaudubas.setLayout(null);
        panneaudubas.setBounds(0, (int)h1, largeur, (int)h);

        //ajout des composants au panneau du bas

        JTextField test = new JTextField();
        test.setBounds(0,0,200,100);
        panneaudubas.add(test);

        //on ajoute la courbe et le JPanel du bas
        Panneaumain.add( panneaudubas);
        Panneaumain.add(dessin);

    }

    /*private void btnSinusListener( ActionEvent event ) {
        dessin.setFunction( (x) -> Math.sin( x ) );
    }

    private void btnCosinusListener( ActionEvent event ) {
        dessin.setFunction( (x) -> Math.cos( x ) );
    }*/

}