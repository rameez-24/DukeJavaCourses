package TemperatureF;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Humidity {
	
	public static CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestRecord = null;
		for (CSVRecord currRecord : parser) {
			if (!currRecord.get("Humidity").equals("N/A")) {
				if (lowestRecord == null) {
					lowestRecord = currRecord;
				} else {
					double lowestHumidity = Double.parseDouble(lowestRecord.get("Humidity"));
					double currHumidity = Double.parseDouble(currRecord.get("Humidity"));
					if (currHumidity < lowestHumidity) {
						lowestRecord = currRecord;
					}
			    }
			}
		}
		return lowestRecord;
	}
	
	public static CSVRecord lowestHumidityInManyFiles() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowestRecord = null;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord currRecord = lowestHumidityInFile(parser);
			if (lowestRecord == null) {
				lowestRecord = currRecord;
			} else {
				double lowestHumidity = Double.parseDouble(lowestRecord.get("Humidity"));
				double currHumidity = Double.parseDouble(currRecord.get("Humidity"));
				if (currHumidity < lowestHumidity) {
					lowestRecord = currRecord;
				}
			}
		}
		return lowestRecord;
	}
	
	public static void testLowestHumidityInManyFiles() {
		CSVRecord record = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
	}
	
	public static void testLowestHumidityInFile() {
		FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestRecord = lowestHumidityInFile(parser);
		System.out.println("Lowest Humidity was " + lowestRecord.get("Humidity") + " at " + lowestRecord.get("DateUTC"));
	}

	public static void main(String[] args) {
//		testLowestHumidityInFile();
		testLowestHumidityInManyFiles();
	}

}
