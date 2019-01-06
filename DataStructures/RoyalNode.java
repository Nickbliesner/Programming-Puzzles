package DataStructures;
import java.util.*;

public class RoyalNode {
	String name;
	boolean alive;
	List<RoyalNode> desc;
	
	public RoyalNode(String name) {
		this.name = name;
		alive = true;
		desc = new LinkedList<RoyalNode>();
	}
}
