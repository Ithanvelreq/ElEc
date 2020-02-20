public abstract class Composant {
    private double caracteristique = 0;
    private String nom;

    public Composant(){

    }

    public Composant( double gamma, String n){
        this.caracteristique = gamma;
        this.nom = n;
    }
    public Composant( String n ){
        this.nom = n;
    }
}
