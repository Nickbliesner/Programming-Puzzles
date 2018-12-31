package nickCodingTest;

// data structure which represents a dictionary of strings
// as a prefix tree
// every prefix will only be stored once thus this structure
// saves space when prefixes in a word dictionary are common
// contains methods for insertion, contains, isPrefix, and removal from the tree
public class Trie {
	private TrieNode root;
	
	public static void main(String[] args) {
		Trie prefixTree = new Trie();
		System.out.println(prefixTree.isPrefix("an"));
		prefixTree.insert("ant");
		System.out.println(prefixTree.isPrefix("an"));
		prefixTree.insert("antelope");
		System.out.println(prefixTree.isPrefix("antel"));
		prefixTree.insert("anteater");
		prefixTree.insert("art");
		System.out.println(prefixTree.contains("antelope"));
		System.out.println(prefixTree.contains("ant"));
		System.out.println(prefixTree.contains("art"));
		System.out.println(prefixTree.contains("anteater"));
		prefixTree.remove("antelope");
		System.out.println(prefixTree.isPrefix("ante"));
		System.out.println(prefixTree.contains("ant"));
		prefixTree.remove("ant");
		System.out.println(prefixTree.isPrefix("ant"));
		System.out.println(prefixTree.contains("anteater"));
		System.out.println(prefixTree.contains("ant"));
		prefixTree.remove("anteater");
		System.out.println(prefixTree.contains("anteater"));
		System.out.println(prefixTree.isPrefix("ant"));
		System.out.println(prefixTree.isPrefix("an"));
		System.out.println(prefixTree.contains("art"));
	}
	
	public Trie() {
		root = new TrieNode(null, false);
	}
	
	// adds the given string to the trie creating new nodes
	// if they are not already created from prior insertions
	public void insert(String word) {
		TrieNode cur = root;
		
		// loop over the string adding a mapping for each character
		// and appropriately mark the node that's at the end of the string
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			if (!cur.charMap.containsKey(c)) {
				// add the appropriate trieNode, dependent if you are at the end of the string
				cur.charMap.put(c, new TrieNode(cur, i == word.length() - 1));
			}
			
			cur = cur.charMap.get(c);
		}
	}
	
	// returns whether or not the trie contains the given word
	public boolean contains(String word) {
		TrieNode leaf = trieSearch(word);
		if (leaf == null) {
			return false;
		}
		
		return leaf.isLeaf;
	}
	
	// returns whether or not the trie contains the given word as a prefix 
	public boolean isPrefix(String word) {
		TrieNode node = trieSearch(word);
		
		return node != null;
	}
	
	// removes the given word value from the trie
	// if the trie doesn't contain the word throws an illegal argument exception
	public void remove(String word) {
		// verify that the trie contains the word as a value first
		if (!contains(word)) {
			// the trie doesn't contain the given word throw an exception
			throw new IllegalArgumentException("the string word: " + word + " is not a value");
		}
		
		TrieNode cur = trieSearch(word);
		cur.isLeaf = false;
		int index = word.length() - 1;
		boolean flag = true;
		
		while (flag) {
			
			// check whether the current node is not a leaf and has no children
			if (cur != null && !cur.isLeaf && cur.charMap.isEmpty()) {
				// the current node must be deleted
				cur = cur.parent;
				
				// verify you weren't already at the root prior
				if (cur != null) {
					
					// remove the current character
					char c = word.charAt(index);
					index--;
					cur.charMap.remove(c);
				}
				
			} else {
				// we can't delete the current node
				// or any of it's predecessor
				flag = false;
			}
		}
		
		// at this point we have deleted the current
	}
	// private helper for traversing the trie given a word string
	// returns null if a character is absent otherwise returns the
	// trie node which corresponds to the end of the string
	private TrieNode trieSearch(String word) {
		TrieNode cur = root;
		
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			if (!cur.charMap.containsKey(c)) {
				// the trie didn't contain a mapping for the current character must return false
				return null;
			}
			
			cur = cur.charMap.get(c); 
		}
		
		// at this point we just return the node we got to
		return cur;
	}
}
