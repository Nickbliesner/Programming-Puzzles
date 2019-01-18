package GraphAlgorithms;

// Class that represents
// a pairing of a color with a vertex
// used by the two color graph algorithm class
public class ColorPair {
	int vert;
	String color;
	
	public ColorPair(int vert, String color) {
		this.vert = vert;
		this.color = color;
	}
}
