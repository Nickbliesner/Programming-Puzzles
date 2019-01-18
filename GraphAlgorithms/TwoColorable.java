package GraphAlgorithms;
import java.util.*;

// Class that contains a method
// to determine whether or not
// a given graph is two colorable,
// that using two different colors one could
// assign every vertex a color such that it 
// differs from the color of it's adjacent neighbors
public class TwoColorable {
	
	public static void main(String[] args) {
		// double array representing each verts edges
		int[][] edges = {{3}, {3}, {1, 2, 4}, {3, 5, 7, 8},
						 {4, 6}, {5, 7}, {4, 6}, {4, 9, 10},
						 {8, 11}, {8, 11}, {9, 10}}; 
		Map<Integer, List<Integer>> graph = fillGraph(edges); 
		printGraph(graph);
		
		System.out.println("the graph is two colorable: " + isTwoColorable(graph));
		System.out.println();
		
		// double array representing 2nd graph's verts edges
		int[][] edges2 = {{2}, {3}, {1}};
		graph = fillGraph(edges2); 
		printGraph(graph);
		
		System.out.println("the graph is two colorable: " + isTwoColorable(graph));
		
	}
	
	// Determines whether or not the given graph is two colorable,
	// that one out of two colors can be assigned to each vertex (red or blue)
	// such that no one has the same color as their neighbors.
	// Since the algorithm uses a breadth first search to assign colors
	// runs in O (E + V) time where E is the total number of edges and v
	// the number of vertices
	public static boolean isTwoColorable(Map<Integer, List<Integer>> graph) {
		// keeps track of the vertexes we have already colored
		// as well as visited
		Map<Integer, String> coloring = new HashMap<Integer, String>();
		
		// add the root/ start vert to our work
		Queue<ColorPair> work = new LinkedList<ColorPair>();
		
		// we'll make the first node blue
		work.add(new ColorPair(1, "red"));
		
		// keep going as long as the graph has more nodes to traverse
		// breadth fist coloring the graph
		while (!work.isEmpty()) {
			ColorPair curPair = work.remove();
			
			if (!coloring.containsKey(curPair.vert)) {
				// we haven't processed this node yet
				// make sure to give it a color opposite
				// it's parent
				String parentColor = curPair.color;
				int vert = curPair.vert;
				String curColor = ""; 
				
				// make the current vert differ from it's parent
				if (parentColor.equals("blue")) {
					curColor = "red";
				} else {
					curColor = "blue";
				}
				
				// add the vert and it's color to the colored mapping
				coloring.put(vert, curColor);
				
				// for each of the current verts neighbors
				// add them in the queue to be visited and denote
				// the current nodes color
				List<Integer> neighbors = graph.get(vert);
				for (int i = 0; i < neighbors.size(); i++) {
					int neighbor = neighbors.get(i);
					work.add(new ColorPair(neighbor, curColor));
				}
			}
		}
		
		// at this point we need to go through the graph and make sure we found
		// a valid two coloring
		for (int vert: coloring.keySet()) {
			String vertColor = coloring.get(vert);
			List<Integer> neighbors = graph.get(vert);
			
			// traverse the neighbors and return false if at any point
			// we have matching colors
			for (int i = 0; i < neighbors.size(); i++) {
				int neighbor = neighbors.get(i);
				String neighborColor = coloring.get(neighbor);
				
				// if the colors match the neighbor the graph is not two colorable
				if (neighborColor.equals(vertColor)) {
					return false;
				}
			}
		}
		
		// no verts matched their neighbors colors this graph is two colorable!
		// lets print out the coloring
		for (int vert: coloring.keySet()) {
			System.out.println("v" + vert + " color: " + coloring.get(vert));
		}
		System.out.println();
		
		return true;
	}
	
	// creates the adjacency list mapping to represent
	// the edges in the given int[][] for each vert
	public static Map<Integer, List<Integer>> fillGraph(int[][] edges) {
		// create the map from vert to list
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
		
		// fill in the adjacency list
		for (int i = 0; i < edges.length; i++) {
			List<Integer> curEdges = new ArrayList<Integer>();
			int[] vertEdges = edges[i];
			
			// transfer over the edge values
			for (int j = 0; j < vertEdges.length; j++) {
				curEdges.add(vertEdges[j]);
			}
			
			// add the adjacency list mapping for the current vert
			adjList.put(i + 1, curEdges);
		}
		
		return adjList;
	}
	
	// prints the contents of the given graph
	public static void printGraph(Map<Integer, List<Integer>> graph) {
		for (int i: graph.keySet()) {
			System.out.print("v" + i);
			
			List<Integer> neighbors = graph.get(i);
			System.out.print(" neighbors: ");
			System.out.print(neighbors.get(0));
			
			// print out the current vertexes list
			for (int j = 1; j < neighbors.size(); j++) {
				System.out.print(", " + neighbors.get(j));
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
}
