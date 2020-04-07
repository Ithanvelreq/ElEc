import java.util.LinkedList;

public abstract class Composant implements Comparable<Composant>{
    protected double caracteristique = 0;
    public LinkedList<Composant> voisins = new LinkedList();
    protected String nom;
    protected String type;

    public Composant(){

    }

    public Composant(double gamma, String n){
        this.caracteristique = gamma;
        this.nom = n;
    }
    public Composant( String n ){
        this.nom = n;
    }


    public int compareTo(Composant c){
        if(nom.compareTo(c.nom) < 0){
            return -1;
        }else if(nom.compareTo(c.nom) > 0){
            return 1;
        }else{
            return 1;
        }
    }
}
