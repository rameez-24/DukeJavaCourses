package TemperatureF;

import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

public class Temperature {
	
	public static CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestRecord = null;
		for (CSVRecord currRecord : parser) {
			if (!currRecord.get("TemperatureF").equals("-9999")) {
				if (coldestRecord == null) {
					coldestRecord = currRecord;
				} else {
					double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
					double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
					if (currTemp < coldestTemp) {
						coldestRecord = currRecord;
					}
			    }
			}
		}
//		System.out.println(coldestRecord.get("TemperatureF"));
		return coldestRecord;
	}
	
	public static String fileWithColdestTemperature() {
		DirectoryResource dr = new DirectoryResource();
		File coldestFile = null;
		double coldestTemp = 9999;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord r = coldestHourInFile(parser);
			double currTemp = Double.parseDouble(r.get("TemperatureF"));
			if (coldestFile == null) {
				coldestFile = f;
				coldestTemp = currTemp;
			} else {
				if (currTemp < coldestTemp) {
					coldestFile = f;
					coldestTemp = currTemp;
				}
			}
		}
		return coldestFile.getName();
	}
	
	public static void testColdestHourInFile() {
		FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldestRecord = coldestHourInFile(parser);
		System.out.println("Coldest Hour on the day was at : " + coldestRecord.get("TimeEDT"));
	}

	public static void testFileWithColdestTemperature() {
		String fileName = fileWithColdestTemperature();
		System.out.println("Coldest day was in File  " + fileName);
		String dir = "nc_weather/"+fileName.substring(8,12)+"/"+fileName;
		FileResource fr = new FileResource(dir);
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldestRecord = coldestHourInFile(parser);
		System.out.println("Coldest Temperature on the day was  " + coldestRecord.get("TemperatureF"));
		parser = fr.getCSVParser();
		System.out.println("All the temperatures on Coldest day were:");
		for (CSVRecord record : parser) {
			System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testColdestHourInFile();
		testFileWithColdestTemperature();
	}

}
