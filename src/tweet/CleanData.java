package tweet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.*;
import java.text.ParseException;
import java.util.ArrayList;
import tweet.Tweet;

/**
 * @author mayaoyu
 *
 */
public class CleanData {

	ArrayList<Tweet> collection;
	Tweet newTweet;
	
	
	public CleanData() {
		collection = new ArrayList<Tweet>();
	}
	
	/**
	 * search for the transaction information
	 * {@link #readFile()}
	 * @return collection
	 * @throws ParseException 
	 */
	public ArrayList<Tweet> readFile(String fileName) throws ParseException {
		File file = new File(fileName);
		ArrayList<Tweet> collection = new ArrayList<Tweet>();
		Pattern datePattern = Pattern.compile("\\w{3}\\s\\w{3}\\s\\d{2}\\s\\d{2}[:]\\d{2}[:]\\d{2}\\s[+]\\d{4}\\s\\d{4}");
		Pattern hashtagPattern = Pattern.compile("(\\{\\\"text\\\":\\\")[\\w\\d]+(\\\",\\\"indices\\\":\\[)\\d+[,]\\d+(\\]\\})");
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				line = line.trim();
				if (line.equals("")) continue; // ignore possible blank lines
				Tweet newTweet = new Tweet();
				Matcher dateMatcher = datePattern.matcher(line);
				if (dateMatcher.find())
				{
					String date = dateMatcher.group();
					newTweet.createAt(date);;
			    }
				else{
					continue;
				}
				Matcher hashtagMatcher = hashtagPattern.matcher(line);
				while (hashtagMatcher.find())
				{
					String rawHashTag = hashtagMatcher.group();
					String[] segs = rawHashTag.split("\":\"");
					String[] segs1 = segs[1].split("\"");
					String hashTag = segs1[0];
					newTweet.addHashTags(hashTag);
			    }
				collection.add(newTweet);
				//newTweet.print();
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return collection;
	}
	
	/**
	 * Write to File
	 * @param collection
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void writeFile(ArrayList<Tweet> collection, String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		for(Tweet tweet: collection){
			writer.print(tweet.toString());
		}
		writer.close();
	}

	

}
