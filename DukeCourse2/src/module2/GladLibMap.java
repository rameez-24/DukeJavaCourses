package module2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLibMap {
	
	private HashMap<String,ArrayList<String>> myMap;
	private ArrayList<String> trackWords;
	private ArrayList<String> trackLabels;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "testData/mod2/GladLibData/datalong";
	
	public GladLibMap(){
		myMap = new HashMap<String,ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		trackLabels = new ArrayList<String>();
		trackWords = new ArrayList<String>();
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		trackWords = new ArrayList<String>();
		trackLabels = new ArrayList<String>();
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String[] labels = {"adjective", "noun", "color", "country",
				"name", "animal", "timeframe", "verb", "fruit"};
		for (String label : labels) {
			myMap.put(label, readIt(source+"/"+label+".txt"));
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (myMap.containsKey(label)) {
			return randomFrom(myMap.get(label));
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String label = w.substring(first+1,last);
		if (!trackLabels.contains(label) && !label.equals("number")) {
			trackLabels.add(label);
		}
		String sub = getSubstitute(label);
		while (trackWords.contains(sub)) {
			sub = getSubstitute(label);
//			System.out.println(sub);
		}
		trackWords.add(sub);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	private int totalWordsInMap() {
		int val = 0;
		for (String word : myMap.keySet()) {
			val += myMap.get(word).size();
		}
		return val;
	}
	
	private int totalWordsConsidered() {
		int val = 0;
		for (String label : trackLabels) {
			int currVal = myMap.get(label).size();
			System.out.println(label);
//			System.out.println(currVal);
			val += currVal;
		}
		return val;
	}
	
	public void makeStory(){
		trackWords.clear();
//		trackLabels.clear();
	    System.out.println("\n");
		String story = fromTemplate("testData/mod2/GladLibData/datalong/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nNumber of different words are "+trackWords.size());
		System.out.println("\nTotal number of words in the whole map are "+ totalWordsInMap());
		System.out.println("\nTotal number of words considered in the map are "+ totalWordsConsidered());
	}

	public static void main(String[] args) {
		GladLibMap gl = new GladLibMap();
		gl.makeStory();
	}

}
