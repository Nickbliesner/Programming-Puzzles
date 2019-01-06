package nickCodingTest;

// class that contains a method to determine
// whether or not a linked list
// is a sublist of another linked list
// for example: 10 -> 20
//				1 -> 10 -> 20 -> 30
// in this case the first list is a sublist of the second list
public class SublistSearch {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(10);
		ListNode next1 = new ListNode(20);
		ListNode nextNext1 = new ListNode(30);
		l1.next = next1;
		next1.next = nextNext1;
		
		ListNode l2 = new ListNode(1);
		ListNode next2 = new ListNode(10);
		ListNode nextNext2 = new ListNode(20);
		ListNode nextNextNext2 = new ListNode(30);
		l2.next = next2;
		next2.next = nextNext2;
		nextNext2.next = nextNextNext2;
		
		System.out.println("is l1 in l2?: " + sublistSearch(l1, l2));
	}
	
	// given a linked list l1 and a linked list l2
	// returns whether or not the first list is 
	// a sublist of the second list
	// Worst case runtime is O (n * m) where n and m are the lengths
	// of the two different lists respectively, as one will need to check
	// all nodes of the second list to see if they could
	// be the start of the sublist
	public static boolean sublistSearch(ListNode l1, ListNode l2) {
		// throw an exception if either of the input lists are empty
		if (l1 == null || l2 == null) {
			throw new IllegalArgumentException("neither input list can be empty");
		}
		
		ListNode start = l2;
		
		// loop over the entire second list and 
		// see if any of the nodes are the start of the l1 sublist
		while (start != null) {
			
			// if the current value matches the sublist head
			// it could be the start of the sublist
			if (start.val == l1.val) {
				ListNode cur2 = start.next;
				ListNode cur1 = l1.next;
				
				// continue to loop until
				// 1.) we reach the end of the sublist
				// 2.) reach the end of the list being searched
				// 3.) the values of the two lists disagree
				while (cur2 != null && cur1 != null && cur2.val == cur1.val) {
					cur2 = cur2.next;
					cur1 = cur1.next;
				}
				
				// we reached the end of the sublist thus we found it
				if (cur1 == null) {
					return true;
					
				// we ran out of elements to check return false now
				// there's not enough elements left in the list to check
				// for the sublist to exist	
				} else if (cur2 == null) {
					return false;
				}
			} 
			
			// move the iterator forward one step
			start = start.next; 
		}
		
		// we were unable to find the sublist return false
		return false;
	}
}
