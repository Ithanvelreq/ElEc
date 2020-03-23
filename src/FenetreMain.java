import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreMain extends JFrame implements ActionListener{

    //obtient les caractéristiques de l'écran pour que la fenetre occupe tout l'espace

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight();
    int largeur = (int)tailleEcran.getWidth();

    //declaration de tous les widgets et des 4 fenêtres

    public JButton[] tableaubouton;
    FenetreA Fenetrecircuit1;
    FenetreB Fenetrecircuit2;
    FenetreC Fenetrecircuit3;
    FenetreD Fenetrecircuit4;


    //constructeur

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

        tableaubouton[0]=new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("imagecircuitRLC.png")))));
        tableaubouton[0].setBounds((int)(0.25*largeur/4), hauteur/6,(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        tableaubouton[1] = new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele.png")))));
        tableaubouton[1].setBounds((int)(2.25*largeur/4), hauteur/6,(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        tableaubouton[2] = new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele2.png")))));
        tableaubouton[2].setBounds((int)(0.25*largeur/4), tableaubouton[0].getY()+tableaubouton[0].getHeight()+50,(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        tableaubouton[3]= new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele3.png")))));
        tableaubouton[3].setBounds((int)(2.25*largeur/4), tableaubouton[0].getY()+tableaubouton[0].getHeight()+50,(int)((largeur/2)*0.7), (int)((hauteur/2)*0.7));

        for(JButton b :tableaubouton){
            b.addActionListener(this);
            Panneauprincipal.add(b);
        }

        JLabel titre = new JLabel("Simulateur de circuits électriques");
        titre.setFont(new Font("Arial", Font.BOLD,60));
        titre.setBounds(largeur/2-500,20,1000,70);

        JLabel sousTitre = new JLabel("Il y a 4 circuits disponibles. Veuillez cliquer sur le circuit de votre choix pour lancer sa configuration.");
        sousTitre.setFont(new Font("Arial", Font.ITALIC,30));
        sousTitre.setBounds(largeur/2-675,titre.getY()+titre.getHeight()+20,1350,40);

        Panneauprincipal.add(titre);
        Panneauprincipal.add(sousTitre);


        add(Panneauprincipal);  //ajout du panneau à la fenêtre
        this.validate();   // permet à tous les boutons d'apparaitre, sinon il fallait passer la souris dessus pour qu'ils apparaissent(je sais pas pk)

    }

    public void actionPerformed (ActionEvent e) {

        if (e.getSource()== tableaubouton[0]){

            Fenetrecircuit1 = new FenetreA();
            Fenetrecircuit1.setVisible(true);
        }
        if (e.getSource()== tableaubouton[1]){

            Fenetrecircuit2 = new FenetreB();
            Fenetrecircuit2.setVisible(true);
        }
        if (e.getSource()== tableaubouton[2]){

            Fenetrecircuit3 = new FenetreC();
            Fenetrecircuit3.setVisible(true);
        }
        if (e.getSource()== tableaubouton[3]){

            Fenetrecircuit4 = new FenetreD();
            Fenetrecircuit4.setVisible(true);
        }

    }

}
