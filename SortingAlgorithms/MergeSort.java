package nickCodingTest;

import java.util.Random;

// Class that contains method to 
// sort an int [] using a merge sort, sorting subarrays
// and then merging the sorted results together recursively
public class MergeSort {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(50) + 1;
		}
		
		printArray(arr);
		int[] sorted = mergeSort(arr, 0, arr.length);
		printArray(sorted);
	}
	
	// does a merge sort on the given int array
	// and returns the sorted array (as a new array with the same data as the original)
	// worst case runtime complexity is O (n * log (n)) where n is the size of the given array
	// recursively sorts ranges of the array
	public static int[] mergeSort(int[] arr, int low, int high) {
		if (arr.length == 0 || high == low) {
			return new int[0];
		} else if (high - low == 1) {
			// base case return array with arr[low] of size 1
			int[] res = new int[1];
			res[0] = arr[low];
			return res;
		}
		
		int mid = (high + low) / 2;
		int[] left = mergeSort(arr, low, mid);
		int[] right = mergeSort(arr, mid, high);
		
		int[] sorted = new int[left.length + right.length];
		int i = 0;
		int j = 0;
		int index = 0;
		
		// use the left and right sorted subarrays and combine into one merged array
		// traverse both arrays simultaneously picking the appropriately smaller
		// element each time
		while (i < left.length && j < right.length) {
			int val;
			
			if (left[i] < right[j]) {
				val = left[i];
				i++;
			} else {
				val = right[j];
				j++;
			}
			
			sorted[index] = val;
			index++;
		}
		
		int[] rest;
		int restDx;
		
		if (i < left.length) {
			rest = left;
			restDx = i;
		} else {
			rest = right;
			restDx = j;
		}
		
		for (int k = index; k < sorted.length; k++, restDx++) {
			sorted[k] = rest[restDx];
		}
		
		// return the new merged array
		return sorted;
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
