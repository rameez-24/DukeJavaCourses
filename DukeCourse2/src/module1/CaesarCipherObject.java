package module1;

public class CaesarCipherObject {
	
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;
	
	public CaesarCipherObject() { }
	
	public CaesarCipherObject(int Key) {
		mainKey = Key;
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(Key) + alphabet.substring(0,Key);
	}
	
	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder(input);
		for (int i=0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			boolean lowerCase = false;
			int idx = -1;
			if (Character.isLowerCase(currChar)) {
				lowerCase = true;
				idx = alphabet.indexOf(Character.toUpperCase(currChar));
			} else {
				idx = alphabet.indexOf(currChar);
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
	
	public String decrypt(String encrypted) {
		CaesarCipherObject cco = new CaesarCipherObject(26-mainKey);
		return cco.encrypt(encrypted);		
	}

}
