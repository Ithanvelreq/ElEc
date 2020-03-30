import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

public class ItemComposant extends ItemElement implements MouseListener{

    //attributs
    JLabel unite;
    JTextField saisie;
    JComboBox menuComposant;
    int n;


    //constructeur
    public ItemComposant(String[] listeCompo, int x) {
        n = x;

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
    public Dipole RenvoiComposant(String item,double f){
        Impedance a= new Impedance(0,0);
        String w = "s";
        Dipole r= new Dipole(a,"r");
        if (item.equals("Resistance")){
            w = "Resistance"+n;
            a.setRe(Double.parseDouble(saisie.getText()));
            a.setIm(0);
        }
        if (item.equals("Condensateur")){
            w = "Condensateur"+n;
            a.setRe(0);
            a.setIm(-1/(Double.parseDouble(saisie.getText())*(f*2*Math.PI)));
        }
        if (item.equals("Bobine")) {
            w = "Bobine"+n;
            a.setRe(0);
            a.setIm(Double.parseDouble(saisie.getText())*(f*2*Math.PI));
        }
        r.nom = w;
        r.z=a;
        return r;
    }

    //méthode interface
    public void mouseClicked(MouseEvent e){
        if(menuComposant.getSelectedItem().toString()=="Bobine"){
            unite.setText("H");
        }
        if(menuComposant.getSelectedItem().toString()=="Résistance"){
            unite.setText("ohm");
        }
        if(menuComposant.getSelectedItem().toString()=="Condensateur"){
            unite.setText("F");
        }
        if(menuComposant.getSelectedItem().toString()=="source de tension"){
            unite.setText("V");
        }
    }
    public void mouseExited(MouseEvent e){
        if(menuComposant.getSelectedItem().toString()=="Bobine"){
            unite.setText("H");
        }
        if(menuComposant.getSelectedItem().toString()=="Résistance"){
            unite.setText("ohm");
        }
        if(menuComposant.getSelectedItem().toString()=="Condensateur"){
            unite.setText("F");
        }
        if(menuComposant.getSelectedItem().toString()=="source de tension"){
            unite.setText("V");
        }
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
}
