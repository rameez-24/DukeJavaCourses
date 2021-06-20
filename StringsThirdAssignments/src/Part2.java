import edu.duke.FileResource;

public class Part2 {

	public static double cgRatio(String dna) {
		double cg = 0.0;
		for (int i = 0; i < dna.length(); i++) {
			String ele = dna.substring(i,i+1);
			if (ele.equals("C") || ele.equals("G")) {
				cg = cg +1;
			}
		}
		return cg/dna.length();
	}
	
	public static int countCTG(String dna) {
		int ctg = 0;
		int startIndex = 0;
		startIndex = dna.indexOf("CTG",startIndex);
		while(true) {
			ctg++;
			startIndex = dna.indexOf("CTG",startIndex+3);
			if (startIndex == -1) {
				break;
			}
//			System.out.println("hey");
		}
		return ctg;
	}
	
	public static void test() {
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString();
		System.out.println(countCTG(dna));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(cgRatio("TGCCAGTTA"));
//		System.out.println(countCTG("ATCTGGCCTGTTA"));
		test();
	}

}
