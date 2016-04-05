/**
 * 
 */
package tweet;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mayaoyu
 *
 */
public class WindowTest {

	Window window;
	String hashtag1,hashtag2,hashtag3,hashtag4,
	hashtag5,hashtag6,hashtag7;
	String date1,date2,date3,date4,date5,date6;
	String result1;
	Tweet  tweet1,tweet2,tweet3,tweet4,tweet5,tweet6;
	HashTagGraph graph,graph2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		window = new Window();
		hashtag1 = "Spark";
		hashtag2 = "Apache";
		hashtag3 = "Hadoop";
		hashtag4 = "Storm";
		hashtag5 = "Flink";
		hashtag6 = "HBase";
		date1    = "Thu Mar 24 17:51:10 +0000 2016";
		date2    = "Thu Mar 24 17:51:15 +0000 2016";
		date3    = "Thu Mar 24 17:51:30 +0000 2016";
		date4    = "Thu Mar 24 17:51:35 +0000 2016";
		date5    = "Thu Mar 24 17:51:58 +0000 2016";
		date6    = "Thu Mar 24 17:52:12 +0000 2016";
		result1  = "hashtags = [Spark, Apache],	created_at: Thu Mar 24 17:51:10 +0000 2016";
		tweet1   = new Tweet(date1);
		tweet1.addHashTags(hashtag1);
		tweet1.addHashTags(hashtag2);
		tweet2   = new Tweet(date2);
		tweet2.addHashTags(hashtag2);
		tweet2.addHashTags(hashtag3);
		tweet2.addHashTags(hashtag4);
		tweet3   = new Tweet(date3);
		tweet3.addHashTags(hashtag3);
		tweet4   = new Tweet(date4);
		tweet4.addHashTags(hashtag5);
		tweet4.addHashTags(hashtag1);
		tweet5   = new Tweet(date5);
		tweet5.addHashTags(hashtag6);
		tweet5.addHashTags(hashtag1);
		tweet6   = new Tweet(date6);
		tweet6.addHashTags(hashtag2);
		tweet6.addHashTags(hashtag3);
		graph = new HashTagGraph();
		graph2 = new HashTagGraph();
		
	}

	/**
	 * Test method for {@link tweet.Window#addTweet(tweet.Tweet)}.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public final void testAddTweet() throws FileNotFoundException, UnsupportedEncodingException {
		String avr1 = window.addTweet(tweet1);
		assertEquals("1.00",avr1);
		String avr2 = window.addTweet(tweet2);
		assertEquals("2.00",avr2);
		String avr3 = window.addTweet(tweet3);
		assertEquals("2.00",avr3);
		String avr4 = window.addTweet(tweet4);
		assertEquals("2.00",avr4);
		String avr5 = window.addTweet(tweet5);
		assertEquals("2.00",avr5);
		String avr6 = window.addTweet(tweet6);
		assertEquals("1.66",avr6);
	}

	/**
	 * Test method for {@link tweet.Window#checkTime(tweet.Tweet)}.
	 */
	@Test
	public final void testCheckTime() {
		window.currentTime = tweet1.getcreateAt();
		window.oneMinAgo = new Date(window.currentTime.getTime() - 60000);
		assertEquals(3,window.checkTime(tweet1));
		assertEquals(1,window.checkTime(tweet6));
		
		window.currentTime = tweet6.getcreateAt();
		window.oneMinAgo = new Date(window.currentTime.getTime() - 60000);
		assertEquals(2,window.checkTime(tweet1));
	}

}
