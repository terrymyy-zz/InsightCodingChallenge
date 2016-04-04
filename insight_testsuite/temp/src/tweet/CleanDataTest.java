/**
 * 
 */
package tweet;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mayaoyu
 *
 */
public class CleanDataTest {

	CleanData extracted;
	ArrayList<Tweet> collection;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		extracted = new CleanData();
		collection = new ArrayList<Tweet>();;
	}

	/**
	 * Test method for {@link tweet.CleanData#readFile(java.lang.String)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testReadFile() throws ParseException {
		collection = extracted.readFile("tweet_input/tweets.txt");
		for(int i=0;i<collection.size();i++){
			assertTrue(collection.get(i) instanceof Tweet);
		}
	}

}
