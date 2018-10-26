import java.io.*;
import java.util.*;

public class BFSearch {

	//declaration
	public int limit;
	public String initialLoc;
	public String destinationLoc;
	public Map graph;
	public int expansionCount;
	
	public BFSearch (Map graph, String initialLoc, String destinationLoc, int limit) {
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
		Node initialNode = new Node(graph.findLocation(initialLoc));
		//create new frontier ----> queue
		Frontier queue = new Frontier();
		queue.addToBottom(initialNode);
		//add the initial node to the queue
		if (repeatedState==true) {
		HashSet<String> visited_nodes = new HashSet<String>();
		while(true) {
			if(queue.isEmpty()) {
				return null;	
			} else {
				initialNode = queue.removeTop();
				if(initialNode.isDestination(destinationLoc)) { //if the destination is found then return the node.
					return initialNode;
				}
				visited_nodes.add(initialNode.loc.name); //add visited nodes to list.
				initialNode.expand();   //expand to add children
				expansionCount = expansionCount + 1;  //expansion counter
			}
			
			for(Node i: initialNode.children) { //set i to each children
				
				if(!visited_nodes.contains(i.loc.name) && !queue.contains(i.loc.name)) { //if the visited nodes does not have i and the queue does not have i, add it to the queue.
					queue.addToBottom(i);
					
					
					
				}
				
			}
			if(queue.isEmpty()) {
				return null;
			}
			
		} 
		
		
		} else { //if the repeated state is not being checked
			while(!queue.isEmpty() && initialNode.depth<=limit) { 
				initialNode = queue.removeTop();
				
				if(initialNode.isDestination(destinationLoc)) {
					return initialNode;
				}
				initialNode.expand();
				expansionCount = expansionCount +1;
				queue.addToBottom(initialNode.children);
				
			}
			return null;
			
		}
			
		
		
		//while (true)
			//if this frontier is empty-->return null
			//remove a node --->node
			// if reached goal --> return node
		
		
		//add children if the node does not contain
		
	}
	
	
	
}
