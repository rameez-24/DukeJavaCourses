import edu.duke.URLResource;

public class Part4 {
	
	public static void readWebPage() {
		int i = 1;
		URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
		for (String word : ur.words()) {
			String lowercaseWord = word.toLowerCase();
			int pos =lowercaseWord.indexOf("youtube.com");
			if (pos != -1) {
				int startIndex = lowercaseWord.lastIndexOf("\"",pos);
				int stopIndex = lowercaseWord.indexOf("\"",pos);
				String result = word.substring(startIndex+1,stopIndex);
				System.out.println(i+")"+result);
				i++;
			}
		}
	}

	public static void main(String[] args) {
		readWebPage();
	}

}
