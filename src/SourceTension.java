public class SourceTension extends Composant {
    private double omega;

    public SourceTension(double gamma, String n, double w){
        super(gamma, n);
        this.omega = w;
    }

    public double getOmega() {
        return omega;
    }
}
