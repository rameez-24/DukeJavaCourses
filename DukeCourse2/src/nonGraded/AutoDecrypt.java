package nonGraded;

import edu.duke.FileResource;
import module1.CaesarCipher;

public class AutoDecrypt {
	
	public int[] countLetters(String input) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		
		for (int i=0; i < input.length(); i++) {
			int idx = alphabet.indexOf(input.substring(i,i+1));
			if (idx != -1) {
				counts[idx] += 1;
			}
		}
		return counts;
	}
	
	public int maximumIdx(int[] count) {
		int maxIdx = 0;
		int max = 0;
		for (int j = 0; j < count.length; j++) {
			int k = count[j];
//			System.out.println(k + " "+ max);
			if (max < k) {
				maxIdx = j;
				max = k;
			}
		}
		return maxIdx;
	}
	
	public void decrypt(String input) {
		
		int [] counts = countLetters(input);
		int maxIdx = maximumIdx(counts);
		
//		for (int i=0; i < counts.length; i++) {
//			System.out.println(i +" "+ counts[i]);
//		}
		
		int key = 4 - maxIdx;
		if (maxIdx > 4) {
			key = 26 - (maxIdx-4);
		}
		
//		System.out.println(input + key + maxIdx);
		
//		CaesarCipher cc = new CaesarCipher();
		String encrypted = CaesarCipher.encrypt(input, key);
		
		System.out.println(encrypted);
	}
	
	public void testDecrypt() {
		FileResource fr = new FileResource();
		String input = fr.asString();
		decrypt(input);
	}

	public static void main(String[] args) {
		AutoDecrypt ad = new AutoDecrypt();
		ad.testDecrypt();
	}

}