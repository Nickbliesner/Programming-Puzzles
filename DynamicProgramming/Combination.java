package DynamicProgramming;

// Class that contains a method to compute
// combinations. Given a number of elements
// as well as a number of successes, returns how many ways
// to pick the number of successes from that many things
// For example: 4 C 2 = 6 -> 1 1 0 0, 1 0 1 0, 
//			     1 0 0 1, 0 1 1 0, 
// 			     0 1 0 1, 0 0 1 1
public class Combination {

	public static void main(String[] args) {
		// compute the first 8 rows of pascals triangle
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.println(i + " choose " + j + " : " + getCombinations(i, j, new int[i + 1][j + 1]));
			}
		}
	}
	
	// given an int n and an int c
	// returns how many ways you can pick c successes out of 
	// n total things (number of combinations n choose c)
	// with caching worst case possible runtime is O (n ^ 2)
	// as all elements up to the nth row of pascals triangle will need to be 
	// computed, and the cum sum of things in pascals triangles is proportional to the row
	// number squared
	public static int getCombinations(int n, int c, int[][] cache) {
		// throw an exception if n is less than 0 it doesn't make
		// much sense to pick combinations from a negative amount of things 
		if (n < 0) {
			throw new IllegalArgumentException("the given int n: " + n + ", can't' be less than 0");
		}
		
		// throw an exception if c is less than 0
		// or if c is greater than n as it doesn't make sense
		// to select a negative amount of things or more things than available
		if (c < 0 || n < c) {
			throw new IllegalArgumentException("the given int c: " + c + ", can't be less than 0, " + 
											   "or greater than n: " + n);
		}
		
		// base case:
		// if we have 0 things to choose from
		// or want all of the n things to be successes
		// or none of the n things to be successes there's only one way to do so
		if (n == 0 || c == 0 || c == n) {
			return 1;
		}
		
		// check whether or not this combination has been cached already
		if (cache[n][c] != 0) {
			return cache[n][c];
		}
		
		// the combination has not been cached let's compute it
		// we need to cover both cases: that the current element being selected
		// is a success or it is not a success and call the combination function accordingly 
		int sum = 0;
		
		// we choose the current item as a success
		sum += getCombinations(n - 1, c - 1, cache);
		
		// we choose the current item as a failure
		sum += getCombinations(n - 1, c, cache);
		
		// cache the calculated value
		cache[n][c] = sum;
		return sum;
		
	}
}
