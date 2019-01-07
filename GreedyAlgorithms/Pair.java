package GreedyAlgorithms;

// Data structure utilized by graph traversal
// algorithms requiring the need to keep track of
// vertex numbers as well as distances to that vert
// from a chosen vert (ie. Dijkstras etc.)
public class Pair implements Comparable<Pair> {
	// vertex number
	int vert;
	
	// distance to current vert from a target vertex
	int dist;
	
	public Pair(int vert, int dist) {
		this.vert = vert;
		this.dist = dist;
	}
	
	public int compareTo(Pair other) {
		if (this.dist > other.dist) {
			return 1;
		} else if (this.dist < other.dist) {
			return -1;
		} else {
			return 0;
		}
	}
}
