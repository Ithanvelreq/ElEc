import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

public class ItemComposant extends ItemElement implements MouseListener{

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
        //blocage de la saisie de lettre dans les JTextField
        saisie.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) { //on n'autorise que l'ecriture des chiffres
                if (e.getKeyChar()==VK_0 || e.getKeyChar()==VK_1 || e.getKeyChar()==VK_2 || e.getKeyChar()==VK_3 || e.getKeyChar()==VK_4 || e.getKeyChar()==VK_5 || e.getKeyChar()==VK_6|| e.getKeyChar()==VK_7|| e.getKeyChar()==VK_8|| e.getKeyChar()==VK_9 ) {
                }else{
                    e.consume();
                }
            }
        });

        menuComposant = new JComboBox(listeCompo);
        menuComposant.setBounds(0, 40, 115, 30);
        menuComposant.addMouseListener(this);


        this.add(unite);
        this.add(saisie);
        this.add(menuComposant);
    }

    //méthode
    public String getComposant(){ return menuComposant.getSelectedItem().toString(); }

    // permet de récupérer les caractéristiques du composant choisi
    public Dipole RenvoieComposant(String item){
        Impedance a= new Impedance(0,0);
        Dipole r= new Dipole(a,"r");
        if (item.equals("Resistance")){
            a.setRe(-1*Double.parseDouble(saisie.getText()));
            a.setIm(0);
        }
        if (item.equals("Condensateur")){
            a.setRe(0);
            a.setIm(1/Double.parseDouble(saisie.getText()));
        }
        if (item.equals("Bobine")) {
            a.setRe(0);
            a.setIm(-1*Double.parseDouble(saisie.getText()));
        }
        r.z=a;
        return r;
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
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}



}
