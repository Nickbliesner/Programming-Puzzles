package DataStructures;
import java.util.*;

// implementation for a min k heap data structure
// of integers, where each node has up to k children. 
// insertion and removal are both
// O(log base k (n)), where n is the number of things in the heap
public class KHeap {
	private int size;
	private int k;
	private int[] heap;
	
	public static void main(String[] args) {
		KHeap threep = new KHeap(3);
		int[] arr = new int[50];
		Random r = new Random();
		
		for (int i = 0; i < 50; i++) {
			arr[i] = r.nextInt(100) + 1;
		}
		
		printArray(arr, arr.length);
		
		for (int i = 0; i < arr.length; i++) {
			threep.add(arr[i]);
		}
		
		threep.printHeap();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = threep.remove();
		}
		
		printArray(arr, arr.length);
	}
	
	// k must be 2 or larger or in the context of the heap it just
	// doesn't make any sense to be less than 2
	// in general the heap will have good performance as long
	// as k is not excessively large
	public KHeap(int k) {
		if (k < 2) {
			throw new IllegalArgumentException("k must be 2 or larger, k: " + k);
		}
		
		this.heap = new int[5 * k];
		this.size = 0;
		this.k = k;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	// adds the given integer val to the heap
	// and adjusts the heap accordingly
	// worst case runtime is the height of the heap comparisons
	// or O(log base k (n))
	public void add(int val) {
		if (size == heap.length) {
			copy();
		}
		
		// we add it to the size spot and percolate up
		int curIndex = size;
		heap[size] = val;
		
		// we keep swapping until our current node is not less than it's parent
		// could potentially percolate to the top but this won't occur too frequently
		while (curIndex != 0 && heap[curIndex] < heap[(curIndex - 1) / k]) {
			int nextIndex = (curIndex - 1) / k;
			int temp = heap[curIndex];
			heap[curIndex] = heap[nextIndex];
			heap[nextIndex] = temp;
			curIndex = nextIndex;
		}
		
		// insertion was a success, update the size
		size++;
	}
	
	// removes the head of the min heap
	// if the heap is size 0 throws an illegal state exception since we can't
	// take away from nothing
	// returns the current head of the min k heap
	// worst case runtime is O (log base k (n)), having to percolate all the way down
	public int remove() {
		if (size == 0) {
			throw new IllegalStateException("can't remove from heap of size 0");
		}
		
		// save the return value
		int retVal = heap[0];
		
		// move last element to the top of the heap and decrease the heap size
		heap[0] = heap[size - 1];
		int curIndex = 0;
		boolean flag = true;
		size--;
		
		// continues as long as the current index is not at the bottom of the heap
		// or is larger than its smallest child
		while (flag && (curIndex * k + 1) < size) {
			int minDex = curIndex * k + 1;
			
			// gets the smallest child
			for (int i = 2; i <= k; i++) {
				if (curIndex * k + i < size) {
					// it's a valid index let's check whether
					// or not it's less than the value at mindex
					if (heap[curIndex * k + i] < heap[minDex]) {
						minDex = curIndex * k + i;
					}
				}
			}
			
			if (heap[minDex] < heap[curIndex]) {
				// we must swap to preserve the min property of the heap
				int temp = heap[minDex];
				heap[minDex] = heap[curIndex];
				heap[curIndex] = temp;
				
				// update the index to be the old child minimum
				curIndex = minDex;
			} else {
				// the value is already where it should be in the heap
				flag = false;
			}
		}
		
		return retVal;
	}
	
	// prints the heap array
	public void printHeap() {
		printArray(heap, size);
	}
	
	// private function used for allocating new array when
	// current heap is full and another add occurs
	private void copy() {
		int[] tempHeap = new int[heap.length * 2];
		
		for (int i = 0; i < heap.length; i++) {
			tempHeap[i] = heap[i];
		}
		
		heap = tempHeap;
	}
	
	// prints the given number of elements from the given array
	// assumes els is less than arrays length
	private static void printArray(int[] arr, int els) {
		System.out.print("[");
		if (els > 0) {
			System.out.print(arr[0]);
		}
		
		for (int i = 1; i < els; i++) {
			System.out.print(", " + arr[i]);
		}
		
		System.out.println("]");
	}
}
