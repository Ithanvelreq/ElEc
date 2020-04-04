import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class dessinoscillo extends JComponent {

    private static final long serialVersionUID = 7800853645256601960L;

    public CurveFunction[] tabfct= new CurveFunction[4];

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
    public JPanel panneaudubas;
    public double xmax=20;
    public double xmin=-20;
    public double ymax=12;
    public double ymin=-12;

    public dessinoscillo(JCheckBox[] tabcheckbox, Impedance[] z, ItemElement[] tableaumenu, JPanel panneaudubas){
        this.tabcheckbox=tabcheckbox;
        this.z=z;
        this.tableaumenu=tableaumenu;
        this.panneaudubas=panneaudubas;
        remplipanneau();

        // on implemente les fonctions de chaque composant.

        creerfonction();
        tabcouleur[0]= new Color( 200, 29, 32);
        tabcouleur[1]= new Color(14, 7, 200);
        tabcouleur[2]= new Color(10, 99, 7);
        tabcouleur[3]= new Color(0, 0, 0);
    }

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

        double pas = 0.1;

        for(int i=0;i<tabfct.length;i++) {
            g2.setColor(tabcouleur[i]);

            if(tabcheckbox[i].isSelected()==true) {
                int oldX = xToPixel((int) xmin); //commence a tracer a partir de xmin
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

    public void setFunction(CurveFunction function) {

        for(int i=0;i<tabfct.length;i++) {
            this.tabfct[i] = function;
            this.repaint();
        }

    }

    private int xToPixel( double x ) {    // accepte un double entre la borne min et max sur les x
        return (int)( largeur * (x + xmax)/(2*xmax) );
    }

    private int yToPixel( double y ) {   // accepte un double entre la borne min et max sur les y
        return (int)( ((int) h1) * (1 - (y+ymax)/(2*ymax)));
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

    public void placeechelle(Graphics2D g2, double xmin, double xmax, double ymin, double ymax) {

        int i=0;
        int j=0;

        for ( double x=xmin; x<=xmax; x++) {

            /*Double xaffiche = new Double(x);
            g2.drawString(xaffiche.toString(), (int) (x * (largeur/ (xmax))), (int) ((h1) * 0.54));*/
            
            if (x!=0) {
                Double xaffiche = new Double(x);
                g2.drawString(xaffiche.toString(), (int) (largeur * (-0.001 + (1 / (Math.abs(xmin) + xmax))) * i), (int) ((h1) * 0.54));
            }else{
                g2.drawString("0", (int) (largeur * 0.49), (int) ((h1) * 0.54));
            }
            i++;
        }

        for ( double y=ymin; y<=ymax; y++) {
            Double yaffiche= new Double(y);
            if(y!=0) {
                g2.drawString(yaffiche.toString(), (int) (largeur * 0.51), (int) (h1 - (h1 * (-0.001+(1/(Math.abs(ymin)+ymax))) * j)));
            }
            j++;
        }
    }

    public void remplipanneau(){

        //création des Jslider pour sélectionner l'échelle de la courbe
        JLabel affichexmax = new JLabel("X max : 20");
        affichexmax.setBounds(0,(int) (h/2)-20,70,20);
        JLabel afficheymax = new JLabel("Y max : 12");
        afficheymax.setBounds(70,(int) (h/2)-20,70,20);

        JSlider curseurxmax = new JSlider();
        curseurxmax.setMaximum(100);
        curseurxmax.setMinimum(1);
        curseurxmax.setValue(20); // valeur initiale par défaut de xmax
        curseurxmax.setPaintTicks(true);
        curseurxmax.setPaintLabels(true);
        curseurxmax.setMinorTickSpacing(15);
        curseurxmax.setMajorTickSpacing(15);
        curseurxmax.setBounds(0,0,140,(int) (h/2)-20);
        curseurxmax.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                affichexmax.setText("X max : " + ((JSlider)event.getSource()).getValue());
                xmax=((JSlider)event.getSource()).getValue();
                xmin=-((JSlider)event.getSource()).getValue();
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
