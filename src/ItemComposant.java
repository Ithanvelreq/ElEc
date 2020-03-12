import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ItemComposant extends JPanel{

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

        menuComposant = new JComboBox(listeCompo);
        menuComposant.setBounds(0, 40, 115, 30);

        this.add(unite);
        this.add(saisie);
        this.add(menuComposant);
    }
}
