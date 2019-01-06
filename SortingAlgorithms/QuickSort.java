package SortingAlgorithms;

import java.util.Random;

// Class that contains method to 
// sort an int [] using a quick sort, picking pivots
// grouping based on higher or lower/equal than the pivot and then
// sorts sub groups recursively
public class QuickSort {
	public static void main(String[] args) {
		int[] arr = new int[10];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(100) + 1;
		}
		
		printArray(arr);
		quickSort(arr, 0, arr.length);
		printArray(arr);
	}
	
	// does a quick sort on the given int array in place
	// picks a pivot and then sorts the data range based on whether or not values
	// are higher or lower then the pivot
	// worst case runtime complexity is O (n ^ 2) if values are always skewed about the pivots
	// best case runtime complexity is O (n * log (n)) if in general values are symmetric about the pivots
	public static void quickSort(int[] arr, int low, int high) {
		// if the array is empty, the subrange has 0 or 1 elements
		// we have hit the base case and should just return at this point
		if (arr.length == 0 || high - low <= 1) {
			return;
		}
		
		int mid = (high + low) / 2;
		int pivot = arr[mid];
		
		// initially swap the pivot with the first value
		arr[mid] = arr[low];
		int lower = low + 1;
		int upper = high - 1;
		while (lower != upper) {
			// move the lower frontier forward
			if (arr[lower] <= pivot) {
				lower++;
				continue;
			}
			
			// we keep swapping until the current val is
			// less than or equal to the pivot or
			while (arr[lower] > pivot && lower != upper) {
				int temp = arr[lower];
				arr[lower] = arr[upper];
				arr[upper] = temp;
				upper--;
			}
		}
		
		// now we need to shift over the lower values place the pivot and sort
		// the subranges
		// we'll move all the smaller values first
		for (int i = low; i < lower - 1; i++) {
			arr[i] = arr[i + 1];
		}
		
		// handle that between range element appropriately
		// and update upper and lower accordingly such that
		// the lower and upper subranges can be sorted in place
		if (arr[lower] <= pivot) {
			// the middle element was lower
			arr[lower - 1] = arr[lower];
			arr[lower] = pivot;
			upper++;
		} else {
			// the middle value was higher
			arr[lower - 1] = pivot;
			lower--;
		}
		
		// now we need to sort the left and right subranges
		quickSort(arr, low, lower);
		quickSort(arr, upper, high);
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
