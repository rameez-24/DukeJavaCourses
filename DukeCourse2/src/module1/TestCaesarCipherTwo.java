package module1;

import edu.duke.FileResource;
//import module1.CaesarBreaker;
import nonGraded.AutoDecrypt;

public class TestCaesarCipherTwo {
	
	public String breakCaesarCipher(String input) {
		CaesarBreaker cb = new CaesarBreaker();
		String firstHalf = CaesarBreaker.halfOfString(input, 0);
		String secondHalf = CaesarBreaker.halfOfString(input,1);
		
		AutoDecrypt ad = new AutoDecrypt();
		int[] firstCount = ad.countLetters(firstHalf);
		int[] secondCount = ad.countLetters(secondHalf);
		int maxIdx1 = ad.maximumIdx(firstCount);
		int maxIdx2 = ad.maximumIdx(secondCount);
		
		int key1 = maxIdx1 - 4;
		if (maxIdx1 < 4) {
			key1 = 26 - (maxIdx1 - 4);
		}
		
		int key2 = maxIdx2 - 4;
		if (maxIdx1 < 4) {
			key1 = 26 - (maxIdx2 - 4);
		}
		
		CaesarCipherTwo cct = new CaesarCipherTwo(key1,key2);
		return cct.decrypt(input);
	}
	
	public void simpleTest() {
		FileResource fr = new FileResource();
		String input = fr.asString();
		
//		CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
//		String encrypted = cct.encrypt(input);
//		System.out.println(encrypted);
//		System.out.println(cct.decrypt(encrypted));
		
		System.out.println(breakCaesarCipher(input));
	}

	public static void main(String[] args) {
		TestCaesarCipherTwo tct = new TestCaesarCipherTwo();
		tct.simpleTest();
	}

}
