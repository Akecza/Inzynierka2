package pl.asia;


public class Group {

	private int population;
	private int healthy;
	private int infected;
	private int recovered;
	private double hlt;
	private double inf;
	private double rcv;
	private int nog;
	private double leaving[];
	private int index;
	private double [][] neigharray;
	private double [][] travel;
	
	public Group (int pop, int infected, int ng, int ind, double[][][] neigh) {
		this.neigharray = new double[7][ng];
		this.nog=ng;
		this.index=ind;
		this.population=pop;
		this.healthy=pop-infected;
		this.recovered=0;
		this.infected=infected;
		this.leaving = new double[7];
		this.travel= new double[7][ng];
	
		for(int dzien=0; dzien<7; dzien++) {
		for (int i=0;i<nog;i++) {
			this.neigharray[dzien][i]=neigh[dzien][i][this.index]+0.01;
			this.travel[dzien][i]=0;
		}
		
		this.leaving[dzien]=0;
		
		}

		this.hlt=(double)this.healthy/(double)this.population;
		this.inf=(double)this.infected/(double)this.population;
		this.rcv=(double)this.recovered/(double)this.population;
	}
	
	
	
	void setnewstate(double ni, double nh, double nr) {

		
		double newinf = ni;
		double newhlt = nh;
		double newrcv = nr;
		this.inf=newinf;
		this.hlt=newhlt;
		this.rcv=newrcv;
		this.infected=(int)(this.inf*this.population);
		this.healthy=(int)(this.hlt*this.population);
		this.recovered=(int)(this.rcv*this.population);
		
	}
	
	double newrecovered(double r, double p, Group[] gtab) {
		return this.rcv+r*this.getinf();
	}
	
	double newhealthy(double r, double p, Group[] gtab, int dzien) {
		double t=this.gethlt();
		int popmax=0;
		for(int i=0;i<this.nog;i++) {
			if(popmax<gtab[i].getpopulation()) {
				popmax=gtab[i].getpopulation();
			}
		}
		t-=p*(1-this.leaving[dzien])*this.hlt*this.inf;
		for(int i=0;i<this.nog;i++) {
			if(i!=this.index) {
			t-=p*(1-this.leaving[dzien])*this.hlt*((double)gtab[i].getpopulation()/popmax)*this.neigharray[dzien][i]*gtab[i].getinf();
			t-=p*this.hlt*(1-this.neigharray[dzien][i])*this.travel[dzien][i]*gtab[i].getinf();
			}
		}
		if(t>0) 
			return t;
		else
			return 0;
	}
	
	double newinfected(double r, double p, Group[] gtab, int dzien) {
		double t=0;
		int popmax=0;
		for(int i=0;i<this.nog;i++) {
			if(popmax<gtab[i].getpopulation()) {
				popmax=gtab[i].getpopulation();
			}
		}
		
		t+=p*(1-this.leaving[dzien])*this.hlt*this.inf;
		for(int i=0;i<this.nog;i++) {
			if(i!=this.index) {
			t+=p*(1-this.leaving[dzien])*this.hlt*((double)gtab[i].getpopulation()/popmax)*this.neigharray[dzien][i]*gtab[i].getinf();
			t+=p*this.hlt*(1-this.neigharray[dzien][i])*this.travel[dzien][i]*gtab[i].getinf();
			}
		}
		
		if(t>this.gethlt())
			t=this.gethlt();
		
		t+=(1-r)*this.inf;
		
		return t;
	}
	
	
	int getpopulation() {
		
		return this.population;
	}
	
	double getinf() {
		
		return this.inf;
	}
	
	double gethlt() {
		return this.hlt;
	}
	
	int gethealthy() {
		
		return this.healthy;
	}
	
	int getinfected() {
		
		return this.infected;
	}
	
	int getrecovered() {
		
		return this.recovered;
	}
	
	
	
	
}
