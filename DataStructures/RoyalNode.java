package nickCodingTest;
import java.util.*;

public class RoyalNode {
	String name;
	boolean alive;
	Map<Integer, RoyalNode> desc;
	
	public RoyalNode(String name) {
		this.name = name;
		alive = true;
		desc = new TreeMap<Integer, RoyalNode>();
	}
}
