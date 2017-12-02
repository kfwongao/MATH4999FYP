package twitterAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import twitterAnalysis.NLP;
import twitterAnalysis.TweetManager;

public class twitterDataAnalysis{
	
	static String keyword1 = new String(); 
    static String keyword2 = new String();
    static long totalNoOfRecord1 = 0; static long totalNoOfRecord2 = 0;
    static long noOfPositive1 = 0; static long noOfPositive2 = 0; static long noOfNeutral1 = 0;
    static long noOfNegative1 = 0; static long noOfNegative2 = 0; static long noOfNeutral2 = 0;
    static String ratio1; static String ratio2; static String finalResult = new String();
    
	public static void main(String[] args) {
		// Print out the introduction for the program
		System.out.println( "---------------------------------------------Introduction-------------------------------------------------- \n"
						 +  "This is a program for analysis tweets with specific keywords. After you input the keyword(s), the program   \n"
						 +  "will do a searching on Twitter and then analyse those tweets to determine whether those are positive or  	 \n"
						 +  "negative. Total number of records used, number of positive records, number negative records, number of    	 \n"
						 +  "neutral records and a ratio of percetage of positive records to percetage of negative records will be given \n"	
						 +  "to you in the result. You can input at most two keywords everytime. Branch of log will be print out while 	 \n"
						 +  "running the program, you can simply ignore them and be patient to wait for the result.   				  \n \n"
						 +  "(You can find all the tweets used and the score of them in the text file named as \"[keyword]_Result.txt\"  \n"
						 +  "in format \"[Tweet] : [Score]\". Tweet with score 2 is treated as neutral, larger than 2 is treated as 	 \n"
						 +  "positive and less than 2 is treated as negative.) 															 \n"
						 +  "----------------------------------------------------------------------------------------------------------- \n"
						 +  "Press ENTER to proceed.");
		
		try {
	        System.in.read();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		while (true){
			if(!finalResult.isEmpty()){finalResult = "";}
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a keyword: ");
			keyword1 = reader.nextLine();
			
			System.out.println("Enter another keyword (if any): ");
			keyword2 = reader.nextLine();
			
			if(!keyword1.isEmpty() || !keyword2.isEmpty()){
				if(!keyword1.isEmpty()){
					System.out.printf("\nFetching tweets from twitter with tag \"%s\", please wait a while...", keyword1);
					getData(keyword1, totalNoOfRecord1, noOfPositive1, noOfNegative1, noOfNeutral1, ratio1);
					
				}
	    	
				if(!keyword2.isEmpty()){
					System.out.printf("\nFetching tweets from twitter with tag \"%s\", please wait a while...", keyword2);
					getData(keyword2, totalNoOfRecord2, noOfPositive2, noOfNegative2, noOfNeutral1, ratio2);
				}
			}else{
				System.out.println("No input found! The program will be terminated! ");
				reader.close();
			}
			
			System.out.println("\n" + finalResult + "\n");
			System.out.print("If you want to run the program again, please input \"0\". \n"
								+ "Otherwise, input \"1\" to terminate the program. ");
			
			if(reader.nextInt()==1){
				reader.close();
				System.out.println("The program will be terminated! Thank you for using it!!");
				break;
			}else{
			}
		}
	}
	
	// Connect to twitter through API and search with specific keywords
	// tweets are recorded and sentiment score for each tweet is found
	// which are written to a text file with format "[tweet] : [sentiment score]"
	// Final result is also written to the text file
	private static void getData(String keyword, long totalNoOfRecord, long noOfPositive, long noOfNegative, long noOfNeutral, String ratio){
    	BufferedWriter output = null;
    	try {
    		String name = keyword+"_Result.txt";
    		File file = new File(name);
    		output = new BufferedWriter (new FileWriter(file));
    	
    		if(!keyword.isEmpty()){
    			ArrayList<String> tweets = TweetManager.getTweets(keyword);
    			NLP.init();
    			System.out.println("\nFetching done! Now the tweets will be socred. It may take a while, please be patient...");
    			
    			// Call the "NLP.findSentiment" function to find the sentiment score of each tweet
    			for(String tweet : tweets) {
    				totalNoOfRecord++; 
    				int sentimentScore = NLP.findSentiment(tweet);
    				
    				// Write the result to text file with format "[tweet] : [sentiment score]"
    				String result = tweet + " : " + sentimentScore + "\n";
    				if(sentimentScore>2){
    					noOfPositive++;
					}else if(sentimentScore<2){
						noOfNegative++;
					}else{
						noOfNeutral++;
					}			
    				output.write(result);
    			}
    			ratio = ((double) noOfPositive / totalNoOfRecord * 100) + "% : " + (((double) noOfNegative / totalNoOfRecord * 100)) + "%";
    			
    			// Write the final result to text file
    			output.write("\nTotal No. Of Records: " + totalNoOfRecord + "\n"	
    						+ "#positive tweets: " + noOfPositive + "\n" 
    						+ "#negative tweets: " + noOfNegative + "\n" 
    						+ "#neutral tweets: " + noOfNeutral + "\n"
    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n");
    			
    			// Assign the final result to the variable "finalResult" for further printing
    			if(!finalResult.isEmpty()){
    				finalResult = finalResult + "Total No. Of Records: " + totalNoOfRecord + "\n"	
    						+ "#positive tweets: " + noOfPositive + "\n" 
    						+ "#negative tweets: " + noOfNegative + "\n" 
    						+ "#neutral tweets: " + noOfNeutral + "\n"
    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n";
    			}else{
    				finalResult =  "Total No. Of Records: " + totalNoOfRecord + "\n"	
    						+ "#positive tweets: " + noOfPositive + "\n" 
    						+ "#negative tweets: " + noOfNegative + "\n" 
    						+ "#neutral tweets: " + noOfNeutral + "\n"
    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n";
    			}
    			
    		}
    		output.close();
    	} catch ( IOException e){
    		e.printStackTrace();
    	}
    }
}
