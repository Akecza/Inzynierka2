package pl.asia;

import java.util.Random;

public class Group {

	int population;
	int healthy;
	int infected;
	int infecting;
	int recovered;
	int sick[];
	int incubating[];
	
	void setnewstate(int ni) {
		
		//if(healthy<ni) ni=healthy;
		//if(infected<nr) nr=infected;
		//healthy-=ni;
		//infected=infected+ni-nr;
		//recovered+=nr;
		if(this.healthy<ni) ni=this.healthy;
		int newsick=this.incubating[0];
		this.healthy-=ni;
		this.recovered+=this.sick[0];
		this.newday(this.incubating);
		this.newday(this.sick);
		this.newinfected(ni);
		this.newsick(newsick);
		this.infected=this.incubating[0];
		for(int i=0;i<11;i++) this.infected+=this.sick[i];
		
	}
	
	public Group (int population, int infected) {
		this.population=population;
		this.healthy=population-infected;
		this.incubating = new int[7];
		this.sick = new int[11];
		this.recovered=0;
		this.newinfected(infected);
		this.infected=infected;
		
		//TODO this
		
		
	}
	
	void newinfected(int newinfected) {
		for(int i=0;i<newinfected;i++) {
			Random rand = new Random();
			int[] tab = new int[]{1,1,1,2,2,2,2,2,3,3,3,3,4,4,5,5,6,7};
			int x = rand.nextInt(tab.length);
			x=tab[x]-1;
			this.incubating[x]++;
		}
		
	}
	
	void newsick(int newsick) {
		for(int i=0;i<newsick;i++) {
			Random rand = new Random();
			int[] tab = new int[] {3,3,3,3,4,4,4,4,4,5,5,5,5,6,6,7,7,8,9,10};
			if(rand.nextBoolean()) this.recovered++;
			else
			{
				int x=rand.nextInt(tab.length);
				x=tab[x];
				this.sick[x]++;
			}
			
		}
		
	}
	
	void newday(int[] tab) {
		for(int i=0;i<tab.length-1;i++) {
			tab[i]=tab[i+1];
			
		}
		tab[tab.length-1]=0;
		
	}
	
}
