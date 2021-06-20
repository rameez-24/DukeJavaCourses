package module1;

import edu.duke.FileResource;
import nonGraded.AutoDecrypt;

public class CaesarBreaker {
	
	public static String halfOfString(String message, int start) {
		String half = "";
		for (int i = 0; i < message.length(); i++) {
			if(i%2 == start) {
				half += message.substring(i,i+1);
			}
		}
		return half;
	}
	
	public static int getKey(String s) {
		AutoDecrypt ad = new AutoDecrypt();
		int [] count = ad.countLetters(s);
		int maxIdx = ad.maximumIdx(count);
		
		int key = 4 - maxIdx;
		if (maxIdx > 4) {
			key = 26 - (maxIdx-4);
		}
		return key;
	}

	public static void decryptTwoKeys(String encrypted) {
		String firstHalf = halfOfString(encrypted, 0);
		String secondHalf = halfOfString(encrypted, 1);
		int firstKey = getKey(firstHalf);
		int secondKey = getKey(secondHalf);
		
//		int firstKey = 12;
//		int secondKey = 2; 
		
		String decrypted = CaesarCipher.encryptTwoKeys(encrypted, firstKey, secondKey);
		System.out.println("First key is "+ firstKey +"\nSecond key is " + secondKey);
		System.out.println(decrypted);
	}
	
	public static void testDecryptTwoKeys() {
		FileResource fr = new FileResource();
		String encrypted = fr.asString();
		decryptTwoKeys(encrypted);
//		decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
	}
	
	public static void main(String[] args) {
		testDecryptTwoKeys();
	}

}
