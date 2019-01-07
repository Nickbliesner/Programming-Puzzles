package DynamicProgramming;

// Class that contains a method
// given integers n and m
// finds how many nondecreasing sequences
// can me made of length m with n distinct integers
public class NonDecreasingSequences {

	public static void main(String[] args) {
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 10; j++) {
				int[][] cache = new int[i + 1][j + 1];
				int seqs = countNonDecreasing(i, j, cache);
				System.out.println("n: " + i + ", m: " + j + ", seqs: " + seqs);
			}
		}
	}
	
	// given ints n and m
	// returns how many non decreasing sequences can be made
	// with n distinct ints and of length m
	// worst case runtime is O (n * m) as all function calls
	// with params in the set [1, n] X [1, m] will need to be calculated
	// and the recursive function employs caching
	public static int countNonDecreasing(int n, int m, int[][] cache) {
		// if n is less than 1 or if m is less than 1 throw an
		// exception as that doesn't make sense in the context of the problem
		if (n < 1 || m < 1) {
			throw new IllegalArgumentException("the int n: " + n + ", or the int m: " + m + ", is less than 1");
		}
		
		// base case 1, with length 1 can make n seqs
		if (m == 1) {
			return n;
		}
		
		// base case 2, with 1 element can only make 1 sequence
		if (n == 1) {
			return 1;
		}
		
		// if we have already cached this call return the cached value
		if (cache[n][m] != 0) {
			return cache[n][m];
		}
		
		// we haven't cached this call we need to compute it
		// loop over all the values we could place based on n
		// and compute how many sequences we can make with the rest of
		// the available numbers and 1 less length
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += countNonDecreasing(n - i, m - 1, cache);
		}
		
		// cache the calculated value
		cache[n][m] = sum;
		return sum;
	}
}
