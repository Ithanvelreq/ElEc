import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class dessinoscillo extends JComponent {

    private static final long serialVersionUID = 7800853645256601960L;

    public CurveFunction[] tabfct= new CurveFunction[4]; // création d'un tableau qui contiendra nos fonctions à tracer

    //récupération des dimensions de l'écran et calculs de variables qui nous seront utiles pour le dimensionnement des widgets
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight()-40;
    int largeur = (int)tailleEcran.getWidth();
    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h1= (double) 0.75*hauteur;
    double h=(double) 0.25*hauteur;

    //variables de travail
    public JCheckBox[] tabcheckbox; //récupération du tableau de Jcheckbox de la Fenetreoscillo pour en connaitre leur état(coché ou décoché)
    public Color[] tabcouleur = new Color[4]; // tableau qui contiendra les couleurs des courbes
    public Impedance[] z; // récupération du tableau de solution du système d'équation
    public double[] tableaumodule = new double[4]; // création d'un tableau qui nous permettra de stocker les modules des composants
    public ItemElement[] tableaumenu; // récupération du tableau contenant les ItemElement, qui nous permettra de récupérer les données entrées dans les JtextField
    public double frequence; //initialisation de la frequence
    public JPanel panneaudubas; // récupération du panneau contenant les curseurs et les JCheckbox

    //initialisation des paramètres de visualisation
    public double xmax=1;
    public double xmin=-1;
    public double ymax=12;
    public double ymin=-12;

    //constructeur
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

    /**
     * @param graphics : élément à dessiner
     */
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
     * @param x : valeur horizontale à convertir en pixel
     * @return : valeur en pixel
     */
    private int xToPixel( double x ) {    // accepte un double entre la borne min et max sur les x
        return (int)( largeur * (x + xmax)/(2*xmax) );
    }

    /**
     * @param y : valeur verticale à convertir en pixel
     * @return : valeur en pixel
     */
    private int yToPixel( double y ) {   // accepte un double entre la borne min et max sur les y
        return (int)( ((int) h1) * (1 - (y+ymax)/(2*ymax)));
    }

    //création d'une interface qui nous permet de créer une fonction simplement et qui dépendra d'un paramètre x
    public static interface CurveFunction { public double compute( double x );}

    // méthode qui créé les fonctions qui seront tracées
    public void creerfonction(){

        //récupération de la fréquence
        ItemGenerateur k = (ItemGenerateur) tableaumenu[0];
        frequence=k.getFrequence();

        //calcul des modules
        tableaumodule[0]=Math.sqrt(Math.pow(z[0].getRe(),2)+Math.pow(z[0].getIm(),2));
        tableaumodule[1]=Math.sqrt(Math.pow(z[4].getRe(),2)+Math.pow(z[4].getIm(),2));
        tableaumodule[2]=Math.sqrt(Math.pow(z[5].getRe(),2)+Math.pow(z[5].getIm(),2));
        tableaumodule[3]=Math.sqrt(Math.pow(z[6].getRe(),2)+Math.pow(z[6].getIm(),2));

        //rangement des fonctions dans le tableau
        //la notation ci-dessous est possible grâce à l'interface
        tabfct[0] = (x) -> tableaumodule[0]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));
        tabfct[1] = (x) -> tableaumodule[1]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));
        tabfct[2] = (x) -> tableaumodule[2]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));
        tabfct[3] = (x) -> tableaumodule[3]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));

    }

    /**
     * @param g2 : élément à dessiner
     * @param xmin : valeur minimale sur l'axe des x
     * @param xmax : valeur maximale sur l'axe des x
     * @param ymin : valeur minimale sur l'axe des y
     * @param ymax : valeur maximale sur l'axe des y
     */
    public void placeechelle(Graphics2D g2, double xmin, double xmax, double ymin, double ymax) {

        //mise en place de compteurs
        int i=0;
        int j=0;

        //adaptation du nombre de valeur affichées sur l'axe suivant la valeur de xmax
        for ( double x=xmin; x<=xmax; x++) {

            if(xmax<=4){
                int xaffiche = (int) (x*100);
                if(xmax<1) {
                    if (x > 0) {
                        g2.drawString("0." + String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
                    } else {
                        g2.drawString("-0." + String.valueOf(Math.abs(xaffiche)), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
                    }
                }else{
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
                }
                i++;
            }

           /*if(xmax<=25) {//si xmax est inférieur à 25
               if (x < 0) {
                   int xaffiche = (int) x;
                   g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
               }
               if (x > 0) {
                   int xaffiche = (int) x;
                   g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) - 20, (int) ((h1) * 0.54));
               }
               i++;

           }
            if(xmax>25 && xmax<=50) {//si xmax est compris entre 25 et 50
                if (x < 0 && x%2==0) {
                    int xaffiche = (int) x;
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
                }
                if (x > 0 && x%2==0) {
                    int xaffiche = (int) x;
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) - 20, (int) ((h1) * 0.54));
                }
                i++;
            }

            if(xmax>50 && xmax<=75) {//si xmax est compris entre 50 et 75
                if (x < 0 && x%3==0) {
                    int xaffiche = (int) x;
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
                }
                if (x > 0 && x%3==0) {
                    int xaffiche = (int) x;
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) - 20, (int) ((h1) * 0.54));
                }
                i++;
            }

            if(xmax>75 && xmax<=100) {//si xmax est compris entre 75 et 100
                if (x < 0 && x%4==0) {
                    int xaffiche = (int) x;
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) + 10, (int) ((h1) * 0.54));
                }
                if (x > 0 && x%4==0) {
                    int xaffiche = (int) x;
                    g2.drawString(String.valueOf(xaffiche), (int) (i * ((largeur) / (2 * xmax))) - 20, (int) ((h1) * 0.54));
                }
                i++;
            }*/


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

    //méthode qui permet d'ajouter les Jslider dans la Fenetreoscillo
    public void remplipanneau(){

        //création des Jslider pour sélectionner l'échelle de la courbe
        JLabel affichexmax = new JLabel("X max : 1");
        affichexmax.setBounds(0,(int) (h/2)-20,70,20);
        JLabel afficheymax = new JLabel("Y max : 12");
        afficheymax.setBounds(70,(int) (h/2)-20,70,20);

        JSlider curseurxmax = new JSlider();
        curseurxmax.setMaximum(301);
        curseurxmax.setMinimum(1);
        curseurxmax.setValue(100); // valeur initiale par défaut de xmax
        curseurxmax.setPaintTicks(true);
        curseurxmax.setPaintLabels(true);
        curseurxmax.setMinorTickSpacing(100);
        curseurxmax.setMajorTickSpacing(100);
        curseurxmax.setBounds(0,0,140,(int) (h/2)-20);
        curseurxmax.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                affichexmax.setText("X max : " + ((JSlider)event.getSource()).getValue());
                xmax=(((JSlider)event.getSource()).getValue())/100.0;
                xmin=(-((JSlider)event.getSource()).getValue())/100.0;
            }
        });
        JSlider curseurymax = new JSlider();
        curseurymax.setMaximum(24);
        curseurymax.setMinimum(1);
        curseurymax.setValue(12); // valeur initiale par défaut de ymax
        curseurymax.setPaintTicks(true);
        curseurymax.setPaintLabels(true);
        curseurymax.setMinorTickSpacing(3);
        curseurymax.setMajorTickSpacing(3);
        curseurymax.setBounds(0,(int) (h/2),140,(int) (h/2)-20);
        curseurymax.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                afficheymax.setText("Y max : " + ((JSlider)event.getSource()).getValue());
                ymax=((JSlider)event.getSource()).getValue();
                ymin=-((JSlider)event.getSource()).getValue();
            }
        });

        panneaudubas.add(affichexmax);
        panneaudubas.add(afficheymax);
        panneaudubas.add(curseurxmax);
        panneaudubas.add(curseurymax);
    }

}
