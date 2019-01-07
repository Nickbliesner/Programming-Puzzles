package DynamicProgramming;

// Class that contains a method to
// find how many unique paths a robot can take
// to get from the top left corner to the bottom right
// corner of a grid with given dimensions n by m
// only going rightward and downward each step
// ** contains a method utilizing recursion
// ** as well as one not needing the stack memory using iteration instead
public class UniquePaths {

	public static void main(String[] args) {
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				System.out.println("Recursive Unique paths for a grid of m: " + i + ", by n: " + j + ", " 
						            + countPathsRecur(i, j, new int[i + 1][j + 1]));
				System.out.println("Iterative Unique paths for a grid of m: " + i + ", by n: " + j + ", " 
			            + countPathsIter(i, j));
			}
		}
	
	}
	
	
	// given grid dimension ints n and m
	// returns how many different paths a robot can
	// take to get from the top left corner to the bottom
	// right corner of the grid only moving rightward and downward
	// Worst case runtime is O (m * n) as in order to compute
	// this it turns into computing unique paths paths for all grids with dimensions
	// [1 - m] X [1 - n], which is about n * m computations
	// **if n and m are large enough could overflow the stack's memory
	public static int countPathsRecur(int m, int n, int[][] cache) {
		if (m == 0 || n == 0) {
			throw new IllegalArgumentException("The given int n: " + n + ", and the given int m: " + ", can't be 0");
		}
		
		// if either grid dimension is 1 there's only one way
		// to get to the end
		if (m == 1 || n == 1) {
			return 1;
		}
		
		// check whether or not this value has been cached already
		if (cache[m][n] != 0) {
			// return the cached value
			return cache[m][n];
		}
		
		// move right one and down one and compute how many paths we
		// can take from those positions
		// and add them together
		int sum = 0;
		sum += countPathsRecur(m - 1, n, cache);
		sum += countPathsRecur(m, n - 1, cache);
		
		// cache the value and then return
		cache[m][n] = sum;
		return sum;
	}
	
	// **This version of the unique paths problem solves it iteratively
	// **no need to worry about running out of memory for stack frames in this implementation
	// **still requires usage of caching though and O(n * m) memory
	// given grid dimension ints n and m
	// returns how many different paths a robot can
	// take to get from the top left corner to the bottom	
	// right corner of the grid only moving rightward and downward
	// Worst case runtime is O (m * n) as in order to compute
	// this it turns into computing unique paths paths for all grids with dimensions
	// [1 - m] X [1 - n], which is about n * m computations
	public static int countPathsIter(int m, int n) {
		if (m == 0 || n == 0) {
			throw new IllegalArgumentException("The given int n: " + n + ", and the given int m: " + ", can't be 0");
		}
			
		// if either dimension is 1 there's only one unique path to take
		if (m == 1 || n == 1) {
			return 1;
		}
		
		int[][] cache = new int[m][n];
		
		// initialize the known values of the cache, where there's one possible
		// path stemming from the elements.
		// ie. the bottom row and the last column of the cache array
		for (int i = 0; i < cache.length; i++) {
			cache[i][n - 1] = 1;
		}
		
		// initialize the bottom row
		int[] bottom = cache[m - 1];
		for (int j = 0; j < bottom.length; j++) {
			bottom[j] = 1;
		}
		
		// now we iteratively compute each "L" shape's unique paths
		// in order to retrieve the number of unique paths
		// we will calculate values with the fact that
		// if the computation right of an element as well as below
		// the element has been done one can compute the number of 
		// unique paths stemming from the current element
		// for example: cache[0][0] = cache[0][1] + cache[1][0]
		for (int k = m - 2; k >= 0; k--) {
			int shift = m - k;
			int col = n - shift;
					
			// compute the unique paths for the rightmost column
			for (int j = k; j >= 0; j--) {
				cache[j][col] = cache[j + 1][col] + cache[j][col + 1];
			}

			// compute the unique paths for the bottom-most row
			int[] row = cache[k];
			for (int j = col; j >= 0; j--) {
				row[j] = row[j + 1] + cache[k + 1][j];
			}
			
			// check if the column became 0, if so we are finished
			// and we need to break or we will have calculation issues
			// and over counting
			if (col == 0) {
				break;
			}
		}
		
		// the value at 0, 0 will now be the cached value
		// that we are seeking, return it
		return cache[0][0];
	}
}
