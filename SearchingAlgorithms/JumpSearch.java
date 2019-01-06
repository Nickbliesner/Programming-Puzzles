package SearchingAlgorithms;

import java.util.Random;

import SortingAlgorithms.MergeSort;

// Class that contains a method for performing 
// a jump search on a sorted int [].
// Employs the sorted properties of the array by
// jumping a gap size each time through the array
// until the elements are found to be larger than
// the target, than a linear search is performed
// on the gap where the target element would be 
public class JumpSearch {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(15) + 1;
		}
		
		printArray(arr);
		int[] sorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(sorted);
		int target = 11;
		System.out.println(jumpSearch(sorted, target));
	}
	
	// performs a jump search on the given  int[] arr
	// for the target val
	// returns boolean representing whether or not
	// the value is present in the sorted given array
	// Worst case runtime is O (n ^ 1/2) as if n ^ 1/2 is
	// used as the jump gap it will minimize the number of 
	// comparisons that need to occur when jumping
	// as well as how many elements need to be checked when
	// linear searching the gap where the target could be
	// (if m is gapsize, and n is array size, comparisons is roughly-> n / m + (m - 1)
	// the comparisons will thus be minimized when gap size = n ^ 1/2)
	public static boolean jumpSearch(int[] arr, int target) {
		int index  = 0;
		int jump = (int) Math.sqrt(arr.length);
		
		// continue to jump through the array until we go out of
		// bounds or the current element is larger than the target
		while (index < arr.length && arr[index] < target) {
			index += jump;
		}
		
		// check whether or not we ended on the target
		if (index < arr.length && arr[index] == target) {
			return true;
		}
		
		// if the target is less than the first element
		// it can't be in the array
		if (index == 0) {
			return false;
		}
		
		// linear search the gap range where the target could possibly be
		int startGap = index - jump + 1;
		for (int i = startGap; i < Math.min(arr.length, index); i++) {
			
			// check whether or not the value is the target
			if (arr[i] == target) {
				return true;
				
			// we passed where the target could be and since
			// the current values larger it can't be present	
			} else if (target < arr[i]) {
				break;
			}
		}
		
		// the linear search was unsuccessful we couldn't find the desired target
		return false;
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
