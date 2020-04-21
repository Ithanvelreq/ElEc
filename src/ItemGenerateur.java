import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

/**
 * Generateur de tension du circuit
 */
public class ItemGenerateur extends ItemElement {

    /**
     * Titre de l'item
     */
    JLabel source;
    /**
     * Label indiquant la frequence a saisir
     */
    JLabel freq;
    /**
     * Label indiquant l'amplutide a saisir
     */
    JLabel ampl;
    /**
     * Champ de saisie de la frequence
     */
    JTextField saisieFreq;
    /**
     * Champ de saisie de l'amplitude
     */
    JTextField saisieAmpl;
    /**
     * Panel contenant le dessin de generateur
     */
    Trace_Composant dessin;
    /**
     * Rappel l'amplitude saisie par l'utilisateur
     */
    JLabel rappelAmplitude;
    /**
     * Rappel la frequence saisie par l'utilisateur
     */
    JLabel rappelFrequence;


    /**
     *Constructeur
     */
    public ItemGenerateur(){

        this.setBackground(new Color(215,215,215));
        this.setSize(160, 120);
        this.setLayout(null);

        source = new JLabel("Source de tension");
        source.setBounds(8,5,140,30);
        source.setFont(new Font("Arial", Font.BOLD,14));

        freq = new JLabel("Fréquence (Hz)");
        freq.setBounds(10,35,100,30);

        ampl = new JLabel("Amplitude (V)");
        ampl.setBounds(10,75,100,30);

        saisieAmpl = new JTextField();
        saisieAmpl.setBounds(100,75,50,30);

        saisieFreq = new JTextField();
        saisieFreq.setBounds(100,35,50,30);

        rappelAmplitude = new JLabel();
        rappelAmplitude.setBounds(0,105,100,10);
        rappelAmplitude.setFont(new Font("Arial", Font.BOLD,13));

        rappelFrequence = new JLabel();
        rappelFrequence.setBounds(0,5,100,10);
        rappelFrequence.setFont(new Font("Arial", Font.BOLD,13));


        //blocage de la saisie de lettre dans les JTextField
        JTextField[] chpSaisie = {saisieFreq,saisieAmpl};
        for (JTextField t : chpSaisie){
            t.addKeyListener(new KeyAdapter(){
                public void keyTyped(KeyEvent e) { //on n'autorise que l'ecriture des chiffres et des points
                    if (e.getKeyChar()==VK_0 || e.getKeyChar()==VK_1 || e.getKeyChar()==VK_2 || e.getKeyChar()==VK_3 || e.getKeyChar()==VK_4 || e.getKeyChar()==VK_5 || e.getKeyChar()==VK_6|| e.getKeyChar()==VK_7|| e.getKeyChar()==VK_8|| e.getKeyChar()==VK_9 || e.getKeyChar()==VK_PERIOD ) {
                    }else{
                        e.consume();
                    }
                }
            });
        }

        this.add(source);
        this.add(freq);
        this.add(ampl);
        this.add(saisieFreq);
        this.add(saisieAmpl);
        this.add(rappelAmplitude);
        this.add(rappelFrequence);


    }

    /**
     * Getter de la frequence du generateur
     * @return frequence du generateur
     */
    public double getFrequence (){
        return  Double.parseDouble(saisieFreq.getText());
    }

    /**
     * Getter de l'amplitude du generateur
     * @return Amplitude du generateur
     */
    public double getAmpl (){
        return  Double.parseDouble(saisieAmpl.getText());
    }

    //METHODE
    /**
     * Methode qui permet de dessiner la composant choisi
     * @param aDessiner True s'il faut dessiner ou non l'element False sinon
     * @param vertical True si le dessin est vertical False sinon
     */
    public void dessine(boolean aDessiner, boolean vertical){

        if(aDessiner) {
            //on paint le composant
            dessin = new Trace_Composant("generateur",vertical,this.getHeight(),this.getWidth(),aDessiner);
            this.add(dessin);
            //on rappel la valeur de générateur
            rappelAmplitude.setText("U = "+saisieAmpl.getText()+" V");
            rappelFrequence.setText("f = "+saisieFreq.getText()+" Hz");
            //on cache tous les widgets
            source.setVisible(false);
            freq.setVisible(false);
            ampl.setVisible(false);
            saisieAmpl.setVisible(false);
            saisieFreq.setVisible(false);
            repaint();
        }
        if(!aDessiner){
            //on fait disparaitre le dessin
            dessin.setVisible(false);
            rappelAmplitude.setText(null);
            rappelFrequence.setText(null);
            //on remet les widgets
            source.setVisible(true);
            freq.setVisible(true);
            ampl.setVisible(true);
            saisieFreq.setVisible(true);
            saisieAmpl.setVisible(true);
            repaint();
        }
    }
}
