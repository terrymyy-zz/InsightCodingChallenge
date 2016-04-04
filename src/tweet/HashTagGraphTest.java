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
public class HashTagGraphTest {

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
	 * Test method for {@link tweet.HashTagGraph#toString()}.
	 * @throws ParseException 
	 */
	@Test
	public final void testToString() throws ParseException {
		Tweet tweet2  = new Tweet(date1);
		graph.createGraph(tweet2);
		assertEquals("",graph.toString());
		
		graph.createGraph(tweet1);
		assertEquals("Spark <-> Apache\n",graph.toString());
		
	}

	/**
	 * Test method for {@link tweet.HashTagGraph#print()}.
	 */
	@Test
	public final void testPrint() {
		graph.createGraph(tweet1);
		//graph.print();
		//System.out.print(graph.createGraph(tweet1));
	}

	/**
	 * Test method for {@link tweet.HashTagGraph#createGraph(tweet.Tweet)}.
	 */
	@Test
	public final void testCreateGraph() {
		//System.out.print(graph.createGraph(tweet1));
		assertEquals("Spark <-> Apache\n",graph.createGraph(tweet1));
		tweet1.addHashTags(hashtag3);
		//System.out.print(graph.createGraph(tweet1));
		assertEquals("Spark <-> Apache\nSpark <-> Hadoop"
				+ "\nApache <-> Hadoop\n",graph.createGraph(tweet1));
		tweet1.addHashTags(hashtag4);
		//System.out.print(graph.createGraph(tweet1));
		assertEquals("Spark <-> Apache\nSpark <-> Hadoop\n"
				+ "Apache <-> Hadoop\n"
				+ "Spark <-> Storm\nApache <-> Storm\nHadoop <-> Storm\n",graph.createGraph(tweet1));
	}
	
	/**
	 * Test method for {@link tweet.HashTagGraph#averageDegree(tweet.Tweet)}.
	 */
	@Test
	public final void testAverageDegree(){
		graph.createGraph(tweet1);
		//graph.print();
		//System.out.print("\n");
		assertEquals(1.00,graph.averageDegree(),0.0001);
		graph.createGraph(tweet2);
		//graph.print();
		//System.out.print("\n");
		assertEquals(2.00,graph.averageDegree(),0.0001);
		graph.createGraph(tweet3);
		//graph.print();
		//System.out.print("\n");
		assertEquals(2.00,graph.averageDegree(),0.0001);
		graph.createGraph(tweet4);
		//graph.print();
		//System.out.print("\n");
		assertEquals(2.00,graph.averageDegree(),0.0001);
	}
	
	/**
	 * Test method for {@link tweet.HashTagGraph#getAverageDegreeString(tweet.Tweet)}.
	 */
	@Test
	public final void testGetAverageDegreeString(){
		graph.createGraph(tweet1);
		assertEquals("1.00",graph.getAverageDegreeString());
		graph.createGraph(tweet2);
		assertEquals("2.00",graph.getAverageDegreeString());
		graph.createGraph(tweet3);
		assertEquals("2.00",graph.getAverageDegreeString());
		graph.createGraph(tweet4);
		assertEquals("2.00",graph.getAverageDegreeString());
		graph2.createGraph(tweet2);
		graph2.createGraph(tweet3);
		graph2.createGraph(tweet4);
		graph2.createGraph(tweet5);
		graph2.createGraph(tweet6);
		assertEquals("1.66",graph2.getAverageDegreeString());
	}

}
