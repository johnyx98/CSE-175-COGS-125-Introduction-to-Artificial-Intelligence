//
// Pzero
//
// This class provides a "main" method that acts as a driver program for
// evaluating breadth-first search and depth-first search algorithms, as
// applied to shortest-path searches on a map. A Map object is used to 
// read in and record a map from two files:  one containing a list of
// locations and the other containing a list of road segments. The user
// is then prompted for a starting location on this map and a destination
// location. The two search algorithms in question are then tested on the
// specified search problem, and the effect of repeated state checking is
// also examined. A depth limit is provided to the search algorithms, and
// the algorithms are expected to terminate and report failure if that depth
// limit is ever reached during search. Summary results are sent to the 
// standard output stream.
//
// David Noelle -- Tue Sep 11 16:11:07 PDT 2018
//


import java.io.*;


public class Pzero {

	public static void main(String[] args) {
		try {
			Map graph = new Map();
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			String initialLoc;
			String destinationLoc;
			Node solution;
			int limit = 1000; // depth limit, to avoid infinite loops

			System.out.println("UNINFORMED SEARCH ALGORITHM COMPARISON");
			// Read map ...
			if (!(graph.readMap())) {
				System.err.println("Error: Unable to read map.");
				return;
			}
			// Get initial and final locations ...
			System.out.println("Enter the name of the initial location:");
			initialLoc = in.readLine();
			System.out.println("Enter the name of the destination location:");
			destinationLoc = in.readLine();

			// Testing BFS without repeated state checking ...
			System.out.println("TESTING BFS WITHOUT REPEATED STATE CHECKING");
			BFSearch bfs = new BFSearch(graph, initialLoc, destinationLoc, limit);
			solution = bfs.search(false);
			System.out.println("Solution:");
			if (solution == null) {
				System.out.println("None found.");
			} else {
				solution.reportSolution(System.out);
				System.out.printf("Path Cost = %f.\n", solution.partialPathCost);
			}
			System.out.printf("Number of Node Expansions = %d.\n", bfs.expansionCount);

			// Testing BFS with repeated state checking ...
			System.out.println("TESTING BFS WITH REPEATED STATE CHECKING");
			solution = bfs.search(true);
			System.out.println("Solution:");
			if (solution == null) {
				System.out.println("None found.");
			} else {
				solution.reportSolution(System.out);
				System.out.printf("Path Cost = %f.\n", solution.partialPathCost);
			}
			System.out.printf("Number of Node Expansions = %d.\n", bfs.expansionCount);

			// Testing DFS without repeated state checking ...
			System.out.println("TESTING DFS WITHOUT REPEATED STATE CHECKING");
			DFSearch dfs = new DFSearch(graph, initialLoc, destinationLoc, limit);
			solution = dfs.search(false);
			System.out.println("Solution:");
			if (solution == null) {
				System.out.println("None found.");
			} else {
				solution.reportSolution(System.out);
				System.out.printf("Path Cost = %f.\n", solution.partialPathCost);
			}
			System.out.printf("Number of Node Expansions = %d.\n", dfs.expansionCount);

			// Testing DFS with repeated state checking ...
			System.out.println("TESTING DFS WITH REPEATED STATE CHECKING");
			solution = dfs.search(true);
			System.out.println("Solution:");
			if (solution == null) {
				System.out.println("None found.");
			} else {
				solution.reportSolution(System.out);
				System.out.printf("Path Cost = %f.\n", solution.partialPathCost);
			}
			System.out.printf("Number of Node Expansions = %d.\n", dfs.expansionCount);

			// Done ...
			System.out.println("ALGORITHM COMPARISON COMPLETE");
		} catch (IOException e) {
			// Something went wrong ...
		}
	}
    
}
