import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class FenetreA extends JFrame implements ActionListener{

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
    public JButton boutonaffichage1;
    String[] sourcestension = {"source de 5V", "source de 12 V"};  //tableau permettant la selection des elements des menus deroulants
    String[] autrescomposants = {"Résistance", "Bobine", "Condensateur"};  //tableau permettant la selection des elements des menus deroulants
    ItemComposant[] tableaumenu = new ItemComposant[4]; // tableau de menu déroulants
    boolean[] estvertical = new boolean[4]; // tableau pour savoir si les menus sont sur un segment vertical ou non
    public JPanel Panneausysteme;
    public JPanel Panneaubouton;
    public ImageIcon icone;  // image qui doit s'afficher à la place des menus déroulants
    public JLabel jlabel;   //jlabel contenant l'image qui doit s'afficher
    public JLabel zonedessin;
    public ImageIcon imagefond;

    public ItemComposant item;

    public FenetreA() {

        this.setSize(largeur,hauteur);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setTitle("circuit 1");     //titre

        // permet d'afficher la fenêtre en plein écran

        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   // permet de fermer la fenêtre sans fermer tout le programme

        //création du panneau où l'on fait son système

        Panneausysteme = new JPanel();
        Panneausysteme.setBounds(0,0,(int) l,hauteur);
        Panneausysteme.setLayout(null);
        Panneausysteme.setBackground(new Color(228,229,230));

        imagefond = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("circuitnumero1.png")));
        zonedessin = new JLabel(imagefond);
        zonedessin.setLayout(null);
        zonedessin.setBounds(0,0,(int) l,hauteur);
        zonedessin.setVisible(true);
        Panneausysteme.add(zonedessin);

        //création du panneau avec les boutons de configuration

        Panneaubouton = new JPanel();
        Panneaubouton.setBounds((int) l,0,(int) l1,hauteur);
        Panneaubouton.setLayout(null);
        Panneaubouton.setBackground(new Color(72, 79, 81));

        boutonvalider = new JButton("Valider");
        boutonvalider.setBounds(Panneaubouton.getWidth()/4,10,200,50);
        boutonvalider.setBackground(Color.gray);
        boutonvalider.setForeground(Color.white);
        boutonvalider.addActionListener(this);
        Panneaubouton.add(boutonvalider);
        boutonvalider.setVisible(false);

        boutonaffichage = new JButton("Afficher les résultats");
        boutonaffichage.setBounds(Panneaubouton.getWidth()/4,100,200,50);
        boutonaffichage.setBackground(Color.gray);
        boutonaffichage.setForeground(Color.white);
        boutonaffichage.addActionListener(this);
        Panneaubouton.add(boutonaffichage);

        boutonaffichage1 = new JButton("Valider les composants");
        boutonaffichage1.setBounds(Panneaubouton.getWidth()/4,200,200,50);
        boutonaffichage1.setBackground(Color.gray);
        boutonaffichage1.setForeground(Color.white);
        boutonaffichage1.addActionListener(this);
        Panneaubouton.add(boutonaffichage1);

        //affichage des menus déroulants

        for (int i=0;i<4;i++){

            if(i==0){
                tableaumenu[i] = new ItemComposant(sourcestension);
                tableaumenu[i].setLocation(40,Panneausysteme.getHeight()/2-50);
                estvertical[i]=true;
            }else{
                tableaumenu[i]= new ItemComposant(autrescomposants);
                if(i==1) {
                    tableaumenu[i].setLocation(Panneausysteme.getWidth() / 2 - 50, 80); //composant d'en haut
                    estvertical[i]=false;
                }
                if(i==2){
                    tableaumenu[i].setLocation(Panneausysteme.getWidth()/2+350,Panneausysteme.getHeight()/2-50); //composant de droite
                    estvertical[i]=true;
                }
                if(i==3){
                    tableaumenu[i].setLocation(Panneausysteme.getWidth()/2-50,Panneausysteme.getHeight()/2+270); // composant d'en bas
                    estvertical[i]=false;
                }
            }
            zonedessin.add(tableaumenu[i]);
        }

        //création panneau principal

        JPanel Panneaumain = new JPanel();
        Panneaumain.setBounds(0,0,largeur,hauteur);
        Panneaumain.setLayout(null);
        Panneaumain.setBackground(Color.gray);

        Panneaumain.add(Panneaubouton);
        Panneaumain.add(Panneausysteme);

        //ajout du panneau à la fenêtre

        add(Panneaumain);

    }

    //methode actionperformed

    public void actionPerformed (ActionEvent e) {

        if (e.getSource()==boutonaffichage1){

            boutonvalider.setVisible(true);
            remplacemenu(tableaumenu, estvertical);
            Panneausysteme.repaint();

        }

    }

    //methode qui remplace les menus deroulants par des dessins correspondants aux composants selectionnes

    public void remplacemenu(ItemComposant[] tab, boolean[]tab1){


        for (int j=0;j<tab.length;j++) {

            if(tab[j].getItem()=="Résistance"){

                icone= new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("résistance.png")));
                jlabel = new JLabel(icone);
                jlabel.setLayout(null);
                jlabel.setBounds(tab[j].getX(),tab[j].getY(),300,50);
                jlabel.setVisible(true);
                tab[j].setVisible(false);
                zonedessin.add(jlabel);

                if(tab1[j]==true){
                    tourneimage(90,jlabel,icone);
                    jlabel.setBounds(tab[j].getX(),tab[j].getY(),50,300);
                }

            }

            if(tab[j].getItem()=="Bobine"){

                icone= new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("bobines.png")));
                jlabel = new JLabel(icone);
                jlabel.setLayout(null);
                jlabel.setBounds(tab[j].getX(),tab[j].getY(),300,50);
                jlabel.setVisible(true);
                tab[j].setVisible(false);
                zonedessin.add(jlabel);

                if(tab1[j]==true){
                    tourneimage(90,jlabel,icone);
                    jlabel.setBounds(tab[j].getX(),tab[j].getY(),50,300);
                }


            }

            if(tab[j].getItem()=="Condensateur"){

                icone= new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("condo.png")));
                jlabel = new JLabel(icone);
                jlabel.setLayout(null);
                jlabel.setBounds(tab[j].getX(),tab[j].getY(),300,50);
                jlabel.setVisible(true);
                tab[j].setVisible(false);
                zonedessin.add(jlabel);

                if(tab1[j]==true){
                    tourneimage(90,jlabel,icone);
                    jlabel.setBounds(tab[j].getX(),tab[j].getY(),50,300);
                }

            }

            if(tab[j].getItem()=="source de 5V" || tab[j].getItem()=="source de 12 V"){

                icone= new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("sourceU.png")));
                jlabel = new JLabel(icone);
                jlabel.setLayout(null);
                jlabel.setBounds(tab[j].getX(),tab[j].getY(),300,50);
                jlabel.setVisible(true);
                tab[j].setVisible(false);
                zonedessin.add(jlabel);

                if(tab1[j]==true){
                    tourneimage(90,jlabel,icone);
                    jlabel.setBounds(tab[j].getX(),tab[j].getY(),50,300);
                }

            }
        }
    }

    public void tourneimage(int angle, JLabel lelabel, ImageIcon imageselectionnee) {
        int w = lelabel.getIcon().getIconWidth();
        int h = lelabel.getIcon().getIconHeight();
        int type = BufferedImage.TYPE_INT_RGB;  // other options, see api

        BufferedImage DaImage = new BufferedImage(h, w, type);
        Graphics2D g2 = DaImage.createGraphics();

        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);

        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(imageselectionnee.getImage(), at, lelabel);
        g2.dispose();

        lelabel.setIcon(new ImageIcon(DaImage));
    }


}
