import edu.duke.StorageResource;
import edu.duke.FileResource;

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
		}
		return sr;
	}
	
	public static double cgRatio(String dna) {
		double cg = 0.0;
		for (int i = 0; i < dna.length(); i++) {
			String ele = dna.substring(i,i+1);
			if (ele.equals("C") || ele.equals("G")) {
				cg = cg +1;
			}
		}
		return cg/dna.length();
	}
	
	public static void processGenes(String dna) {
		StorageResource sr = getAllGenes(dna);
		StorageResource lsr = new StorageResource();
		StorageResource cgsr = new StorageResource();
		int longene = 0;
		for (String gene : sr.data()) {
			if (gene.length() > 60) {
				lsr.add(gene);
			}
			if (cgRatio(gene) > 0.35) {
				cgsr.add(gene);
			}
			if (gene.length() > longene) {
				longene = gene.length();
			}
		}
		System.out.println("\n\nTotal no. of genes in  dna are : "+ sr.size());
		System.out.println("\n\nGenes with greater than 60 characters long");
		for (String lgene : lsr.data()) {
			System.out.println(lgene);
		}
		System.out.println("\nNo. of genes longer than 60 characters are : " + lsr.size());
		System.out.println("\nGenes with a cg ratio greater than 0.35 \n");
		for (String lgene : cgsr.data()) {
			System.out.println(lgene);
		}
		System.out.println("No. of genes with cg ratio greater than 0.35 are : " + cgsr.size());
		System.out.println("\n\n Longest gene is : " + longene);
	}
	
	public static void testprocessGenes() {
//		String[] tests = {
//				"ATGTAATGATGTGGATATAA"
//		};
//		
//		for (String dna : tests) {
//			processGenes(dna);
//		}
//		
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String fdna = fr.asString();
		processGenes(fdna);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testprocessGenes();
	}

}
