package pl.asia;

import java.util.Random;

public class Group {

	private int population;
	private int healthy;
	private int infected;
	private int recovered;
	private double hlt;
	private double inf;
	private double rcv;
	private int nog;
	private double neighbour[];
	private double leaving;
	private double travel[];
	private int index;


	
	public Group (int population, int infected, int ng, int ind) {
		this.nog=ng;
		this.index=ind;
		this.population=population;
		this.healthy=population-infected;
		this.recovered=0;
		this.infected=infected;
		this.leaving=0.25;
		this.neighbour = new double[ng];
		this.travel = new double[ng];
		for (int i=0;i<nog;i++) {
			this.neighbour[i]=1;
			this.travel[i]=0.05;
		}
		this.neighbour[ind]=0;
		this.travel[ind]=0;
		this.hlt=(double)this.healthy/(double)this.population;
		this.inf=(double)this.infected/(double)this.population;
		this.rcv=(double)this.recovered/(double)this.population;
	}
	
	
	
	void setnewstate(double ni, double r, double p) {

		
		double newinf = (1-r)*this.inf + ni;
		double newhlt = this.hlt-ni;
		double newrcv = this.rcv + r*this.inf;
		this.inf=newinf;
		this.hlt=newhlt;
		this.rcv=newrcv;
		this.infected=(int)(this.inf*this.population);
		this.healthy=(int)(this.hlt*this.population);
		this.recovered=(int)(this.rcv*this.population);
		
	}
	
	double newinfected(double r, double p, Group[] gtab) {
		double t=0;
		int popmax=0;
		for(int i=0;i<this.nog;i++) {
			popmax+=gtab[i].getpopulation();
		}
		
		//t+=(1-r)*this.inf;
		t+=p*(1-this.leaving)*this.hlt*this.inf;
		for(int i=0;i<this.nog;i++) {
			t+=p*(1-this.leaving)*this.hlt*(gtab[i].getpopulation()/popmax)*this.neighbour[i]*this.inf;
			t+=p*this.hlt*(1-this.neighbour[i])*this.travel[i]*this.inf;
			
		}

		return t;
	}
	
	
	int getpopulation() {
		
		return this.population;
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
