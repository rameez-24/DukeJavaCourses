package module4;

import edu.duke.FileResource;
import java.util.Arrays;

public class Tester {
	
	private void testSliceString() {
		VigenereBreaker vbr = new VigenereBreaker();
		System.out.println(vbr.sliceString("abcdefghijklm", 2, 5));
	}

	private void testTryKeyLength() {
		VigenereBreaker vbr = new VigenereBreaker();
		FileResource fr = new FileResource("testData/mod4/secret/secretmessage1.txt");
		String text = fr.asString();
//		System.out.println(text);
		int[] key = vbr.tryKeyLength(text, 4, 'e');
		System.out.println(Arrays.toString(key));
	}
	
	private void testBreakVigenere() {
		VigenereBreaker vbr = new VigenereBreaker();
		vbr.breakVigenere();
	}

	private void testBreakVigenereUnknownKeyLength() {
		VigenereBreaker vbr = new VigenereBreaker();
		vbr.breakVigenereUnknownKeyLength();
	}
	
	private void testBreakVigenereUnknownKeyLengthLanguage() {
		VigenereBreaker vbr = new VigenereBreaker();
		vbr.breakVigenereUnknownKeyLengthLanguage();
	}
	
	public static void main(String[] args) {
		Tester t = new Tester();
//		t.testSliceString();
//		t.testTryKeyLength();
//		t.testBreakVigenere();
//		t.testBreakVigenereUnknownKeyLength();
		t.testBreakVigenereUnknownKeyLengthLanguage();
	}

}
