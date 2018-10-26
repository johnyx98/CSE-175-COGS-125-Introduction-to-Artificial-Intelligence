import java.io.*;
import java.util.*;

public class DFSearch {

	public int limit;
	public String initialLoc;
	public String destinationLoc;
	public Map graph;
	public int expansionCount;
	
	public DFSearch (Map graph, String initialLoc, String destinationLoc, int limit) {
		this.initialLoc = initialLoc;
		this.graph=graph;
		this.limit=limit;
		this.destinationLoc=destinationLoc;
	
	}
	
	public int expansionCount() {
		return expansionCount;
		
	}
	
	public Node search(boolean repeatedState) {
		expansionCount = 0;
	
		//create new frontier ----> stack
		
		
		if(repeatedState) { //if repeated state checking is being used
			Node initialNode = new Node(graph.findLocation(initialLoc));
			Frontier stack = new Frontier();
			stack.addToTop(initialNode);
			HashSet<String> visited_nodes = new HashSet<String>();
		
		while(true) {
			if(stack.isEmpty()) {
				return null;
			} else {
				initialNode = stack.removeTop();
				if(initialNode.isDestination(destinationLoc)) {
					return initialNode;
				}
				visited_nodes.add(initialNode.loc.name);
				initialNode.expand();
				expansionCount = expansionCount+1;
				
				
			}
			for(Node i: initialNode.children) {
				if(!visited_nodes.contains(i.loc.name) && !stack.contains(i.loc.name)) {
					stack.addToTop(i);
				}
			}
			if (stack.isEmpty()) {
				return null;
			}
		}
			
		}
		
		 else { //if repeated state checking is not being used
			Node initialNode = new Node(graph.findLocation(initialLoc));
			Frontier stack = new Frontier();
			stack.addToTop(initialNode);
			
			while(true) {
				if(stack.isEmpty() || initialNode.depth == limit-1) {
					return null;
				}else {
					initialNode= stack.removeTop();
				if(initialNode.isDestination(destinationLoc)) {
					return initialNode;
				} 
					initialNode.expand();
					expansionCount = expansionCount +1;
					stack.addToTop(initialNode.children);
				
				}
				
			}
			
		}
		
		
	}
	
	
	
	
	}


		
