import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreD extends JFrame implements ActionListener{

    //obtient les caractéristiques de l'écran pour que la fenetre occupe tout l'espace

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight();
    int largeur = (int)tailleEcran.getWidth();

    // obtention de 3/4 et de 1/4 de la longueur de l'écran

    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;

    // declaration des widgets

    public JButton boutonvalider;
    public JButton boutonaffichage;
    String[] sourcestension = {"source de 5V", "source de 12 V"};
    String[] autrescomposants = {"Résistance", "Bobine", "Condensateur"};
    JComboBox[] tableaumenu = new JComboBox[4]; // tableau de menu déroulants

    public FenetreD() {

        this.setSize(largeur,hauteur);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setTitle("circuit 4");     //titre

        // permet d'afficher la fenêtre en plein écran

        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   // permet de fermer la fenêtre sans fermer tout le programme

        //création du panneau où l'on fait son système

        JPanel Panneausysteme = new Panneaudessin(4);
        Panneausysteme.setBounds(0,0,(int) l,hauteur);
        Panneausysteme.setLayout(null);

        for (int i=0;i<4;i++){

            if(i==0){
                tableaumenu[i] = new JComboBox(sourcestension);
                tableaumenu[i].setBounds(40,Panneausysteme.getHeight()/2-50,100,50);  // source
            }else{
                tableaumenu[i]= new JComboBox(autrescomposants);
                if(i==1) {
                    tableaumenu[i].setBounds(Panneausysteme.getWidth()/4,Panneausysteme.getHeight()/2-50,100,50); //composant de gauche
                }
                if(i==2){
                    tableaumenu[i].setBounds((int)(Panneausysteme.getWidth()*0.55),Panneausysteme.getHeight()/2-50,100,50); //composant de du milieu
                }
                if(i==3){
                    tableaumenu[i].setBounds(Panneausysteme.getWidth()-175,Panneausysteme.getHeight()/2-50,100,50); // composant de droite
                }
            }
            Panneausysteme.add(tableaumenu[i]);
        }

        //création du panneau avec les boutons de configuration

        JPanel Panneaubouton = new JPanel();
        Panneaubouton.setBounds((int) l,0,(int) l1,hauteur);
        Panneaubouton.setLayout(null);
        Panneaubouton.setBackground(new Color(72, 79, 81));

        boutonvalider = new JButton("Valider");
        boutonvalider.setBounds(10,10,160,50);
        boutonvalider.setBackground(Color.gray);
        boutonvalider.setForeground(Color.white);
        boutonvalider.addActionListener(this);
        Panneaubouton.add(boutonvalider);

        boutonaffichage = new JButton("Afficher les résultats");
        boutonaffichage.setBounds(10,100,160,50);
        boutonaffichage.setBackground(Color.gray);
        boutonaffichage.setForeground(Color.white);
        boutonaffichage.addActionListener(this);
        Panneaubouton.add(boutonaffichage);

        //création panneau principal

        JPanel Panneaumain = new JPanel();
        Panneaumain.setBounds(0,0,largeur,hauteur);
        Panneaumain.setLayout(null);
        Panneaumain.setBackground(Color.gray);

        Panneaumain.add(Panneaubouton);
        Panneaumain.add(Panneausysteme);

        //ajout du panneau à la fenêtre

        this.add(Panneaumain);
    }

    public void actionPerformed (ActionEvent e) {

    }

}