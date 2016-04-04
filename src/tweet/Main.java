/**
 * 
 */
package tweet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

/**
 * @author mayaoyu
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException,ParseException {
		// read in raw data
		CleanData extracted = new CleanData();
		ArrayList<Tweet> collection;
		File dir = new File("../tweet_output/Graphs");
		dir.mkdir();
		
		//System.out.println("cleaning....");
		collection = extracted.readFile("../tweet_input/tweets.txt");
		//System.out.println("Finish!");
			
		// write extracted tweets
		//System.out.println("writing....");
		extracted.writeFile(collection, "../tweet_output/extracted_tweets.txt");
		//System.out.println("Finish!");
		
		// draw Hashtags Graph
		HashTagGraph graph = new HashTagGraph();
		Window window = new Window();
		String content = "";
		int counter = 0;
		for(Tweet tweet : collection){
			content += window.addTweet(tweet)+"\n";
			counter++;
		}
		window.writeAverageDegreeFile("../tweet_output/output.txt", content);
		//graph.print();
//		graph.writeGraphFile("tweet_output/hashtag_graph.txt");
//		graph.averageDegree();
//		graph.writeDegreeFile("tweet_output/output.txt");
	}
}
