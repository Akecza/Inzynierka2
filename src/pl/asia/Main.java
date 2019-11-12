package pl.asia;


public class Main {

	public static void main(String[] args) {
		System.out.println("STAAART");
		int nog=6; //number of groups;
		double recovery_rate=0.6;
		double transmission_rate=0.5;
		
		
		Group node[] = new Group[nog];
		
		node[0]=new Group(100,20,6,0);
		for (int i=1;i<nog;i++) {//starting conditions
			node[i] = new Group(100, 0,6,i);
		}
		
		//starting conditions done

		
		
		while(true) { //one day loop
			double temp[]=new double[nog];
			
			System.out.print(node[0].gethealthy()+" "+node[0].getinfected()+" "+node[0].getrecovered()+"     ");
			System.out.println(node[1].gethealthy()+" "+node[1].getinfected()+" "+node[1].getrecovered());
			
			for (int i=0;i<nog;i++) {
				double t=node[i].newinfected(recovery_rate, transmission_rate, node);
				temp[i]=t;
			}
			for (int i=0;i<nog;i++) {
				node[i].setnewstate(temp[i], recovery_rate, transmission_rate);
			}
			//System.out.print(node[0].gethealthy()+" "+node[0].getinfected()+" "+node[0].getrecovered()+"     ");
			//System.out.println(node[1].gethealthy()+" "+node[1].getinfected()+" "+node[1].getrecovered());
			
			boolean cb=true;
			for (int i=0;i<nog;i++) {
				if(node[i].getinfected()>0) cb=false;
			}
			//TODO break condition
			if(cb) break;
		}

	}

}
