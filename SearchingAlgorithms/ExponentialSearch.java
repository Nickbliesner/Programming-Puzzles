package SearchingAlgorithms;

import java.util.Random;

import SortingAlgorithms.MergeSort;

// Class that contains a method for performing 
// an exponential search on a sorted int [].
// Employs the sorted properties of the array by
// checking the last element of subarrays
// (exponentially increasing size by 2 each time)
// until the last element is larger than the target val
// than a binary search is performed on the
// sub array to find the target, can outperform
// searches like binary search in certain cases (target is close to the beginning)
public class ExponentialSearch {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(15) + 1;
		}
		
		printArray(arr);
		int[] sorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(sorted);
		int target = 15;
		System.out.println(exponentialSearch(sorted, target));
	}
	
	// performs an exponential search on the given int[] arr
	// for the target val
	// returns boolean representing whether or not
	// the value is present in the sorted given array
	// Worst case runtime is O (log (i)) where i represents
	// the index where the target would be present in the array
	// as that is how many sub array checks will occur
	// till the proper range is found
	// will out perform things like binary search
	// where values are closer to the start of the array for example
	// throws illegal argument exception if input array is empty
	public static boolean exponentialSearch(int[] arr, int target) {
		// if input array is empty throw an exception since there's nothing
		// to search through
		if (arr.length == 0) {
			throw new IllegalArgumentException("given array arr is empty");
		}
		
		int low = 0;
		int high = 1;
		
		// double sub array size until we find the subarray
		// that could contain the target
		while (high < arr.length && arr[high] < target) {
			// update the lower bound since we know
			// the current element is less than the target
			low = high;
			high *= 2;
		}
		
		// check if the reason we excited the array was since
		// we found the target
		if (high < arr.length && arr[high] == target) {
			return true;
		}
		
		// appropriately set the high value for the binary search
		high = Math.min(arr.length, high);
		
		// binary search the sub array
		return BinarySearch.binarySearch(arr, target, low, high);
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
