public class Complexe {
	private String typeEntre = "cartesien";
	private double re;
	private double im;
	private double Rho;
	private double Theta;
	public Complexe (double a, double b){
		this.re = a;
		this.im = b;
		majPolaire();
	}

	public Complexe(String s, double rh, double th){
		this.typeEntre = s;
		this.Rho = rh;
		this.Theta = th;
		majCart();
	}
	public boolean estDansCadrant(int x){
		boolean w = false;
		if (x==1 && this.re>= 0 && this.im>= 0){
			w = true;
		}else if(x==2 && this.re<= 0 && this.im>=0){
			w = true;
		}else if (x==3 && this.re<= 0 && this.im<= 0){
			w = true;
		}else if (x==4 && this.re>= 0 && this.im<= 0){
			w = true;
		}
		return w;
	}
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
	public double getRe(){
		return this.re;
	}
	public double getIm(){
		return this.im;
	}
	public double getRho(){
		return this.Rho;
	}
	public double getTheta(){
		return this.Theta;
	}
	public void sommeV1(Complexe x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		setRe(p);
		setIm (q);
	}
	public Complexe sommeV2(Complexe x){
		double p = this.re + x.getRe();
		double q = this.im + x.getIm();
		return new Complexe (p, q); 
	}
	public void multiplicationV1(Complexe x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		setRho(p);
		setTheta(q);
	}
	public Complexe multiplicationV2 (Complexe x){
		double p = this.Rho*x.getRho();
		double q = this.Theta + x.getTheta();
		return new Complexe ("polaire", p, q);
	}
	public boolean equals (Complexe x){
		boolean w = false;
		if (this.re == x.getRe() && this.im == x.getIm()){
			w = true;
		}
		return w;
	}		
}

