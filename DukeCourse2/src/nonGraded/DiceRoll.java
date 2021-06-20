package nonGraded;

import java.util.Random;

public class DiceRoll {
	
	public static void simulate(int rolls) {
		int [] count = new int[13];
		
		Random rand = new Random();
		for (int i = 0; i<rolls; i++) {
			int d1 = rand.nextInt(6) + 1;
			int d2 = rand.nextInt(6) + 1;
			count[d1+d2] += 1;
		}
		for (int j=0; j<11; j++) {
			double prob = count[j+2]*100.0/rolls;
			System.out.println(j+2 + "'s are -----" + count[j+2] + "---" + prob);
		}
	}

	public static void main(String[] args) {
		simulate(1000000);
	}

}
