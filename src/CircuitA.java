import java.util.LinkedList;

public class CircuitA extends Circuit {

    //ajout de tableaux pour stocker les équations
    public String[] eq1 = new String[4];
    Dipole a = new Dipole();

    public CircuitA(ItemComposant[] compA) {
        super(compA);
        mailles.add(new Maille(compA));
        eq1[0]= String.valueOf(compA[0].saisie);
        for(int i=1;i<4;i++){
            //a=compA[i].RenvoiComposant(compA[i].getItem());
            eq1[i]= String.valueOf(a.z.module());
        }
    }




}
