package TemperatureF;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class avgTemp {
	
	public static double averageTemperatureInFile(CSVParser parser) {
		double sumOfTemp = 0;
		int noOfRecords = 0;
		for (CSVRecord record : parser) {
			double currTemp = Double.parseDouble(record.get("TemperatureF"));
			if (currTemp == -9999) {
				continue;
			}
			sumOfTemp += currTemp;
			noOfRecords += 1;
		}
		return sumOfTemp/noOfRecords;
	}
	
	public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double value) {
		double sumOfTemp = 0;
		int noOfRecords = 0;
		for (CSVRecord record : parser) {
			double currHumidity = Double.parseDouble(record.get("Humidity"));
			if (currHumidity >= value) {
				double currTemp = Double.parseDouble(record.get("TemperatureF"));
				if (currTemp == -9999) {
					continue;
				}
				sumOfTemp += currTemp;
				noOfRecords += 1;
//				System.out.println(noOfRecords);
			}
		}
		if (noOfRecords == 0) {
			return -9999;
		}
		return sumOfTemp/noOfRecords;
	}
	
	
	public static void testAverageTemperatureInFile() {
		FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
		CSVParser parser = fr.getCSVParser();
		double avgTemp = averageTemperatureInFile(parser);
		System.out.println("Average Temperature in file is " + avgTemp);
	}

	public static void testAverageTemperatureWithHighHumidityInFile() {
		double HumidityValue = 80;
		FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
		CSVParser parser = fr.getCSVParser();
		double avgTemp = averageTemperatureWithHighHumidityInFile(parser, HumidityValue);
		if (avgTemp == -9999) {
			System.out.println("No Temperatures with that Humidity!");
		} else {
			System.out.println("Average Temperature in file is " + avgTemp);
		}	
	}
	
	public static void main(String[] args) {
//		testAverageTemperatureInFile();
		testAverageTemperatureWithHighHumidityInFile();
	}

}
