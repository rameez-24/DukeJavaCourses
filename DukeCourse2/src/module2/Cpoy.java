package module2;

import edu.duke.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

public class Cpoy {

	private HashMap<String, ArrayList<String>> wordsMap;
	    
    public Cpoy() {
    	wordsMap = new HashMap<String, ArrayList<String>>();
    }
	    
	    private void addWordsFromFile(File f) {
	        FileResource fr = new FileResource(f);
	        for(String word : fr.words()) {
	            if (!wordsMap.containsKey(word)) {
	                //System.out.println("Adding \"" + w + "\" to hash map");
	                wordsMap.put(word, new ArrayList<String>());
	            }
	            String fileName = f.getName();
	            
	            if (!wordsMap.get(word).contains(fileName)) {
	                wordsMap.get(word).add(fileName);
	            }
	        }
	    }
	    
	    void buildWordFileMap() {
	        wordsMap.clear();
	        DirectoryResource dr = new DirectoryResource();
	        for(File f : dr.selectedFiles()) {
	            addWordsFromFile(f);
	        }
	    }
	    
	    int maxNumber() {
	        int size = 0;
	        for(String word : wordsMap.keySet()) {
	            ArrayList<String> filenames = new ArrayList<String>();
	            filenames = wordsMap.get(word);
	            int currentSize = filenames.size();
	            if(currentSize > size) {
	                size = currentSize;
	            }
	        }
	        return size;
	    }
	    
	    public ArrayList<String> wordsInNumFiles(int number) {
	        ArrayList<String> output = new ArrayList<String>();
	        for (ArrayList<String> arrList : wordsMap.values()) {
	            int currNumFiles = arrList.size();
	            if (currNumFiles == number) {
	                output = arrList;
	            }
	        }
	        System.out.println("No array list was found for file appearance of " + number);
	        return output;
	        
	    }
	    
	    void printFilesIn(String word) {
	        for(String key : wordsMap.keySet()) {
	            ArrayList<String> filename = new ArrayList<String>();
	            filename = wordsMap.get(key);
	            if(word.equals(key)) {
	                for(int i = 0; i < filename.size(); i++) {
	                    System.out.println(filename.get(i));
	                }
	            }
	        }
	    }
	    
	    void tester() {
	       buildWordFileMap();
	       System.out.println(wordsMap.size());
	       System.out.println("Max number of files: " + maxNumber());
	       ArrayList<String> sample = wordsInNumFiles(5);
	       System.out.println(sample.size());
	       for(int i = 0; i < sample.size(); i++) {
	           System.out.println(sample.get(i));
	       }
	       System.out.println(sample.size());
	       
	       printFilesIn("tree"); //should print brief1.txt, 3, 4
	       //printFilesIn("dogs"); //should print brief2.txt, 3
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cpoy c = new Cpoy();
		c.tester();
	}

}
