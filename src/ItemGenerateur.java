import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ItemGenerateur extends ItemElement {

    //attributs
    public JLabel source;
    public JLabel freq;
    public JLabel ampl;
    public JTextField saisieFreq;
    public JTextField saisieAmpl;

    //constructeur
    public ItemGenerateur(){
        this.setBackground(Color.black);
        //this.setBackground(new Color(228,229,230));
        this.setSize(200, 200);
        this.setLayout(null);

        source = new JLabel("Source de tension");
        source.setBounds(10,10,140,30);

        freq = new JLabel("Fréquence (V)");
        freq.setBounds(10,60,50,30);

        ampl = new JLabel("Amplitude");
        ampl.setBounds(10,110,50,30);

        saisieAmpl = new JTextField();
        saisieAmpl.setBounds(80,110,50,30);

        saisieFreq = new JTextField();
        saisieFreq.setBounds(80,60,50,30);

        this.add(source);
        this.add(freq);
        this.add(saisieFreq);
        this.add(saisieAmpl);
    }

    public JTextField getSaisieFreq(){ return saisieFreq; }
    public JTextField getSaisieAmpl(){ return saisieAmpl; }

}
