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
        //Fenetre attribut
    Fenetreoscillo oscillo;
        //JPanel principaux
    JPanel PanelMain;
    JPanel PanelCircuit;
    JPanel PanelGestion;
    Trace_Circuit dessinCircuit;
        //JButton
    JButton boutonvalidation;
    JButton boutonResultat;
    JButton boutonreinit;
        //variables de travail
    ItemElement[] tableaumenu; // tableau de menu déroulants
    boolean[] estvertical = new boolean[4]; // tableau pour savoir si les menus sont sur un segment vertical ou non
    String[] listeComposants = {"Resistance", "Bobine", "Condensateur"};  //tableau permettant la selection des elements des menus deroulants
    JTextField[] tableauzonetexte;
    boolean composantvalide = false;

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

        boutonvalidation = new JButton("Valider les composants");
        boutonvalidation.setBounds(PanelGestion.getWidth()/4,(int)(PanelGestion.getHeight()*3.5/5),PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonvalidation.setBackground(Color.gray);
        boutonvalidation.setForeground(Color.white);
        boutonvalidation.setFont(new Font("Arial", Font.BOLD,18));
        boutonvalidation.addActionListener(this);
        PanelGestion.add(boutonvalidation);

        boutonreinit = new JButton("Réinitialiser");
        boutonreinit.setBounds(PanelGestion.getWidth()/4,boutonvalidation.getY()+boutonvalidation.getHeight()+30,PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonreinit.setBackground(Color.gray);
        boutonreinit.setForeground(Color.white);
        boutonreinit.setFont(new Font("Arial", Font.BOLD,18));
        boutonreinit.addActionListener(this);
        PanelGestion.add(boutonreinit);

        boutonResultat = new JButton("Afficher les résultats");
        boutonResultat.setBounds(PanelGestion.getWidth()/4,boutonreinit.getY()+boutonreinit.getHeight()+30,PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonResultat.setBackground(Color.gray);
        boutonResultat.setForeground(Color.white);
        boutonResultat.setFont(new Font("Arial", Font.BOLD,18));
        boutonResultat.addActionListener(this);
        PanelGestion.add(boutonResultat);
        boutonResultat.setVisible(false);

        //PanelCircuit : panel de gauche : visualisation du circuit
        PanelCircuit = new JPanel();
        PanelCircuit.setLayout(null);
        PanelCircuit.setBounds(0,0,PanelMain.getWidth()*3/4,PanelMain.getHeight());
        PanelCircuit.setBackground(new Color(228,229,230));
        PanelMain.add(PanelCircuit);

            //mise en place des ItemsElement (paramétrages des composants)
        // les ItemElement ont des tailles définies fixes.

        tableaumenu=SetUpItemElement();
        for (ItemElement i : tableaumenu){
            PanelCircuit.add(i); //on ajoute l'ItemComposant
        }

        tableauzonetexte = this.regrouperJTextField(tableaumenu.length+1);

            //on trace désormais le circuit
        dessinCircuit = new Trace_Circuit(1,PanelCircuit.getHeight(),PanelCircuit.getWidth());
        PanelCircuit.add(dessinCircuit);

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
    public void actionPerformed (ActionEvent e){
        if (e.getSource()==boutonvalidation){
            for(int i=0; i<4;i++) {
                while (tableauzonetexte[i].getText().equals("") ||Integer.parseInt(tableauzonetexte[i].getText()) > 10000 || Integer.parseInt(tableauzonetexte[i].getText()) < 1) {
                    JOptionPane.showMessageDialog(this, "Veuillez rentrer une valeur de R, L ou C correcte (entre 1 et 10000 USI) !");
                    tableauzonetexte[i].setText("Changer"); // le fait de faire apparaitre changer fait apparaitre des messages d'erreur dans la console mais ce n'est pas grave, c'est parce que le TextField n'est pas censé pouvoir contenir du texte
                }
            }
            boutonResultat.setVisible(true);
            composantvalide=true;
            //on dessine les composants correspondants
            for(int j=0;j<4;j++){
                tableaumenu[j].dessine(true,estvertical[j]);
            }
        }
    /*
        if (e.getSource()==boutonvalider) {

            oscillo = new Fenetreoscillo(tableaumenu);
            oscillo.setVisible(true);
            CircuitA circuitCalcul = new CircuitA(tableaumenu);
            String [] w = circuitCalcul.inconnues();
            Impedance []z = circuitCalcul.solutions();
            System.out.println("xx");

        }
        */
        if (e.getSource()==boutonreinit) {
            if (composantvalide){
                boutonResultat.setVisible(false);
                for (int k = 0; k < 4; k++) {
                    tableaumenu[k].dessine(false ,estvertical[k]);
                }
            }
            composantvalide=false;
        }


    }

    public ItemElement[] SetUpItemElement(){

        ItemElement[] r = new ItemElement[4];
        r[0] = new ItemGenerateur();  //générateur à gauche
        r[0].setLocation((PanelCircuit.getWidth()/9)-80,(PanelCircuit.getHeight()/2)-60);
        estvertical[0] = true;

        r[1] = new ItemComposant(listeComposants, 1);
        r[1].setLocation((PanelCircuit.getWidth()/2)-60,(PanelCircuit.getHeight()/10)-37); //composant d'en haut
        estvertical[1] = false;

        r[2] = new ItemComposant(listeComposants, 2);
        r[2].setLocation((PanelCircuit.getWidth()*8/9)-60,(PanelCircuit.getHeight()/2)-37); //composant de droite
        estvertical[2] = true;

        r[3] = new ItemComposant(listeComposants, 3);
        r[3].setLocation((PanelCircuit.getWidth()/2)-60, (PanelCircuit.getHeight()*9/10)-37); // composant d'en bas
        estvertical[3] = false;

        return r;
    }
}
