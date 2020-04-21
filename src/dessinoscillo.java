import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Classe qui va dessiner les courbes et les tracer a l'ecran
 */
public class dessinoscillo extends JComponent {
    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 7800853645256601960L;
    /**
     * Creation d'un tableau qui contiendra nos fonctions a tracer
     */
    public CurveFunction[] tabfct= new CurveFunction[4]; // création d'un tableau qui contiendra nos fonctions à tracer

    //récupération des dimensions de l'écran et calculs de variables qui nous seront utiles pour le dimensionnement des widgets
    /**
     * Caracteristiques de l'ecran
     */
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Hauteur utile de l'ecran
     */
    int hauteur = (int)tailleEcran.getHeight()-40;
    /**
     * largeur utile de l'ecran
     */
    int largeur = (int)tailleEcran.getWidth();
    /**
     * Variable qui vaut 75% de la hauteur pour dimensionner les panneaux
     */
    double h1= (double) 0.75*hauteur;
    /**
     * Variable qui vaut 25% de la hauteur pour dimensionner les panneaux
     */
    double h=(double) 0.25*hauteur;

    //variables de travail
    /**
     * Recuperation du tableau de Jcheckbox de la Fenetreoscillo pour en connaitre leur etat (coche ou decoche)
     */
    public JCheckBox[] tabcheckbox;
    /**
     * Tableau qui contiendra les couleurs des courbes
     */
    public Color[] tabcouleur = new Color[4];
    /**
     * Recuperation du tableau de solution du systeme d'equation
     */
    public Impedance[] z;
    /**
     * Creation d'un tableau qui nous permettra de stocker les modules des composants
     */
    public double[] tableaumodule = new double[4]; // c
    /**
     * Creation d'un tableau qui nous permettra de stocker les arguments des composants
     */
    public double [] tableauargument = new double[4];
    /**
     * Recuperation du tableau contenant les ItemElement, qui nous permettra de recuperer les donnees entrees dans les JtextField
     */
    public ItemElement[] tableaumenu;
    /**
     * Initialisation de la frequence
     */
    public double frequence;
    /**
     * Recuperation du panneau contenant les curseurs et les JCheckbox
     */
    public JPanel panneaudubas;

    //initialisation des paramètres de visualisation
    /**
     * Parametre de visuialisation
     */
    public double xmax=1;
    /**
     * Parametre de visuialisation
     */
    public double xmin=-1;
    /**
     * Parametre de visualisation
     */
    public double ymax=12;
    /**
     * Parametre de visualisation
     */
    public double ymin=-12;

    /**
     * Methode qui recupere les valeurs chiffrees des autres classes afin de tracer les courbes
     * @param tabcheckbox Tableau de checkbox qui contient les checkbox a afficher dans la fenetreoscillo
     * @param z Tableau contenant les impedances du circuit et calculees dans une des 4 fenetres (A B C ou D)
     * @param tableaumenu tableau d'ItemElement contenant tous les widgets et nous permettant de recuperer les informations qu'ils contiennent
     * @param panneaudubas JPanel que l'on recupere de la fenetreoscillo afin d'y ajouter des Jcheckbox
     */
    public dessinoscillo(JCheckBox[] tabcheckbox, Impedance[] z, ItemElement[] tableaumenu, JPanel panneaudubas){
        this.tabcheckbox=tabcheckbox;
        this.z=z;
        this.tableaumenu=tableaumenu;
        this.panneaudubas=panneaudubas;
        remplipanneau(); // méthode qui ajoute les curseurs sur la Fenetreoscillo

        // on implemente les fonctions de chaque composant.
        creerfonction();
        //on attribue une couleur à chaque fonction
        tabcouleur[0]= new Color( 200, 29, 32);
        tabcouleur[1]= new Color(14, 7, 200);
        tabcouleur[2]= new Color(10, 99, 7);
        tabcouleur[3]= new Color(0, 0, 0);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // place le fond blanc
        g2.setColor(new Color(228,229,230));
        g2.fillRect( 0, 0, largeur, (int) h1 );

        // dessine les axes
        g2.setColor( Color.black );
        g2.drawLine( 0, ((int) h1)/2, getWidth(), ((int) h1)/2);
        g2.drawLine( getWidth()/2, 0, getWidth()/2, ((int) h1));

        // dessine les valeurs au bord des axes
        g2.setColor( Color.BLACK );
        placeechelle(g2,xmin,xmax,ymin,ymax);

        // dessin de la courbe

        double pas = 0.001;

        for(int i=0;i<tabfct.length;i++) {
            g2.setColor(tabcouleur[i]);

            if(tabcheckbox[i].isSelected()==true) {
                //int oldX = xToPixel((int) xmin); //commence a tracer a partir de xmin
                int oldX = xToPixel(xmin);
                int oldY = yToPixel(tabfct[i].compute(xmin));

                for (double lx = xmin + pas; lx <= xmax + pas; lx += pas) {
                    int x = xToPixel(lx);
                    int y = yToPixel(tabfct[i].compute(lx));

                    g2.drawLine(x, y, oldX, oldY);

                    oldX = x;
                    oldY = y;
                }
                repaint();
            }
            repaint();
        }
    }
    /**
     * Converti la valeur x de la fonction en pixel afin d'avoir une courbe lisible a l'ecran
     * @param x Valeur horizontale a convertir en pixel
     * @return Valeur en pixel
     */
    private int xToPixel( double x ) {    // accepte un double entre la borne min et max sur les x
        return (int)( largeur * (x + xmax)/(2*xmax) );
    }

    /**
     * Converti la valeur y de la fonction en pixel afin d'avoir une courbe lisible a l'ecran
     * @param y Valeur verticale a convertir en pixel
     * @return Valeur en pixel
     */
    private int yToPixel( double y ) {   // accepte un double entre la borne min et max sur les y
        return (int)( ((int) h1) * (1 - (y+ymax)/(2*ymax)));
    }

    /**
     * Creation d'une interface qui nous permet de creer une fonction simplement et qui dependra d'un parametre x
     */
    public interface CurveFunction { public double compute( double x );}

    /**
     * Methode qui cree les fonctions qui seront tracees
     */
    public void creerfonction(){

        //récupération de la fréquence
        ItemGenerateur k = (ItemGenerateur) tableaumenu[0];
        frequence=k.getFrequence();

        //calcul des modules
        tableaumodule[0]=z[0].module();
        tableaumodule[1]= z[4].module();
        tableaumodule[2]= z[5].module();
        tableaumodule[3]= z[6].module();
        //calcul des arguments
        tableauargument[0] = z[0].argument();
        tableauargument[1] = z[4].argument();
        tableauargument[2] = z[5].argument();
        tableauargument[3] = z[6].argument();

        //rangement des fonctions dans le tableau
        //la notation ci-dessous est possible grâce à l'interface
        tabfct[0] = (x) -> tableaumodule[0]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x) + tableauargument[0]);
        tabfct[1] = (x) -> tableaumodule[1]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x) + tableauargument[1]);
        tabfct[2] = (x) -> tableaumodule[2]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x) + tableauargument[2]);
        tabfct[3] = (x) -> tableaumodule[3]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x) + tableauargument[3]);

    }

    /**
     * Methode qui fixe l'echelle des courbes qui seront affichees. On fixe ainsi que l'axe des x est affiche entre xmin et xmax et que celui des y est affiche entre ymin et ymax
     * @param g2 Element a dessiner
     * @param xmin Valeur minimale sur l'axe des x
     * @param xmax Valeur maximale sur l'axe des x
     * @param ymin Valeur minimale sur l'axe des y
     * @param ymax Valeur maximale sur l'axe des y
     */
    public void placeechelle(Graphics2D g2, double xmin, double xmax, double ymin, double ymax) {

        //mise en place de compteurs
        int i=0;
        int j=0;
        g2.drawString("0.0", (int) (0.48*largeur), (int) ((h1) * 0.48));

        //adaptation du nombre de valeur affichées sur l'axe suivant la valeur de xmax
        for ( double x=xmin; x<=xmax; x++) {

            if(xmax<=14 && xmax>=1){ // affichage standard entre 1 et 4
                int xaffiche = (int) (x*100);
                double position = i * ((largeur) / (2 * xmax));

                if (x > 0 && (Math.abs(xaffiche % 100)) < 10) {
                    if (position >= largeur && xmax<=10) {
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (0.98*largeur), (int) ((h1) * 0.54));
                    } else {
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                    }
                }

                if (x > 0 && (Math.abs(xaffiche % 100)) >= 10) {
                    if (position >= largeur && xmax<=10) {
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (0.98*largeur), (int) ((h1) * 0.54));
                    } else{
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                    }

                }

                if (x < 0 && (Math.abs(xaffiche % 100)) < 10) {
                    if (position >= largeur && xmax<=10) {
                        g2.drawString("-" + String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (0.98*largeur), (int) ((h1) * 0.54));
                    }else{
                        g2.drawString("-" + String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                    }
                }

                if (x < 0 && (Math.abs(xaffiche % 100)) >= 10) {
                    if (position >= largeur && xmax<=10) {
                        g2.drawString("-" + String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (0.98*largeur), (int) ((h1) * 0.54));
                    }else{
                        g2.drawString("-" + String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                    }
                }

                if(x==0){
                    //ligne présente pour éviter que le zéro ne s'affiche 2 fois
                }
                i++;
            }
            // affichage lorsque l'on passe en dessous de la valeur 1
            if(xmax<1){

                int xaffiche = (int) (x*100);
                double position = i * ((largeur) / (2 * xmax));

                if (x > 0 && (Math.abs(xaffiche % 100)) < 10) {
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                }

                if (x > 0 && (Math.abs(xaffiche % 100)) >= 10 && x!=0.49) {
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                }

                if (x < 0 && (Math.abs(xaffiche % 100)) < 10) {
                        g2.drawString("-" + String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + ".0" + String.valueOf(Math.abs(xaffiche % 100)), (int) (0.98*largeur), (int) ((h1) * 0.54));
                }

                if (x < 0 && (Math.abs(xaffiche % 100)) >= 10) {
                        g2.drawString("-" + String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (position), (int) ((h1) * 0.54));
                        g2.drawString(String.valueOf(Math.abs(xaffiche / 100)) + "." + String.valueOf(Math.abs(xaffiche % 100)), (int) (0.98*largeur), (int) ((h1) * 0.54));
                }

                if(x==0){
                    //ligne présente pour éviter que le zéro ne s'affiche 2 fois
                }
                i++;

            }

        }

        //adaptation du nombre de valeur affichées sur l'axe suivant la valeur de ymax
        for ( double y=ymin; y<=ymax; y++) {

            if(ymax<=8) {//si ymax est inférieur à 8
                if (y < 0) {
                    int yaffiche = (int) (-y);
                    g2.drawString(String.valueOf(yaffiche), (int) (largeur * 0.51), (int) (j * ((h1) / (2 * ymax))+20));
                }
                if (y > 0) {
                    int yaffiche = (int) (-y);
                    g2.drawString(String.valueOf(yaffiche), (int) (largeur * 0.51), (int) (j * ((h1) / (2 * ymax))));
                }
                j++;

            }

            if(ymax>8 && ymax<=16) {//si ymax est compris entre 8 et 16
                if (y < 0 && y%2==0) {
                    int yaffiche = (int) (-y);
                    g2.drawString(String.valueOf(yaffiche), (int) (largeur * 0.51), (int) (j * ((h1) / (2 * ymax))+20));
                }
                if (y > 0 && y%2==0) {
                    int yaffiche = (int) (-y);
                    g2.drawString(String.valueOf(yaffiche), (int) (largeur * 0.51), (int) (j * ((h1) / (2 * ymax))));
                }
                j++;

            }

            if(ymax>16 && ymax<=24) {//si ymax est compris entre 16 et 24
                if (y < 0 && y%2==0) {
                    int yaffiche = (int) (-y);
                    g2.drawString(String.valueOf(yaffiche), (int) (largeur * 0.51), (int) (j * ((h1) / (2 * ymax))+20));
                }
                if (y > 0 && y%2==0) {
                    int yaffiche = (int) (-y);
                    g2.drawString(String.valueOf(yaffiche), (int) (largeur * 0.51), (int) (j * ((h1) / (2 * ymax))));
                }
                j++;

            }

        }
    }

    /**
     * Methode qui place les Jslider dans la fenetre de loscilloscope afin dadapter l'echelle de la courbe
     */
    public void remplipanneau(){

        //création des Jslider pour sélectionner l'échelle de la courbe
        JLabel affichexmax = new JLabel("X max : 1");
        affichexmax.setBounds(0,(int) (h/2)-20,70,20);
        JLabel afficheymax = new JLabel("Y max : 12");
        afficheymax.setBounds(80,(int) (h/2)-20,70,20);

        JSlider curseurxmax = new JSlider();  // on créé un curseur pour choisir xmax sur la courbe
        curseurxmax.setMaximum(1400); // on fixe son maximum
        curseurxmax.setMinimum(1); // on fixe son minimum
        curseurxmax.setValue(100); // valeur initiale par défaut de xmax
        curseurxmax.setPaintTicks(true);
        curseurxmax.setPaintLabels(true);
        curseurxmax.setMinorTickSpacing(300); //espace minimal entre 2 "bornes affichées" sous le curseur
        curseurxmax.setMajorTickSpacing(300); //espace maximal entre 2 "bornes affichées" sous le curseur
        curseurxmax.setBounds(0,0,140,(int) (h/2)-20);
        curseurxmax.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                // Listener qui detecte le changement de valeur du curseur et qui attribue la valeur qui en découle a xmax et xmin
                affichexmax.setText("X max : " + ((JSlider)event.getSource()).getValue()/100.0);
                xmax=(((JSlider)event.getSource()).getValue())/100.0;
                xmin=(-((JSlider)event.getSource()).getValue())/100.0;
            }
        });
        JSlider curseurymax = new JSlider(); // on créé un curseur pour choisir ymax sur la courbe
        curseurymax.setMaximum(24); // on fixe son maximum
        curseurymax.setMinimum(1); // on fixe son minimum
        curseurymax.setValue(12); // valeur initiale par défaut de ymax
        curseurymax.setPaintTicks(true);
        curseurymax.setPaintLabels(true);
        curseurymax.setMinorTickSpacing(3); //espace minimal entre 2 "bornes affichées" sous le curseur
        curseurymax.setMajorTickSpacing(3); //espace maximal entre 2 "bornes affichées" sous le curseur
        curseurymax.setBounds(0,(int) (h/2),140,(int) (h/2)-20);
        curseurymax.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                // Listener qui detecte le changement de valeur du curseur et qui attribue la valeur qui en découle a ymax et ymin
                afficheymax.setText("Y max : " + ((JSlider)event.getSource()).getValue());
                ymax=((JSlider)event.getSource()).getValue();
                ymin=-((JSlider)event.getSource()).getValue();
            }
        });

        //ajout des sliders au panneaudubas de l'oscilloscope
        panneaudubas.add(affichexmax);
        panneaudubas.add(afficheymax);
        panneaudubas.add(curseurxmax);
        panneaudubas.add(curseurymax);
    }

}
