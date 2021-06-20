
public class Part3 {
	
	public static boolean twoOccurences(String stringa,String stringb) {
		int len = stringa.length();
		int startIndex = stringb.indexOf(stringa);
		if (startIndex != -1) {
			int nextIndex = stringb.indexOf(stringa,startIndex + len);
			if (nextIndex != -1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static String lastpart(String stringa,  String stringb) {
		int len = stringa.length();
		int startIndex = stringb.indexOf(stringa);
		if (startIndex != -1) {
			return stringb.substring(startIndex + len);
		} else {
			return stringb;
		}
	}
	
	public static void testing() {
		String[][] strings = {
				{"by","A story by Abby Long"},
				{"a","banana"},
				{"atg","ctgtatagt"},
				{"an","banana"},
				{"zoo","forest"},
				{"bb","bbb"}
		};
		for (String[] string : strings) {
			System.out.println("The strings are : " + string[0] + "," + string[1]);
			System.out.println("There are atleast two Occurences : " + twoOccurences(string[0],string[1]));
			System.out.println("The last Part is : " + lastpart(string[0],string[1]) + "\n");
		}
	}

	public static void main(String[] args) {
		testing();
	}

}
