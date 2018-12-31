package nickCodingTest;
import java.util.*;

public class TrieNode {
	boolean isLeaf;
	TrieNode parent;
	Map<Character, TrieNode> charMap;
	
	public TrieNode(TrieNode parent, boolean isLeaf) {
		this.parent = parent;
		this.isLeaf = isLeaf;
		charMap = new HashMap<Character, TrieNode>();
	}
}
