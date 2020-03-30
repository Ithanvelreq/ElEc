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

    private static final long serialVersionUID = 1671314658637614873L;
    private int inset = 5;
    private Color buttonColor = new Color(36, 47, 68).darker().darker().darker();

    public Boutonmodif (String aNameString, Icon icon){
        super(aNameString, icon);
        setContentAreaFilled(false);
        setForeground((new Color(36, 47, 68)));
    }

    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int vWidth = getWidth();
        int vHeight = getHeight();

        // Calculate the size of the button
        int vButtonHeight = vHeight - (inset * 4);
        int vButtonWidth = vWidth - (inset * 4);
        int vArcSize = vButtonHeight;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create the gradient paint for the first layer of the button
        Color vGradientStartColor =  buttonColor.darker().darker().darker();
        Color vGradientEndColor = buttonColor.brighter().brighter().brighter();
        Paint vPaint = new GradientPaint(0, inset, vGradientStartColor, 0, vButtonHeight, vGradientEndColor, false);
        g2d.setPaint(vPaint);

        // Paint the first layer of the button
        g2d.fillRoundRect(inset, inset, vButtonWidth, vButtonHeight, vArcSize/2, vArcSize/2);

        // Calulate the size of the second layer of the button
        int vHighlightInset = 4;
        int vButtonHighlightHeight = vButtonHeight - (vHighlightInset );
        int vButtonHighlightWidth = vButtonWidth - (vHighlightInset );
        int vHighlightArcSize = vButtonHighlightHeight/2+150;

        // Create the paint for the second layer of the button
        vGradientStartColor = new Color(70, 143, 232);
        vGradientEndColor = buttonColor.brighter().brighter();
        vPaint = new GradientPaint(0,inset+vHighlightInset/2,vGradientStartColor,0,inset+vHighlightInset+(vButtonHighlightHeight/2), buttonColor.brighter(), false);

        // Paint the second layer of the button
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
