package module2;

import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequencies {
	
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public void findUnique() {
		myWords.clear();
		myFreqs.clear();
		
		FileResource fr = new FileResource();
		for (String word : fr.words()) {
//			System.out.println(word.length());
			if (word.length() == 0) {
				continue;
			}
//			System.out.println("hi"+word + "no");
			word = word.toLowerCase();
//			System.out.println("yes");
//			char lastChar = word.charAt(word.length()-1);
//			System.out.println("3");
//			if (!Character.isLetter(lastChar)) {
//				word = word.substring(0,word.length()-1);
//			}
			int idx = myWords.indexOf(word);
			if (idx != -1) {
				int val = myFreqs.get(idx);
				myFreqs.set(idx, val+1);
			} else {
				myWords.add(word);
				myFreqs.add(1);
			}
		}
	}
	
	public int findIndexOfMax() {
		int maxVal = 0;
		int maxIdx = 0;
		
		for (int i = 0; i < myFreqs.size(); i++) {
			int val = myFreqs.get(i);
			if (val > maxVal) {
				maxVal = val;
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	
	public void tester() {
		findUnique();
		for (int i=0; i<myWords.size(); i++) {
			System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
		}
		System.out.println("Number of Unique words are "+myWords.size());
		int maxIdx = findIndexOfMax();
		System.out.println("Word that occurs the most is " + myWords.get(maxIdx) + ".\nIt occurs " + myFreqs.get(maxIdx) + " times.");
	}
	
	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
	}
}
