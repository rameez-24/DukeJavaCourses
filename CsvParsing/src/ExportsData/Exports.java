package ExportsData;

import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports {
	
	public static String countryInfo(CSVParser parser, String country) {
		for (CSVRecord record : parser) {
			String countryname = record.get("Country");
			if (countryname.equals(country)) {
				String retstr = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
				return retstr;
			}
		}
		return "NOT FOUND";
	}
	
	public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String exportItems = record.get("Exports");
			if (exportItems.contains(exportItem1) && exportItems.contains(exportItem2)) {
				System.out.println(record.get("Country"));
			}
		}
	}
	
	public static int numberOfExporters(CSVParser parser, String exportItem) {
		int i = 0;
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(exportItem)) {
				i++;
			}
		}
		return i;
	}
	
	public static void bigExporters(CSVParser parser, String amount) {
		for(CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			if (value.length() > amount.length()) {
				System.out.println(record.get("Country")+ " " + value);
			}
		}
	}
	
	public static void tester() {
		FileResource fr = new FileResource();
		CSVParser pr = fr.getCSVParser(); 
		System.out.println(countryInfo(pr,"Nauru"));
		
		pr = fr.getCSVParser();
		listExportersTwoProducts(pr, "cotton", "flowers");
		
		pr = fr.getCSVParser();
		System.out.println(numberOfExporters(pr, "cocoa"));
		
		pr = fr.getCSVParser();
		bigExporters(pr,"$999,999,999,999");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tester();
	}

}
