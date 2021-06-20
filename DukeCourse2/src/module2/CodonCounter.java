package module2;

import java.util.HashMap;
import edu.duke.FileResource;

public class CodonCounter {
	
	private HashMap<String,Integer> myMap;
	
	public CodonCounter() {
		myMap = new HashMap<String,Integer>();
	}
	
	private void buildCodonMap(int start, String dna) {
		myMap.clear();
		for (int i = start; i<dna.length()-2; i+=3) {
			String currCodon = dna.substring(i,i+3);
			if (!Character.isLetter(currCodon.charAt(2))) {
				break;
			}
			if (!myMap.containsKey(currCodon)) {
				myMap.put(currCodon, 1);
			} else {
				myMap.put(currCodon, myMap.get(currCodon)+1);
			}
		}
	}
	
	private String getMostCommonCodon() {
		int maxVal = 0;
		String maxCodon = null;
		for (String codon : myMap.keySet()) {
			int val = myMap.get(codon);
			if (val > maxVal) {
				maxVal = val;
				maxCodon = codon;
			}
		}
		return maxCodon;
	}
	
	private void printCodonCounts(int start, int end) {
		for (String codon : myMap.keySet()) {
			int val = myMap.get(codon);
			if (val>= start && val<= end) {
				System.out.println(val + "\t" + codon);
			}
		}
	}
	
	public void tester() {
		FileResource fr = new FileResource();
		String dna = fr.asString();
		dna = dna.toUpperCase();
		for (int k=0; k<3; k++) {
			buildCodonMap(k,dna);
			System.out.println("Total numbers of unique Codons in the frame " + k + " are "+ myMap.size());
			String commonCodon = getMostCommonCodon();
			System.out.println("Most common codon is " + commonCodon + "\t" + myMap.get(commonCodon));
			printCodonCounts(7,7);
		}
	}

	public static void main(String[] args) {
		CodonCounter cc = new CodonCounter();
		cc.tester();
	}

}
