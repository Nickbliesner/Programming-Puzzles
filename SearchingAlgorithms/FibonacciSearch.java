package nickCodingTest;

import java.util.Random;

import nickCodingTest.SortingAlgorithms.MergeSort;

// Class that contains a method for performing 
// a fibonacci search on a sorted int [].
// Employs the sorted properties of the array by
// using fibonacci numbers to divide the array
// into unequal parts and appropriately
// fine tune sorting range and look for the target val
public class FibonacciSearch {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(15) + 1;
		}
		
		printArray(arr);
		int[] sorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(sorted);
		int target = 3;
		System.out.println(fibonacciSearch(sorted, target));
	}
	
	// performs a fibonacci search on the given  int[] arr
	// for the target val 
	// returns boolean representing whether or not
	// the value is present in the sorted given array.
	// Worst case runtime is O (log(n)) since the fibonacci search similar
	// to binary search eliminates (2/3 or 1/3 roughly) of the data each time
	// with the fibonacci number usage turns into similar log (n) time
	public static boolean fibonacciSearch(int[] arr, int target) {
		// if the input array is empty throw an exception
		// an empty array has nothing to search for
		if (arr.length == 0) {
			throw new IllegalArgumentException("Given array cannot be empty");
		}
		
		if (arr.length == 1) {
			return arr[0] == target;
		}
		
		int fib2 = 1;
		int fib1 = 1;
		int fib = 2;
		
		// find the first fibonacci number to be greater than or equal to
		// the array's length
		while (fib < arr.length) {
			int temp = fib + fib1;
			fib2 = fib1;
			fib1 = fib;
			fib = temp;
		}
		
		int low = 0;
		
		// perform the fibonacci search
		while (fib2 != 0 && fib1 != 0) {
			int index = Math.min(low + fib2, arr.length - 1);
			
			if (arr[index] == target) {
				return true;
				
			// case	where the target is larger than the offset value
			} else if (arr[index] < target) {
				
				// if the index is the last element the target can't be present
				if (index == arr.length - 1) {
					return false;
				}
				
				// we single decrease the three
				// fibonacci numbers as well as update low bound
				low = index;
				int temp = fib1 - fib2;
				fib = fib1;
				fib1 = fib2;
				fib2 = temp;
				
			// case where the target is less than the offset value	
			} else {
				// we double decrease the three
				// fibonacci numbers don't need to update low bound
				int temp = fib1 - fib2;
				fib = fib1;
				fib1 = fib2;
				fib2 = temp;
				temp = fib1 - fib2;
				fib = fib1;
				fib1 = fib2;
				fib2 = temp;
			}
		}
		
		//check if the last element is the target
		return arr[low] == target;
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
