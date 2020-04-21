import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Boutonmodif extends JButton {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1671314658637614873L;
    /**
     * Entier qui influe sur la taille du bouton à sa création
     */
    private int inset = 5;
    /**
     * Couleur par defaut du bouton
     */
    private Color buttonColor = new Color(36, 47, 68).darker().darker().darker();

    /**
     * constructeur qui definit la premiere couche du bouton,sa couleur et son image
     * @param name nom du bouton
     * @param icon image a inserer dans le bouton
     */
    public Boutonmodif (String name, Icon icon){
        super(name, icon);
        setContentAreaFilled(false);
        setForeground((new Color(36, 47, 68))); // on fixe la couleur de la première couche du bouton
    }

    @Override
    protected void paintComponent(Graphics g) {

        //on converti le graphics en graphics2D pour pouvoir créer des "couches" aux boutons
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //on récupère les infos de la fenêtre (hauteur et largeur)
        int vWidth = getWidth();
        int vHeight = getHeight();

        // calcule la taille du bouton
        int vButtonHeight = vHeight - (inset * 4);
        int vButtonWidth = vWidth - (inset * 4);
        int vArcSize = vButtonHeight;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // créé la première nuance de couleur pour la première couche du bouton
        Color vGradientStartColor =  buttonColor.darker().darker().darker();
        Color vGradientEndColor = buttonColor.brighter().brighter().brighter();
        Paint vPaint = new GradientPaint(0, inset, vGradientStartColor, 0, vButtonHeight, vGradientEndColor, false);
        g2d.setPaint(vPaint);

        // créé la première couche du bouton
        g2d.fillRoundRect(inset, inset, vButtonWidth, vButtonHeight, vArcSize/2, vArcSize/2);

        // créé la deuxième couche du bouton et fixe sa taille
        int vHighlightInset = 4;
        int vButtonHighlightHeight = vButtonHeight - (vHighlightInset );
        int vButtonHighlightWidth = vButtonWidth - (vHighlightInset );
        int vHighlightArcSize = vButtonHighlightHeight/2+150;

        // créé la première nuance de couleur pour la deuxième couche du bouton
        vGradientStartColor = new Color(70, 143, 232);
        vGradientEndColor = buttonColor.brighter().brighter();
        vPaint = new GradientPaint(0,inset+vHighlightInset/2,vGradientStartColor,0,inset+vHighlightInset+(vButtonHighlightHeight/2), buttonColor.brighter(), false);

        // créé la deuxième couche du bouton
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.8f));
        g2d.setPaint(vPaint);

        g2d.fillRoundRect(inset+vHighlightInset,inset+vHighlightInset,vButtonHighlightWidth,vButtonHighlightHeight,vHighlightArcSize/2,vHighlightArcSize/2);

        RoundRectangle2D.Float r2d =new RoundRectangle2D.Float(inset, inset, vButtonWidth, vButtonHeight, vArcSize/8, vArcSize/8);
        g2d.clip(r2d);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
        super.paintComponent(g);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
}
