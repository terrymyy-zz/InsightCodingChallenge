/**
 * 
 */
package tweet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author mayaoyu
 *
 */
public class Window {

	HashTagGraph hashTagGraph;
	Date currentTime;
	ArrayList<Tweet> tweets;
	Date oneMinAgo;
	int flag;
	
	/**
	 * Constructor
	 */
	public Window() {
		hashTagGraph = new HashTagGraph();
		currentTime = new Date();
		oneMinAgo = new Date();
		tweets = new ArrayList<Tweet>();
		flag = 0;
	}
	
	/**
	 * addTweet
	 * @param tweet
	 * @return
	 */
	public String addTweet(Tweet tweet) {
		if(flag == 0){
			tweets.add(tweet);
			hashTagGraph.createGraph(tweet);
			currentTime = tweet.getcreateAt();
			oneMinAgo = new Date(currentTime.getTime() - 60000);
			flag = 1;
			return hashTagGraph.getAverageDegreeString();
		}
		switch(checkTime(tweet)){
		// 1 for after currentTime, refresh Graph
		case 1:
			currentTime = tweet.getcreateAt();
			oneMinAgo = new Date(currentTime.getTime() - 60000);
			hashTagGraph = new HashTagGraph();
			tweets.add(tweet);
			hashTagGraph.createGraph(tweet);
			for(int i = tweets.size()-1 ; i>=0 ; i--){
				if(checkTime(tweets.get(i))==2){
					tweets.remove(tweets.get(i));
				}else{
					hashTagGraph.createGraph(tweets.get(i));
				}
			}
			return hashTagGraph.getAverageDegreeString();
		// 2 for before oneMinAgo, disregard
		case 2:
			return hashTagGraph.getAverageDegreeString();
		// 3 for within the timestamp, add the tweet to the graph
		case 3:
			tweets.add(tweet);
			hashTagGraph.createGraph(tweet);
			return hashTagGraph.getAverageDegreeString();
		default:
			break;
		}
		return "";
	}
	
	/**
	 * checkTime
	 * @param tweet
	 * @return
	 */
	public int checkTime(Tweet tweet){
		// if after currentTime, return 1
		if(tweet.getcreateAt().after(currentTime)){
			return 1;
		}
		// if before oneMinAgo, return 2
		else if(tweet.getcreateAt().before(oneMinAgo)){
			return 2;
		}
		// if within timestamp, return 3
		else{
			return 3;
		}
	}
	
	/**
	 * writeAverageDegreeFile
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void writeAverageDegreeFile(String fileName, String content) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.print(content);
		writer.close();
	}

}
