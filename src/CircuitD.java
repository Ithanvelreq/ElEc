/**
 * Classe permettant de realiser les calculs du circuit A, en haut a gauche
 */
public class CircuitD extends Circuit {
    /**
     * Constructeur
     * @param compD Tableau des differents ItemElement choisis par l'utilisateur, stockes dans FenetreD_bis
     */
    public CircuitD(ItemElement[] compD) {
        //création du circuit
        super(compD);
        ItemElement[]maille1 = {compD[0],compD[1]};
        mailles.add(new Maille(maille1));
        ItemElement[]maille2 = {compD[1],compD[2]};
        mailles.add(new Maille(maille2));
        ItemElement[]maille3 = {compD[2],compD[3]};
        mailles.add(new Maille(maille3));



        //récupération de la fréquence et de la tension du générareur
        ItemGenerateur x =(ItemGenerateur) compD[0];
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

        for(int l1=1;l1<mailles.get(0).Icomposants.size();l1++){
            ItemComposant y = (ItemComposant) mailles.get(0).Icomposants.get(l1);
            a=y.RenvoiComposant(y.getComposant(),frequence);
            m1[l1][1] = a.z.multiplicationV2(new Impedance(-1,0));
        }

        for(int l2=0;l2<mailles.get(1).Icomposants.size();l2++){
            ItemComposant y = (ItemComposant) mailles.get(1).Icomposants.get(l2);
            a=y.RenvoiComposant(y.getComposant(),frequence);
            m1[2][l2+1] = a.z;
            m1[l2+4][l2+1] = a.z.multiplicationV2(new Impedance(-1,0));
        }
        m1[2][2]=m1[2][2].multiplicationV2(new Impedance(-1,0));

        for(int l3=0;l3<mailles.get(2).Icomposants.size();l3++){
            ItemComposant y = (ItemComposant) mailles.get(2).Icomposants.get(l3);
            a=y.RenvoiComposant(y.getComposant(),frequence);
            m1[3][l3+2] = a.z;
            m1[l3+5][l3+2] = a.z.multiplicationV2(new Impedance(-1,0));
        }
        m1[3][3]=m1[3][3].multiplicationV2(new Impedance(-1,0));

        for(int k=0;k<3;k++){
            m1[k+4][k+4]=new Impedance(1,0);
        }

        //remplissage de m2 avec les coefficients droits de chaque équation
        m2[0][0]= new Impedance(amplitude,0);

        for(int y=0;y<7;y++){
            for(int w=0;w<7;w++){
                System.out.print(m1[y][w].getRe()+" "+m1[y][w].getIm()+" /");
            }
            System.out.println();
        }

    }
}
