import java.io.*;
import java.util.*;

public class UniformCostSearch {

	public int limit;
	public int expansionCount;
	public String initialLoc;
	public String destinationLoc;
	public StreetMap graph;
	
	public UniformCostSearch(StreetMap graph, String initialLoc, String destinationLoc, int limit) {
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
		SortedFrontier prioq = new SortedFrontier(SortBy.g);
		prioq.addSorted(initialNode);
		HashSet<String> visited_nodes = new HashSet<String>();
		expansionCount = 0;
		Node temp = new Node();
		temp = initialNode;
		
		
		if(repeatedState) {
			while(true) {
				//int index = 0;
				if(prioq.isEmpty()) {
					return null;
				} else {
				initialNode = prioq.removeTop();
				if(initialNode.isDestination(destinationLoc)) {
					return initialNode;
				}
				
				if(!visited_nodes.contains(initialNode.loc.name)){
				visited_nodes.add(initialNode.loc.name);
				initialNode.expand();
				expansionCount = expansionCount +1;
				}
				
				}
				for(Node i: initialNode.children) {
					 
					//set temp node = i
					//use a second enhanced for loop?
					
					
					if(!visited_nodes.contains(i.loc.name) && !prioq.contains(i.loc.name)) {
						prioq.addSorted(i);
						
					}
					else if(prioq.contains(i) && i.partialPathCost > initialNode.partialPathCost) {
						prioq.remove(initialNode);
						prioq.addSorted(i);
						
					//create 2 nodes?
				} //else {
					//index++;
				//}
					 
				
			}
		

		
			}
			} else {
				while(!prioq.isEmpty() && initialNode.depth <limit) {
					initialNode = prioq.removeTop();
					
					if(initialNode.isDestination(destinationLoc)) {
						return initialNode;
					}
					initialNode.expand();
					expansionCount = expansionCount +1;
					prioq.addSorted(initialNode.children);
					
				

			}
				return null;
			}
	}
	
	
}
	
