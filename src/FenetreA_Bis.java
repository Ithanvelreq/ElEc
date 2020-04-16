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
    JCheckBox[] choixResultat;
    //variables de travail
    ItemElement[] tableaumenu; // tableau des items éléments pour paramétrer les composants
    boolean[] estvertical = new boolean[4]; // tableau pour savoir si les menus sont sur un segment vertical ou non
    String[] listeComposants = {"Resistance", "Bobine", "Condensateur"};  //tableau permettant la selection des elements des menus deroulants
    JTextField[] tableauzonetexte;  //regroupe tous les chp de saisie
    boolean composantvalide;        //savoir si le système a été validé
    boolean ResultatAffiche;        //savoir si les résultats sont affichés
    int taillePoliceCaractere;      //taille police caractère selon résolution
    String[] w; //tableau rassemblant les inconnues du système d'équations
    Impedance[] z; //tableau rassemblant les solutions du système d'équations
    ItemResultat[] Label_Affichage_Res;  //tableau des JPanel qui affichent les résultats numériques
    boolean oscilloDisplayed; //savoir si la fenêtre de l'oscillo est ouverte


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

        // mise en place des CheckBox pour le choix des résultats
        choixResultat = SetUpCheckBoxResultats();

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
     * méthode qui génère les CheckBox pour le choix des résultats à afficher pour l'utilisateur
     * @return : tab contenant les 2 box
     */
    public JCheckBox[] SetUpCheckBoxResultats(){

        JCheckBox box1 = new JCheckBox("Courant & tension des composants");
        box1.setFont(new Font("Arial", Font.BOLD,taillePoliceCaractere));
        box1.setForeground(Color.white);
        box1.setBackground(new Color(72, 79, 81));
        box1.setBounds(PanelGestion.getWidth()/9,PanelGestion.getHeight()/5,PanelGestion.getWidth()*7/9,PanelGestion.getHeight()/30);
        box1.addActionListener(this);
        PanelGestion.add(box1);
        box1.setVisible(false);

        JCheckBox box2 = new JCheckBox("Oscilloscope");
        box2.setFont(new Font("Arial", Font.BOLD,taillePoliceCaractere));
        box2.setForeground(Color.white);
        box2.setBackground(new Color(72, 79, 81));
        box2.setBounds(PanelGestion.getWidth()/9,PanelGestion.getHeight()/5+box1.getHeight(),PanelGestion.getWidth()*7/9,PanelGestion.getHeight()/30);
        box2.addActionListener(this);
        PanelGestion.add(box2);
        box2.setVisible(false);

        JCheckBox[] r = {box1,box2};

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
    public ItemResultat[] afficherResultat(Impedance[] resultats, ItemElement[] tableaumenu, boolean[] estvertical){

        ItemResultat[] tabRes = new ItemResultat[tableaumenu.length-1];

        for (int i = 1; i<=tabRes.length; i++) {
            tabRes[i - 1] = new ItemResultat(resultats[i + 3], resultats[i]);
            if (estvertical[i]) {
                tabRes[i - 1].setLocation(tableaumenu[i].getX()-(tabRes[i-1].getWidth()), tableaumenu[i].getY());
            }
            if (!estvertical[i]) {
                tabRes[i - 1].setLocation(tableaumenu[i].getX(), tableaumenu[i].getY() + tableaumenu[i].getHeight());
            }
        }
        //position particulière pour le composant du bas
        tabRes[2].setLocation(tableaumenu[3].getX(),tableaumenu[3].getY()-tabRes[2].getHeight());
        for (ItemResultat i : tabRes){
            PanelCircuit.add(i);
        }
        return tabRes;
    }

    /**
     * permet de cacher les résultats numériques pour chaque composant
     */
    public void cacherResultat(){
        for (ItemResultat r : Label_Affichage_Res){
           PanelCircuit.remove(r);
        }
        repaint();
    }


    //méthode évènement
    public void actionPerformed (ActionEvent e){

        if (e.getSource()==boutonvalidation){  //bouton "Valider les composants" <> étape 1

            //vérifie si les valeurs rentrées dans les JTextfield sont correctes
            for(int i=0; i<4;i++) {
                while (tableauzonetexte[i].getText().equals("") ||Double.parseDouble(tableauzonetexte[i].getText()) > 30000 || Double.parseDouble(tableauzonetexte[i].getText()) <=0) {
                    JOptionPane.showMessageDialog(this, "Veuillez rentrer une valeur de R, L ou C correcte (entre 0 et 30000 USI) !");
                    tableauzonetexte[i].setText("Changer"); // le fait de faire apparaitre changer fait apparaitre des messages d'erreur dans la console mais ce n'est pas grave, c'est parce que le TextField n'est pas censé pouvoir contenir du texte
                }
            }

            //on affiche le bouton suivant
            boutonResultat.setVisible(true);
            //on enregistre le fait que les composants soient validés
            composantvalide=true;

            //on dessine les composants correspondants aux choix de l'utilisateur
            for(int j=0;j<4;j++){
                tableaumenu[j].dessine(true,estvertical[j]);
            }

            //on calcule numériquement les solutions du circuit
            CircuitA circuitCalcul = new CircuitA(tableaumenu);
            w = circuitCalcul.inconnues();
            z = circuitCalcul.solutions();

            //on génère les résultats sans les afficher
            ResultatAffiche = false;
            Label_Affichage_Res=afficherResultat(z,tableaumenu,estvertical);
            cacherResultat();
            oscillo = new Fenetreoscillo(w,z,tableaumenu);
            oscillo.setVisible(false);
            oscilloDisplayed=false;
        }

        if (e.getSource()==boutonResultat) {  //bouton "Afficher les résultats" > permet de proposer les différents résultats que l'utilisateur souhaite voir
            //on enregistre que l'on présente les résultats
            ResultatAffiche = true;
            //on affiche le choix des résultats à afficher
            choixResultat[0].setVisible(true);
            choixResultat[1].setVisible(true);
        }

        //Réinitialisation
        if (e.getSource()==boutonreinit) {

            //on vérifie si les composants ont été validé
            if (composantvalide) {
                boutonResultat.setVisible(false);
                //on retire les dessins de chaque composant
                for (int k = 0; k < 4; k++) {
                    tableaumenu[k].dessine(false, estvertical[k]);
                }
            }
            //on déclare les composants comme plus valide
            composantvalide = false;

            //on regarde si des résultats sont affichés
            if (ResultatAffiche) {
                //on cache toutes les formes de résultats
                oscillo.setVisible(false);
                ResultatAffiche=false;
                cacherResultat();
                for (JCheckBox t : choixResultat) {
                    t.setVisible(false);
                    t.setSelected(false);
                }
                repaint();
            }
        }

        //Action des JCheckBox:
        //affichage résultats numériques
        if(choixResultat[0].isSelected()){
            //affichages des résultats pour chaque composant
            Label_Affichage_Res = afficherResultat(z,tableaumenu,estvertical);
            repaint();
        }
        //affichage de l'oscilloscope
        if(choixResultat[1].isSelected() && !oscilloDisplayed){
            //ouverture du tracé à l'oscilloscope
            oscillo.setVisible(true);
            oscilloDisplayed = true;
        }
        //cacher les résultats numériques
        if(!choixResultat[0].isSelected()){
            cacherResultat();
            repaint();
        }
        //on cache l'oscilloscope
        if(!choixResultat[1].isSelected()){
            oscilloDisplayed=false;
            oscillo.setVisible(false);
        }
    }
}
