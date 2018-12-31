package nickCodingTest;

import java.util.Random;

// Class that contains method to 
// sort an int [] using a shell sort, an 
// expansion of the the comparison
// and insertion sorting of bubble sort and insertion sort.
// Makes several passes sorting all elements i apart each time, decreasing
// the gap size i until it reaches 1 and a normal insertion sort is performed.
// with a good gap sequence moves elements in place much more quickly
// than a normal insertion sort is able to
public class ShellSort {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(50) + 1;
		}
		
		// sorting by 2^k - 1 as gaps
		// is a pretty good average runtime
		int[] gaps = {15, 7, 3, 1};
		
		printArray(arr);
		shellSort(arr, gaps);
		printArray(arr);
	}
	
	// does a shell sort on the given int array
	// in place based on the gap sequence in the gaps array (last element should be 1)
	// sorts list of items that are i apart, several times using insertion/comparison sort
	// until the gap size is 1 and a normal comparison sort is done 
	// worst case runtime complexity is O (n ^ 2) (that of a normal insertion or bubble sort) 
	// where n is the size of the given array
	// in general average performance depends on gap sequence, with a good gap sequence
	// elements can move faster to where they should be and the sort uses fewer comparisons
	// if gaps of 2^k - 1 (31, 15, 7 etc.) are used could be as fast as O (n ^ 3/2)
	public static void shellSort(int[] arr, int[] gaps) {
		
		// comparison sort each list of elements gap apart
		// in the gap order described by the gaps array
		// last element will be 1 to do a normal comparison sort
		// and ensure sorted order
		for (int i = 0; i < gaps.length; i++) {
			sortGapList(arr, gaps[i]);
			printArray(arr);
		}
		
	}
	
	// helper function used by shellSort to sort all lists
	// of elements that are the given gap apart from one another
	private static void sortGapList(int[] arr, int gap) {
		// we can only do a gap sort if there's at least 2
		// elements in one of the gap lists
		if (gap < arr.length) {
			
			// comparison sort all the
			// lists of items gap apart
			for (int i = 0; i < gap; i++) {
				boolean flag = true;
				
				// we swap elements until the list is sorted
				while (flag) {
					
					flag = false;
					for (int j = i; j + gap < arr.length; j+= gap) {
						
						int cur = arr[j];
						int next = arr[j + gap];
						
						// if the current element is greater than the next one swap them
						if (next < cur) {
							arr[j] = next;
							arr[j + gap] = cur;
							flag = true;
						}
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
