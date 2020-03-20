import java.util.LinkedList;

public abstract class Composant {
    protected double caracteristique = 0;
    public LinkedList<Composant> voisins = new LinkedList();
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

    public void link(Composant o){
        voisins.add(o);
        if(!o.voisins.contains(this)){
            o.link(this);
        }
    }
}
