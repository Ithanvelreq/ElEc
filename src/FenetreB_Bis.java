package ElEc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre d'affichage pour le circuit B, celui d'en haut a droite dans la fenetre principale
 */
public class FenetreB_Bis extends JFrame implements ActionListener {

    /**
     * Caracteristiques de l'ecran
     */
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * On recupere la hauteur de l'ecran
     */
    int hauteurEcran = (int)tailleEcran.getHeight()-40; //l'ajout du -40 correspond à la taille de la barre des taches
    /**
     * On recupere la largeur de l'ecran
     */
    int largeurEcran= (int)tailleEcran.getWidth();
    //attributs_widgets
    /**
     * Fenetre attribut
     */
    Fenetreoscillo oscillo;
    /**
     * JPanel principaux
     */
    JPanel PanelMain;
    /**
     * JPanel principaux
     */
    JPanel PanelCircuit;
    /**
     * JPanel principaux
     */
    JPanel PanelGestion;
    /**
     * JPanel principaux
     */
    Trace_Circuit dessinCircuit;
    /**
     * JButton pour l'interface
     */
    JButton boutonvalidation;
    /**
     * JButton pour l'interface
     */
    JButton boutonResultat;
    /**
     * JButton pour l'interface
     */
    JButton boutonreinit;
    //variables de travail
    /**
     * JCheckBox qui permet de selectionner l'affichage de l'oscillo ou des valeurs des composants
     */
    JCheckBox[] choixResultat;
    //variables de travail
    /**
     * Tableau des items elements pour parametrer les composants
     */
    ItemElement[] tableaumenu;
    /**
     * Tableau pour savoir si les menus sont sur un segment vertical ou non
     */
    boolean[] estvertical = new boolean[4];
    /**
     * Tableau permettant la selection des elements des menus deroulants
     */
    String[] listeComposants = {"Resistance", "Bobine", "Condensateur"};
    /**
     * Regroupe tous les champs de saisie
     */
    JTextField[] tableauzonetexte;
    /**
     * Savoir si le systeme a ete valide
     */
    boolean composantvalide;
    /**
     * Savoir si les resultats sont affiches
     */
    boolean ResultatAffiche;
    /**
     * Taille police caractere selon resolution
     */
    int taillePoliceCaractere;
    /**
     * Tableau rassemblant les inconnues du systeme d'equations
     */
    String[] w;
    /**
     * Tableau rassemblant les solutions du systeme d'equations
     */
    Impedance[] z;
    /**
     * Tableau des JPanel qui affichent les resultats numeriques
     */
    ItemResultat[] Label_Affichage_Res;
    /**
     * Savoir si la fenetre de l'oscillo est ouverte
     */
    boolean oscilloDisplayed;
    /**
     * Parametres regulants l'usage des boutons et des JCheckbox dans le programme (empeche certains bug)
     */
    public int j=0;
    /**
     * Parametres regulants l'usage des boutons et des JCheckbox dans le programme (empeche certains bug)
     */
    public int k=0;

    /**
     * Constructeur de la fenetre
     */
    public FenetreB_Bis(){

        //caractéristiques fenêtre
        this.setSize(largeurEcran,hauteurEcran);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setTitle("circuit B");     //titre
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
        dessinCircuit = new Trace_Circuit(2,PanelCircuit.getHeight(),PanelCircuit.getWidth());
        PanelCircuit.add(dessinCircuit);
    }

    //METHODE

    /**
     * Regroupe les JTextfields sous forme d'un tableau
     * @param taille  Nombre de JtextField a regrouper
     * @return  Un tableau contenant tous les JTextField
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
     * Genere et postionne l'ensemble des ItemElements de chaque element du circuit
     * @return  Un tableau regroupant les ItemElements
     */
    public ItemElement[] SetUpItemElement(){

        ItemElement[] r = new ItemElement[4];
        r[0] = new ItemGenerateur();  //générateur à gauche
        r[0].setLocation((PanelCircuit.getWidth()/9)-80,(PanelCircuit.getHeight()/2)-60);
        estvertical[0] = true;

        r[1] = new ItemComposant(listeComposants, 1);
        r[1].setLocation((PanelCircuit.getWidth()*11/36)-60,(PanelCircuit.getHeight()/10)-37); //composant d'en haut
        estvertical[1] = false;

        r[2] = new ItemComposant(listeComposants, 2);
        r[2].setLocation((PanelCircuit.getWidth()*8/9)-60,(PanelCircuit.getHeight()/2)-37); //composant de droite
        estvertical[2] = true;

        r[3] = new ItemComposant(listeComposants, 3);
        r[3].setLocation((PanelCircuit.getWidth()/2)-60, (PanelCircuit.getHeight()/2)-37); // composant milieu
        estvertical[3] = true;

        return r;
    }

    /**
     * Methode qui genere les CheckBox pour le choix des resultats a afficher pour l'utilisateur
     * @return  Tableau contenant les 2 box
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
     * Permet de definir la taille de police de caractere adequat a l'ecran
     * @return  La bonne taille
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
     * Permet d'afficher les resultats pour chaque composant
     * @param resultats  Resultats numeriques
     * @param tableaumenu  Tableau des composants
     * @param estvertical Tableau pour verifier si le composant est verticale ou pas
     * @return  Tableau contenant les JPanel presentant les resultats
     */
    public ItemResultat[] afficherResultat(Impedance[] resultats, ItemElement[] tableaumenu, boolean[] estvertical){

        ItemResultat[] tabRes = new ItemResultat[tableaumenu.length-1];
        //composant haut
        tabRes[0] = new ItemResultat(resultats[4] , resultats[1]);
        tabRes[0].setLocation(tableaumenu[1].getX(), tableaumenu[1].getY() + tableaumenu[1].getHeight());
        //composant milieu
        tabRes[1] = new ItemResultat(resultats[5],resultats[2]);
        tabRes[1].setLocation(tableaumenu[3].getX()-(tabRes[1].getWidth()), tableaumenu[3].getY());
        //composant droite
        tabRes[2]=new ItemResultat(resultats[6],resultats[3]);
        tabRes[2].setLocation(tableaumenu[2].getX()-(tabRes[2].getWidth()), tableaumenu[2].getY());

        for (ItemResultat i : tabRes){
            PanelCircuit.add(i);
        }
        return tabRes;
    }

    /**
     * Permet de cacher les resultats numeriques pour chaque composant
     */
    public void cacherResultat(){
        for (int i=0;i<Label_Affichage_Res.length;i++){

            Label_Affichage_Res[i].intensite.setText("");
            Label_Affichage_Res[i].tension.setText("");
            Label_Affichage_Res[i].ValIntensite.setText("");
            Label_Affichage_Res[i].ValTension.setText("");
            PanelCircuit.remove(Label_Affichage_Res[i]);
        }
        repaint();
    }


    //méthode évènement
    public void actionPerformed (ActionEvent e){

        if (e.getSource()==boutonvalidation && k==0){  //bouton "Valider les composants" <> étape 1

            //vérifie si les valeurs rentrées dans les JTextfield sont correctes
            for(int i=0; i<tableauzonetexte.length;i++) {
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
            k++;

            //on calcule numériquement les solutions du circuit
            CircuitB circuitCalcul = new CircuitB(tableaumenu);
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
        if (e.getSource()==boutonreinit && k==1) {

            k--;

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
        if(choixResultat[0].isSelected() && j==0){
            //affichages des résultats pour chaque composant
            Label_Affichage_Res = afficherResultat(z,tableaumenu,estvertical);
            repaint();
            j++;
        }
        //affichage de l'oscilloscope
        if(choixResultat[1].isSelected() && !oscilloDisplayed){
            //ouverture du tracé à l'oscilloscope
            oscillo.setVisible(true);
            oscilloDisplayed = true;
        }
        //cacher les résultats numériques
        if(!choixResultat[0].isSelected() && j==1){
            cacherResultat();
            j--;
            repaint();
        }
        //on cache l'oscilloscope
        if(!choixResultat[1].isSelected()){
            oscilloDisplayed=false;
            oscillo.setVisible(false);
        }
    }
}