package nonGraded;

import edu.duke.FileResource;

public class ShakespearePlays {
	
	public static String[] getCommon() {
		FileResource fr = new FileResource("testData/common.txt");
		String [] common = new String[20];
		int k=0;
		for (String word : fr.words()) {
			common[k] = word;
			k++;
		}
		return common;
	}
	
	public static int indexOf(String[] common, String word) {
		for (int k=0; k < common.length; k++) {
			if (common[k].equals(word)) {
				return k;
			}
		}
		return -1;
	}
	
	public static void countWords(FileResource fr, String[] common, int[] count) {
		for (String word : fr.words()) {
			int idx = indexOf(common, word);
			if (idx != -1) {
				count[idx] += 1;
			}
		}
	}

	public static void play() {
		String[] plays= {"caesar.txt", "errors.txt", "hamlet.txt", 
						"likeit.txt", "macbeth.txt", "romeo.txt"};
		
//		String[] plays= {"small.txt"};
		
		String[] common = getCommon();
		int [] count = new int[common.length];
		for (int k = 0; k<plays.length; k++) {
			FileResource fr = new FileResource("testData/plays/"+ plays[k]);
			countWords(fr, common, count);
			System.out.println("Done with " + plays[k]);
			
			for (int i = 0; i < count.length; i++) {
				System.out.println(common[i] + "'s are \t" + count[i]);
			}
		}
		
//		for (int i = 0; i < count.length; i++) {
//			System.out.println(common[i] + "'s are \t" + count[i]);
//		}
	}
	
	public static void main(String[] args) {
		play();
	}

}
