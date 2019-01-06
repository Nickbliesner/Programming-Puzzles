package SortingAlgorithms;

import java.util.Random;
import java.util.*;

// Class that contains method to 
// sort an int [] using a frequency sort,
// returns a new array
// which contains each distinct value in the original array
// sorted by descending frequency (highest to smallest) 
// breaking ties based on ascending order by value
public class FreqSort {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(15) + 1;
		}
		
		printArray(arr);
		int[] mergeSorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(mergeSorted);
		int[] freqSorted = freqSort(arr);
		printArray(freqSorted);
	}
	
	// does a frequency sort on the given int array
	// and returns the sorted array of distinct values descending sorted by frequency
	// in the original given array
	// breaking frequency ties based on ascending by value
	// throws an illegal argument exception if the given array is empty
	// worst case runtime complexity is O (n * log (n)) where n is the size of the given array
	// as a heap sort is used to sort the pairs of int and frequency
	public static int[] freqSort(int[] arr) {
		if (arr.length == 0) {
			throw new IllegalArgumentException("Input array arr is length 0, no elements to sort");
		}
		
		// stores counts of the different values in arr
		Map<Integer, Integer> inv = new HashMap<Integer, Integer>();
		
		// compute and store the counts for all the distinct values in the
		// given array
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			
			// check if there's a mapping already for this value
			if (!inv.containsKey(val)) {
				inv.put(val, 0);
			}
			
			// update the count by 1
			inv.put(val, inv.get(val) + 1);
		}
		
		// create the min heap that will store the pairs
		// and do the frequency sorting
		PriorityQueue<Pair> heap = new PriorityQueue<Pair>();
		for (int val: inv.keySet()) {
			heap.add(new Pair(val, inv.get(val)));
		}
		
		// create the array to store the values
		int[] sorted = new int[inv.keySet().size()];
		
		// loop over the array taking the current minimum
		// val out of the heap as the next element of the array
		for (int i = 0; i < sorted.length; i++) {
			Pair iPair = heap.remove();
			sorted[i] = iPair.val;
		}
		
		// return the new merged array
		return sorted;
	}
	
	// prints the given array (the appropriate to string of the array)
	public static void printArray(int[] arr) {
		System.out.print("[");
		if (arr.length > 0) {
			System.out.print(arr[0]);
		}
		
		for (int i = 1; i < arr.length; i++) {
			System.out.print(", " + arr[i]);
		}
		
		System.out.println("]");
	}
}
