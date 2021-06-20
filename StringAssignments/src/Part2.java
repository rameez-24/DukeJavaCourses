
public class Part2 {
	
	public String FindSimpleGene (String DNA,String startCodon,String stopCodon) {
		String result = "";
		
		if (DNA.toLowerCase().equals(DNA)) {
			startCodon = startCodon.toLowerCase();
			stopCodon = stopCodon.toLowerCase();
		} else {
			DNA = DNA.toUpperCase();
		}
			
		int StartIndex = DNA.indexOf(startCodon);
		if (StartIndex == -1) {
			return result;
		}
		int StopIndex = DNA.indexOf(stopCodon);
		if (StopIndex == -1) {
			return result;
		}
		if ((StopIndex-StartIndex)%3 == 0) {
			result = DNA.substring(StartIndex,StopIndex+3);
		}
		return result;
	}
	
	public void testSimpleGene () {
		String[] tests = {
				"CACGAAGTAA", //NO ATG
				"TTGACATGGATTA", //NO TAA
				"ctatggacacctcataaatc", //ATG TAA MULT 3 lowercase
				"ctatGgaCaccgAgtaAATc", //ATG TAA MULT 3 mixed
				"TATCGGACTACGAGTTAGAA", //NO ATG NO TAA
				"AGATGATATTGCTAAGA", //ATG TAA NOT MULT 3 
		};
		
		for (String DNA : tests) {
		System.out.println("DNA sequence : " + DNA);
		System.out.println("Gene found : " + FindSimpleGene(DNA,"ATG","TAA") + "\n");
		}
	}


	public static void main(String[] args) {
		Part2 P = new Part2();
		P.testSimpleGene();
	}

}
