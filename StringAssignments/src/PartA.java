
public class PartA {

	public String FindSimpleGene (String DNA) {
		String result = "";
		int StartIndex = DNA.indexOf("ATG");
		if (StartIndex == -1) {
			return result;
		}
		int StopIndex = DNA.indexOf("TAA");
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
		"ATFTTATAAFTG", // no ATG
		"AATGTTAFTGAF", // no TAA
		"AATGTTAFTATAA", // ATG and TAA
		"AATGTTAFTTAAA", // not multiple of 3
		"AATFTTAFTAFAA", // no ATG and TAA
		"AAATGCCCTAACTAGATTAAGAAACC" //quiz1 
		};
		
		for (String DNA : tests) {
		System.out.println("DNA sequence : "+ DNA);
		System.out.println("Gene found : " +FindSimpleGene(DNA));
		}
	}


	public static void main(String[] args) {
		PartA P = new PartA();
		P.testSimpleGene();
	}

}
