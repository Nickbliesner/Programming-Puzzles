package DynamicProgramming;

import java.math.BigInteger;

// Class that contains a method to calculate the nth fibonacci
// number. Has 3 different implementations
// **fibonacciRecur: Computes fib nums recursively, 
//					 uses caching for faster runtime but vulnerable to stack overflow
// **fibonacciIter:  Computes fib nums iteratively, 
//					 if n is too large will have integer overflow and return the wrong answer
// **fibonacciBig:   Computes fib nums iteratively, but uses big integers
//					 such that larger n fibonaccis (n > 45) can be calculated accurately without integer overflow
public class Fibonacci {
	
	public static void main(String[] args) {
		for (int i = 0; i < 46; i++) {
			System.out.println("Recursive Fibonnacci, n: " + i + ", fib(n): " + fibonacciRecur(i, new int[i + 1]));
			System.out.println("Iterative Fibonnacci, n: " + i + ", fib(n): " + fibonacciIter(i));
			System.out.println("Big Fibonnacci, n: " + i + ", fib(n): " + fibonacciBig(i));
		}
		
		for (int i = 46; i < 100; i++) {
			System.out.println("Big Fibonnacci, n: " + i + ", fib(n): " + fibonacciBig(i));
		}
	}
	
	// Given an int n, returns the
	// nth fibonacci number (where fib(0) = 1 and fib(1) = 1)
	// Worst case runtime is O(n) since caching is utilized
	// all fibonacci numbers 0 - n willl each need to be calculated
	// only one time
	// (to increase runtime caching and O(n) space is used to prevent recalculation)
	// Since recursion is used for fibonacci this approach is vulnerable to stack overflows
	// if stack memory runs out
	public static int fibonacciRecur(int n, int[] cache) {
		// if n < 0 this doesn't make sense
		// throw an exception negative fibonaccis are not possible
		if (n < 0) {
			throw new IllegalArgumentException("The given int n: " + n + ", can't be less than 0");
		}
		
		// base case, the 0th or 1st fibonacci number
		if (n == 0 || n == 1) {
			return 1;
		}
		
		// check whether or not this value has already been cached before
		if (cache[n] != 0) {
			// we have already cached this computation, just return it
			return cache[n];
		}
		
		// we haven't cached this value let's compute it then cache it
		int sum = fibonacciRecur(n - 1, cache) + fibonacciRecur(n - 2, cache);
		
		// cache the current computation
		cache[n] = sum;
		return sum;
	}
	
	// Given an int n, returns the
	// nth fibonacci number (where fib(0) = 1 and fib(1) = 1)
	// Worst case runtime is O(n), since iteratively the first
	// n - 1 fibonacci numbers will need to be calculated to get the nth one
	// This iterative approach only requires O (1) space and 
	// is not vulnerable to stack overflow but,
	// if n is larger than 45 integer overflow will occur and
	// fib (n) will not be computed accurately/properly
	public static int fibonacciIter(int n) {
		// if n < 0 this doesn't make sense
		// throw an exception negative fibonaccis are not possible
		if (n < 0) {
			throw new IllegalArgumentException("The given int n: " + n + ", can't be less than 0");
		}
		
		// base case, the 0th or 1st fibonacci number
		if (n == 0 || n == 1) {
			return 1;
		}
		
		// store the values of the last two computed fibonacci numbers
		int secondLast = 1;
		int last = 1;
		
		// iteratively compute the fibonacci numbers 2 - n 
		for (int i = 2; i <= n; i++) {
			int res = secondLast + last;
			
			// shift over the fibonacci values
			secondLast = last;
			last = res;
		}
		
		// the nth fibonacci number is now being stored as the last computed one
		return last;
	}
	
	// Given an int n, returns the
	// nth fibonacci number (where fib(0) = 1 and fib(1) = 1) as a big integer
	// Worst case runtime is O(n), since iteratively the first
	// n - 1 fibonacci numbers will need to be calculated to get the nth one
	// This iterative approach only requires O (1) space and 
	// is not vulnerable to stack overflow, since big integers are used
	// integer overflow will not occur and fibonnaci n's larger than 45 can be calculated
	public static BigInteger fibonacciBig(int n) {
		// if n < 0 this doesn't make sense
		// throw an exception negative fibonaccis are not possible
		if (n < 0) {
			throw new IllegalArgumentException("The given int n: " + n + ", can't be less than 0");
		}
		
		// base case, the 0th or 1st fibonacci number
		if (n == 0 || n == 1) {
			return new BigInteger("1");
		}
		
		// store the values of the last two computed fibonacci numbers
		BigInteger secondLast =  new BigInteger("1");
		BigInteger last = new BigInteger("1");
		
		// iteratively compute the fibonacci numbers 2 - n 
		for (int i = 2; i <= n; i++) {
			BigInteger res = secondLast.add(last);
			
			// shift over the fibonacci values
			secondLast = last;
			last = res;
		}
		
		// the nth fibonacci number is now being stored as the last computed one
		return last;
	}
	
}
