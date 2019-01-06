package SearchingAlgorithms;

import java.util.Random;

// Class that contains a method for performing 
// a linear search on an int [].
// Looks through each item one by one until
// the target is found or looks through
// everything and doesn't find the target
public class LinearSearch {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(15) + 1;
		}
		
		printArray(arr);
		int target = 4;
		System.out.println(linearSearch(arr, target));
	}
	
	// performs a linear search on the given  int[] arr
	// for the target val. Looking at elements one by one
	// to see if the target val is in the array.
	// Returns boolean representing whether or not
	// the value is present in the given array
	// Worst case runtime is O (n) as the linear search
	// will potentially have to look through everything to determine the target is missing.
	// Alright searching algorithm for unsorted data
	public static boolean linearSearch(int[] arr, int target) {
		// traverse the array until the target val is found
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				return true;
			}
		}
		
		// if we get to this point we traversed the entire
		// array and couldn't find the target
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
