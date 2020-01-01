package pl.asia;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import pl.asia.main.CsvReader;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("STAAART");
		
		//int groupSize = 3;
		//double[][][] data = CsvReader.read(groupSize);
		
		//for (double[][] dayData : data) {
		//	for (int i=0;i<3;i++) {
		//		System.out.println(dayData[i][0]);
		//		System.out.println(dayData[i][1]);
		//		System.out.println(dayData[i][2]);
		//	}
		//}
		
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Number of groups: ");
		int nog=Integer.parseInt(reader.readLine()); //number of groups;
		double recovery_rate=0.6;
		double transmission_rate=0.5;
		
		
		Group node[] = new Group[nog];
		
		node[0]=new Group(100,20,nog,0);
		for (int i=1;i<nog;i++) {//starting conditions
			node[i] = new Group(100, 0,nog,i);
		}
		
		//starting conditions done

		System.out.print(node[0].gethealthy()+" "+node[0].getinfected()+" "+node[0].getrecovered()+"     ");
		System.out.println(node[1].gethealthy()+" "+node[1].getinfected()+" "+node[1].getrecovered());
		int time=0;
		
		
		
		while(true) { //one day loop
			time++;
			
			double[] newinf = new double[nog];
			double[] newhlt = new double[nog];
			double[] newrcv = new double[nog];
			int hsum=0;
			int isum=0;
			int rsum=0;
			
			for (int i=0;i<nog;i++) {
				newinf[i]=node[i].newinfected(recovery_rate, transmission_rate, node);
				newhlt[i]=node[i].newhealthy(recovery_rate, transmission_rate, node);
				newrcv[i]=node[i].newrecovered(recovery_rate, transmission_rate, node);
			}
			
			for (int i=0;i<nog;i++) {
				node[i].setnewstate(newinf[i], newhlt[i], newrcv[i]);
				hsum+=node[i].gethealthy();
				isum+=node[i].getinfected();
				rsum+=node[i].getrecovered();
			}
			System.out.print(node[0].gethealthy()+" "+node[0].getinfected()+" "+node[0].getrecovered()+"     ");
			System.out.print(node[1].gethealthy()+" "+node[1].getinfected()+" "+node[1].getrecovered()+"     ");
			System.out.println(hsum+" "+isum+" "+rsum);
			boolean cb=true;
			//for (int i=0;i<nog;i++) {
			//	if(node[i].getinfected()>0) cb=false;
			//}
			//TODO break condition
			if(time>=15) break;
		}

	}

}
