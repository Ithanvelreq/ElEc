package elec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Fenetre principale de l'application
 */
public class FenetreMain extends JFrame implements ActionListener{

    //obtient les caractéristiques de l'écran pour que la fenetre occupe tout l'espace
    /**
     * Caracteristiques de l'ecran
     */
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Hauteur de l'ecran
     */
    int hauteur = (int)tailleEcran.getHeight()-40;
    /**
     * Largeur de l'ecran
     */
    int largeur = (int)tailleEcran.getWidth();

    //declaration de tous les widgets et des 4 fenêtres
    /**
     * Tableau qui contient les bouttons de la fenetre principale
     */
    public JButton[] tableaubouton;
    /**
     * JLabel contenant le titre de la fenetre principale
     */
    JLabel titre;
    /**
     * JLabel contenant le sous-titre de la fenetre principale
     */
    JLabel sousTitre;
    /**
     * JBoutton pour afficher les credits
     */
    JButton credits;
    /**
     * Fenetre contenant le circuit A
     */
    FenetreA_Bis Fenetrecircuit1;
    /**
     * Fenetre contenant le circuit B
     */
    FenetreB_Bis Fenetrecircuit2;
    /**
     * Fenetre contenant le circuit C
     */
    FenetreC_Bis Fenetrecircuit3;
    /**
     * Fenetre contenant le circuit D
     */
    FenetreD_Bis Fenetrecircuit4;
    /**
     * Image du circuitA pour acceder a la fenetreA
     */
    public ImageIcon imagebouton1;
    /**
     * Image du circuitB pour acceder a la fenetreB
     */
    public ImageIcon imagebouton2;
    /**
     * Image du circuitC pour acceder a la fenetreC
     */
    public ImageIcon imagebouton3;
    /**
     * Image du circuitD pour acceder a la fenetreD
     */
    public ImageIcon imagebouton4;


    /**
     * Constructeur
     */
    public FenetreMain(){

        setTitle("SIMULATEUR DE CIRCUIT ELECTRIQUE");
        setSize(largeur,hauteur);			// taille de la fenêtre
        setLocation(0,0);		//position de la fenêtre
        setVisible(true);			//visibilité de la fenêtre

        //this.pack();
        //this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);    //fenêtre en plein écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // permet de fermer la fenêtre

        //création panneau principal

        JPanel Panneauprincipal = new JPanel();
        Panneauprincipal.setBounds(0,0,largeur,hauteur);
        Panneauprincipal.setLayout(null);
        Panneauprincipal.setBackground(new Color(228,229,230));

        //création et placement des boutons adaptatif en fonction de la taille de l'écran
        tableaubouton = new JButton[4];

        //adaptation des tailles des images
        imagebouton1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("imagecircuitRLC.png")));
        Image image = imagebouton1.getImage().getScaledInstance(350,200, Image.SCALE_SMOOTH);
        imagebouton1 = new ImageIcon(image, imagebouton1.getDescription());

        imagebouton2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele.png")));
        Image image1 = imagebouton2.getImage().getScaledInstance(350,200, Image.SCALE_SMOOTH);
        imagebouton2 = new ImageIcon(image1, imagebouton2.getDescription());

        imagebouton3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele2.png")));
        Image image2 = imagebouton3.getImage().getScaledInstance(350,200, Image.SCALE_SMOOTH);
        imagebouton3 = new ImageIcon(image2, imagebouton3.getDescription());

        imagebouton4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele3.png")));
        Image image3 = imagebouton4.getImage().getScaledInstance(350,200, Image.SCALE_SMOOTH);
        imagebouton4 = new ImageIcon(image3, imagebouton4.getDescription());

        tableaubouton[0]=new Boutonmodif("",imagebouton1);
        tableaubouton[0].setBounds((int)(0.25*largeur/4), hauteur/5,(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        tableaubouton[1] = new Boutonmodif("",imagebouton2);
        tableaubouton[1].setBounds((int)(2.25*largeur/4), hauteur/5,(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        tableaubouton[2] = new Boutonmodif("",imagebouton3);
        tableaubouton[2].setBounds((int)(0.25*largeur/4), tableaubouton[0].getY()+tableaubouton[0].getHeight(),(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        tableaubouton[3]= new Boutonmodif("",imagebouton4);
        tableaubouton[3].setBounds((int)(2.25*largeur/4), tableaubouton[0].getY()+tableaubouton[0].getHeight(),(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        for(JButton b :tableaubouton){
            b.addActionListener(this);
            Panneauprincipal.add(b);
        }

        titre = new JLabel("Simulateur de circuits électriques");
        titre.setFont(new Font("Arial", Font.BOLD,60));
        titre.setBounds(largeur/2-500,20,1000,70);

        sousTitre = new JLabel("Il y a 4 circuits disponibles. Veuillez cliquer sur le circuit de votre choix pour lancer sa configuration.");
        sousTitre.setFont(new Font("Arial", Font.ITALIC,30));
        sousTitre.setBounds(largeur/2-675,titre.getY()+titre.getHeight()+20,1350,40);

        credits = new JButton("Crédits");
        credits.setBounds(10,tableaubouton[3].getY()+tableaubouton[3].getHeight(),90,25);
        credits.addActionListener(this);

        Panneauprincipal.add(titre);
        Panneauprincipal.add(sousTitre);
        Panneauprincipal.add(credits);


        add(Panneauprincipal);  //ajout du panneau à la fenêtre
        this.validate();   // permet à tous les boutons d'apparaitre, sinon il fallait passer la souris dessus pour qu'ils apparaissent(je sais pas pk)

    }

    @Override
    public void actionPerformed (ActionEvent e) {

        if (e.getSource()== tableaubouton[0]){

            Fenetrecircuit1 = new FenetreA_Bis();
            Fenetrecircuit1.setVisible(true);
        }
        if (e.getSource()== tableaubouton[1]){

            Fenetrecircuit2 = new FenetreB_Bis();
            Fenetrecircuit2.setVisible(true);
        }
        if (e.getSource()== tableaubouton[2]){

            Fenetrecircuit3 = new FenetreC_Bis();
            Fenetrecircuit3.setVisible(true);
        }
        if (e.getSource()== tableaubouton[3]){

            Fenetrecircuit4 = new FenetreD_Bis();
            Fenetrecircuit4.setVisible(true);
        }
        if (e.getSource()==credits){
            JOptionPane.showMessageDialog(this,  "Projet Algo S4\n'Simulateur de circuits électriques'\nIthan VELARDE - Florian VELOSO - Sacha BAILLY - Florian REYNAUD\nINSA LYON FIMI 2A - 2019/2020");
        }

    }

}
