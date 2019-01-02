package nickCodingTest;
import java.util.*;

// Data structure which stores 
// a pair of a word and a list/path of words to get
// to that word changing one letter at a time
// used by the WordChange class
// for finding the minimum operations to transform
// one word into another
public class WordPair {
	String word;
	List<String> path;

	public WordPair(String word) {
		this.word = word;
		this.path = new LinkedList<String>();
	}
}