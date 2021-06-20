
public class Part2 {
	
	public static int howMany(String stringa, String stringb) {
		int currIndex = stringb.indexOf(stringa,0);
		int i = 0;
		while (currIndex != -1) {
			i++;
			currIndex = stringb.indexOf(stringa,currIndex+stringa.length());
		}
		return i;
	}
	
	public static void testhowMany() {
		String[][] tests = {
				{"GAA","ATGAATAGAAGAATA"},
				{"AA","ATAAAA"}
		};
		for (String[] test : tests) {
			System.out.println("Strings are : "+ test[0]+", " + test[1]);
			System.out.println("No. of occurences are : "+ howMany(test[0],test[1]));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testhowMany();

	}

}
