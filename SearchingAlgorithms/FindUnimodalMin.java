package SearchingAlgorithms;

// Class that contains a method to approximate where the minimum
// value of a unimodal function f occurs on an interval from [a, b].
// That is, on the interval [a, b] if there's a minval x
// from [a, x] f decreases and from [x, b] f increases
public class FindUnimodalMin {

	public static void main(String[] args) {
		double minX = ternarySearchMin(-100, 100, "func1");
		System.out.println("the min of x^2 + 4x + 1, is x: " + minX + ", the minVal " + func1(minX));

		minX = ternarySearchMin(3, 6, "func2");
		System.out.println("the min of sin (x) between (0, 2PI), is x: " + minX + ", the minVal " + func2(minX));
	}
	
	// given a low and high range
	// finds the min of a unimodal function where, at the min x
	// the function decreases from [low, x] and increases [x, high]
	// Worst case runtime is roughly O (log base 1.5 (high - low))
	// since 2/3 of the search space remains after each pass
	public static double ternarySearchMin(double low, double high, String method) {
		// keep going as long as the high - low difference
		// is larger than 10 ^ -9
		// once it's less than the tolerance we'll return the
		// low bound as the min x
		while (Math.pow(10, -9) < high - low) {
			// get the lower thrd and the upper thrd
			double thrd = (high - low) / 3;
			double lowThrd = low + thrd;
			double highThrd = high - thrd;
			
			// the min can't be in the upper range
			// since the lefthand thrd is less than the righthand thrd
			if (func(lowThrd, method) < func(highThrd, method)) {
				high = highThrd;
				
			// the min can't be in the lower range
			// since lefthand thrd is greater than the righthand thrd	
			} else {
				low = lowThrd;
			}
		}
		
		// we'll use our low value as our approximation of the min x
		return low;
	}
	
	// returns the evaluation of the given method/function x 
	// at the given value of x
	public static double func(double x, String method) {
		if (method.equals("func1")) {
			return func1(x);
		} else if (method.equals("func2")) {
			return func2(x);
		}
		return -1;
	}
	
	// function that returns
	// the evaluation of f(x) = x ^ 2 + 4x + 1
	// at the given x
	public static double func1(double x) {
		return x * x + 4 * x + 1;
	}
	
	// function that returns
	// the evaluation of f(x) = sin(x)
	// at the given x
	public static double func2(double x) {
		return Math.sin(x);
	}
}
