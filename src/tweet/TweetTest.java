/**
 * 
 */
package tweet;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mayaoyu
 *
 */
public class TweetTest {
	String hashtag1;
	String hashtag2;
	String date1;
	String result1;
	Tweet  tweet1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hashtag1 = "Spark";
		hashtag2 = "Apache";
		date1    = "Thu Mar 24 17:51:10 +0000 2016";
		result1 = "hashtags = [Spark, Apache],	created_at: Thu Mar 24 17:51:10 +0000 2016";
	}

	/**
	 * Test method for {@link tweet.Tweet#toString()}.
	 * @throws ParseException 
	 */
	@Test
	public final void testToString() throws ParseException {
		tweet1  = new Tweet(date1);
		assertEquals("hashtags = [],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
		tweet1.addHashTags(hashtag1);
		assertEquals("hashtags = [Spark],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
		tweet1.addHashTags(hashtag2);
		assertEquals("hashtags = [Spark, Apache],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
	}

	/**
	 * Test method for {@link tweet.Tweet#createAt(java.lang.String)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testCreateAt() throws ParseException {
		tweet1   = new Tweet();
		assertEquals("hashtags = [],	created_at: \n",tweet1.toString());
		tweet1.createAt(date1);
		assertEquals("hashtags = [],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
		try {
			tweet1.createAt("");
	        fail("expected exception was not occured.");
	    } catch(ParseException e) {
	    	assertTrue(e instanceof ParseException);
	    }
	}

	/**
	 * Test method for {@link tweet.Tweet#addHashTags(java.lang.String)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testAddHashTags() throws ParseException {
		tweet1   = new Tweet(date1);
		assertEquals("hashtags = [],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
		tweet1.addHashTags(hashtag1);
		assertEquals("hashtags = [Spark],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
		tweet1.addHashTags(hashtag2);
		assertEquals("hashtags = [Spark, Apache],	created_at: Thu Mar 24 17:51:10 +0000 2016\n",tweet1.toString());
	}

	/**
	 * Test method for {@link tweet.Tweet#getcreateAt()}.
	 * @throws ParseException 
	 */
	@Test
	public final void testGetcreateAt() throws ParseException {
		tweet1   = new Tweet(date1);
		assertEquals(tweet1.createAt,tweet1.getcreateAt());
	}

	/**
	 * Test method for {@link tweet.Tweet#getcreateAtString()}.
	 * @throws ParseException 
	 */
	@Test
	public final void testGetcreateAtString() throws ParseException {
		tweet1   = new Tweet(date1);
		assertEquals("Thu Mar 24 17:51:10 +0000 2016",tweet1.getcreateAtString());
	}

}
