import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Fenetre d'affichage pour le circuit C, celui d'en bas a droite dans la fenetre principale
 */
public class FenetreC_Bis extends JFrame implements ActionListener {

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
     * Tableau de menu deroulants
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
     * Constructeur
     */
    public FenetreC_Bis(){

        //caractéristiques fenêtre
        this.setSize(largeurEcran,hauteurEcran);			// taille de la fenêtre
        this.setLocation(0,0);		//position de la fenêtre
        this.setVisible(false);			//visibilité de la fenêtre
        this.setTitle("circuit C");     //titre
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
        dessinCircuit = new Trace_Circuit(2,PanelCircuit.getHeight(),PanelCircuit.getWidth());
        PanelCircuit.add(dessinCircuit);
    }

    //METHODE

    /**
     * Methode qui regroupe tous les JtextField sous un meme tableau
     * @param taille Nombre de JTextField a regrouper
     * @return Un tableau contenant tous les JTextField
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
     * Genere et positionne l'ensemble des ItemElements de chaque element du circuit
     * @return Un tableau regroupant les ItemElements
     */
    public ItemElement[] SetUpItemElement(){

        ItemElement[] r = new ItemElement[4];
        r[0] = new ItemGenerateur();  //générateur à gauche
        r[0].setLocation((PanelCircuit.getWidth()/9)-80,(PanelCircuit.getHeight()/2)-60);
        estvertical[0] = true;

        r[1] = new ItemComposant(listeComposants, 1);
        r[1].setLocation((PanelCircuit.getWidth()*25/36)-60,(PanelCircuit.getHeight()/10)-37); //composant d'en haut
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
     * Permet de definir la taille de police de caractere adequiat a l'ecran
     * @return La bonne taille
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
     * @param resultats Resultats numeriques
     * @param tableaumenu Tableau des composants
     * @return Tableau contenant les JPanel
     */
    public ItemResultat[] afficherResultat(Impedance[] resultats, ItemElement[] tableaumenu){

        ItemResultat[] tabRes = new ItemResultat[tableaumenu.length-1];

        //composant milieu
        tabRes[0] = new ItemResultat(resultats[4] , resultats[1]);
        tabRes[0].setLocation(tableaumenu[3].getX()-tabRes[0].getWidth(), tableaumenu[3].getY());
        //composant haut
        tabRes[1] = new ItemResultat(resultats[5],resultats[2]);
        tabRes[1].setLocation(tableaumenu[1].getX(), tableaumenu[1].getY()+tableaumenu[1].getHeight());
        //composant droite
        tabRes[2]=new ItemResultat(resultats[6],resultats[3]);
        tabRes[2].setLocation(tableaumenu[2].getX()-tabRes[2].getWidth(), tableaumenu[2].getY());

        for (ItemResultat i : tabRes){
            PanelCircuit.add(i);
        }
        return tabRes;
    }

    /**
     * Permet de cacher les resultats numeriques pour chaque composant
     */
    public void cacherResultat(){
        for (ItemResultat r : Label_Affichage_Res){
            PanelCircuit.remove(r);
        }
        repaint();
    }


    //méthode évènement
    public void actionPerformed (ActionEvent e){
        //vérifie si les valeurs rentrées dans les JTextfield sont correctes
        if (e.getSource()==boutonvalidation){
            for(int i=0; i<4;i++) {
                while (tableauzonetexte[i].getText().equals("") ||Double.parseDouble(tableauzonetexte[i].getText()) > 30000 || Double.parseDouble(tableauzonetexte[i].getText()) <=0) {
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

        if (e.getSource()==boutonResultat) {
            //fait apparaitre la fenetre de l'oscilloscope pour visualiser les courbes
            CircuitC circuitCalcul = new CircuitC(tableaumenu);
            w = circuitCalcul.inconnues();
            z = circuitCalcul.solutions();

            //affichages résultats pour chaque composant
            Label_Affichage_Res = afficherResultat(z,tableaumenu);
            repaint();

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
            cacherResultat();
        }
    }
}