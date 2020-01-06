package pl.asia;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import pl.asia.main.CsvReader;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("STAAART");

		
		int nog=18;
		double[][][] data = CsvReader.read(nog);
		
		//for (double[][] dayData : data) {
		//	for (int i=0;i<3;i++) {
		//		System.out.println(dayData[i][0]);
		//		System.out.println(dayData[i][1]);
		//		System.out.println(dayData[i][2]);
		//	}
		//}
		

		double recovery_rate=0.4;
		double transmission_rate=0.38;
		
		
		Group node[] = new Group[nog];
		int poplist[] = {69, 42, 130, 27, 49, 35, 91, 35, 22, 81, 89, 36, 58, 66, 21, 21, 66, 28};

		for (int i=0;i<nog;i++) {//starting conditions
			int pop=poplist[i];
			int inf=(int) pop/10;
			node[i] = new Group(pop, inf ,nog, i, data);

		}
		
		//starting conditions done
		int readind=18;
		readind--;
		//System.out.println(popmax);
		System.out.println(node[readind].gethealthy()+" "+node[readind].getinfected()+" "+node[readind].getrecovered());
		int time=0;
		
		
		
		while(true) { //one day loop
			time++;
			if(time==7)time=0;
			
			double[] newinf = new double[nog];
			double[] newhlt = new double[nog];
			double[] newrcv = new double[nog];
			int hsum=0;
			int isum=0;
			int rsum=0;
			
			for (int i=0;i<nog;i++) {
				newinf[i]=node[i].newinfected(recovery_rate, transmission_rate, node, time);
				newhlt[i]=node[i].newhealthy(recovery_rate, transmission_rate, node, time);
				newrcv[i]=node[i].newrecovered(recovery_rate, transmission_rate, node);
			}
			
			for (int i=0;i<nog;i++) {
				node[i].setnewstate(newinf[i], newhlt[i], newrcv[i]);
				hsum+=node[i].gethealthy();
				isum+=node[i].getinfected();
				rsum+=node[i].getrecovered();
			}
			System.out.println(node[readind].gethealthy()+" "+node[readind].getinfected()+" "+node[readind].getrecovered());
			//System.out.println((hsum+isum+rsum)+" "+hsum+" "+isum+" "+rsum);
			boolean cb=true;
			for (int i=0;i<nog;i++) {
				if(node[i].getinfected()>0) cb=false;
			}
			//TODO break condition
			if(cb) {
				//System.out.println(rsum);
				break;
			}
		}

	}

}
