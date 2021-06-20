import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class babyNames {
	
	public static void printNames(FileResource fr) {
		for (CSVRecord rec : fr.getCSVParser(false)) {
			System.out.println("Name : " + rec.get(0) +
							   " | Gender : " + rec.get(1) +
							   " | Num Births : " + rec.get(2));
		}
	}

	public static void totalBirths(FileResource fr) {
		int totalbirths = 0;
		int numboys = 0;
		int numgirls = 0;
		int totNames = 0;
		int girlNames = 0;
		int boyNames = 0; 
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numbirths = Integer.parseInt(rec.get(2));
			totalbirths += numbirths;
			if (rec.get(1).equals("M")) {
				boyNames += 1;
				numboys += numbirths;
			} else {
				girlNames += 1;
				numgirls += numbirths;
			}
			totNames += 1;
		}
		System.out.println("Total births are  " + totalbirths);
		System.out.println("Total boys are  " + numboys);
		System.out.println("Total girls are  " + numgirls);
		System.out.println("Total names are  " + totNames);
		System.out.println("Total boy names are  " + boyNames);
		System.out.println("Total girls names are  " + girlNames);
	}
	
	public static int getRank(int year, String name, String gender) {
		int frank = 0;
		int rank = 0;
		boolean condition = false; 
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(1).equals(gender)) {
				frank += 1;
				if (record.get(0).equals(name)) {
					rank = frank;
					condition = true;
//					return rank;
				}
			}
		}
		if (condition) {
			return rank;
		}
		return -1;
	}
	
	public static String getName(int year,int rank, String gender) {
		String Name = "NO NAME";
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(1).equals(gender)) {
				if (rank == 1) {
					Name = record.get(0);
					break;
				}
				if (rank < 0) {
					break;
				}
				rank -= 1;
			}
		}
		return Name;
	}
	
	public static void whatIsNameInYear(String name, int year, int newyear, String gender) {
		int rank = getRank(year, name, gender);
		if (rank != -1) {
			String newName = getName(newyear, rank, gender);
			if (newName.equals("NO NAME")) {
				System.out.println("Your name is not so popular :/");
			} else {
				System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newyear);
			}
		}
	}
	
	public static int yearOfHighestRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int rank = 0;
		int year = 0;
		for (File f : dr.selectedFiles()) {
			int fileYear = Integer.parseInt(f.getName().substring(3,7));
//			System.out.println(fileYear);
			int fileRank = getRank(fileYear,name,gender);
			if (fileRank != -1) {
				if (rank == 0 || rank > fileRank) {
					rank = fileRank;
					year = fileYear;
				}
			}
		}
		if (rank == 0) {
			return -1;
		}
		return year;
	}
	
	public static double getAverageRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int rankSum = 0;
		int noOfYears = 0;
		double avgRank = 0.0;
		for (File f : dr.selectedFiles()) {
			int fileYear = Integer.parseInt(f.getName().substring(3,7));
			int fileRank = getRank(fileYear,name,gender);
//			System.out.println(fileRank);
			if (fileRank != -1) {
				rankSum += fileRank;
				noOfYears += 1;
			}
		}
		if (rankSum == 0) {
			return -1.0;
		}
		double sum = rankSum; 
//		System.out.println(sum);
//		System.out.println(noOfYears);
		avgRank = sum/noOfYears;
		return avgRank;
	}
	
	public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int totalBirths = 0;
		int rank = getRank(year, name, gender);
		FileResource fr = new FileResource("data/us_babynames_by_year/yob"+year+".csv");
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(1).equals(gender)) {
				if (rank > 1) {
					totalBirths += Integer.parseInt(record.get(2));
					rank -= 1;
				} else {
					break;
				}
			}
		}
		return totalBirths;
	}
	
	public static void testTotalBirths() {
		FileResource fr = new FileResource("data/us_babynames_by_year/yob1905.csv");
		printNames(fr);
		totalBirths(fr);
	}
	
	public static void tests() {
//		System.out.println(getRank(1971,"Frank","M"));
//		System.out.println(getName(1982,450,"M"));
//		whatIsNameInYear("Owen",1974,2014,"M");
//		System.out.println(yearOfHighestRank("Mich","M"));
//		System.out.println(getAverageRank("Robert","M"));
		System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
	}
	
	public static void main(String[] args) {
//		testTotalBirths();
		tests();
		}

}
