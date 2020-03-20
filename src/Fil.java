import java.util.LinkedList;

public class Fil {
    private Object amont = null;
    private Object aval = null;

    public Fil(){
    }

    public Object autreBout(Noeud n){
        if(amont instanceof Noeud) {
            return aval;
        }else if (aval instanceof Noeud){
            return amont;
        }else{
            return null;
        }
    }

    public void link(Object o){
        if(amont == null){
            amont = o;
        }else if (aval == null){
            aval = o;
        }
    }
}
