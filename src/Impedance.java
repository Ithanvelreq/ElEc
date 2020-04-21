package ElEc;
 /**
 *La classe Impedance sert a manipuler des nombres complexes et fourni les methodes numeriques afin de pouvoir resoudre les systemes d'equations
 */
public class Impedance {
	/**
	 * Partie reele du complexe
	 */
	private double re;
	/**
	 * Partie imaginaire du complexe
	 */
	private double im;
	/**
	 * Module du complexe
	 */
	private double Rho;
	/**
	 * Argument du complexe
	 */
	private double Theta;

	/**
	 * Constructeur du nombre complexe a partir de la forme algebrique
	 * @param a Partie reele du complexe
	 * @param b Partie imaginaire du complexe
	 */
	public Impedance (double a, double b){
		this.re = a;
		this.im = b;
		majPolaire();
	}

	/**
	 * Constructeur du nombre complexe a partir de la forme exponentielle
	 * @param module Module du complexe
	 * @param argument Argument du complexe
	 * @param s Il s'agit d'un String quelconque, rentrer s aussi
	 */

	public Impedance(double module, double argument, String s){
		this.Rho = module;
		this.Theta = argument;
		majCart();
	}

	/**
	 * Getter de partie reele du complexe
	 * @return Partie reele du complexe
	 */
	public double getRe(){
		return this.re;
	}

	/**
	 * Getter de partie imaginaire du complexe
	 * @return Partie imaginaire du complexe
	 */
	public double getIm(){
		return this.im;
	}

	/**
	 * Getter du module du complexe
	 * @return Module du complexe
	 */
	public double getRho(){
		return this.Rho;
	}

	/**
	 * Getter de l'argument du complexe
	 * @return Argument du complexe
	 */
	public double getTheta() {
		return this.Theta;
	}

	/**
	 * Setter pour le module du complexe
	 * @param x Nouveau module pour le complexe et mise a jour
	 */
	public void setRho(double x){
		this.Rho = x;
		majCart();
	}

	/**
	 * Setter de l'argument du complexe
	 * @param x Nouveau argument pour le complexe et mise a jour
	 */
	public void setTheta(double x){
		this.Theta = x;
		majCart();
	}

	/**
	 *Setter pour la partie reele du complexe
	 * @param x Nouvelle partie reele pour le complexe et mise a jour
	 */
	public void setRe (double x){
		this.re = x;
		majPolaire();
	}

	/**
	 * Setter de la partie imaginaire du complexe
	 * @param x Nouvelle partie reele pour le complexe et mise a jour
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
	 * @param x reel qu'il faut analyser
	 * @return signe de x
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
	 * @return Chaine de caracteres decrivant le complexe
	 */
	public String toString(){
		return this.re+" "+signe(this.im)+" i*"+Math.abs(this.im);
	}

	/**
	 * Affecte a this la somme avec x
	 * @param x Complexe ajoute a this
	 */
	public void sommeV1(Impedance x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		setRe(p);
		setIm (q);
	}

	/**
	 * Renvoie un complexe issu de la somme entre this et x
	 * @param x Deuxièmme therme de la somme
	 * @return Somme entre this et x
	 */
	public Impedance sommeV2(Impedance x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		return new Impedance (p, q);
	}

	/**
	 * Affecte a this la multiplication avec x
	 * @param x facteur qui multiplie this
	 */
	public void multiplicationV1(Impedance x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		setRho(p);
		setTheta(q);
	}

	/**
	 * Renvoie un complexe issu du produit entre this et x
	 * @param x facteur qui multiplie this
	 * @return Résultat du produit
	 */
	public Impedance multiplicationV2 (Impedance x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		return new Impedance (p, q, "s");
	}

	/**
	 * Test d'egalite
	 * @param x Complexe avec lequel on compare this
	 * @return true si ils sont egaux et false sinon
	 */
	public boolean equals (Impedance x){
		boolean w = false;
		if (this.re == x.getRe() && this.im == x.getIm()){
			w = true;
		}
		return w;
	}

	/**
	 *Renvoie le module du complexe
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
	 * @return Inverse du complexe
	 */
	public Impedance inverseV2(){
		double r = 1 / this.Rho;
		double t = -this.Theta;
		Impedance w = new Impedance(r, t, "s");
		return w;
	}

	/**
	 *Renvoie l'argument du complexe
	 * @return Argument du complexe
	 */
	public double argument(){
		return Math.atan2(this.im, this.re);
	}
}

