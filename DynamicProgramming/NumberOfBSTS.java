package nickCodingTest;

// Class that contains a method
// to find how many unique Binary
// search trees can be created from
// n/ a number of distinct integers
public class NumberOfBSTS {

	public static void main(String[] args) {
		for (int i = 0; i < 8; i++) {
			System.out.println(i + " numbers make " + findUniqueBSTS(i) + " unique BST'S");
		}
	}
	
	// given a number of nodes n
	// returns how many Unique Binary Search trees
	// can be made with n distinct integers.
	// Worst case runtime is O (n ^ 2)
	// as to calculate number of BSTS for n nodes
	// the number of BSTS for 0 through n - 1 nodes will need to be computed as well
	// O (n) space is used to cache computed results
	// throws an illegal argument exception if n is negative
	public static int findUniqueBSTS(int n) {
		// if n is negative throw an exception
		// since a negative number of nodes doesn't make sense
		// as n represents a count
		if (n < 0) {
			throw new IllegalArgumentException("the given int n: " + n + ", can't be less than 0");
		}
		
		// base case so to speak for iterating
		if (n == 0 || n == 1) {
			return 1;
		}
		
		// we need to cache smaller BST vals
		// to get the larger ones
		// cache the base case values
		int[] cache = new int[n + 1];
		cache[0] = 1;
		cache[1] = 1;
		
		// calculate the unique BSTs for 0
		// up to n nodes
		for (int i = 2; i <= n; i++) {
			int sum = 0;
			
			// calculate how many BST's can be made with i nodes
			for (int j = 0; j < i; j++) {
				int numLeft = j;
				int numRight = i - 1 - j;
				
				// to get how many BST's we get we take
				// all combinations of left and right subtrees
				sum += cache[numLeft] * cache[numRight];
			}
			
			// cache the computed value for unique BSTS with i nodes
			cache[i] = sum;
		}
		
		return cache[n];
	}
}
