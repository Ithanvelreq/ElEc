import java.awt.*;
import javax.swing.*;

public class dessinoscillo extends JComponent {

    private static final long serialVersionUID = 7800853645256601960L;

    //ajout

    public CurveFunction[] tabfct= new CurveFunction[4];
    //

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight()-40;
    int largeur = (int)tailleEcran.getWidth();
    // obtention de 3/4 et de 1/4 de la longueur de l'écran

    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h1= (double) 0.75*hauteur;
    double h=(double) 0.25*hauteur;
    public JCheckBox[] tabcheckbox;
    public Color[] tabcouleur = new Color[4];
    public Impedance[] z;
    public double[] tableaumodule = new double[4];
    public ItemElement[] tableaumenu;
    public double frequence;

    public dessinoscillo(JCheckBox[] tabcheckbox, Impedance[] z, ItemElement[] tableaumenu){
        this.tabcheckbox=tabcheckbox;
        this.z=z;
        this.tableaumenu=tableaumenu;

        // on implemente les fonctions de chaque composant.

        creerfonction();

        for(int i=0;i<tabfct.length;i++){ // on fixe une couleur aléatoire a chaque courbe
            tabcouleur[i]= new Color( (int) (255-(Math.random()*250)), (int) (21+(Math.random()*230)), (int) (24+(Math.random()*230)));
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // --- Activate antialiasing flag ---
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // --- White background ---
        g2.setColor(new Color(228,229,230));
        g2.fillRect( 0, 0, largeur, (int) h1 );

        // --- Draw axis ---
        g2.setColor( Color.black );
        g2.drawLine( 0, ((int) h1)/2, getWidth(), ((int) h1)/2);
        g2.drawLine( getWidth()/2, 0, getWidth()/2, ((int) h1));

        // --- Draw values ---
        g2.setColor( Color.BLACK );
        g2.drawString( "0,0", (int)(getWidth()*0.51), (int)((h1)*0.54));
        g2.drawString( "-\u03c0", (int)(getWidth()*0.02), (int)((h1)*0.54));
        g2.drawString( "\u03c0", (int)(getWidth()*0.96), (int)((h1)*0.54));


        // dessin de la courbe
        double pas = 0.1;
        verifevaleur();   // a supprimer plus tard

        for(int i=0;i<tabfct.length;i++) {
            g2.setColor(tabcouleur[i]);

            if(tabcheckbox[i].isSelected()==true) {
                int oldX = xToPixel(-Math.PI);
                int oldY = yToPixel(tabfct[i].compute(-Math.PI));

                for (double lx = -Math.PI + pas; lx <= Math.PI + pas; lx += pas) {
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

    /*protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // --- Activate antialiasing flag ---
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // --- White background ---
        g2.setColor(new Color(228,229,230));
        g2.fillRect( 0, 0, largeur, (int) h1 );

        // --- Draw axis ---
        g2.setColor( Color.black );
        g2.drawLine( 0, ((int) h1)/2, getWidth(), ((int) h1)/2);
        g2.drawLine( getWidth()/2, 0, getWidth()/2, ((int) h1));

        // --- Draw values ---
        g2.setColor( Color.BLACK );
        g2.drawString( "0,0", (int)(getWidth()*0.51), (int)((h1)*0.54));
        g2.drawString( "-\u03c0", (int)(getWidth()*0.02), (int)((h1)*0.54));
        g2.drawString( "\u03c0", (int)(getWidth()*0.96), (int)((h1)*0.54));


        // dessin de la courbe
        double pas = 0.1;
        ItemGenerateur k = (ItemGenerateur) tableaumenu[0];
        frequence = k.getFrequence();

        for(int i=0;i<4;i++){
            System.out.println(z[i].getRe());
            System.out.println(z[i].getIm());
        }

        for(int i=0;i<tabfct.length;i++) {
            g2.setColor(tabcouleur[i]);

            if(tabcheckbox[i].isSelected()==true) {

                for (int x = 0; x < largeur; x++) {
                    g2.drawLine(x, (int) f(x), x+1, (int) f(x+1));
                    g2.drawLine(x, (int) g(x), x+1, (int) g(x+1));
                    g2.drawLine(x, (int) m(x), x+1, (int) m(x+1));
                    g2.drawLine(x, (int) n(x), x+1, (int) n(x+1));
                }
                repaint();
            }
            repaint();
        }
    }

    double f(double x) {
        tableaumodule[0] = Math.sqrt(Math.pow(z[0].getRe(), 2) + Math.pow(z[0].getIm(), 2));
        return (tableaumodule[0] * Math.cos(Math.toRadians(2 * (Math.PI) * frequence * x)) *0.05*hauteur;
    }

    double g(double x) {
        tableaumodule[1] = Math.sqrt(Math.pow(z[4].getRe(), 2) + Math.pow(z[4].getIm(), 2));
        return (tableaumodule[1] * Math.cos(Math.toRadians(2 * (Math.PI) * frequence * x)) *0.05*hauteur;
    }

    double m(double x) {
        tableaumodule[2] = Math.sqrt(Math.pow(z[5].getRe(), 2) + Math.pow(z[5].getIm(), 2));
        return (tableaumodule[2] * Math.cos(Math.toRadians(2 * (Math.PI) * frequence * x)) *0.05*hauteur;
    }

    double n(double x) {
        tableaumodule[3] = Math.sqrt(Math.pow(z[6].getRe(), 2) + Math.pow(z[6].getIm(), 2));
        return (tableaumodule[3] * Math.cos(Math.toRadians(2 * (Math.PI) * frequence * x)) *h;
    }*/

    public void setFunction(CurveFunction function) {

        for(int i=0;i<tabfct.length;i++) {
            this.tabfct[i] = function;
            this.repaint();
        }

    }

    private int xToPixel( double x ) {
        return (int)( largeur * (x + Math.PI)/(2*Math.PI) );
    }

    private int yToPixel( double y ) {
        return (int)( ((int) h1) * (1 - (y + 1)/2.0 ) );
    }

    public static interface CurveFunction {

        public double compute( double x );

    }

    public void creerfonction(){

        ItemGenerateur k = (ItemGenerateur) tableaumenu[0];
        frequence=k.getFrequence();


        tableaumodule[0]=Math.sqrt(Math.pow(z[0].getRe(),2)+Math.pow(z[0].getIm(),2));
        tableaumodule[1]=Math.sqrt(Math.pow(z[4].getRe(),2)+Math.pow(z[4].getIm(),2));
        tableaumodule[2]=Math.sqrt(Math.pow(z[5].getRe(),2)+Math.pow(z[5].getIm(),2));
        tableaumodule[3]=Math.sqrt(Math.pow(z[6].getRe(),2)+Math.pow(z[6].getIm(),2));

        tabfct[0] = (x) -> tableaumodule[0]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));
        tabfct[1] = (x) -> tableaumodule[1]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));
        tabfct[2] = (x) -> tableaumodule[2]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));
        tabfct[3] = (x) -> tableaumodule[3]*Math.cos(Math.toRadians(2*(Math.PI)*frequence*x));

    }

    public void verifevaleur(){

            System.out.println("frequence : " + frequence);

            for (int i = 0; i < z.length; i++) {  // for temporaire --> affiche les élements et leur valeur dans la console
                System.out.println(z[i]);
                System.out.println();
            }

            for (int j = 0; j < 4; j++) {
                System.out.println("module : " + tableaumodule[j]);
                System.out.println();
            }

            for (int x = 0; x < 10; x++) {
                System.out.println("2*pi*f = " + (2 * (Math.PI) * frequence * x));
                System.out.println("cos(2*pi*f) = " + Math.cos(Math.toRadians(2 * (Math.PI) * frequence * x)));
                System.out.println("module * cos(2*pi*f) = " + tableaumodule[0] * Math.cos(Math.toRadians(2 * (Math.PI) * frequence * x)));

            }


    }


}
