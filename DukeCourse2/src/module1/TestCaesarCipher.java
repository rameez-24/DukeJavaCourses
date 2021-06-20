package module1;

import edu.duke.FileResource;
import nonGraded.AutoDecrypt;

public class TestCaesarCipher {
	
	public String breakCaesarCipher(String input) {
		AutoDecrypt ad = new AutoDecrypt();
		int[] count = ad.countLetters(input);
		int maxIdx = ad.maximumIdx(count);
		
		int key = maxIdx - 4;
		if (maxIdx < 4) {
			key = 26 - (maxIdx - 4);
		}
		System.out.println("Key is " + key);
		
		CaesarCipherObject cco = new CaesarCipherObject(key);
		return cco.decrypt(input);
	}
	
	public void simpleTests() {
		FileResource fr = new FileResource();
		String input = fr.asString();
		
//		CaesarCipherObject cco = new CaesarCipherObject(1);
//		String encrypted = cco.encrypt(input);
//		System.out.println(encrypted);
//		System.out.println(cco.decrypt(encrypted));
		
		System.out.println(breakCaesarCipher(input));
	}
	
	public static void main(String[] args) {
		TestCaesarCipher tcc = new TestCaesarCipher();
		tcc.simpleTests();
	}
	
}
