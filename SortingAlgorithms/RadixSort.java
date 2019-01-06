package SortingAlgorithms;

import java.util.*;

// Class that contains method to 
// sort an int [] using a radix sort, sorting ints into buckets based 
// on the current digit (0th, 1st, 2nd etc.) each time
// and continues to look at all ints until the highest nonzero digit
// has been observed for all numbers
public class RadixSort {
	public static void main(String[] args) {
		int[] arr = new int[20];
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(1100) + 1;
		}
		
		printArray(arr);
		radixSort(arr);
		printArray(arr);
	}
	
	// does a radix sort on the given int array, looking at the ints 
	// and grouping them digit by digit
	// worst case runtime complexity is O (w * n) where n is the size of the given array
	// and w is the number of digits in the longest int of the array
	public static void radixSort(int[] arr) {
		int maxLen = 0;
		for (int i = 0; i < arr.length; i++) {
			int curLen = numDigits(arr[i]);
			
			if (curLen > maxLen) {
				maxLen = curLen;
			}
		}
		
		Map<Integer, List<Integer>> oldDigitMap = null;
		for (int i = 0; i < maxLen; i++) {
			// we need to use a tree map since we rely on sorting the ints
			// digit by digit into buckets when we traverse we must observe the
			// smallest digits first and then higher ones later
			Map<Integer, List<Integer>> curDigitMap = new TreeMap<Integer, List<Integer>>();
			
			if (oldDigitMap == null) {
				
				// this is the 0th digit, we need to add in the initial array vals
				// since we don't have a prior old digit mapping to go off off
				for (int j = 0; j < arr.length; j++) {
					int curInt = arr[j];
					int curDig = getDigit(curInt, 0);
					
					if (!curDigitMap.containsKey(curDig)) {
						curDigitMap.put(curDig, new LinkedList<Integer>());
					}
					
					curDigitMap.get(curDig).add(curInt);
				}	
			} else {
				
				// we need to loop over the old digit mappings for the last processed digit and 
				// add the numbers from each bucket into the new buckets from lowest digit to highest
				for (int j: oldDigitMap.keySet()) {
					List<Integer> bucket = oldDigitMap.get(j);
					
					for (int curInt: bucket) {
						// retrieve the ith digit of the current int in the bucket
						int curDig = getDigit(curInt, i);
						
						if (!curDigitMap.containsKey(curDig)) {
							curDigitMap.put(curDig, new LinkedList<Integer>());
						}
						
						curDigitMap.get(curDig).add(curInt);
					}
				}
			}
			
			// at this point all we need to do is update the old digit map
			// to be the current digit map
			oldDigitMap = curDigitMap;
		}
		
		// the ints at this point are sorted according to bucket all we need to do
		// is traverse the buckets in the proper sorted order and update the given array
		int index = 0;
		for (int i: oldDigitMap.keySet()) {
			List<Integer> bucket = oldDigitMap.get(i);
			
			// traverse the current bucket and add each int to the array
			for (int curInt: bucket) {
				arr[index] = curInt;
				index++;
			}
		}
	}
	
	// returns the number of digits in the given int n
	public static int numDigits(int n) {
		int count = 0;
		while (n > 0) {
			count++;
			n /= 10;
		}
		return count;
	}
	
	// returns the given digit, dig, from the int n
	public static int getDigit(int n, int dig) {
		return (int) (n / Math.pow(10, dig)) % 10;
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
