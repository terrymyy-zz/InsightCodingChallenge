/**
 * 
 */
package tweet;

import java.util.*;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  

/**
 * @author mayaoyu
 *
 */
public class Tweet {
	
	ArrayList<String> hashTags;
	SimpleDateFormat sdf;
	Date createAt;
	String createAtString;
	
	public Tweet(){
		sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		hashTags = new ArrayList<String>();
		createAtString = "";
	}
	
	public Tweet(String date) throws ParseException {
		sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		hashTags = new ArrayList<String>();
		createAt(date);
	}
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		String s = "hashtags = [";
		if(hashTags.size()==0){
			s += "],\tcreated_at: " + createAtString + "\n";
			return s;
		}
		else{
			for(int i = 0;i<hashTags.size();i++){
				if(i>0){
					s +=  ", ";
				}
				s += hashTags.get(i);
			}
			s += "],\tcreated_at: " + createAtString + "\n";
			return s;
		}
	}
	
	/**
	 * print method to print out the result
	 * {@link #print()}
	 */
	public void print(){
		System.out.print(this.toString());
	}
	
	/**
	 * set createAt date and string
	 * @throws ParseException 
	 */
	 public void createAt(String date) throws ParseException{
		 createAt = sdf.parse(date);
		 createAtString = date;
	 }
	 
	 /**
	  * add hashTags
	  * @return hashTags
	  */
	 public ArrayList<String> addHashTags(String hashTag) {
		 if(!hashTags.contains(hashTag)){
			 hashTags.add(hashTag);
		 }
		 return hashTags;
	 }
	 
	 /**
	  * get hashTags
	  * @return hashTags
	  */
	 public ArrayList<String> getHashTags(){
		 return hashTags;
	 }
	 
	 /**
	  * get createAt
	  * @return createAt
	  */
	 public Date getcreateAt(){
		 return createAt;
	 }
	 
	 /**
	  * get createAtString
	  * @return createAtString
	  */
	 public String getcreateAtString(){
		 return createAtString;
	 }
}
