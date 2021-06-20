import edu.duke.*;

public class Part1 {
	
	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		int currIndex = dna.indexOf(stopCodon,startIndex+3);
		while (currIndex != -1) {
			if ((currIndex - startIndex) % 3 == 0) {
				return currIndex;
			} else {
				currIndex = dna.indexOf(stopCodon,currIndex+3);
			}
		}
		return dna.length();
	}
	
	public static String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if (startIndex != -1) {
			int taaIndex = findStopCodon(dna,startIndex,"TAA");
			int tagIndex = findStopCodon(dna,startIndex,"TAG");
			int tgaIndex = findStopCodon(dna,startIndex,"TGA");
			int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
			if (minIndex != dna.length()) {
				return dna.substring(startIndex,minIndex+3);
			}
			return "";
		}
		return "";
	}
	
	public static StorageResource getAllGenes(String dna) {
		StorageResource sr = new StorageResource();
//		int startIndex = 0;
		int startIndex = dna.indexOf("ATG");
		while (startIndex != -1 ) {
			String Gene = findGene(dna,startIndex);
			if (!Gene.isEmpty()) {
				sr.add(Gene);
				startIndex = dna.indexOf("ATG",startIndex+Gene.length());
			} else {
				startIndex = dna.indexOf("ATG",startIndex+3);
			}
//			startIndex = dna.indexOf("ATG",startIndex+Gene.length());
		}
		return sr;
	}
	
	public static void testgetAllGenes(){
		String[] testCases = {
				"GAATGCTATACTCACAGTAGTTAGGGTAA",
				"TCAATGCCATATTGACAATAGGG",
				"ATGTAATTATAG",
				"AATATTGTTAATAGATGAATATA",
				"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGTGA",
				"ATGAAAATGAAATAA",
				"AATGCTAACTAGCTGACTAAT",
				"ATGAATGTAA",
				"ATGTAATGATGTGGATATAA"
				};
		int i = 1;
		for(String dna : testCases){
			System.out.println("Test case "+ i);
			StorageResource tsr = getAllGenes(dna);
			for (String str : tsr.data()) {
				System.out.println(str);
			}
			i++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testgetAllGenes();
	}

}
