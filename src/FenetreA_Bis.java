import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreA_Bis extends JFrame implements ActionListener {

    //caractéristiques écran
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteurEcran = (int)tailleEcran.getHeight()-40; //ajout du -40 qui correspond à la taille de la barre des taches
    int largeurEcran= (int)tailleEcran.getWidth();


    //attributs_widgets
        //JPanel principaux
    JPanel PanelMain;
    JPanel PanelCircuit;
    JPanel PanelGestion;
    Trace_Circuit dessinCircuit = new Trace_Circuit();
        //JButton
    JButton boutonvalider;
    JButton boutonaffichage1;
    JButton boutonreinit;
        //variables de travail
    ItemElement[] tableaumenu = new ItemElement[4]; // tableau de menu déroulants
    boolean[] estvertical = new boolean[4]; // tableau pour savoir si les menus sont sur un segment vertical ou non
    String[] listeComposants = {"Résistance", "Bobine", "Condensateur"};  //tableau permettant la selection des elements des menus deroulants
    JTextField[] tableauzonetexte;

    //constructeur
    public FenetreA_Bis(){

        //caractéristiques fenêtre
        this.setSize(largeurEcran,hauteurEcran);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setTitle("circuit A");     //titre
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //PanelMain = pour travailler sur les dimensions exploitables de la fenêtre
        PanelMain = new JPanel();
        PanelMain.setLayout(null);
        PanelMain.setBounds(0,0,largeurEcran,hauteurEcran-this.getInsets().top);
        this.add(PanelMain);

        //PanelGestion : Panel de droite : menu principal
        PanelGestion = new JPanel();
        PanelGestion.setLayout(null);
        PanelGestion.setBounds(PanelMain.getWidth()*9/12,0,PanelMain.getWidth()*3/12,PanelMain.getHeight());
        PanelGestion.setBackground(new Color(72, 79, 81));
        PanelMain.add(PanelGestion);

            //mise en place des boutons

        boutonaffichage1 = new JButton("Valider les composants");
        boutonaffichage1.setBounds(PanelGestion.getWidth()/4,(int)(PanelGestion.getHeight()*3.5/5),PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonaffichage1.setBackground(Color.gray);
        boutonaffichage1.setForeground(Color.white);
        boutonaffichage1.setFont(new Font("Arial", Font.BOLD,18));
        boutonaffichage1.addActionListener(this);
        PanelGestion.add(boutonaffichage1);

        boutonreinit = new JButton("Réinitialiser");
        boutonreinit.setBounds(PanelGestion.getWidth()/4,boutonaffichage1.getY()+boutonaffichage1.getHeight()+30,PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonreinit.setBackground(Color.gray);
        boutonreinit.setForeground(Color.white);
        boutonreinit.setFont(new Font("Arial", Font.BOLD,18));
        boutonreinit.addActionListener(this);
        PanelGestion.add(boutonreinit);

        boutonvalider = new JButton("Afficher les résultats");
        boutonvalider.setBounds(PanelGestion.getWidth()/4,boutonreinit.getY()+boutonreinit.getHeight()+30,PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonvalider.setBackground(Color.gray);
        boutonvalider.setForeground(Color.white);
        boutonvalider.setFont(new Font("Arial", Font.BOLD,18));
        boutonvalider.addActionListener(this);
        PanelGestion.add(boutonvalider);
        boutonvalider.setVisible(false);

        //PanelCircuit : panel de gauche : visualisation du circuit
        PanelCircuit = new JPanel();
        PanelCircuit.setLayout(null);
        PanelCircuit.setBounds(0,0,PanelMain.getWidth()*3/4,PanelMain.getHeight());
        PanelCircuit.setBackground(new Color(228,229,230));
        PanelMain.add(PanelCircuit);

            //mise en place des ItemsElement (paramétrages des composants)
        // les ItemElement ont des tailles définies fixes.

        tableaumenu[0] = new ItemGenerateur();  //générateur à gauche
        tableaumenu[0].setLocation((PanelCircuit.getWidth()/9)-80,(PanelCircuit.getHeight()/2)-60);
        estvertical[0] = true;

        tableaumenu[1] = new ItemComposant(listeComposants, 1);
        tableaumenu[1].setLocation((PanelCircuit.getWidth()/2)-60,(PanelCircuit.getHeight()/10)-37); //composant d'en haut
        estvertical[1] = false;

        tableaumenu[2] = new ItemComposant(listeComposants, 2);
        tableaumenu[2].setLocation((PanelCircuit.getWidth()*8/9)-60,(PanelCircuit.getHeight()/2)-37); //composant de droite
        estvertical[2] = true;

        tableaumenu[3] = new ItemComposant(listeComposants, 3);
        tableaumenu[3].setLocation((PanelCircuit.getWidth()/2)-60, (PanelCircuit.getHeight()*9/10)-37); // composant d'en bas
        estvertical[3] = false;

        for (ItemElement i : tableaumenu){
            PanelCircuit.add(i); //on ajoute l'ItemComposant à la zone de dessin
        }

        tableauzonetexte = this.regrouperJTextField(tableaumenu.length+1);




    }

    //méthode
    public JTextField[] regrouperJTextField(int taille){
        JTextField[] r = new JTextField[taille];

        for (int i=0;i<tableaumenu.length;i++){
            if(tableaumenu[i] instanceof ItemGenerateur){
                ItemGenerateur x = (ItemGenerateur) tableaumenu[i];
                r[i]=x.saisieAmpl;
                r[i+1]=x.saisieFreq;
            }
            if(tableaumenu[i] instanceof ItemComposant){
                ItemComposant x = (ItemComposant)tableaumenu[i];
                r[i+1]=x.saisie;
            }
        }
        return r;
    }

    //méthode évènement
    public void actionPerformed (ActionEvent e){}
}
