import java.awt.*;
import javax.swing.*;

public class Fenetreoscillo extends JFrame {

    private static final long serialVersionUID = -3914578220391097071L;
    private dessinoscillo dessin = new dessinoscillo(); //creation de la courbe

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight()-40;
    int largeur = (int)tailleEcran.getWidth();
    public ItemElement[] tableaumenu;

    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h= (double) 0.25*hauteur;
    double h1= (double) 0.75*hauteur;

    public Fenetreoscillo(ItemElement[] tableaumenu) {
        super( "Oscilloscope" );
        this.tableaumenu=tableaumenu;
        this.setDefaultCloseOperation( HIDE_ON_CLOSE );
        //this.pack();
        //this.setDefaultLookAndFeelDecorated(true);
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
        panneaudubas.setBackground(new Color(195, 188, 181));

        recupcomposant(panneaudubas); // méthode appellée pour récupérer les valeurs des composants et leur type

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

    public void recupcomposant(JPanel panneau){

        //création d'un emplacement pour le texte
        JLabel colonne1 = new JLabel();
        colonne1.setBounds(0,0,200,(int)h);
        colonne1.setVisible(true);
        panneau.add(colonne1);



        colonne1.setText("<html>Mes lignes <br> 2ème ligne <br> 3ème ligne </html>");


    }

}