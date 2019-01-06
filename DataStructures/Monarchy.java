package DataStructures;
import java.util.*;

// data structure to represent a monarchy/ royal family tree
// children can be birthed to already birthed parents,
// family members may die, and the current order of succession
// of family members can be retrieved as well
public class Monarchy {
	RoyalNode root;
	Map<String, RoyalNode> family;
	
	public Monarchy(String first) {
		this.family = new HashMap<String, RoyalNode>();
		this.root = new RoyalNode(first);
		this.family.put(first, root);
	}
	
	public static void main(String[] args) {
		Monarchy tree = new Monarchy("Victoria");
		tree.birth("Victoria", "Edward");
		tree.birth("Victoria", "Alice");
		tree.birth("Victoria", "Henry");
		printList(tree.getOrder());
		tree.birth("Edward", "Richard");
		tree.birth("Edward", "Elizabeth");
		tree.birth("Elizabeth", "Cleo");
		tree.birth("Alice", "Desdemona");
		tree.birth("Alice", "Othello");
		printList(tree.getOrder());
		tree.death("Edward");
		printList(tree.getOrder());
		tree.death("Richard");
		printList(tree.getOrder());
		tree.death("Alice");
		printList(tree.getOrder());
	}
	
	// births the child to the parent
	// with the hashmap of tree nodes birth can occur in O(1) as insertion into the parents descendent list
	// should be roughly fast as adding to a linked list is a quick operation
	// If parent is not yet birthed will throw an illegal argument exception
	// will only birth if the parent is alive, otherwise silently does nothing
	public void birth(String parent, String child) {
		if (!family.containsKey(parent)) {
			throw new IllegalArgumentException("arg parent: " + parent + ", not inserted already");
		}
		
		if (family.get(parent).alive) {
			RoyalNode childNode = new RoyalNode(child);
			family.put(child, childNode);
			List<RoyalNode> desc = family.get(parent).desc;
			desc.add(childNode);
		}
	}
	
	// marks the given person as deceased
	// with the hashmap of nodes death can occur in O(1)
	// If person is already deceased throws an illegal argument exception
	// If person is not yet born throws an illegal argument exception
	public void death(String person) {
		if (!family.containsKey(person)) {
			throw new IllegalArgumentException("arg person: " + person + ", not inserted already");
		}
		
		RoyalNode curPerson = family.get(person);
		if (!curPerson.alive) {
			throw new IllegalArgumentException("arg person: " + person + ", is already dead");
		}
		
		curPerson.alive = false;
	}
	
	// returns a list with the names of the royal family in the order of succession based
	// upon the current status of the royal family tree
	public List<String> getOrder() {
		List<String> res = new LinkedList<String>();
		orderHelper(res, root);
		return res;
	}
	
	// recursive helper to traverse the family tree and fill in the succession list
	private void orderHelper(List<String> list, RoyalNode person) {
		if (person.alive) {
			list.add(person.name);
		}
		
		for (RoyalNode child: person.desc) {
			orderHelper(list, child);
		}
	}
	
	// prints out the given list if strings
	private static void printList(List<String> list) {
		System.out.print(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			System.out.print(", " + list.get(i));
		}
		System.out.println();
	}
	
}
