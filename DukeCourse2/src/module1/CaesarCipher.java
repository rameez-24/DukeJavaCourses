package module1;

import edu.duke.*;

public class CaesarCipher {
	
	public static String encrypt(String input, int key) {
		StringBuilder encrypted = new StringBuilder(input);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
		for (int i = 0; i < input.length(); i++) {
			char Char = encrypted.charAt(i);
			boolean lowerCase = false;
			int idx = -1;
			if (Character.isLowerCase(Char)) {
				lowerCase = true;
				idx = alphabet.indexOf(Character.toUpperCase(Char));
			} else {
				idx = alphabet.indexOf(Char);
			}
			if (idx != -1) {
				if (lowerCase) {
					encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet.charAt(idx)));
				} else {
					encrypted.setCharAt(i, shiftedAlphabet.charAt(idx));
				}
			}
		}
		return encrypted.toString();
	}
	
	public static String encryptTwoKeys(String input, int key1, int  key2) {
		String encrypted = "";
		for (int i = 0; i < input.length(); i++) {
			if (i%2 == 0) {
				String currStr = encrypt(input.substring(i,i+1), key1);
				encrypted += currStr;
			} else {
				String currStr = encrypt(input.substring(i,i+1), key2);
				encrypted += currStr;
			}
		}
		return encrypted;
	}

	public static void testCaesar() {
//		FileResource fr = new FileResource();
//		String message = fr.asString();
//		System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket", 15));
//		System.out.println("The Key is " + 17 + "/n");
//		FileResource fr = new FileResource();
//		String message2 = fr.asString();
		System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
	}
	
	public static void main(String[] args) {
		testCaesar();
	}

}
