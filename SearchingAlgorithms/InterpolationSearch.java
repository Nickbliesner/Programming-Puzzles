package SearchingAlgorithms;

import SortingAlgorithms.MergeSort;
import java.util.Random;

// Class that contains a method for performing 
// an interpolation search on a sorted int [].
// Employs the sorted properties of the array by
// repeatedly using linear interpolation to estimate 
// where in the array the index should be for the target
// and adjusting high and low bounds accordingly
// thus is a better runtime then binary search if the
// values are uniformly distributed and the target is
// not aligned close to a mid val
public class InterpolationSearch {
	public static void main(String[] args) {
		int[] arr = {1, 10, 12, 4, 2, 3, 9, 8, 5, 15, 13, 14, 17, 18, 6, 11, 1, 10, 12, 19, 20, 4, 7};
		int[] arr2 = new int[20];
		Random r = new Random();

		for (int i = 0; i < arr2.length; i++) {
		    arr2[i] = r.nextInt(50) + 1;
		}

		printArray(arr);
		int[] sorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(sorted);
		int target = 15;
		System.out.println("The value of the search: " + interpolationSearch(sorted, target, 0, arr.length - 1));
		System.out.println();

		printArray(arr2);
		int[] sorted2 = MergeSort.mergeSort(arr2, 0, arr2.length);
		printArray(sorted2);
		int target2 = 18;
		System.out.println("The value of the search: " + interpolationSearch(sorted2, target2, 0, arr2.length - 1));
	}
	
	// performs an interpolation search on the given  int[] arr
	// for the target val
	// returns boolean representing whether or not
	// the value is present in the sorted given array
	// Worst case runtime is O (n) as all elements
	// will need to be compared. But if elements are uniformly
	// distributed runs on average O (log(log(n))) as the
	// interpolation search will do a better estimate of searching for 
	// the value in the array than things like binary search will do
	public static boolean interpolationSearch(int[] arr, int target, int low, int high) {
		// base case 1: one item on the interval
		if (high == low) {
			return arr[low] == target;
			
		// base case 2: the interval is empty
		// the interpolation search failed	
		} else if (high < low) {
			return false;
		}
			
		// estimate where the value should be on the interval
		double slope = (high - low) / ((arr[high] - arr[low]) * 1.0);
		int changeDx = (int) (slope * (target - arr[low]));
		int index = low + changeDx;
			
		// it's not on the interval of possible values it can't
		// be here
		if (index < low || high < index) {
			return false;
		}
			
		// at this point check whether or not the current
		// value is the target and call interpolation search appropriately otherwise
		// based on the value at the approximated index
		if (arr[index] == target) {
			return true;
		} else if (arr[index] < target) {
			return interpolationSearch(arr, target, index + 1, high);
		} else {
			return interpolationSearch(arr, target, low, index - 1);
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
