package pl.asia;

import java.util.Random;
import java.lang.Math;

public class Main {

	public static void main(String[] args) {
		System.out.println("STAAART");
		Random rand = new Random();
		int nog=6; //number of groups;
		Group node[] = new Group[nog];
		
		float neigh[][]= new float [nog][nog];
		
		for (int i=0;i<nog;i++) {//starting conditions
			node[i] = new Group(20, 5);
	//		node[i]=new Group();
	//		node[i].population=20;
	//		node[i].infected=rand.nextInt(5);
	//		node[i].healthy=node[i].population-node[i].infected;
	//		node[i].recovered=0;
		}
		node[0].healthy=19;
		node[0].infected=1;
		//starting conditions done

		//neighbourhood array
		for (int i=0;i<nog;i++) {
			for(int j=0;j<i;j++) {
				int n=rand.nextInt(101);
				neigh[i][j]=n/100+(float)0.2;
				neigh[j][i]=n/100+(float)0.2;
				
			}
			neigh[i][i]=1;
		}//neigh array done
		
		
		while(true) { //one day loop
			
			for (int i=0;i<nog;i++) {
				float n=0;
				for (int j=0;j<nog;j++) {
					n+=node[j].getinfected()*neigh[i][j];
				}//done counting infected
				int ni=Math.round(n);
				//int nr=Math.round(node[i].infected/4)+1;
				node[i].setnewstate(ni);
				System.out.print(node[i].gethealthy() + ", " + node[i].getinfected() + ", " + node[i].recovered + ", ");
				
			}
			System.out.println(" ");
			
			boolean cb=true;
			for (int i=0;i<nog;i++) {
				if(node[i].recovered<node[i].population) cb=false;
			}
			//TODO break condition
			if(cb) break;
		}

	}

}
