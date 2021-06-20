package module1;

public class WordPlay {
	
	public static boolean isVowel(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
			|| ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
			return true;
		} else {
			return false;
		}
	}
	
	public static String replaceVowels(String phrase, char ch) {
		StringBuilder newPhrase = new StringBuilder(phrase);
		for (int i = 0; i < phrase.length(); i++) {
			char newChar = newPhrase.charAt(i);
			if (isVowel(newChar)) {
				newPhrase.setCharAt(i, ch);
			}
		}
		return newPhrase.toString();
	}
	
	public static String emphasize(String phrase, char ch) {
		StringBuilder newPhrase = new StringBuilder(phrase);
		for (int i = 0; i < phrase.length(); i++) {
//			System.out.println(phrase.length());
			char newChar = Character.toLowerCase(newPhrase.charAt(i));
			if (newChar == Character.toLowerCase(ch)) {
				if (i%2 == 0) {
					newPhrase.setCharAt(i, '*');
				} else {
					newPhrase.setCharAt(i, '+');
				}
			}
		}
		return newPhrase.toString();
	}
	
	public static void test() {
//		System.out.println(isVowel(' '));
//		System.out.println(replaceVowels("Hello World", '*'));
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	}

	public static void main(String[] args) {
		test();
	}

}
