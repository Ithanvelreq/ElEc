import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panneaudessin extends JPanel {

    public int num;

    public Panneaudessin(int numerofenetre){

        this.num=numerofenetre;

    }
        public void paint(Graphics g){

            if (this.num==1) {
                Graphics2D g2 = (Graphics2D) g; //on passe d'un graphic à un graphics 2D, qui nous permettra de changer l'épaisseur d'une ligne

                g.setColor(new Color(226, 228, 227));   // on fixe la couleur du panneau
                g.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(3));   //on change l'épaisseur du trait à tracer
                g2.drawRect(75, 75, (int) (getWidth() * 0.8), (int) (getHeight() * 0.75)); //rectangle

            }

            // on dessine l'allure de chaque circuit

            if(this.num==2) {

                Graphics2D g2 = (Graphics2D) g; //on passe d'un graphic à un graphics 2D, qui nous permettra de changer l'épaisseur d'une ligne

                g.setColor(new Color(226, 228, 227));   // on fixe la couleur du panneau
                g.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(3));   //on change l'épaisseur du trait à tracer
                g2.drawRect(75, 75, (int) (getWidth() * 0.8), (int) (getHeight() * 0.75)); //rectangle
                g2.drawLine((int) (getWidth() * 0.6),75, (int) (getWidth() * 0.6),(int) (getHeight() * 0.75)+75); //ligne de la branche parallèle

            }

            if(this.num==3) {

                Graphics2D g2 = (Graphics2D) g; //on passe d'un graphic à un graphics 2D, qui nous permettra de changer l'épaisseur d'une ligne

                g.setColor(new Color(226, 228, 227));   // on fixe la couleur du panneau
                g.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(3));   //on change l'épaisseur du trait à tracer
                g2.drawRect(75, 75, (int) (getWidth() * 0.8), (int) (getHeight() * 0.75));
                g2.drawLine((int) (getWidth() * 0.5),75, (int) (getWidth() * 0.5),(int) (getHeight() * 0.75)+75); //ligne de la branche du T

            }

            if(this.num==4) {

                Graphics2D g2 = (Graphics2D) g; //on passe d'un graphic à un graphics 2D, qui nous permettra de changer l'épaisseur d'une ligne

                g.setColor(new Color(226, 228, 227));   // on fixe la couleur du panneau
                g.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(3));   //on change l'épaisseur du trait à tracer
                g2.drawRect(75, 75, (int) (getWidth() * 0.8), (int) (getHeight() * 0.75));
                g2.drawLine((int) (getWidth() * 0.3),75, (int) (getWidth() * 0.3),(int) (getHeight() * 0.75)+75); //1ère parallele
                g2.drawLine((int) (getWidth() * 0.6),75, (int) (getWidth() * 0.6),(int) (getHeight() * 0.75)+75); //2eme parallele

            }
        }





}
