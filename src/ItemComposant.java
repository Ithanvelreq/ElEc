import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;
/**
 * Modelisation d'un dipole pour l'interface
 */
public class ItemComposant extends ItemElement implements MouseListener{

    /**
     * indique l'unite de la valeur a saisir
     */
    JLabel unite;
    /**
     * champ de saisie de la valeur
     */
    JTextField saisie;
    /**
     * menu deroulant contenant les composants
     */
    JComboBox menuComposant;
    /**
     * JPanel contenant le dessin du composant
     */
    Trace_Composant dessin;
    /**
     * Numero du composant
     */
    int n;


    /**
     * constructeur de l'item complet
     * @param listeCompo
     * @param x
     */
    public ItemComposant(String[] listeCompo, int x) {
        n = x;

        this.setBackground(new Color(215,215,215));
        this.setSize(120, 75);
        this.setLayout(null);

        unite = new JLabel("unite");
        unite.setBounds(60, 5, 50, 30);

        saisie = new JTextField();
        saisie.setBounds(3, 5, 50, 30);
        saisie.addMouseListener(this);
        //blocage de la saisie de lettre dans les JTextField
        saisie.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) { //on n'autorise que l'ecriture des chiffres et des points
                if (e.getKeyChar()==VK_0 || e.getKeyChar()==VK_1 || e.getKeyChar()==VK_2 || e.getKeyChar()==VK_3 || e.getKeyChar()==VK_4 || e.getKeyChar()==VK_5 || e.getKeyChar()==VK_6|| e.getKeyChar()==VK_7|| e.getKeyChar()==VK_8|| e.getKeyChar()==VK_9|| e.getKeyChar()==VK_PERIOD ) {
                }else{
                    e.consume();
                }
            }
        });

        menuComposant = new JComboBox(listeCompo);
        menuComposant.setBounds(3, 40, 115, 30);
        menuComposant.addMouseListener(this);


        this.add(unite);
        this.add(saisie);
        this.add(menuComposant);
    }

    /**
     * Renvoie le nom du composant choisi
     * @return Le nom en String
     */
    public String getComposant(){ return menuComposant.getSelectedItem().toString(); }

    /**
     * Methode qui renvoie le dipole correspondant a la saisie de l'utilisateur
     * @param item Nom du composant
     * @param f Frequence générateur
     * @return Le dipôle correspondant
     */
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

    /**
     * Methode qui permet de dessiner la composant choisi
     * @param aDessiner True s'il faut dessiner ou non l'élément False sinon
     * @param vertical True si le dessin est vertical False sinon
     */
    public void dessine(boolean aDessiner, boolean vertical){
        if(aDessiner){
            dessin = new Trace_Composant(this.getComposant(),vertical,this.getHeight(),this.getWidth(),aDessiner);
            this.add(dessin);
            //on cache les widgets
            unite.setVisible(false);
            saisie.setVisible(false);
            menuComposant.setVisible(false);
            repaint();
        }
        if(!aDessiner){
            //on cache le dessin
            dessin.setVisible(false);
            //on remet les widgets
            unite.setVisible(true);
            saisie.setVisible(true);
            menuComposant.setVisible(true);
            repaint();
        }
    }

    public void mouseClicked(MouseEvent e){
        if(menuComposant.getSelectedItem().toString()=="Bobine"){
            unite.setText("H");
        }
        if(menuComposant.getSelectedItem().toString()=="Resistance"){
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
        if(menuComposant.getSelectedItem().toString()=="Resistance"){
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
