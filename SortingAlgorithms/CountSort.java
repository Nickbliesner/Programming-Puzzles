package SortingAlgorithms;

import java.util.Random;

// Class that contains method to 
// sort an int [] using a count sort, compiling/
// using counting to take an inventory of the array's vals counts
// and then put the counts in the appropriate order to the output array
// requires knowledge of the largest and smallest 
// possible int that could be present in the input data
public class CountSort {
	
	// class constant representing largest possible int in the input data
	public static final int MAX_INT = 10;
	
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(10) + 1;
		}
		
		printArray(arr);
		countSort(arr);
		printArray(arr);
	}
	
	// does a count sort on the given int array
	// taking an inventory of the input data and counting occurrences of each value
	// then outputting the counts in the proper order to the original array
	// worst case runtime complexity is O (n) where n is the size of the given array
	// as all elements in the array will need to be counted and inventoried for
	// used O (n) space, as well as sort requires knowledge of the largest possible int
	// and that the smallest int is 0 or above (otherwise a linear mapping can be used)
	public static void countSort(int[] arr) {
		int[] inv = new int[MAX_INT + 1];
		
		// get an inventory of all values in the array
		for (int i = 0; i < arr.length; i++) {
			int curVal = arr[i];
			inv[curVal]++;
		}
		
		// use the inventory to sort the array
		int index = 0;
		
		// traverse the newly generated inventory
		for (int i = 0; i < inv.length; i++) {
			
			// insert the current int i the the number of 
			// times it occured in the input array
			for (int j = 0; j < inv[i]; j++) {
				arr[index] = i;
				index++;
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
