import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ItemComposant extends JPanel implements MouseListener{

    //attributs
    JLabel unite;
    JTextField saisie;
    JComboBox menuComposant;


    //constructeur
    public ItemComposant(String[] listeCompo) {

        this.setBackground(new Color(228,229,230));
        this.setSize(120, 75);
        this.setLayout(null);

        unite = new JLabel("unite");
        unite.setBounds(60, 0, 50, 30);

        saisie = new JTextField();
        saisie.setBounds(0, 0, 50, 30);
        saisie.addMouseListener(this);

        menuComposant = new JComboBox(listeCompo);
        menuComposant.setBounds(0, 40, 115, 30);
        menuComposant.addMouseListener(this);


        this.add(unite);
        this.add(saisie);
        this.add(menuComposant);
    }

    //méthode
    public String getItem(){
        return menuComposant.getSelectedItem().toString();
    }

    //méthode interface
    public void mouseClicked(MouseEvent e){
        if(menuComposant.getSelectedItem().toString()=="Bobine"){
            unite.setText("mH");
        }
        if(menuComposant.getSelectedItem().toString()=="Résistance"){
            unite.setText("ohm");
        }
        if(menuComposant.getSelectedItem().toString()=="Condensateur"){
            unite.setText("micro F");
        }
        if(menuComposant.getSelectedItem().toString()=="source de tension"){
            unite.setText("V");
        }
    }
    public void mouseExited(MouseEvent e){
        if(menuComposant.getSelectedItem().toString()=="Bobine"){
            unite.setText("mH");
        }
        if(menuComposant.getSelectedItem().toString()=="Résistance"){
            unite.setText("ohm");
        }
        if(menuComposant.getSelectedItem().toString()=="Condensateur"){
            unite.setText("micro F");
        }
        if(menuComposant.getSelectedItem().toString()=="source de tension"){
            unite.setText("V");
        }
    }


    // permet de récupérer les caractéristiques du composant choisi
    /*public Dipole RenvoiComposant(String item){
        if (item == "Resistance"){
            Impedance z = new Impedance(Double.parseDouble(-saisie.getText()),0.0);
            Resistance r =new Resistance(z,"r");
            return r;
        }
        if (item == "Condensateur"){
            Impedance z = new Impedance(0,1/(saisie.getText()));
            Condensateur c = new Condensateur(z,"c");
            return c;
        }
        if (item == "Bobine") {
            Impedance z = new Impedance(0,-saisie.getText());
            Bobine b = new Bobine(z, "b");
            return b;
        }


    }*/


    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
}
