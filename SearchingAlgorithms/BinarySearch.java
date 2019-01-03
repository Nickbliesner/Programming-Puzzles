package nickCodingTest;

import java.util.Random;

import nickCodingTest.SortingAlgorithms.MergeSort;

// Class that contains a method for performing 
// a binary search on a sorted int [].
// Employs the sorted properties of the array by
// when searching for a value eliminating half
// the search space each pass of the search
public class BinarySearch {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(15) + 1;
		}
		
		printArray(arr);
		int[] sorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(sorted);
		int target = 4;
		System.out.println(binarySearch(sorted, target, 0, arr.length));
	}
	
	// performs a binary search on the given array arr
	// for the target val, with range low inclusive and high exclusive 
	// returns boolean representing whether or not
	// the value is present in the sorted given array
	// Worst case runtime is O (log(n)) as the binary search
	// cuts the number of potential candidates in half each pass
	public static boolean binarySearch(int[] arr, int target, int low, int high) {
		// base case 1: it can't be present no items in current possible set
		if (high == low) {
			return false;
			
		// base case 2: there's 1 item left check if it's the right one	
		} else if (high - low == 1) {
			return arr[low] == target;
		}
		
		// find the mid, compare it to the target and search accordingly 
		int mid = (high + low) / 2;
		int midVal = arr[mid];
		
		// check if the midVal is the target
		if (midVal == target) {
			// we found the value
			return true;
			
		// if the target's less search the left range	
		} else if (target < midVal) {
			return binarySearch(arr, target, low, mid);
		
		// if the target's greater search the right range
		} else {
			return binarySearch(arr, target, mid + 1, high);
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
