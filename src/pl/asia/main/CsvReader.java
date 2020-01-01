package pl.asia.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CsvReader {
	
	private static final String[] days = {"monday.csv", "tuesday.csv", "wednesday.csv", "thursday.csv", "friday.csv", "saturday.csv", "sunday.csv"};

	/**
	 * @param groupsNumber - równe wielkoœci tablicy w pliku z danymi
	 * @return
	 */
	public static double[][][] read(int groupsNumber) {
		
        double[][][] weekData = new double[7][][];
        int dayNo = 0;
        
        for (String day : days) {
        	double[][] dayData = parseCsvFile(groupsNumber, day);
        	weekData[dayNo] = dayData;
        	dayNo++;
        }

        return weekData;
    }
	
	private static double[][] parseCsvFile(int groupsNumber, String fileName) {
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        double[][] dayData = new double[groupsNumber][groupsNumber];
	   try {
            br = new BufferedReader(new FileReader(fileName));
            
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
            	
                // use comma as separator
                String[] lineStringData = line.split(cvsSplitBy);
                
                double[] lineValues = Arrays.stream(lineStringData)
                        .mapToDouble(Double::parseDouble)
                        .toArray();
                
                dayData[lineNo]=lineValues;
                lineNo++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	   return dayData;
	}
}
