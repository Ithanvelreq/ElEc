import java.util.LinkedList;

public class CircuitA extends Circuit {

    //ajout de tableaux pour stocker les équations
    public String[] eq1 = new String[4];
    Dipole a = new Dipole();
    double frequence;

    public CircuitA(ItemElement[] compA) {
        super(compA);
        //mailles.add(new Maille(compA));

        ItemGenerateur x =(ItemGenerateur) compA[0];
        frequence = x.getFrequence();

        eq1[0]= String.valueOf(1);
        for(int i=1;i<compA.length;i++){
            ItemComposant y = (ItemComposant) compA[i];
            a=y.RenvoiComposant(y.getComposant(),frequence);
            eq1[i]= String.valueOf(a.z.module());
        }
    }*/

}
