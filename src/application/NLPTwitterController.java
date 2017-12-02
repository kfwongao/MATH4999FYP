package application;
/**
 * This class is a javafx GUI implementation of comparing tweets of two different phrases/words
 * This class is extensively developed for this MATH4999 project
 * due to lack of developing time, the programming is neither perfectly following the MVC nor very good design of
 * both codes and UI design, you can do refactoring of codes and follow the MVC pattern to create Main, View, and Controller
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import twitterAnalysis.NLP;
import twitterAnalysis.TweetManager;

public class NLPTwitterController {

	@FXML
	private Button backToWelcome;
	@FXML
	private Button searchWord1;
	@FXML
	private Button searchWord2;
	@FXML
	private Button goToHowToUse;
	@FXML
	private Button showChart1;
	@FXML
	private Button showChart2;
	@FXML
	private Button compareChrt;
	@FXML
	private TextField keyword1TextField;
	@FXML
	private TextField keyword2TextField;
	@FXML
	private TextArea keyword1TextArea;
	@FXML
	private TextArea keyword2TextArea;
	@FXML
	private ProgressBar pb1;
	@FXML
	private ProgressBar pb2;
	@FXML
	private Label statusLabel1;
	@FXML
	private Label statusLabel2;

	
	public static String keyword1 = new String(); 
	public static String keyword2 = new String();
	public static int totalNoOfRecord1 = 0; public static int totalNoOfRecord2 = 0;
	public static int noOfPositive1 = 0; public static int noOfPositive2 = 0; public static int noOfSlightlyPositive1 = 0; public static int noOfSlightlyPositive2 = 0; public static int noOfNeutral1 = 0;
	public static int noOfNegative1 = 0; public static int noOfNegative2 = 0; public static int noOfNeutral2 = 0; public static int noOfSlightlyNegative1 = 0; public static int noOfSlightlyNegative2 = 0;
	public static String ratio1; public static String ratio2; public static String finalResult = new String();
	private int noOfPositiveW1; private int noOfPositiveW2; private int noOfSlightlyPositiveW1; private int noOfSlightlyPositiveW2; private int noOfNeutralW1;
	private int noOfNegativeW1; private int noOfNegativeW2; private int noOfNeutralW2; private int noOfSlightlyNegativeW1; private int noOfSlightlyNegativeW2;
	
	
	
	
	public int getNoOfPositiveW1() {
		return noOfPositiveW1;
	}

	public void setNoOfPositiveW1(int noOfPositiveW1) {
		this.noOfPositiveW1 = noOfPositiveW1;
	}

	public int getNoOfPositiveW2() {
		return noOfPositiveW2;
	}

	public void setNoOfPositiveW2(int noOfPositiveW2) {
		this.noOfPositiveW2 = noOfPositiveW2;
	}

	public int getNoOfSlightlyPositiveW1() {
		return noOfSlightlyPositiveW1;
	}

	public void setNoOfSlightlyPositiveW1(int noOfSlightlyPositiveW1) {
		this.noOfSlightlyPositiveW1 = noOfSlightlyPositiveW1;
	}

	public int getNoOfSlightlyPositiveW2() {
		return noOfSlightlyPositiveW2;
	}

	public void setNoOfSlightlyPositiveW2(int noOfSlightlyPositiveW2) {
		this.noOfSlightlyPositiveW2 = noOfSlightlyPositiveW2;
	}

	public int getNoOfNeutralW1() {
		return noOfNeutralW1;
	}

	public void setNoOfNeutralW1(int noOfNeutralW1) {
		this.noOfNeutralW1 = noOfNeutralW1;
	}

	public int getNoOfNegativeW1() {
		return noOfNegativeW1;
	}

	public void setNoOfNegativeW1(int noOfNegativeW1) {
		this.noOfNegativeW1 = noOfNegativeW1;
	}

	public int getNoOfNegativeW2() {
		return noOfNegativeW2;
	}

	public void setNoOfNegativeW2(int noOfNegativeW2) {
		this.noOfNegativeW2 = noOfNegativeW2;
	}

	public int getNoOfNeutralW2() {
		return noOfNeutralW2;
	}

	public void setNoOfNeutralW2(int noOfNeutralW2) {
		this.noOfNeutralW2 = noOfNeutralW2;
	}

	public int getNoOfSlightlyNegativeW1() {
		return noOfSlightlyNegativeW1;
	}

	public void setNoOfSlightlyNegativeW1(int noOfSlightlyNegativeW1) {
		this.noOfSlightlyNegativeW1 = noOfSlightlyNegativeW1;
	}

	public int getNoOfSlightlyNegativeW2() {
		return noOfSlightlyNegativeW2;
	}

	public void setNoOfSlightlyNegativeW2(int noOfSlightlyNegativeW2) {
		this.noOfSlightlyNegativeW2 = noOfSlightlyNegativeW2;
	}

	public void goToWelcomePage(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		try {
			Parent rootOfWelcomePage = FXMLLoader.load(getClass().getResource("/application/welcomePage.fxml"));
			Scene scene = new Scene(rootOfWelcomePage,300,380);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MATH4999 Capstone Project");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goToHowToUse(ActionEvent event) {
		Stage primaryStage = new Stage();
		try {
			Parent rootOfHowToUse = FXMLLoader.load(getClass().getResource("/application/howToUse.fxml"));
			Scene scene = new Scene(rootOfHowToUse,500,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("How to Use");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchKeyword1(ActionEvent event) {
		keyword1 = keyword1TextField.getText();
		if(!keyword1.isEmpty()) {
			keyword1TextArea.clear();
			totalNoOfRecord1 = 0;
		    noOfPositive1 = 0; 
		    noOfNeutral1 = 0;
		    noOfNegative1 = 0;
		    ratio1 = "";
			String message = String.format("\nFetching tweets from twitter with tag \"%s\", %nplease wait a while...", keyword1);
			keyword1TextArea.appendText(message);
			statusLabel1.setText("Start Fetching...");
			Thread t1 = new Thread(() -> getDataForWord1(keyword1, keyword1TextArea, searchWord1, pb1, statusLabel1, totalNoOfRecord1, noOfPositive1, noOfSlightlyPositive1, noOfNegative1, noOfSlightlyNegative1, noOfNeutral1, ratio1));
			t1.start();
		}
	}
	
	public void searchKeyword2(ActionEvent event) {
		keyword2 = keyword2TextField.getText();
		if(!keyword2.isEmpty()) {
			keyword2TextArea.clear();
			totalNoOfRecord2 = 0;
		    noOfPositive2 = 0; 
		    noOfNeutral2 = 0;
		    noOfNegative2 = 0;
		    ratio2 = "";
			keyword1TextArea.clear();
			String message = String.format("\nFetching tweets from twitter with tag \"%s\", %nplease wait a while...", keyword2);
			keyword2TextArea.appendText(message);
			statusLabel2.setText("Start Fetching...");
			Thread t2 = new Thread(() -> getDataForWord2(keyword2, keyword2TextArea, searchWord2, pb2, statusLabel2, totalNoOfRecord2, noOfPositive2, noOfSlightlyPositive2, noOfNegative2, noOfSlightlyNegative2, noOfNeutral1, ratio2));
			t2.start();
//			getData(keyword2, keyword2TextArea, totalNoOfRecord2, noOfPositive2, noOfNegative2, noOfNeutral1, ratio2);		
		}
	}	
	
	// Connect to twitter through API and search with specific keywords
		// tweets are recorded and sentiment score for each tweet is found
		// which are written to a text file with format "[tweet] : [sentiment score]"
		// Final result is also written to the text file
	private void getDataForWord1(String keyword, TextArea textarea, Button searchBtn, ProgressBar pb, Label status, int totalNoOfRecord, int noOfPositive, int noOfSlightlyPositive, int noOfNegative, int noOfSlightlyNegative, int noOfNeutral, String ratio){
		BufferedWriter output = null;
    	searchBtn.setDisable(true);
    	try {
    		String name = keyword+"_Result.txt";
    		File file = new File(name);
    		output = new BufferedWriter (new FileWriter(file));
    	
    		if(!keyword.isEmpty()){
    			ArrayList<String> tweets = TweetManager.getTweets(keyword);
    			NLP.init();
    			System.out.println("\nFetching done! Now the tweets will be socred. It may take a while, please be patient...");
    			textarea.appendText("\nFetching done! Now the tweets will be socred. \nIt may take a while, please be patient...");
    			Platform.runLater( () -> status.setText("Fetching done! Now Scoring tweets."));
    			// Call the "NLP.findSentiment" function to find the sentiment score of each tweet
    			for(String tweet : tweets) {
    				totalNoOfRecord++; 
    				final int i = totalNoOfRecord;
    				int sentimentScore = NLP.findSentiment(tweet);
    				
    				// Write the result to text file with format "[tweet] : [sentiment score]"
    				String result = tweet + " : " + sentimentScore + "\n";
    				String resultText = "Analyzing the tweet: \n" + tweet + "\n The score is " + sentimentScore + "\n";
    				Platform.runLater( () -> textarea.appendText(resultText) );
    				Platform.runLater( () -> pb.setProgress((double)i/(TweetManager.MAX_QUERIES*TweetManager.TWEETS_PER_QUERY)) );
    				
    				if((totalNoOfRecord % 10) == 0) {
    					final int temp = totalNoOfRecord;
    					Platform.runLater( () -> textarea.appendText("\n\n\n\n\n\n\nFinished analyzing "+ temp + " tweets\n\n\n\n\n\n\n"));
    					Platform.runLater( () -> status.setText("Finished analyzing " + temp + " tweets..."));
    					setNoOfNegativeW1(noOfNegative);
	    	    			setNoOfSlightlyNegativeW1(noOfSlightlyNegative);
	    	    			setNoOfNeutralW1(noOfNeutral);
	    	    			setNoOfSlightlyPositiveW1(noOfSlightlyPositive);
	    	    			setNoOfPositiveW1(noOfPositive);
    				}
    				
    				if(sentimentScore == 4)
    					noOfPositive++;
				if(sentimentScore == 3)
					noOfSlightlyPositive++;
				if(sentimentScore == 2)
					noOfNeutral++;
				if(sentimentScore == 1)
					noOfSlightlyNegative++;
				if(sentimentScore == 0)
					noOfNegative++;
							
    				output.write(result);
    			}
    			ratio = ((double) (noOfPositive + noOfSlightlyPositive) / totalNoOfRecord * 100) + "% : " + (((double) (noOfNegative + noOfSlightlyNegative) / totalNoOfRecord * 100)) + "%";
    			Platform.runLater( () -> status.setText("Analysis is done!!!"));
    			
    			Platform.runLater( () -> pb.setProgress(1.0));
    			Platform.runLater( () -> textarea.appendText("\nAnalysis is done!  The following is the result: \n"));
    			// Write the final result to text file
    			output.write("\nTotal No. Of Records: " + totalNoOfRecord + "\n"	
    						+ "#positive tweets: " + (noOfPositive + noOfSlightlyPositive) + "\n" 
    						+ "#negative tweets: " + (noOfNegative + noOfSlightlyNegative) + "\n" 
    						+ "#neutral tweets: " + noOfNeutral + "\n"
    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n");
    			
    			String finalResultMessage = "\nTotal No. Of Records: " + totalNoOfRecord + "\n"	
						+ "#positive tweets: " + (noOfPositive + noOfSlightlyPositive) + "\n" 
						+ "#negative tweets: " + (noOfNegative + noOfSlightlyNegative) + "\n" 
						+ "#neutral tweets: " + noOfNeutral + "\n"
						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n";
    			Platform.runLater( () -> textarea.appendText(finalResultMessage));
    			searchBtn.setDisable(false);
    			// Assign the final result to the variable "finalResult" for further printing
    			
    				finalResult =  "Total No. Of Records: " + totalNoOfRecord + "\n"	
    						+ "#positive tweets: " + (noOfPositive + noOfSlightlyPositive) + "\n" 
    						+ "#negative tweets: " + (noOfNegative + noOfSlightlyNegative) + "\n" 
    						+ "#neutral tweets: " + noOfNeutral + "\n"
    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n";
    			
	    			setNoOfNegativeW1(noOfNegative);
	    			setNoOfSlightlyNegativeW1(noOfSlightlyNegative);
	    			setNoOfNeutralW1(noOfNeutral);
	    			setNoOfSlightlyPositiveW1(noOfSlightlyPositive);
	    			setNoOfPositiveW1(noOfPositive);
	    			
	    		}
	    		output.close();
	    		
	    	} 
	    	catch ( IOException e){
	    		e.printStackTrace();
	    	}
	}

	private void getDataForWord2(String keyword, TextArea textarea, Button searchBtn, ProgressBar pb, Label status, int totalNoOfRecord, int noOfPositive, int noOfSlightlyPositive, int noOfNegative, int noOfSlightlyNegative, int noOfNeutral, String ratio){
	    BufferedWriter output = null;
	    	searchBtn.setDisable(true);
	    	try {
	    		String name = keyword+"_Result.txt";
	    		File file = new File(name);
	    		output = new BufferedWriter (new FileWriter(file));
	    	
	    		if(!keyword.isEmpty()){
	    			ArrayList<String> tweets = TweetManager.getTweets(keyword);
	    			NLP.init();
	    			System.out.println("\nFetching done! Now the tweets will be socred. It may take a while, please be patient...");
	    			textarea.appendText("\nFetching done! Now the tweets will be socred. \nIt may take a while, please be patient...");
	    			Platform.runLater( () -> status.setText("Fetching done! Now Scoring tweets."));
	    			// Call the "NLP.findSentiment" function to find the sentiment score of each tweet
	    			for(String tweet : tweets) {
	    				totalNoOfRecord++; 
	    				final int i = totalNoOfRecord;
	    				int sentimentScore = NLP.findSentiment(tweet);
	    				
	    				// Write the result to text file with format "[tweet] : [sentiment score]"
	    				String result = tweet + " : " + sentimentScore + "\n";
	    				String resultText = "Analyzing the tweet: \n" + tweet + "\n The score is " + sentimentScore + "\n";
	    				Platform.runLater( () -> textarea.appendText(resultText) );
	    				Platform.runLater( () -> pb.setProgress((double)i/(TweetManager.MAX_QUERIES*TweetManager.TWEETS_PER_QUERY)) );
	    				
	    				if((totalNoOfRecord % 10) == 0) {
	    					final int temp = totalNoOfRecord;
	    					Platform.runLater( () -> textarea.appendText("\n\n\n\n\n\n\nFinished analyzing "+ temp + " tweets\n\n\n\n\n\n\n"));
	    					Platform.runLater( () -> status.setText("Finished analyzing " + temp + " tweets..."));
	    					setNoOfNegativeW2(noOfNegative);
		    	    			setNoOfSlightlyNegativeW2(noOfSlightlyNegative);
		    	    			setNoOfNeutralW2(noOfNeutral);
		    	    			setNoOfSlightlyPositiveW2(noOfSlightlyPositive);
		    	    			setNoOfPositiveW2(noOfPositive);
		    				}
	    				if(sentimentScore == 4)
	    					noOfPositive++;
					if(sentimentScore == 3)
						noOfSlightlyPositive++;
					if(sentimentScore == 2)
						noOfNeutral++;
					if(sentimentScore == 1)
						noOfSlightlyNegative++;
					if(sentimentScore == 0)
						noOfNegative++;
								
	    				output.write(result);
	    			}
	    			ratio = ((double) (noOfPositive + noOfSlightlyPositive) / totalNoOfRecord * 100) + "% : " + (((double) (noOfNegative + noOfSlightlyNegative) / totalNoOfRecord * 100)) + "%";
	    			Platform.runLater( () -> status.setText("Analysis is done!!!"));
	    			
	    			Platform.runLater( () -> pb.setProgress(1.0));
	    			Platform.runLater( () -> textarea.appendText("\nAnalysis is done!  The following is the result: \n"));
	    			// Write the final result to text file
	    			output.write("\nTotal No. Of Records: " + totalNoOfRecord + "\n"	
	    						+ "#positive tweets: " + (noOfPositive + noOfSlightlyPositive) + "\n" 
	    						+ "#negative tweets: " + (noOfNegative + noOfSlightlyNegative) + "\n" 
	    						+ "#neutral tweets: " + noOfNeutral + "\n"
	    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n");
	    			
	    			String finalResultMessage = "\nTotal No. Of Records: " + totalNoOfRecord + "\n"	
    						+ "#positive tweets: " + (noOfPositive + noOfSlightlyPositive) + "\n" 
    						+ "#negative tweets: " + (noOfNegative + noOfSlightlyNegative) + "\n" 
    						+ "#neutral tweets: " + noOfNeutral + "\n"
    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n";
	    			Platform.runLater( () -> textarea.appendText(finalResultMessage));
	    			searchBtn.setDisable(false);
	    			// Assign the final result to the variable "finalResult" for further printing
	    			
	    				finalResult =  "Total No. Of Records: " + totalNoOfRecord + "\n"	
	    						+ "#positive tweets: " + (noOfPositive + noOfSlightlyPositive) + "\n" 
	    						+ "#negative tweets: " + (noOfNegative + noOfSlightlyNegative) + "\n" 
	    						+ "#neutral tweets: " + noOfNeutral + "\n"
	    						+ "positive% : negative% for \"" + keyword + "\" = " + ratio + "\n";
	    			
	    			setNoOfNegativeW2(noOfNegative);
	    			setNoOfSlightlyNegativeW2(noOfSlightlyNegative);
	    			setNoOfNeutralW2(noOfNeutral);
	    			setNoOfSlightlyPositiveW2(noOfSlightlyPositive);
	    			setNoOfPositiveW2(noOfPositive);
	    			
	    		}
	    		output.close();
	    		
	    	} 
	    	catch ( IOException e){
	    		e.printStackTrace();
	    	}
	}
	
	
	public void showBarChart1(ActionEvent event) {
		showBarChart1(keyword1);
	}
	
	public void showBarChart2(ActionEvent event) {
		showBarChart2(keyword2);
	}
	
	public void showCompareChart(ActionEvent event) {
		showCompareChart(keyword1, keyword2);
	}
	
	private void showBarChart1(String keyword) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Sentiment scores");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No. of Samples");
		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
		dataSeries1.setName(keyword);

		dataSeries1.getData().add(new XYChart.Data<String, Number>("Negative", getNoOfNegativeW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("SlightlyNegative", getNoOfSlightlyNegativeW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Neutral", getNoOfNeutralW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("SlightlyPositive", getNoOfSlightlyPositiveW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Positive", getNoOfPositiveW1()));

		barChart.getData().add(dataSeries1);
		
		Stage primaryStage = new Stage();
		primaryStage.setTitle("BarChart");
		
		VBox vbox = new VBox(barChart);

		Scene scene = new Scene(vbox, 150, 300);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(500);

        primaryStage.show();

	}
	
	private void showBarChart2(String keyword) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Sentiment scores");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No. of Samples");
		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
		dataSeries1.setName(keyword);

		dataSeries1.getData().add(new XYChart.Data<String, Number>("Negative", getNoOfNegativeW2()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("SlightlyNegative", getNoOfSlightlyNegativeW2()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Neutral", getNoOfNeutralW2()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("SlightlyPositive", getNoOfSlightlyPositiveW2()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Positive", getNoOfPositiveW2()));

		barChart.getData().add(dataSeries1);
		
		Stage primaryStage = new Stage();
		primaryStage.setTitle("BarChart");
		
		VBox vbox = new VBox(barChart);

        Scene scene = new Scene(vbox, 150, 300);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(500);

        primaryStage.show();

	}
	
	@SuppressWarnings("unchecked")
	private void showCompareChart(String keyword1, String keyword2) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Sentiment scores");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No. of Samples");
		
		BarChart<String, Number> compareBarChart = new BarChart<String, Number>(xAxis, yAxis);
		
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
		dataSeries1.setName(keyword1);

		dataSeries1.getData().add(new XYChart.Data<String, Number>("Negative", getNoOfNegativeW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("SlightlyNegative", getNoOfSlightlyNegativeW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Neutral", getNoOfNeutralW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("SlightlyPositive", getNoOfSlightlyPositiveW1()));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Positive", getNoOfPositiveW1()));

		XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
		dataSeries2.setName(keyword2);
		
		dataSeries2.getData().add(new XYChart.Data<String, Number>("Negative", getNoOfNegativeW2()));
		dataSeries2.getData().add(new XYChart.Data<String, Number>("SlightlyNegative", getNoOfSlightlyNegativeW2()));
		dataSeries2.getData().add(new XYChart.Data<String, Number>("Neutral", getNoOfNeutralW2()));
		dataSeries2.getData().add(new XYChart.Data<String, Number>("SlightlyPositive", getNoOfSlightlyPositiveW2()));
		dataSeries2.getData().add(new XYChart.Data<String, Number>("Positive", getNoOfPositiveW2()));
		
		compareBarChart.getData().addAll(dataSeries1, dataSeries2);
		
		Stage primaryStage = new Stage();
		primaryStage.setTitle("compare Bar Chart");
		
		VBox vbox = new VBox(compareBarChart);

        Scene scene = new Scene(vbox, 150, 300);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(500);

        primaryStage.show();

	}
}
