package nickCodingTest;

import java.util.Random;

// Class that contains method to 
// sort an int [] using a insertion sort, keeping track
// of the already sorted left frontier and inserting next
// elements one by one into the already sorted data
// pairwise and swapping when necessary repeating passes
// until the process has created a sorted array
public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = new int[10];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(50) + 1;
		}
		
		printArray(arr);
		insertionSort(arr);
		printArray(arr);
	}
	
	// does an insertion sort on the given int array
	// in place
	// worst case runtime complexity is O (n ^ 2) where n is the size of the given array
	// keeps track of sorted frontier and appropriately inserts each new element
	// into the sorted part of the array expanding the sorted frontier
	// worst case each element will have to be compared to all the other elements
	public static void insertionSort(int[] arr) {
		
		// loop over the array comparing each element
		// to the front of the sorted frontier and inserting where appropriate
		for (int i = 1; i < arr.length; i++) {
			int cur = arr[i];
			int prior = arr[i - 1];
			
			// if the current value is smaller than the head of the frontier
			// we need to insert it appropriately into the sorted part of the array
			if (cur < prior) {
				
				// loop over the sorted portion of the array and find where to insert
				for (int j = 0; j <= i - 1; j++) {
					int el = arr[j];
					
					// if we are less than the current element we have found the spot
					if (cur <= el) {
						
						// shift the elements over and insert
						for (int k = i; k > j; k--) {
							arr[k] = arr[k - 1];
						}
						
						arr[j] = cur;
						break;
					}
				}
			}
		}
		
		
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
