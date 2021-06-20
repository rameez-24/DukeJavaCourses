package module4;

import java.util.HashSet;
import java.util.HashMap;
import java.io.File;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i<message.length(); i += totalSlices) {
        	sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
        	String sliced = sliceString(encrypted, i, klength);
        	int newKey = ccr.getKey(sliced);
        	key[i] = newKey;
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
    	HashSet<String> dict = new HashSet<String>();
    	for (String word : fr.lines()) {
    		dict.add(word.toLowerCase());
    	}
    	return dict;
    }
    
    public int countWords(String message, HashSet<String> dict) {
    	int valid = 0;
    	for (String word : message.split("\\W+")) {
    		if (dict.contains(word.toLowerCase())) {
    			valid++;
    		}
    	}
    	return valid;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict) {
    	int highestWords = 0;
    	String decrypted = null;
    	int keyLength = 0;
    	for (int i = 1; i < 101; i++) {
    		int[] keys = tryKeyLength(encrypted, i, mostCommonCharIn(dict));
        	VigenereCipher vc = new VigenereCipher(keys);
        	String text = vc.decrypt(encrypted);
        	int totalWords = countWords(text, dict);
//        	if (i == 38) {
//        		System.out.println(totalWords);
//        	}
        	if (totalWords > highestWords) {
        		decrypted = text;
        		highestWords = totalWords;
        		keyLength = i;
        	}
//        	System.out.println(totalWords);
    	}
//    	System.out.println(keyLength);
//    	System.out.println(highestWords);
    	return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dict) {
    	String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	int[] lets = new int[26];
    	for (String word : dict) {
    		for (int i = 0; i<word.length(); i++) {
    			int idx = alphabet.indexOf(word.charAt(i));
    			if (idx != -1) {
        			lets[idx] += 1;
    			}
    		}
    	}
    	int maxDex = 0;
        for(int k=0; k < lets.length; k++){
            if (lets[k] > lets[maxDex]){
                maxDex = k;
            }
        }
        return alphabet.charAt(maxDex);
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
    	int maxVal = 0;
    	String original = null;
    	String lang = null;
    	for (String language : languages.keySet()) {
    		String decrypted = breakForLanguage(encrypted, languages.get(language));
    		System.out.println("Decrypted for " + language);
    		int val = countWords(decrypted, languages.get(language));
    		System.out.println("Word count for this language is " + val);
    		if (val > maxVal) {
    			maxVal = val;
    			original = decrypted;
    			lang = language;
    		}
    	}
    	System.out.println(original.substring(0,100));
    	System.out.println(lang);
    	System.out.println(maxVal);
    }
    
    public void breakVigenere() {
    	FileResource fr = new FileResource("testData/mod4/TestData/athens_keyflute.txt.");
    	String text = fr.asString();
    	int[] keys = tryKeyLength(text, 4, 'e');
    	VigenereCipher vc = new VigenereCipher(keys);
    	System.out.println(vc.decrypt(text).substring(0,100));
    }
    
    public void breakVigenereUnknownKeyLength() {
    	FileResource fr = new FileResource("testData/mod4/secret/secretmessage2.txt");
    	String text = fr.asString();
    	FileResource FR = new FileResource("testData/mod4/dictionaries/English");
    	HashSet<String> dict = readDictionary(FR);
    	System.out.println(breakForLanguage(text, dict).substring(0,100));
    }
    
    public void breakVigenereUnknownKeyLengthLanguage() {
    	FileResource fr = new FileResource("testData/mod4/secret/secretmessage4.txt");
    	String text = fr.asString();
    	HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
    	DirectoryResource dr = new DirectoryResource();
    	for (File newFile : dr.selectedFiles()) {
    		String name = newFile.getName();
    		FileResource res = new FileResource("testData/mod4/dictionaries/"+name);
    		languages.put(name, readDictionary(res));
    		System.out.println("read in " + name);
    	}
    	breakForAllLangs(text, languages);
    }
    
}
