/**
 * 
 */
package tweet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author mayaoyu
 *
 */
public class HashTagGraph {

	String hashTagGraph;
	HashMap<String , Double> map;
	double averageDegree;

	/**
	 * constructor
	 */
	public HashTagGraph() {
		hashTagGraph = "";
		map = new HashMap<String , Double>();
		averageDegree = 0.00;
	}

	/**
	 * toString method
	 * @return hashTagGraph
	 */
	@Override
	public String toString() {
		return hashTagGraph;
	}

	/**
	 * print method to print out the result
	 * {@link #print()}
	 */
	public void print(){
		System.out.print(this.toString());
	}

	/**
	 * create hashtags graph
	 * @param tweet
	 * @return
	 */
	public String createGraph(Tweet tweet){
		for(String hashTag1 : tweet.getHashTags()){
			for(String hashTag2 : tweet.getHashTags()){
				String connect = hashTag1 + " <-> " + hashTag2 + "\n";
				if(hashTagGraph.contains(connect)
						|| hashTagGraph.contains(hashTag2 + " <-> " + hashTag1)
						|| hashTag1.equals(hashTag2)){
					continue;
				}else{
					if(!map.containsKey(hashTag1)){
						map.put(hashTag1, 1.0);
					}
					else{
						double value = map.get(hashTag1);
						map.put(hashTag1,value+1);
					}
					if(!map.containsKey(hashTag2)){
						map.put(hashTag2, 1.0);
					}
					else{
						double value = map.get(hashTag2);
						map.put(hashTag2,value+1);
					}
					hashTagGraph += connect;
				}
			}
		}
		return hashTagGraph;
	}

	/**
	 * Write Graph to File
	 * @param collection
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void writeGraphFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.print(this.toString());
		writer.close();
	}

	/**
	 * Calculate Average Degree
	 * @return averageDegree
	 */
	public double averageDegree(){
		int length = map.size();
		if(length==0){
			return 0.0;
		}
		double sum = 0.0;
		for(String key : map.keySet()){
			sum += map.get(key);
		}
		averageDegree = sum/length;
		return averageDegree;
	}
	
	/**
	 * getAverageDegreeString
	 * @return string form of average degree(2 digits after decimal place)
	 */
	public String getAverageDegreeString(){
		averageDegree();
		double s = (double)Math.floor(averageDegree * 100d) / 100d;
		return String.format("%.2f", s);
	}

	/**
	 * writeDegreeFile
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void writeDegreeFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.print(this.getAverageDegreeString() + "\n");
		writer.close();
	}
}
