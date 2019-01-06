package SortingAlgorithms;

public class Pair implements Comparable<Pair> {
	int val;
	int freq;
	
	public Pair(int val, int freq) {
		this.val = val;
		this.freq = freq;
	}
	
	// since we'll use a priority queue to do our sort by
	// frequency we want pairs with higher frequencies to be considered
	// less, when frequency matches well prioritize smaller values
	public int compareTo(Pair other) {
		// compare by frequency first
		if (this.freq > other.freq) {
			return -1;
		} else if (this.freq < other.freq) {
			return 1;
		}
		
		// prioritize smaller values if they match frequency
		if (this.val < other.val) {
			return -1;
		} else if (this.val > other.val) {
			return 1;
		}
		
		// the pairs are equal
		return 0;
	}
}
