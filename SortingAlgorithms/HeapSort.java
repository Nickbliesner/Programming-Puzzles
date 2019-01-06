package SortingAlgorithms;

import DataStructures.KHeap;
import java.util.Random;

// Class that contains method to 
// sort an int [] using a heap sort
// (using a min heap to store and sort the given data)
public class HeapSort {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(50) + 1;
		}
		
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}
	
	// does a heap sort on the given int array (sort using a min heap)
	// worst case runtime complexity is O (n * log base 3 (n))
	// as each heap operation is roughly O (log base 3 (n))
	// as a three child heap is used
	public static void heapSort(int[] arr) {
		KHeap threep = new KHeap(3);
		for (int i = 0; i < arr.length; i++) {
			threep.add(arr[i]);
		}
		
		int index  = 0;
		while (!threep.isEmpty()) {
			arr[index] = threep.remove(); 
			index++;
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
