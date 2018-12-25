package nickCodingTest;

public class Pair implements Comparable<Pair> {
	int vert;
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