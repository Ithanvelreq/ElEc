public abstract class Composant {
    protected double caracteristique = 0;
    protected String nom;

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
