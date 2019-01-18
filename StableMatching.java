package MatchingAlgorithms;
import java.util.*;

// Class that implements the Stable Matching Problem algorithm.
// Given a list of men and women and their preferences
// finds a set of pairs which is a stable matching, ie. noone
// has incentive to swap their current partner with one
// that is higher up on the preference list currently paired with
// someone else
public class StableMatching {
	public static void main(String[] args) {
		stableMatching(4);
	}
	
	// runs the stable matching algorithm
	// on random permutations of M's and W's
	// for size n, to produce  a stable matching
	// for the generated preference lists
	// Worst case runtime is O (n ^ 2) as
	// this is the maximum number of proposals that
	// could occur, data structures such as hash maps, array lists (fast lookup)
	// as well as queues are used such that
	// proposals can occur as fast as possible
	public static void stableMatching(int n) {
		// use hash maps combined with array list
		// to ensure fast preference list lookup
		// keep track of mens preference lists as well as womens preference lists
		Map<Integer, List<Integer>> menInv = new HashMap<Integer, List<Integer>>();
		Map<Integer, List<Integer>> womInv = new HashMap<Integer, List<Integer>>();
		
		// generate the random preference list input
		genInput(menInv, womInv, n);
		
		// create the map to keep track of the next person for each man to propose to
		// initialize all mens indices to 0
		Map<Integer, Integer> menDx = new HashMap<Integer, Integer>();
		for (int i = 1; i <= n; i++) {
			menDx.put(i, 0);
		}
		
		// create the reverse mapping for each women
		Map<Integer, Map<Integer, Integer>> womPrefs = new HashMap<Integer, Map<Integer, Integer>>(); 
		for (int i: womInv.keySet()) {
			List<Integer> wiPrefs = womInv.get(i);
			Map<Integer, Integer> revLookup = new HashMap<Integer, Integer>();
			
			// fill in the reverse mapping of men to ranking on the current women's list
			for (int j = 0; j < wiPrefs.size(); j++) {
				int man = wiPrefs.get(j);
				// map the man to the index in wi's preference list 
				revLookup.put(man, j);
			}
			
			// add the reverse mapping for the current women
			womPrefs.put(i, revLookup);
		}
		
		// create a mapping to house the stable matches of the resulting stable matching
		// maps woman to men
		// as well as a queue of work/ the men who have yet to be matched
		// as the algorithm follows the reording property
		Map<Integer, Integer> matches = new HashMap<Integer, Integer>();
		Queue<Integer> unMatched = new LinkedList<Integer>();
		
		// add all the men to the initial unmatched queue
		for (int i = 1; i <= n; i++) {
			unMatched.add(i);
		}
		
		// implement the stable matching algorithm (Gale Shapley stable marriage algorithm)
		// while there are men that are unmatched let's try and match them based on their preferences
		while (!unMatched.isEmpty()) {
			int nextM = unMatched.remove();
			
			// get his preference list and the index of the next woman to propose to
			List<Integer> nextMPrefs = menInv.get(nextM);
			int index = menDx.get(nextM);
			
			// get the next women for him to propose to on his list 
			// and check whether she's already matched
			int nextW = nextMPrefs.get(index);
			
			// check whether or not shes matched
			if (!matches.containsKey(nextW)) {
				// she's not matched she tentavively accepts the current m's proposal
				matches.put(nextW, nextM);
				
			} else {
				// she's already matched get the preference of the current m and the other m
				Map<Integer, Integer> revLookup = womPrefs.get(nextW);
				
				// get the rankings of her current match as well as the new possible one
				int newRank = revLookup.get(nextM);
				int oldRank = revLookup.get(matches.get(nextW));
				
				// check whether or not she prefers her new or old match
				if (oldRank < newRank) {
					// she prefers her old match, move the 
					// current man's index forward and re-add him to the queue
					unMatched.add(nextM);
					menDx.put(nextM, menDx.get(nextM) + 1);
					
				} else {
					// she prefers her new match to her old one
					// swap the match and readd the new unmatched man to the queue
					int oldM = matches.get(nextW); 
					matches.put(nextW, nextM);
					
					// move the unmatched mans index forward in his list
					unMatched.add(oldM);
					menDx.put(oldM, menDx.get(oldM) + 1);
				}
			}
		}
		
		// at this point once the loop terminates we have a successfull
		// stable matching
		System.out.println("matchings: ");
		for (int i: matches.keySet()) {
			System.out.println("(w" + i + ", m" + matches.get(i) + ")");
		}
		System.out.println();
		
		// calculate the mRank as well as the wRank
		int mRank = 0;
		int wRank = 0;
		
		// get the mRank
		for (int m: menDx.keySet()) {
			// since the mapping maps to the index
			// in m's list of his match we add 1 to it to get the
			// preference value
			mRank += menDx.get(m) + 1;
		}
		
		// get the wRank
		for (int w: matches.keySet()) {
			// get her match and lookup his index value
			// in her reverse preference map
			int matchM = matches.get(w);
			Map<Integer, Integer> reverseLookup = womPrefs.get(w);
			wRank += reverseLookup.get(matchM) + 1;
		}
		
		System.out.println("mRank: " + mRank + ", wRank: " + wRank);
	}
	
	// creates and sets up the preference lists for each of the n men and the women
	// in the given mappings
	public static void genInput(Map<Integer, List<Integer>> menInv, Map<Integer, List<Integer>> womInv, int n) {
		// System.out.println("Men Preferences: ");
		// create the preference lists for each man
		for (int i = 1; i <= n; i++) {
			List<Integer> miPrefs = genPerm(n);
			menInv.put(i, miPrefs);
			printList(miPrefs);
		}
		
		System.out.println();
		
		// fill in the preference lists for each women
		// System.out.println("Women Preferences: ");
		for (int i = 1; i <= n; i++) {
			List<Integer> wiPrefs = genPerm(n);
			womInv.put(i, wiPrefs);
			printList(wiPrefs);
		}
		
		System.out.println();
	}
	
	// returns a random permutation list of the given size
	// of the numbers 1 - n inclusive
	public static List<Integer> genPerm(int n) {
		// initialize the input list
		List<Integer> inv = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			inv.add(i);
		}
		
		Random r = new Random();
		
		// array list is used since we want to lookup priority
		// lists fast, we will not add or remove from the lists
		// after they are filled in
		List<Integer> res = new ArrayList<Integer>();
		
		while (!inv.isEmpty()) {
			// get a random value from the list
			int index = r.nextInt(inv.size());
			
			// add the random value to the permutation
			res.add(inv.remove(index));
		}
		
		return res;
	}
	
	// given a list of integers prints the elements in
	// comma separated format
	public static void printList(List<Integer> li) {
		if (li.size() > 0) {
			System.out.print(li.get(0));
		}
		
		// print the rest of the list
		for (int i = 1; i < li.size(); i++) {
			System.out.print(", " + li.get(i));
		}
		
		System.out.println();
	}
}
