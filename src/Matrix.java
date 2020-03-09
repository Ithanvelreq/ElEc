public class Matrix {
    private Impedance [][] matrix;
    private Impedance [][] vecteur;
    private int imax = 0;

    public Matrix(Impedance [][] comp, Impedance [][]res){
        this.matrix = comp;
        this.vecteur = res;
    }

    public void resolSys(){
        for(int i = 0; i<(matrix.length-1); i++){
           imax = imaxFinder(i);
           if(matrix[imax][i].getRho() == 0){
               System.out.println("pas de solution unique possible");
           }
           Impedance [] temat = matrix[imax];
           Impedance [] temvec = vecteur[imax];
           matrix[imax] = matrix[i];
           vecteur[imax] = vecteur[i];
           matrix[i] = temat;
           vecteur[i] = temvec;

           for(int l = i+1; l<matrix.length; l++){
               Impedance facteur = matrix[l][i].multiplicationV2(matrix[i][i].inverseV2());
               facteur.minus();
               matrix[l][i] = new Impedance(0, 0);
               for(int col = i+1; col<matrix.length; col++){
                   matrix[l][col] = matrix[l][col].sommeV2(matrix[i][col].multiplicationV2(facteur));
               }
               vecteur[l][0] = vecteur[l][0].sommeV2(vecteur[i][0].multiplicationV2(facteur));
           }
        }

    }

    public int imaxFinder(int i){
        int j = 0;
        double m = 0;
        for(int p = i; i<(matrix.length-1); p++){
            if (matrix[p][i].getRho()>m){
                j = p;
                m = matrix[p][i].getRho();
            }
        }
        return j;
    }
}
