import java.awt.*;

import javax.swing.JComponent;

public class dessinoscillo extends JComponent {

    private static final long serialVersionUID = 7800853645256601960L;
    private CurveFunction function = (x) -> Math.sin( x );
    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight();
    int largeur = (int)tailleEcran.getWidth();
    // obtention de 3/4 et de 1/4 de la longueur de l'écran

    double l= (double) 0.75*largeur;
    double l1= (double) 0.25*largeur;
    double h1= (double) 0.75*hauteur;
    double h=(double) 0.25*hauteur;

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
        g2.setColor( new Color( 255, 21, 24) );

        int oldX = xToPixel( -Math.PI );
        int oldY = yToPixel( function.compute( -Math.PI ) );

        for( double lx=-Math.PI+pas; lx<= Math.PI+pas; lx+=pas ) {
            int x = xToPixel( lx );
            int y = yToPixel( function.compute( lx ) );

            g2.drawLine( x, y, oldX, oldY );

            oldX = x;
            oldY = y;
        }
    }

    public void setFunction(CurveFunction function) {
        this.function = function;
        this.repaint();
    }

    private int xToPixel( double x ) {
        return (int)( getWidth() * (x + Math.PI)/(2*Math.PI) );
    }

    private int yToPixel( double y ) {
        return (int)( ((int) h1) * (1 - (y + 1)/2.0 ) );
    }

    public static interface CurveFunction {

        public double compute( double x );

    }

}
