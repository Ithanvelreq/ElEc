import java.util.LinkedList;

public class CircuitB extends Circuit{
    /**
     * Constructeur
     * @param compB tableau des différents ItemElement choisi par l'utilisateur, stocké dans FenêtreB_bis
     */
    public CircuitB(ItemElement[] compB) {
        //création du circuit
        super(compB);
        ItemElement[]maille1 = {compB[0],compB[1],compB[2]};
        mailles.add(new Maille(maille1));
        ItemElement[]maille2 = {compB[2],compB[3]};
        mailles.add(new Maille(maille2));


        //récupération de la fréquence et de la tension du générareur
        ItemGenerateur x =(ItemGenerateur) compB[0];
        frequence = x.getFrequence();
        amplitude = x.getAmpl();

        //remplissage de la matrice m1 qu'avec des 0
        for(int i1=0;i1<m1.length;i1++){
            for(int j1=0;j1<m1[0].length;j1++){
                m1[i1][j1] = new Impedance(0,0);
            }

        }

        //remplissage de la matrice m2 qu'avec des 0
        for(int i2=0;i2<m2.length;i2++){
            for(int j2=0;j2<m2[0].length;j2++){
                m2[i2][j2] = new Impedance(0,0);
            }

        }

        //remplissage de chaque ligne de m1 avec les coefficients gauches de chaque équation
        Dipole a;
        m1[0][0]= new Impedance(1,0);
        m1[1][0]= new Impedance(1,0);
        m1[3][1]= new Impedance(1,0);
        m1[3][2]= new Impedance(-1,0);
        m1[3][3]= new Impedance(-1,0);
        for(int l=1;l<mailles.get(0).Icomposants.size();l++){
            ItemComposant y = (ItemComposant) mailles.get(0).Icomposants.get(l);
            a=y.RenvoiComposant(y.getComposant(),frequence);
            m1[1][l] = a.z.multiplicationV2(new Impedance(-1,0));
            m1[l+3][l] = a.z.multiplicationV2(new Impedance(-1,0));
        }
        for(int j=0;j<mailles.get(1).Icomposants.size();j++){
            ItemComposant y = (ItemComposant) mailles.get(1).Icomposants.get(j);
            a=y.RenvoiComposant(y.getComposant(),frequence);
            m1[2][j+2] = a.z;
            m1[j+5][j+2]=a.z.multiplicationV2(new Impedance(-1,0));
        }
        m1[2][3]=m1[0][3].multiplicationV2(new Impedance(-1,0));
        for(int k=0;k<3;k++){
            m1[k+4][k+4]=new Impedance(1,0);
        }

        //remplissage de m2 avec les coefficients droits de chaque équation
        m2[0][0]= new Impedance(amplitude,0);

    }
}
