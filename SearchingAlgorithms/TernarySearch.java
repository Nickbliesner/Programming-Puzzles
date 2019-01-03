package nickCodingTest;

import java.util.Random;

import nickCodingTest.SortingAlgorithms.MergeSort;

// Class that contains a method for performing 
// a ternary search on a sorted int [].
// Employs the sorted properties of the array by
// when searching for a value eliminating two thirds of
// the search space each pass of the search
// through picking 2 comparison points each time
public class TernarySearch {
	public static void main(String[] args) {
		int[] arr = new int[50];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(100) + 1;
		}
		
		printArray(arr);
		int[] sorted = MergeSort.mergeSort(arr, 0, arr.length);
		printArray(sorted);
		int target = 26;
		System.out.println(ternarySearch(sorted, target, 0, arr.length));
	}
	
	// performs a ternary search on the given  int[] arr
	// for the target val, with range low inclusive and high exclusive 
	// returns boolean representing whether or not
	// the value is present in the sorted given array
	// Worst case runtime is O (log base 3 (n)) as the ternary search
	// cuts number of potential candidates into a third each time
	public static boolean ternarySearch(int[] arr, int target, int low, int high) {
		// base case 1: it can't be present no items in current possible set
		if (high == low) {
			return false;
			
		// base case 2: there's  only 1 or two items left check if either is the target
		} else if (high - low < 3) {
			
			// check if the range contains the target
			for (int i = low; i < high; i++) {
				if (arr[i] == target) {
					return true;
				}
			}
			
			// the target was not either of the items or the single item
			return false;
		}
		
		// find the lower third boundary and the upper thrd boundary  
		int thrd = (high - low) / 3;
		int lowThrd = low + thrd;
		int upperThrd = high - thrd;
		int lowThrdVal = arr[lowThrd];
		int upperThrdVal = arr[upperThrd];
		
		// check if the lowThrd or upperThrd is the target
		if (lowThrdVal == target || upperThrdVal == target) {
			// we found the value
			return true;
			
		// if the target's less than the low third search the left range	
		} else if (target < lowThrdVal) {
			return ternarySearch(arr, target, low, lowThrd);
		
		// if the target's greater than the low third 
	    // but less than the upper third search the middle range
		} else if (target < upperThrdVal) {
			return ternarySearch(arr, target, lowThrd + 1, upperThrd);
		
		// the target must be greater than the upper thrd value
		// search the right range
		} else {
			return ternarySearch(arr, target, upperThrd + 1, high);
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