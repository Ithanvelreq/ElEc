import java.util.LinkedList;

public class CircuitA extends Circuit {

    //ajout de tableaux pour stocker les équations
    public String[] eq1 = new String[4];
    Dipole a = new Dipole();

    /*public CircuitA(ItemElement[] compA) {
        super(compA);
        mailles.add(new Maille(compA));
        for (int i=1;i<4;i++) {
            ItemComposant x = (ItemComposant) compA[i];
        }
        ItemGenerateur x = (ItemGenerateur) compA[0];

        eq1[0]= String.valueOf(1);
        for(int i=1;i<4;i++){
            a=compA[i].RenvoiComposant(compA[i].getComposant(),compA[0]);
            eq1[i]= String.valueOf(a.z.module());
        }
    }*/

}
