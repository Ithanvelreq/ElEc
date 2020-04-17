import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Fenetreoscillo extends JFrame implements ActionListener {

    private static final long serialVersionUID = -3914578220391097071L;
    public JCheckBox[] tabcheckbox = new JCheckBox[4]; // tableau de Jcheckbox
    public String[] w; // tableau rassemblant les inconnues du système d'équations du système
    public Impedance[] z; // tableau rassemblant les solutions du système d'équations du système
    public ItemElement[] tableaumenu; // récupération du tableau contenant les ItemElement, qui nous permettra de récupérer les données entrées dans les JtextField
    public String[] nomdescomposants = new String[4]; // création d'un tableau de String qui contiendra les noms des composants du circuit.

    //récupération des dimensions de l'écran et calculs de variables qui nous seront utiles pour le dimensionnement des widgets
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight()-40;
    int largeur = (int)tailleEcran.getWidth();
    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h= (double) 0.25*hauteur;
    double h1= (double) 0.75*hauteur;

    //constructeur
    public Fenetreoscillo(String[] w, Impedance[] z, ItemElement[] tableaumenu) {

        super( "Oscilloscope" );
        //caractértistiques fenetre
        this.setDefaultCloseOperation( HIDE_ON_CLOSE );
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setSize(largeur,hauteur);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setLocationRelativeTo( null );
        // récupération des tableaux d'inconnues, de solutions et d'Itemelement contenus dans FenetreA/B/C ou D_bis
        this.w=w;
        this.z=z;
        this.tableaumenu=tableaumenu;

        // creation du panneau principal
        JPanel Panneaumain = (JPanel) this.getContentPane();

        //creation d'un panneau accueillera les Jcheckbox et les curseurs
        JPanel panneaudubas = new JPanel();
        panneaudubas.setLayout(null);
        panneaudubas.setBounds(0, (int)h1, largeur, (int)h);
        panneaudubas.setBackground(new Color(195, 188, 181));

        //initialisation du dessin des courbes
        dessinoscillo dessin = new dessinoscillo(tabcheckbox,z, tableaumenu, panneaudubas); //creation de la courbe

        //ajout JCheckBox au panneau du bas
        nommecomposant();
        for(int i=0;i<tabcheckbox.length;i++) {

            if(tableaumenu[i] instanceof ItemComposant) {
                tabcheckbox[i] = new JCheckBox("Afficher la tension de la / du " + nomdescomposants[i]);
                tabcheckbox[i].setBounds((int) (largeur * (1 + (i * 2)) / 8.8 - tabcheckbox[i].getWidth() / 2)-50, 0, 330, (int) h);
                tabcheckbox[i].addActionListener(this);
                tabcheckbox[i].setBackground(new Color(195, 188, 181));
                panneaudubas.add(tabcheckbox[i]);
            }else {
                ItemGenerateur y = (ItemGenerateur) tableaumenu[i];
                tabcheckbox[i] = new JCheckBox("Afficher la tension du générateur");
                tabcheckbox[i].setBounds((int) (largeur * (1 + (i * 2)) / 9 - tabcheckbox[i].getWidth() / 2), 0, 230, (int) h);
                tabcheckbox[i].addActionListener(this);
                tabcheckbox[i].setBackground(new Color(195, 188, 181));
                panneaudubas.add(tabcheckbox[i]);
            }

        }

        //on ajoute la courbe et le panneau du bas
        Panneaumain.add( panneaudubas);
        Panneaumain.add(dessin);

    }

    //méthode évènement
    public void actionPerformed (ActionEvent e) {

    }

    //méthode qui différencie les composants en les nommant dans les cas où des circuits ont 2 composants identiques
    public void nommecomposant(){

        for(int i=0; i<tableaumenu.length;i++){
            if(tableaumenu[i] instanceof ItemComposant){
                ItemComposant y = (ItemComposant) tableaumenu[i];
                if(y.getComposant()=="Resistance"){
                    //nomdescomposants[i] ="Résistance "+String.valueOf(nbR);
                    nomdescomposants[i] ="Résistance de "+y.saisie.getText()+ " ohms";
                }
                if(y.getComposant()=="Condensateur"){
                    nomdescomposants[i] ="Condensateur de "+y.saisie.getText()+ " F";
                }
                if(y.getComposant()=="Bobine"){
                    nomdescomposants[i] ="Bobine de "+y.saisie.getText()+ " H";
                }
            }
        }

    }

}