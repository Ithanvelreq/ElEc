import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreA_Bis extends JFrame implements ActionListener {

    //caractéristiques écran
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteurEcran = (int)tailleEcran.getHeight()-40; //on recupère la hauteur de l'écran --> l'ajout du -40 correspond à la taille de la barre des taches
    int largeurEcran= (int)tailleEcran.getWidth(); //on récupère la largeur de l'écran


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
    JTextField[] tableauzonetexte;  //regroupe tous les chp de saisie
    boolean composantvalide;        //savoir si le système a été validé
    int taillePoliceCaractere;      //taille police caractère selon résolution
    String[] w; //tableau rassemblant les inconnues du système d'équations
    Impedance[] z; //tableau rassemblant les solutions du système d'équations
    ItemResultat[] LabelaffichageRes;

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

        taillePoliceCaractere=setTaillePolice();

        boutonvalidation = new JButton("Valider les composants");
        boutonvalidation.setBounds(PanelGestion.getWidth()/4,(int)(PanelGestion.getHeight()*3.5/5),PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonvalidation.setBackground(Color.gray);
        boutonvalidation.setForeground(Color.white);
        boutonvalidation.setFont(new Font("Arial", Font.BOLD,taillePoliceCaractere));
        boutonvalidation.addActionListener(this);
        PanelGestion.add(boutonvalidation);

        boutonreinit = new JButton("Réinitialiser");
        boutonreinit.setBounds(PanelGestion.getWidth()/4,boutonvalidation.getY()+boutonvalidation.getHeight()+30,PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonreinit.setBackground(Color.gray);
        boutonreinit.setForeground(Color.white);
        boutonreinit.setFont(new Font("Arial", Font.BOLD,taillePoliceCaractere));
        boutonreinit.addActionListener(this);
        PanelGestion.add(boutonreinit);

        boutonResultat = new JButton("Afficher les résultats");
        boutonResultat.setBounds(PanelGestion.getWidth()/4,boutonreinit.getY()+boutonreinit.getHeight()+30,PanelGestion.getWidth()/2,PanelGestion.getHeight()/20);
        boutonResultat.setBackground(Color.gray);
        boutonResultat.setForeground(Color.white);
        boutonResultat.setFont(new Font("Arial", Font.BOLD,taillePoliceCaractere));
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

    //METHODE

    /**
     * @param taille : nb de JtextField à regrouper
     * @return : un tableau contenant tous les JTextField
     */
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

    /**
     * génère et postionne l'ensemble des ItemElements de chaque élément du circuit
     * @return : un tableau regroupant les ItemElements
     */
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

    /**
     * permet de définir la taille de police de caractère adéquat à l'écran
     * @return : la bonne taille
     */
    public int setTaillePolice(){
        int r=11; //défaut
        if(hauteurEcran==1040 && largeurEcran==1920){   //resolution full HD
            r=18;
        }
        if(hauteurEcran==728 && largeurEcran==1366) {   //resolution HD+
            return 12;
        }
        return r;
    }

    /**
     * permet d'afficher les résultats pour chaque composant
     * @param resultats : résultats numériques
     * @param tableaumenu : tab des composants
     * @return : tab contenant les JPanel présentant les résultats
     */
    public ItemResultat[] afficheResultat(Impedance[] resultats, ItemElement[] tableaumenu){

        ItemResultat[] tabRes = new ItemResultat[tableaumenu.length-1];
        for (int i = 1; i<=tabRes.length; i++){
            tabRes[i-1]= new ItemResultat(resultats[i+3].getRho(),resultats[i].getRho());
            //tabRes[i-1].setLocation(tableaumenu[i].getX(),tableaumenu[i].getY()+tableaumenu[i].getHeight());
            tabRes[i-1].setLocation(10,5*i);
        }
        for (ItemResultat i : tabRes){
            PanelCircuit.add(i);
            //i.setVisible(true);
        }
        return tabRes;
    }


    //méthode évènement
    public void actionPerformed (ActionEvent e){
        //vérifie si les valeurs rentrées dans les JTextfield sont correctes
        if (e.getSource()==boutonvalidation){
            for(int i=0; i<4;i++) {
                while (tableauzonetexte[i].getText().equals("") ||Double.parseDouble(tableauzonetexte[i].getText()) > 30000 || Double.parseDouble(tableauzonetexte[i].getText()) <=0) {
                    JOptionPane.showMessageDialog(this, "Veuillez rentrer une valeur de R, L ou C correcte (entre 0 et 30000 USI) !");
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

        if (e.getSource()==boutonResultat) {
            //fait apparaitre la fenetre de l'oscilloscope pour visualiser les courbes
            CircuitA circuitCalcul = new CircuitA(tableaumenu);
            w = circuitCalcul.inconnues();
            z = circuitCalcul.solutions();
            LabelaffichageRes = afficheResultat(z,tableaumenu);
            oscillo = new Fenetreoscillo(w,z,tableaumenu);
            oscillo.setVisible(true);
        }

        //reinitialise les composants et les valeurs au besoin
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
}
