public class Impedance {

	//attributs
	private double re;
	private double im;
	private double Rho;
	private double Theta;

	//constructeur
	public Impedance (double a, double b){
		this.re = a;
		this.im = b;
		majPolaire();
	}

	//getter
	public double getRe(){
		return this.re;
	}
	public double getIm(){
		return this.im;
	}
	public double getRho(){
		return this.Rho;
	}
	public double getTheta() {
		return this.Theta;
	}

	//setter
	private void setRho(double x){
		this.Rho = x;
		majCart();
	}
	private void setTheta(double x){
		this.Theta = x;
		majCart();
	}
	private void setRe (double x){
		this.re = x;
		majPolaire();
	}
	private void setIm (double x){
		this.im = x;
		majPolaire();
	}

	//maj des coordonnées
	private void majPolaire (){
		this.Rho = Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2));
		this.Theta = Math.atan2(this.im, this.re);
	}
	private void majCart(){
		this.re = this.Rho*Math.cos(this.Theta);
		this.im = this.Rho*Math.sin(this.Theta);
	}
	private char signe(double x){
		char sgn = '+';
		if (x<0){
			sgn = '-';
		}
		return sgn;
	}
	public String toString(){
		return this.re+" "+signe(this.im)+" i*"+Math.abs(this.im);
	}

	//méthode calculatoire

	//somme sans retour
	public void sommeV1(Impedance x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		setRe(p);
		setIm (q);
	}

	//somme avec Z en retour
	public Impedance sommeV2(Impedance x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		return new Impedance (p, q);
	}

	//multiplication sans retour
	public void multiplicationV1(Impedance x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		setRho(p);
		setTheta(q);
	}

	//multiplication avec Z en retour
	public Impedance multiplicationV2 (Impedance x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		return new Impedance (p, q);
	}

	//test égalite
	public boolean equals (Impedance x){
		boolean w = false;
		if (this.re == x.getRe() && this.im == x.getIm()){
			w = true;
		}
		return w;
	}		
}

