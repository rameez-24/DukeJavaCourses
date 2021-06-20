
public class Part3 {
	
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
	
	public static int countGenes(String dna) {
		int startIndex = 0;
		String Gene = findGene(dna,startIndex);
		int i = 0;
		while (!Gene.isEmpty()) {
			i++;
			startIndex = dna.indexOf("ATG",startIndex) + Gene.length();
			Gene = findGene(dna,startIndex);
		}
		return i;
	}
	
	public static void testcountGenes(){
		String[] testCases = {
				"GAATGCTATACTCACAGTAGTTAGGGTAA",
				"TCAATGCCATATTGACAATAGGG",
				"ATGTAATTATAG",
				"AATATTGTTAATAGATGAATATA",
				"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGTGA",
				"ATGAAAATGAAATAA",
				"ATGTAAGATGCCCTAGT"
				};
		int i = 0;
		for(String dna : testCases){
			System.out.println("Test case "+ i);
			System.out.println(countGenes(dna));
			i++;
		}
	}

	public static void main(String[] args) {
		testcountGenes();
	}

}
