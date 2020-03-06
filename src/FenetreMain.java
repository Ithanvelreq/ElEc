import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreMain extends JFrame implements ActionListener{

    //obtient les caractéristiques de l'écran pour que la fenetre occupe tout l'espace

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight();
    int largeur = (int)tailleEcran.getWidth();

    //declaration de tous les widgets ci-dessous

    public JButton[] tableaubouton;

    //constructeur

    public FenetreMain(){

        setSize(largeur,hauteur);			// taille de la fenêtre
        setLocation(0,0);		//position de la fenêtre
        setVisible(true);			//visibilité de la fenêtre

        // permet d'afficher la fenêtre en plein écran

        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // permet de fermer la fenêtre

        //création panneau principal

        JPanel Panneauprincipal = new JPanel();
        Panneauprincipal.setBounds(0,0,largeur,hauteur);
        Panneauprincipal.setLayout(null);
        Panneauprincipal.setBackground(new Color(228,229,230));

        //création et placement des boutons adaptatif en fonction de la taille de l'écran

        tableaubouton = new JButton[4];

        for (int i=0; i<4;i++) {

            if (i<2) {

                // on selectionne les images que l'on met dans les boutons (on mettra nos images plus tard)

                if (i==0) {
                    tableaubouton[i] = new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("imagecircuitRLC.png")))));
                }
                if(i==1){
                    tableaubouton[i] = new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele.png")))));
                }
                tableaubouton[i].setBounds(10+((largeur/2)-10)*i, 10, (largeur/2)-20+(i*10), (hauteur/2)-30);
                tableaubouton[i].addActionListener(this);
                Panneauprincipal.add(tableaubouton[i]);

            }else{

                // on selectionne les images que l'on met dans les boutons (on mettra nos images plus tard)

                if (i==2) {
                    tableaubouton[i] = new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("imagecircuitRLC.png")))));
                }
                if(i==3){
                    tableaubouton[i] = new Boutonmodif("",(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitenparallele.png")))));
                }
                tableaubouton[i].setBounds(10+((largeur/2)-10)*(i-2), ((hauteur/2)-10), (largeur/2)-20+((i-2)*10), (hauteur/2)-30);
                tableaubouton[i].addActionListener(this);
                Panneauprincipal.add(tableaubouton[i]);

            }

        }

        add(Panneauprincipal);  //ajout du panneau à la fenêtre
        this.validate();   // permet à tous les boutons d'apparaitre, sinon il fallait passer la souris dessus pour qu'ils apparaissent(je sais pas pk)

    }

    public void actionPerformed (ActionEvent e) {

        if (e.getSource()== tableaubouton[0]){

            FenetreA Fenetrecircuit1 = new FenetreA();
            Fenetrecircuit1.setVisible(true);
        }
        if (e.getSource()== tableaubouton[1]){

            FenetreB Fenetrecircuit2 = new FenetreB();
            Fenetrecircuit2.setVisible(true);
        }
        if (e.getSource()== tableaubouton[2]){

            FenetreC Fenetrecircuit3 = new FenetreC();
            Fenetrecircuit3.setVisible(true);
        }
        if (e.getSource()== tableaubouton[3]){

            FenetreD Fenetrecircuit4 = new FenetreD();
            Fenetrecircuit4.setVisible(true);
        }

    }

}
