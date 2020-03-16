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
    public Dipole RenvoiComposant(String item){
        Impedance a= new Impedance(0,0);
        Dipole r= new Dipole(a,"r");
        if (item == "Resistance"){
            a.setRe(-1*Double.parseDouble(saisie.getText()));
            a.setIm(0);
        }
        if (item == "Condensateur"){
            a.setRe(0);
            a.setIm(1/Double.parseDouble(saisie.getText()));
        }
        if (item == "Bobine") {
            a.setRe(0);
            a.setIm(-1*Double.parseDouble(saisie.getText()));
        }
        r.z=a;
        return r;
    }


    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
}
