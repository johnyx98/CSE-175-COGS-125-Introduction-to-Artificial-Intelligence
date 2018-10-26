import java.io.*;
import java.util.*;

public class AStarSearch {

	public int limit;
	public int expansionCount;
	public String initialLoc;
	public String destinationLoc;
	public StreetMap graph;
	
	public AStarSearch(StreetMap graph, String initialLoc, String destinationLoc, int limit) {
		this.initialLoc = initialLoc;
		this.graph = graph;
		this.destinationLoc= destinationLoc;
		this.limit = limit;
		
	}
	
	
	
	public int expansionCount() {
		return expansionCount;
	}
	
	
	public Node search(boolean repeatedState) {
		Node initialNode = new Node(graph.findLocation(initialLoc));
		SortedFrontier prioq = new SortedFrontier(SortBy.f);
		prioq.addSorted(initialNode);
		HashSet<String> visited_nodes = new HashSet<String>();
		GoodHeuristic val = new GoodHeuristic(graph.findLocation(destinationLoc));
		expansionCount = 0;
		
		if(repeatedState) {
			while(true) {
				if(prioq.isEmpty()) {
					return null;
				}
				initialNode = prioq.removeTop();
				if(initialNode.isDestination(destinationLoc)) {
					return initialNode;
				}
				if(!visited_nodes.contains(initialNode.loc.name)){
					visited_nodes.add(initialNode.loc.name);
					initialNode.heuristicValue = val.heuristicValue(initialNode);
					initialNode.expand(val);
					//System.out.println(initialNode.loc.name);
					expansionCount = expansionCount +1;
					//System.out.println(expansionCount);
					}
				for(Node i: initialNode.children) {
					if(!visited_nodes.contains(i.loc.name)&& !prioq.contains(i.loc.name)) {
						prioq.addSorted(i);
					}else if(prioq.contains(i) && i.partialPathCost > initialNode.partialPathCost ) {
						prioq.remove(initialNode);
						prioq.addSorted(i);
					}
					
				}
				if(prioq.isEmpty()) {
					return null;
				}
				
			}
		
		}else {
			while(!prioq.isEmpty() && initialNode.depth <limit) {
				initialNode = prioq.removeTop();
				
				if(initialNode.isDestination(destinationLoc)) {
					return initialNode;
				}
				initialNode.heuristicValue = val.heuristicValue(initialNode);
				initialNode.expand(val);	
				//System.out.print(initialNode.loc.name);
				expansionCount = expansionCount +1;
				//System.out.println(expansionCount);
				prioq.addSorted(initialNode.children);
				
				

		}
			return null;
		}
		}
		
			
			
		
	}
	
	
