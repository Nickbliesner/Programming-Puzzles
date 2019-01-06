package SortingAlgorithms;

import java.util.Random;

// Class that contains method to 
// sort an int [] using a bubble sort, comparing elements
// pairwise and swapping when necessary repeating passes
// until the process has created a sorted array
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(50) + 1;
		}
		
		printArray(arr);
		bubbleSort(arr);
		printArray(arr);
	}
	
	// does a bubble sort on the given int array
	// in place
	// worst case runtime complexity is O (n ^ 2) where n is the size of the given array
	// compares pairwise elements and swaps until the array is sorted
	// bubble sort as larger elements will bubble to the end of the array
	// worst case each element will have to be compared to all the other elements
	public static void bubbleSort(int[] arr) {
		boolean swapped = true;
		
		// as long as we had to swap elements we continue sorting
		while (swapped) {
			swapped = false;
			
			// traverse the array and do a pass swapping where appropriate
			for (int i = 0; i < arr.length - 1; i++) {
				
				if (arr[i] > arr[i + 1]) {
					// swap these elements the current one is larger than the next
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					
					// acknowledge this bubble pass had a swap
					swapped = true;
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
