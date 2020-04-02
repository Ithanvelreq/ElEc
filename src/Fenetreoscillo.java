import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Fenetreoscillo extends JFrame implements ActionListener {

    private static final long serialVersionUID = -3914578220391097071L;
    public JCheckBox[] tabcheckbox = new JCheckBox[4];
    public String[] w;
    public Impedance[] z;
    public ItemElement[] tableaumenu;
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight()-40;
    int largeur = (int)tailleEcran.getWidth();
    public String[] nomdescomposants = new String[4];

    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h= (double) 0.25*hauteur;
    double h1= (double) 0.75*hauteur;

    public Fenetreoscillo(String[] w, Impedance[] z, ItemElement[] tableaumenu) {
        super( "Oscilloscope" );
        this.setDefaultCloseOperation( HIDE_ON_CLOSE );
        //this.pack();
        //this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setSize(largeur,hauteur);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setLocationRelativeTo( null );
        this.w=w;
        this.z=z;
        this.tableaumenu=tableaumenu;
        dessinoscillo dessin = new dessinoscillo(tabcheckbox,z, tableaumenu); //creation de la courbe

        JPanel Panneaumain = (JPanel) this.getContentPane(); // creation panneaumain

        //creation d'un panneau accueillera les valeurs des composants choisis avant
        JPanel panneaudubas = new JPanel();
        panneaudubas.setLayout(null);
        panneaudubas.setBounds(0, (int)h1, largeur, (int)h);
        panneaudubas.setBackground(new Color(195, 188, 181));

        recupcomposant(panneaudubas); // méthode appellée pour récupérer les valeurs des composants et leur type --> pas utilisée pour linstant

        //ajout JCheckBox

        for(int i=0;i<tabcheckbox.length;i++) {

            if(tableaumenu[i] instanceof ItemComposant) {
                ItemComposant y = (ItemComposant) tableaumenu[i];
                nomdescomposants[i] = y.getComposant();
                tabcheckbox[i] = new JCheckBox("Afficher la tension de " + nomdescomposants[i]);
                tabcheckbox[i].setBounds((int) (largeur * (1 + (i * 2)) / 9 - tabcheckbox[i].getWidth() / 2), 0, 300, (int) h);
                tabcheckbox[i].addActionListener(this);
                tabcheckbox[i].setBackground(new Color(195, 188, 181));
                panneaudubas.add(tabcheckbox[i]);
            }else {
                ItemGenerateur y = (ItemGenerateur) tableaumenu[i];
                tabcheckbox[i] = new JCheckBox("Afficher la tension du générateur");
                tabcheckbox[i].setBounds((int) (largeur * (1 + (i * 2)) / 9 - tabcheckbox[i].getWidth() / 2), 0, 300, (int) h);
                tabcheckbox[i].addActionListener(this);
                tabcheckbox[i].setBackground(new Color(195, 188, 181));
                panneaudubas.add(tabcheckbox[i]);
            }

        }

        //on ajoute la courbe et le JPanel du bas
        Panneaumain.add( panneaudubas);
        Panneaumain.add(dessin);

    }

    public void recupcomposant(JPanel panneau){

        /*JTextArea[] tabzonetexte= new JTextArea[2]; //création d'un emplacement pour le texte
        for(int j=0;j<2;j++) {
            tabzonetexte[j] = new JTextArea();
            tabzonetexte[j].setEditable(false);
            tabzonetexte[j].setBounds(0+(largeur/4)*j, 0, largeur / 4, (int) h);
            tabzonetexte[j].setVisible(true);
            panneau.add(tabzonetexte[j]);

            for (int i = 0; i < w.length; i++) {

                if (i<4 && j==0 ) {
                    tabzonetexte[j].setText(tabzonetexte[j].getText() + w[i] + " : " + z[i] + "\n" + "\n");
                }
                if(i>3 && j==1){
                    tabzonetexte[j].setText(tabzonetexte[j].getText() + w[i] + " : " + z[i] + "\n" + "\n");
                }
            }
        }
        */
        for (int i = 0; i < w.length; i++) {
            System.out.println(w[i] + " : " + z[i] );
        }

    }

    public void actionPerformed (ActionEvent e) {

    }

}