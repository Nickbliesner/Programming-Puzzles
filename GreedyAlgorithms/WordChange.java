package nickCodingTest;

import java.util.*;
import java.io.*;

// Class that contains a method to
// find the minimum number of operations needed 
// to transform one word into another word,
// of the same length, in a dictionary
// by changing one letter at a time to make a new word
// for example:
// minChange(cat, dog) = 3: cat -> cot -> dot -> dog
// minChange(ant, bug) = 4: ant -> ait -> bit -> but -> bug
// minChange(cat, cog) = 2: cat -> cot -> cog
public class WordChange {

	public static Trie dictionary;
	public static Set<Character> alphabet;
	
	public static void main(String[] args) {
		// get a file object to the appropriate dictionary .txt file
		File f = new File("C:/Users/nick/Desktop/nickCodingTest/dictionary2.txt");
		Scanner scan = null;
		
		// set up the initial dictionary and the alphabet
		// we'll use a trie as the dictionary as 
		// common prefixes can be stored once, thus space can be saved and
		// look up is fairly quick as well since it's worst case the length of the word
		dictionary = new Trie();
		alphabet = new TreeSet<Character>();
		
		try {
			scan = new Scanner(f);
		} catch (Exception e) {
			throw new IllegalArgumentException("the specified filepath was invalid");
		}
		
		// fill in the trie dictionary with the .txt files contents
		while (scan.hasNextLine()) {
			String cur = scan.nextLine();
			dictionary.insert(cur);
		}
		
		// close the scanner
		scan.close();
		
		// fill in the alphabet, in our case just letters a - z lowercase
		for (int i = 0; i <= 25; i++) {
			alphabet.add((char)('a' + i));
		}
		
		System.out.println(minChange("cat", "dog"));
		System.out.println(minChange("wry", "die"));
		System.out.println(minChange("bar", "sea"));
		System.out.println(minChange("ant", "bug"));
		System.out.println(minChange("ant", "dog"));
		System.out.println(minChange("boo", "zit"));
		System.out.println(minChange("cat", "cog"));
		System.out.println(minChange("boo", "zoo"));
		System.out.println(minChange("boa", "zin"));
		System.out.println(minChange("cat", "cat"));
		System.out.println(minChange("cat", "zuz"));
	}
	
	// This method given two words as strings from the dictionary, 
	// returns the minimum number of operations required to turn
	// the first word into the second word
	// by changing strings one letter at a time into other words that are
	// also in the dictionary
	// returns -1 if there's no way to turn the first string into the 2nd string
	// For example: minChange(cat, dog) = 3, cat -> cot -> dot -> dog
	// Worst Case runtime is O(E) where E is the number of edges in the dictionary graph
	// if the string lengths don't match or either word is not in the dictionary
	// throws an illegal argument exception
	public static int minChange(String str1, String str2) {
		if (str1.length() != str2.length()) {
			throw new IllegalArgumentException("the given String str1: " + str1 + ", and the given String str2: " + str2 + 
					   " are not the same length");
		}
		
		if (!dictionary.contains(str1) || !dictionary.contains(str2)) {
			throw new IllegalArgumentException("the given String str1: " + str1 + ", or the given String str2: " + str2 + 
											   " is not a word");
		}
		
		// we use a queue since all edge weights are 1 or the same in the graph 
		// all we need to do is make sure we visit all the words 1 away, then 2 away etc.
		// priority will naturally be kept by the nature of the queue always
		// adding to the back and removing from the front
		Set<String> seen = new HashSet<String>();
		Queue<WordPair> work = new LinkedList<WordPair>();
		work.add(new WordPair(str1));
		
		// traverse the connected graph to the first string
		// until we find the desired given str2
		// or run out of word vertexes to travel to
		while (!work.isEmpty()) {
			WordPair cur = work.remove();
			String word = cur.word; 
			
			// if we haven't yet been to this word in the graph
			// then we have just found the shortest path to it
			if (!seen.contains(word)) {
				
				// identify that the current word vertex has been visited 
				seen.add(word);
				
				if (word.equals(str2)) {
					// we found the target word return the size of the list as 
					// the min number of changes needed
					printList(cur.path);
					return cur.path.size();
				}
				
				// in general we'll add all the words we can make by changing one
				// character of the current word (all words/verts that have an edge to the current word)
				for (int i = 0; i < word.length(); i++) {
				
					// add pairs to the queue if the current word modified by 1 letter
					// is in the dictionary
					for (Character c: alphabet) {
						String newWord = word.substring(0, i) + c + word.substring(i + 1, word.length());
						
						// if the modified string is in the dictionary add it to the work queue
						// as a new pair making sure to maintain notion of distance/ number of operations
						if (dictionary.contains(newWord)) {
							WordPair newPair = new WordPair(newWord);
							newPair.path.addAll(cur.path);
							newPair.path.add(newWord);
							work.add(newPair);
						}
					}
				}
			}
		}
		
		// if we reach this point we never found a path to the target string 
		// (no connection between words in the graph)
		// and thus there is no valid minimum, we return -1 to denote this
		return -1;
	}
	
	public static void printList(List<String> li) {
		if (li.size() > 0) {
			System.out.print(li.get(0));
		}
		
		for (int i = 1; i < li.size(); i++) {
			System.out.print(", " + li.get(i));
		}
		
		System.out.println();
	}
}
