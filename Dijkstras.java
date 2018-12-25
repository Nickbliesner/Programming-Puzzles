package nickCodingTest;
import java.util.*;

public class Dijkstras {
	
	public static void main(String[] args) {
		int[][] edges = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
						 {4, 0, 8, 0, 0, 0, 0, 11, 0},
						 {0, 8, 0, 7, 0, 4, 0, 0, 2},
						 {0, 0, 7, 0, 9, 14, 0, 0, 0},
						 {0, 0, 0, 9, 0, 10, 0, 0, 0},
						 {0, 0, 4, 14, 10, 0, 2, 0, 0},
						 {0, 0, 0, 0, 0, 2, 0, 1, 6},
						 {8, 11, 0, 0, 0, 0, 1, 0, 7},
						 {0, 0, 2, 0, 0, 0, 6, 7, 0}};
		Map<Integer, Pair> minMap = new TreeMap<Integer, Pair>();
		dijkStras(edges, minMap);
		
		for (int i: minMap.keySet()) {
			Pair cur = minMap.get(i);
			System.out.println("The vert: " + cur.vert + " The minDist: " + cur.dist);
		}
	}
	
	public static void dijkStras(int[][] edges, Map<Integer, Pair> minMap) {
		// keeps track of which edges we have found the shortest path already for
		Set<Integer> seen = new HashSet<Integer>();
		minMap.put(0, new Pair(0, 0));
		
		// keeps track of the next edge we need to visit based on distances
		PriorityQueue<Pair> heap = new PriorityQueue<Pair>();
		heap.add(minMap.get(0));
		
		while (!heap.isEmpty()) {
			Pair minVert = heap.remove();
			seen.add(minVert.vert);
			
			// get the vertices for the vertex pulled out of the heap
			int[] vertEdges = edges[minVert.vert];
			
			for (int i = 0; i < vertEdges.length; i++) {
				// we haven't found the minpath and there's an edge to this node
				if (!seen.contains(i) && vertEdges[i] != 0) {
					
					// we need to add it to the map or remove it possibly and read
					if (!minMap.containsKey(i)) {
						minMap.put(i, new Pair(i, minVert.dist + vertEdges[i]));
						heap.add(minMap.get(i));
					// we have seen this node before let's see if we found a faster route
					// if so let's update the min distance for the vertex i	
					} else if (minVert.dist + vertEdges[i] < minMap.get(i).dist) {
						Pair iPair = minMap.get(i);
						heap.remove(iPair);
						iPair.dist = minVert.dist + vertEdges[i];
						heap.add(iPair);
					}
					// we didn't find a smaller path we need to do nothing
					// or we updated the heap accordingly already
				}
			}
		}
	}
}
