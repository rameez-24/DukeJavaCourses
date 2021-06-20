
public class Debug {
	
	public void findAbc(String input) {
		int Index = input.indexOf("abc");
		while (true) {
			if (Index == -1 || Index >= input.length()-3) {
				break;
			}
//			System.out.println("Index is :"+ Index);
			String found = input.substring(Index+1,Index+4);
//			System.out.println("Index before is :"+ Index);
			System.out.println(found);
			Index = input.indexOf("abc",Index+3);
//			System.out.println("Index after updating :"+ Index);
		}
	}
	
	public void test() {
//		findAbc("abcd");
//		findAbc("abcdabce");a
//		findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
		findAbc("abcabcabcabca");
	}
	
	public static double dtest(String ab) {
		int a = 0;
		for (int i = 0; i < ab.length(); i++) {
			String ele = ab.substring(i,i+1);
			if (ele.equals("a") || ele.equals("b")) {
				a =a +1;
			}
		}
		return ((double) a/ab.length());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Debug t = new Debug();
//		t.test();
		System.out.println(dtest("abcdabcde"));
	}

}
