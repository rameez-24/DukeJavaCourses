package module2;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordInFiles {
	
	private HashMap<String, ArrayList<String>> myMap;
	
	public WordInFiles() {
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f) {
		FileResource fr = new FileResource(f);
		for (String word : fr.words()) {
//			word = word.toLowerCase();
			ArrayList<String> list;
			if (!myMap.containsKey(word)) {
				list = new ArrayList<String>();
				list.add(f.getName());
				myMap.put(word, list);
			} else {
				list = myMap.get(word);
				if (!myMap.get(word).contains(f.getName())) {
					list.add(f.getName());
					myMap.put(word, list);
				}
			}
		}
	}
	
	private void buildWordFileMap() {
		myMap.clear();
		DirectoryResource dr = new DirectoryResource();
		for (File f: dr.selectedFiles()) {
			addWordsFromFile(f);
		}
	}
	
	private int maxNumber() {
		int maxVal = 0;
		for (String word : myMap.keySet()) {
			int val = myMap.get(word).size();
			if (val > maxVal) {
				maxVal = val;
			}
		}
		return maxVal;
	}
	
	private ArrayList<String> wordsInNumFiles(int num) {
		ArrayList<String> words = new ArrayList<String>();
		for (String word : myMap.keySet()) {
			if (myMap.get(word).size() == num) {
				words.add(word);
			}
		}
		return words;
	}
	
	private void printFilesIn(String word) {
		ArrayList<String> list = myMap.get(word);
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void tester() {
		buildWordFileMap();
//		int maxVal = maxNumber();
//		System.out.println("Maximum number of files any word is in is " + maxVal);
//		
//		System.out.println("\nAll the word in maximum files are : \n");
		ArrayList<String> words = wordsInNumFiles(7);
		System.out.println(words.size());
//		for (int i= 0; i<words.size(); i++) {
//			String word = words.get(i);
//			System.out.println("\n\n" + word);
//			System.out.println("All the Files the word is in are :");
//			printFilesIn(word);
//		}
//		
//		System.out.println("\n\nThe whole word map is \n");
//		for (String currWord : myMap.keySet()) {
//			System.out.println("\n"+currWord + "\nCurrent word is in\n");
//			printFilesIn(currWord);
//		}
		printFilesIn("laid");
	}
	
	public static void main(String[] args) {
		WordInFiles wf = new WordInFiles();
		wf.tester();
	}

}
