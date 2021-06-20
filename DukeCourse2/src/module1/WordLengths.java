package module1;

import edu.duke.FileResource;

public class WordLengths {
	
	public static void countWordLengths(FileResource fr, int[] count) {
		for (String word : fr.words()) {
			int k = word.length();
			if (Character.isLetter(word.charAt(0)) && Character.isLetter(word.charAt(k-1))) {
					if (k < 31) {
						count[k] += 1;
					} else {
						count[31] += 1;
					}
			} else {
				if (!Character.isLetter(word.charAt(0)) || !Character.isLetter(word.charAt(k-1))) {
					if (k-1 < 31) {
						count[k-1] += 1;
					} else {
						count[31] += 1;
					}
				} else {
					if (k-2 < 31) {
						count[k-2] += 1;
					} else {
						count[31] += 1;
					}
				}
			}
		}
	}
	
	public static int indexOfMax(int[] values) {
		int maxval = 0;
		int maxIdx = 0;
		for (int i = 0; i < values.length; i++) {
			int val = values[i];
			if (maxval < val) {
				maxval = val;
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	
	public static void testCountWordLengths() {
		FileResource fr = new FileResource();
		int[] count = new int[31];
		countWordLengths(fr, count);
		for (int i = 0; i < count.length; i++) {
			System.out.println(i + "'s are \t" + count[i]);
		}
		int maxIdx = indexOfMax(count);
		System.out.println(maxIdx);
	}

	public static void main(String[] args) {
		testCountWordLengths();
	}

}
