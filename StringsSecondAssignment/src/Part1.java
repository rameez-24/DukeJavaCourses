
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
	
	public static String findGene(String dna, int startIndex) {
//		int startIndex = dna.indexOf("ATG", where);
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
	
	public static void printAllGenes(String dna) {
		int startIndex = 0;
		startIndex = dna.indexOf("ATG");
		while (startIndex != -1 ) {
			String Gene = findGene(dna,startIndex);
			if (!Gene.isEmpty()) {
				System.out.println(Gene);
			}
			startIndex = dna.indexOf("ATG",startIndex+3);
		}
	}

	public static void testFindstopCodon() {
		String[][] tests = {
				{"ATGTATATGAATTAGATTAGA","0","TAG"},
				{"GAATGTAGTATGAATAAATTAGA","2","TAA"},
				{"ATGTAA","0","TAG"}
		};
		for (String[] test : tests) {
			int i = Integer.parseInt(test[1]);
			System.out.println("dna is : " + test[0]);
			System.out.println("stop codon is at : "+findStopCodon(test[0],i,test[2])+"\n");
		}
	}
	
	public static void testFindGene() {
		String[] tests = {
				"ATGTATATGAATTAGATTAGA",
				"GAATGTAGTATGAATAAATTAGA",
				"ATGTAA"
		};
		for (String test : tests) {
			System.out.println("DNA is : " + test);
			System.out.println("Gene is : "+findGene(test,0)+"\n");
		}
	}
	
	public static void testPrintAllGenes(){
		String[] testCases = {
				"GAATGCTATACTCACAGTAGTTAGGGTAA",
				"TCAATGCCATATTGACAATAGGG",
				"ATGTAATTATAG",
				"AATATTGTTAATAGATGAATATA",
				"AGATGAAATAATAGATGGTTATTTAAGCTACACCATGAGGTTAAGGTGA",
				"ATGAAAATGAAATAA",
				"AATGCTAACTAGCTGACTAAT",
				"ATGAATGTAA"
				};
		int i = 0;
		for(String dna : testCases){
			System.out.println("Test case "+ i);
			printAllGenes(dna);
			i++;
		}
	}
	
	public static void main(String[] args) {
		testPrintAllGenes();
	}

}
