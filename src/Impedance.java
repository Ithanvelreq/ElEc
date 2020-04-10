public class Impedance {

	private double re;
	private double im;
	private double Rho;
	private double Theta;

	/**
	 * Constructeur du nombre complexe a partir de la forme algebrique
	 * @param a partie reelee du complexe
	 * @param b partie imaginaire du complexe
	 */
	public Impedance (double a, double b){
		this.re = a;
		this.im = b;
		majPolaire();
	}

	/**
	 * Constructeur du nombre complese a partir de la forme exponentielle
	 * @param module module du complexe
	 * @param argument argument du complexe
	 * @param s Il s'agit d'un String quelconque, rentrer s aussi
	 */

	public Impedance(double module, double argument, String s){
		this.Rho = module;
		this.Theta = argument;
		majCart();
	}

	/**
	 * Getter
	 * @return partie reele du complexe
	 */
	public double getRe(){
		return this.re;
	}

	/**
	 * Getter
	 * @return partie imaginaire du complexe
	 */
	public double getIm(){
		return this.im;
	}

	/**
	 * Getter
	 * @return Module du complexe
	 */
	public double getRho(){
		return this.Rho;
	}

	/**
	 * Getter
	 * @return Argument du complexe
	 */
	public double getTheta() {
		return this.Theta;
	}

	/**
	 * Setter
	 * @param x nouveau module pour le complexe et mise a jour
	 */
	public void setRho(double x){
		this.Rho = x;
		majCart();
	}

	/**
	 * Setter
	 * @param x nouveau argument pour le complexe et mise a jour
	 */
	public void setTheta(double x){
		this.Theta = x;
		majCart();
	}

	/**
	 *
	 * @param x nouvelle partie reele pour le complexe et mise a jour
	 */
	public void setRe (double x){
		this.re = x;
		majPolaire();
	}

	/**
	 * Setter
	 * @param x nouvelle partie reele pour le complexe et mise a jour
	 */
	public void setIm (double x){
		this.im = x;
		majPolaire();
	}

	/**
	 * Mise a jour des coordonnees polaires a partir des la forme algebrique
	 */
	private void majPolaire (){
		this.Rho = Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2));
		this.Theta = Math.atan2(this.im, this.re);
	}

	/**
	 * Mise a jour des coordonnees cartesiennes a partir des la forme exponentielle
	 */
	private void majCart(){
		this.re = this.Rho*Math.cos(this.Theta);
		this.im = this.Rho*Math.sin(this.Theta);
	}

	/**
	 * Renvoie le signe de x
	 * @param x
	 * @return
	 */
	private char signe(double x){
		char sgn = '+';
		if (x<0){
			sgn = '-';
		}
		return sgn;
	}

	/**
	 * Methode toString()
	 * @return
	 */
	public String toString(){
		return this.re+" "+signe(this.im)+" i*"+Math.abs(this.im);
	}

	/**
	 * Affecte a this la somme avec x
	 * @param x
	 */
	public void sommeV1(Impedance x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		setRe(p);
		setIm (q);
	}

	/**
	 * Somme entre this et x
	 * @param x
	 * @return
	 */
	public Impedance sommeV2(Impedance x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		return new Impedance (p, q);
	}

	/**
	 * Affecte a this la multiplication avec x
	 * @param x
	 */
	public void multiplicationV1(Impedance x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		setRho(p);
		setTheta(q);
	}

	/**
	 * Produit entre this et x
	 * @param x
	 * @return
	 */
	public Impedance multiplicationV2 (Impedance x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		return new Impedance (p, q, "s");
	}

	/**
	 * Test d'egalite
	 * @param x
	 * @return
	 */
	public boolean equals (Impedance x){
		boolean w = false;
		if (this.re == x.getRe() && this.im == x.getIm()){
			w = true;
		}
		return w;
	}

	/**
	 *
	 * @return Module du complexe
	 */
	public double module(){
		return Math.sqrt(Math.pow(re,2)+ Math.pow(im,2));
	}

	/**
	 * Multiplie this par -1
	 */
	public void minus(){
		this.re = -this.re;
		this.im = -this.im;
		majPolaire();
	}

	/**
	 * Compose par la fontion inverse et affecte le resultat a lui meme
	 */

	public void inverseV1(){
		this.Rho = 1 / this.Rho;
		this.Theta = -this.Theta;
		majCart();
	}

	/**
	 * Renvoie l'inverse du complexe
	 * @return
	 */
	public Impedance inverseV2(){
		double r = 1 / this.Rho;
		double t = -this.Theta;
		Impedance w = new Impedance(r, t, "s");
		return w;
	}

	/**
	 *
	 * @return Argument du complexe
	 */
	public double argument(){
		return Math.atan2(this.im, this.re);
	}
}

