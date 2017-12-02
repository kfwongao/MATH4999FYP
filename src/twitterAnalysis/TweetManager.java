package twitterAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class TweetManager {
	
	public static final int TWEETS_PER_QUERY = 50;
	public static final int MAX_QUERIES = 20;

	public static ArrayList<String> getTweets(String topic) {
		int totalTweets=0;
		long maxID = -1;
		ArrayList<String> tweetList = new ArrayList<String>();
		
		//	Set Configuration of Twitter API
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("cf9EZo6pGGMN1JPvqUE11yQrb")
			.setOAuthConsumerSecret("D4gZrZmGvKJdaghHrTz6eMZEixxVufmtzvK1uo4X743UaO3fQs")
			.setOAuthAccessToken("854258066409234433-UbO1bOBxg4FFNhQhh9BJLFy0kR9SFBp")
			.setOAuthAccessTokenSecret("F3IDgdN4jU6aQMPIp3er8cgyuJ29CC5TITUJH2Mfg5AXw");		
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		try {
			Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus("search");
			
			RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
			
			for(int queryNumber = 0; queryNumber < MAX_QUERIES; queryNumber++){
				if(searchTweetsRateLimit.getRemaining()==0){
					System.out.printf("\n Already hit the rate limits!! The program will sleep for %d seconds. \n", searchTweetsRateLimit.getSecondsUntilReset());
					Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset()+2) * 1000l);
				}
			
				Query query = new Query(topic);
				query.setCount(TWEETS_PER_QUERY);
				query.setLang("en"); // Set the language to English
				
				if (maxID != -1){
					query.setMaxId(maxID - 1);
				}
				
				QueryResult result = twitter.search(query);
				
				if(result.getTweets().size()==0){
					break;
				}
				
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					totalTweets++;
					tweetList.add(tweet.getText());
					if (maxID == -1 || tweet.getId() < maxID){
						maxID = tweet.getId();
					}
				}
				
				if(totalTweets == 0 || totalTweets%100 != 0){
					System.out.print(".");}
				else{
					System.out.println(".");
				}
				searchTweetsRateLimit = result.getRateLimitStatus();
			}
		}catch (Exception te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		
		return tweetList;
	}
}
