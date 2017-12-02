package twitterAnalysis;

import java.util.ArrayList;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NLP {
	static StanfordCoreNLP pipeline;

	public static void init() {
		pipeline = new StanfordCoreNLP("MyPropFile.properties");
	}

//	Calculate the Sentiment Score
	public static int findSentiment(String tweet) {
		ArrayList<Integer> listOfScores = new ArrayList<>();
		if (tweet != null && tweet.length() > 0) {
			Annotation annotation = pipeline.process(tweet);
			for (CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence
						.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
//				String partText = sentence.toString();
				listOfScores.add(sentiment);
				
//				if (partText.length() > longest) {
//					mainSentiment = sentiment;
//					longest = partText.length();
//				}

			}
		}
		System.out.println(listOfScores);
		return mean(listOfScores);
	}
	
	public static int mean(ArrayList<Integer> scores) {
	      // Contains all the modes
	 
//        int mode = scores.get(0);
//        int maxCount = 0;
//        for (int i = 0; i < scores.size(); i++) {
//            int value = scores.get(i);
//            int count = 0;
//            for (int j = 0; j < scores.size(); j++) {
//                if (scores.get(j) == value) count++;
//                if (count > maxCount) {
//                    mode = value;
//                    maxCount = count;
//                    }
//                }
//        }
		int sum = 0;
		for(int i : scores)
			sum+= i;
		
        return (int) Math.ceil((double)sum/scores.size());

	}
}
