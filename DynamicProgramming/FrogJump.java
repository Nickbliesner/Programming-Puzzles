package nickCodingTest;

// Class that contains a method,
// given an array as the bog will return the minimum number of jumps it
// will take a frog to hop across the bog (reach past the last index of the input array) 
// if at a given index i the max jump the frog can take is arr[i]
// and if at any point 0 is encountered the frog gets stuck in the bog and can't keep jumping
public class FrogJump {

	public static void main (String[] args) {
		int[] arr = {8, 1, 1, 1, 1, 1, 9, 6, 5, 1, 1, 1, 1, 0};
		System.out.println("the min jumps is: " + frogJump(arr));
	}
	
	// given an array arr of non negative jump values,
	// where arr[i] represents the maximum jump the frog can make from that position
	// returns the minimum number of jumps a frog
	// needs to make to reach the end and -1 otherwise
	// if there's no jump sequence to cross the bog
	public static int frogJump(int[] arr) {
		// throw an illegal argument exception if the input
		// array is empty, no bog means no value makes sense for jumping
		if (arr.length == 0) {
			throw new IllegalArgumentException(); 
		}
		
		// traverse the array in reverse order. Since we always jump right
		// as we reach each leftmost index all ones right of it will already be processed
		// thus this can be done iteratively.
		// instead of using a cache we can overwrite the array as we process values.
		// Since the jump values are not needed after processing
		for (int i = arr.length - 1; i >= 0; i--) {
			int cur = arr[i];
			
			// check whether or not we can jump out from this index
			if (arr.length <= cur + i) {
				arr[i] = 1;
			
			// check if the frog can't jump from this index	
			} else if (cur == 0) {
				arr[i] = -1;
				
			// traverse all possible jumps from this index
			// and find the min jump path the frog can take	
			} else {
				int min = Integer.MAX_VALUE;
				
				for (int j = cur; j > 0; j--) {
					int curMin = arr[i + j];
					
					// check whether or not to update the
					// min jumps from here
					if (curMin != -1 && curMin < min) {
						min = curMin;
						
						if (min == 1) {
							// nothing can beat this minval
							// stop searching now
							break;
						}
					}
				}
				
				// check whether or not we were able to find a min
				// other than -1, if so add a jump from this index to the 
				if (min != Integer.MAX_VALUE) {
					min++;
				} else {
					min = -1;
				}
				
				arr[i] = min;
			}
		}
		
		// the value at index 0 will now hold the min number of jumps
		return arr[0];
	}
	
}
