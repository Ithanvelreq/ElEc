public class Matrix {
    private Impedance [][] matrix = new Impedance[1][1];
    private Impedance [][] vecteur = new Impedance[1][1];  // Solution finale du sys lin
    private int imax = 0;

    public Matrix(Impedance [][] comp, Impedance [][]res){
        this.matrix = comp;
        this.vecteur = res;
    }

    public void resolSys(){
        this.triangulation();
        this.diagonalisation();
        this.simplification();
    }

    public void triangulation(){
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
               vecteur[l][0].sommeV1(vecteur[i][0].multiplicationV2(facteur));
           }
        }

    }

    public int imaxFinder(int i){
        int j = 0;
        double m = 0;
        for(int p = i; p<(matrix.length-1); p++){
            if (matrix[p][i].getRho()>m){
                j = p;
                m = matrix[p][i].getRho();
            }
        }
        return j;
    }

    public void diagonalisation(){
        int j = matrix[0].length-1;
        for(int i = (matrix.length-1); i>0; i--) {
            for (int k = i - 1; k >= 0; k--) {
                Impedance f = matrix[k][j].multiplicationV2(matrix[i][j].inverseV2());
                f.minus();
                matrix[k][j] = matrix[k][j].sommeV2(matrix[i][j].multiplicationV2(f));
                vecteur[k][0] = vecteur[k][0].sommeV2(vecteur[i][0].multiplicationV2(f));
            }
            j--;
        }
    }

    public void simplification(){
        for(int i =0; i<matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i==j){
                    vecteur[i][0] = vecteur[i][0].multiplicationV2(matrix[i][j].inverseV2());
                    matrix[i][j] = new Impedance(1, 0);
                }else{
                    matrix[i][j] = new Impedance(0, 0);
                }
            }
        }
    }

    public Impedance [] solutions(){
        Impedance [] w = new Impedance[vecteur.length];
        for(int i = 0; i< vecteur.length; i ++){
            w[i] = vecteur[i][0];
        }
        return w;
    }
}
