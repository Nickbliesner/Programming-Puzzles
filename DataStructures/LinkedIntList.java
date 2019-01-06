package nickCodingTest;

import java.util.LinkedList;

// data structure that
// represents a linked list of integers
// with sequential access
// and fairly quick insertion
// has methods to add, remove, get, and set nodes  
// as well as retrieve the current size of the list
public class LinkedIntList {
	private ListNode head;
	private ListNode tail;
	private int size;
	
	public LinkedIntList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public static void main(String[] args) {
		LinkedIntList li = new LinkedIntList();
		for (int i = 0; i < 10; i++) {
			li.add(i);
		}
		
		System.out.println(li);
		
		li.add(5, -1);
		System.out.println(li);
		
		li.remove(li.size() - 1);
		li.add(31);
		li.add(5, 27);
		System.out.println(li);
		
		for (int i = 0; i < li.size(); i++) {
			System.out.print(li.get(i) + " ");
		}
		System.out.println();
		
		for (int i = 0; i < li.size(); i++) {
			li.set(i, i * i);
		}
		System.out.println(li);
		
		li.remove(3);
		li.remove(5);
		li.remove(li.size() - 1);
		li.add(420);
		System.out.println(li);
		
		while (li.size() > 3) {
			li.remove(0);
		}
		System.out.println(li);
	}
	
	// returns the size of the list
	// Worst case runtime is O (1) as size is a field
	public int size() {
		return size;
	}
	
	// returns whether or not the list is empty
	// Worst case runtime is O (1), as the size field is checked
	public boolean isEmpty() {
		return size == 0;
	}
	
	// adds a node to the end of the list and updates the size
	// Worst case runtime is O(1) since a reference to the tail
	// is kept don't need to iterate to the end of the list
	public void add(int val) {
		// create the new listNode
		ListNode newNode = new ListNode(val);
		
		// case where the list was previously empty
		if (head == null) {
			head = newNode;
			tail = head;
		
		// if the head is non-null just add the node to the tail 
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		
		size++;
	}
	
	// add the given value to the head of the list
	// Worst case run time is O (1) as a reference to
	// the head is kept
	public void addHead(int val) {
		ListNode newNode = new ListNode(val);
		newNode.next = head;
		head = newNode;
		
		// if this is the first node make sure to initialize the tail
		// as well
		if (tail == null) {
			tail = head;
		}
		
		size++;
	}
	
	// adds the given val to the given index in the list
	// if the index is out of bounds throws an illegal argument exception
	// Worst case runtime is O (n) as we must iterate through the list
	// to get the point of insertion
	public void add(int index, int val) {
		// verify that the index of insertion is in bounds
		checkBounds(index);
		
		// add to the front of the list and update the head
		if (index == 0) {
			// push the new node to the front of the list
			addHead(val);
		
		// add to the tail of the list and update the tail
		} else if (index == size - 1) {
			// push the new node at the end of the list
			add(val);
			
		// insert the node at the appropriate index
		// and update the size of the list	
		} else {
			// create the new node
			ListNode newNode = new ListNode(val);
			
			// get the node one index before insertion
			ListNode cur = getNode(index - 1);
			
			// update the new nodes next pointer and insert it
			newNode.next = cur.next;
			cur.next = newNode;
			
			//update the size
			size++;
		}
	}
	
	// removes the node at index i from the list.
	// Worst case runtime is O(n) as to delete nodes
	// in the list the node before must be retrieved
	public void remove(int index) {
		// verify the index is in bounds
		checkBounds(index);
		
		// remove the head
		if (index == 0) {
			head = head.next;
			
		// remove the ith node of the list
		// and reset the tail if the tail was just deleted	
		}  else {
			// get the node before the index of removal
			ListNode cur = getNode(index - 1);
			
			// remove the node
			cur.next = cur.next.next;
			
			// update the tail if necessary
			if (index == size - 1) {
				tail = cur;
			}
		}
		
		size--;
	}
	
	// returns the value at the given of the list.
	// Worst case runtime is O (n) as the list must be iterated through
	// to get the ith nodes values
	public int get(int index) {
		// verify the index is in bounds
		checkBounds(index);
		
		// get the value at the given index
		return getNode(index).val;
	}
	
	// sets the value at the given index to the given value val
	public void set(int index, int val) {
		// verify the index is in bounds
		checkBounds(index);
		
		// set the value at the given index
		getNode(index).val = val;
	}
	
	// returns the string representation of the linked list.
	// Worst case runtime is O (n) as all nodes need to observed
	// to get the string representation of the entire list
	public String toString() {
		// use a string builder since append can be costly
		// as new references must be made for each append
		StringBuilder builder = new StringBuilder();
		
		// make sure the list is nonempty before trying to add node vals
		if (head != null) {
			builder.append(head.val + "");
			
			// iterate over the rest of the list
			ListNode cur = head.next;
			while (cur != null) {
				builder.append(", " + cur.val);
				cur = cur.next;
			}
		}
		
		return builder.toString();
	}
	
	// does a deep copy on the list
	// this object represents and returns the head
	// of the copied list
	// Worst case runtime is O (n) as all nodes
	// in the list must be observed to perform a complete copy
	public ListNode deapCopy() {
		if (size == 0) {
			return null;
		}
		
		// create a new head for the deep copys list
		ListNode cpyHead = new ListNode(head.val);
		ListNode prev = cpyHead;
		ListNode cur = head.next;
		
		// create new nodes for the rest of the linked
		// list and push them all onto the copied list
		while (cur != null) {
			// create the new node from the current element
			ListNode newNode = new ListNode(cur.val);
			
			// push the newNode onto the copy list
			prev.next = newNode;
			prev = newNode;
			
			// move the iterator forward
			cur = cur.next;
		}
		
		// return the new copy
		return cpyHead;
	}
	
	// private helper which retrieves the node at the given index 
	private ListNode getNode(int index) {
		ListNode cur = head;
		int i = 0;
		
		// iterate till the index is reached
		while (i < index) {
			i++;
			cur = cur.next;
		}
		
		return cur;
	}
	
	// private helper to verify that an index is in bounds of the linked list
	// throws an exception if the index is out of bounds
	private void checkBounds(int index) {
		// verify the index is in bounds
		// index must be non negative as well as less than the list size
		if (index < 0 || size <= index) {
			throw new IllegalArgumentException("the given int index: " + index + " is out of bounds.");
		}
	}
}
