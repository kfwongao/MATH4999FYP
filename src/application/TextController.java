package application;
/**
 * this class is under constructing
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import twitterAnalysis.NLP;

public class TextController {
	

	public static void main(String[] args) {
		NLP.init();
		File file = new File("data/a.txt");
		System.out.println(file.getAbsolutePath());
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			ArrayList<String> strings = new ArrayList<String>();
			while(sc.hasNextLine()) {
				String s = sc.nextLine();
				strings.add(s);
			}
			strings.remove("");
			int sentimentScore = 2;
			for(String string : strings) {
				
				if(!string.isEmpty())
					sentimentScore = NLP.findSentiment(string);
				
				// Write the result to text file with format "[tweet] : [sentiment score]"
				String result = string + "\n Score: " + sentimentScore + "\n";
				System.out.println(result);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}
}
