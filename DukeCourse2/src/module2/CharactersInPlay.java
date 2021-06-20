package module2;

import edu.duke.FileResource;
import java.util.ArrayList;

public class CharactersInPlay {
	
	private ArrayList<String> names;
	private ArrayList<Integer> freqs;
	
	public CharactersInPlay() {
		names = new ArrayList<String>();
		freqs = new ArrayList<Integer>();
	}
	
	public void update(String person) {
		int idx = names.indexOf(person);
		if (idx == -1) {
			names.add(person);
			freqs.add(1);
		} else {
			int val = freqs.get(idx);
			freqs.set(idx, val+1);
		}
	}
	
	public void findAllCharacters() {
		names.clear();
		freqs.clear();
		
		FileResource fr = new FileResource();
		for (String line : fr.lines()) {
//			System.out.println(line + "111");
			int idx = line.indexOf('.');
			if (idx == -1) {
				continue;
			}
//			System.out.println(idx);
			String person = line.substring(0,idx);
			int j = 0;
			while(!Character.isLetter(person.charAt(j))) {
				j++;
			}
			person = person.substring(j);
			update(person);
		}
	}
	
	public void charactersWithNumParts(int num1, int num2) {
		if (num1 <= num2) {
			for (int i=0; i<names.size(); i++) {
				int val = freqs.get(i);
				if (val >= num1 && val<= num2) {
					System.out.println(names.get(i) + "\t \t \t" + val);
				}
			}
		} else {
			System.out.println(num1 + " is greater than " + num2);
		}
	}
	
	public void tester() {
		findAllCharacters();
//		for (int i=0; i<names.size(); i++) {
//			System.out.println(names.get(i) + "\t" + freqs.get(i));
//		}
		charactersWithNumParts(70,1000);
	}
	
	public static void main(String[] args) {
		CharactersInPlay cp = new CharactersInPlay();
		cp.tester();
	}

}
